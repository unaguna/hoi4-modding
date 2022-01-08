package jp.unaguna.hoi4modding.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CompileModTask: DefaultTask() {
    private val log: Logger = Logging.getLogger(CompileModTask::class.java)

    private val inputDir = project.file("src")
    private val distDir = distDir(project)

    init {
        inputs.dir(inputDir)
        outputs.dir(distDir)
    }

    @TaskAction
    fun exec() {
        val inputFiles = project.fileTree("src")

        log.debug("Dest: $distDir")
        if(distDir.exists()) {
            // TODO: 引数がfalseなら例外を投げる
            distDir.deleteRecursively()
        }
        distDir.mkdir()

        for(srcFileAbs in inputFiles){
            log.debug("Src: $srcFileAbs")

            val fileRel = srcFileAbs.relativeTo(inputDir)
            val destFileAbs = distDir.resolve(fileRel)

            srcFileAbs.copyTo(destFileAbs)
        }
    }

    companion object {
        const val NAME = "compileMod"

        internal fun distDir(project: Project): File = project.buildDir.resolve(NAME)
    }
}