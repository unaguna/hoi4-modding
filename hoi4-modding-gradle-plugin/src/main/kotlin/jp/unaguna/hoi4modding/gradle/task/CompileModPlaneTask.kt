package jp.unaguna.hoi4modding.gradle.task

import jp.unaguna.hoi4modding.gradle.Hoi4ModExtension
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CompileModPlaneTask: DefaultTask() {
    private val log: Logger = Logging.getLogger(CompileModPlaneTask::class.java)

    private val inputDir = project.file("src/main/plane")
    private val distDir = distDir(project)

    init {
        // TODO: inputDir が存在しない場合にエラーにならないように。(たとえば compileJava は src/main/java が存在しなくても、NO-SOURCEと判定されてエラーにはならない)
        inputs.dir(inputDir)
        outputs.dir(distDir)
    }

    @TaskAction
    fun exec() {
        val hoi4ModExtension = project.extensions.getByType(Hoi4ModExtension::class.java)
        val deployTarget = hoi4ModExtension.target
            ?: throw InvalidUserDataException("Task '$name' needs the property '${Hoi4ModExtension.NAME}.target'")
        val inputFiles = project.fileTree("src/main/plane")

        log.debug("Dest: $distDir")
        if(distDir.exists()) {
            // TODO: 引数がfalseなら例外を投げる
            distDir.deleteRecursively()
        }
        distDir.mkdir()

        for(srcFileAbs in inputFiles){
            log.debug("Src: $srcFileAbs")

            val fileRel = srcFileAbs.relativeTo(inputDir)
            val destFileAbs = distDir.resolve(deployTarget).resolve(fileRel)

            srcFileAbs.copyTo(destFileAbs)
        }
    }

    companion object {
        const val NAME = "compileModPlane"

        internal fun distDir(project: Project): File = project.buildDir.resolve(NAME)
    }
}