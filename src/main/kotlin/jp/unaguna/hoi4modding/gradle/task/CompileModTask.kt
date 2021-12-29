package jp.unaguna.hoi4modding.gradle.task

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.TaskAction
import java.io.File

open class CompileModTask: DefaultTask() {
    private val log: Logger = Logging.getLogger(CompileModTask::class.java)

    @TaskAction
    fun exec() {
        val inputDir = project.file("src")
        val inputFiles = project.fileTree("src")
        val distDir = distDir(project)

        log.debug("Dest: $distDir")

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