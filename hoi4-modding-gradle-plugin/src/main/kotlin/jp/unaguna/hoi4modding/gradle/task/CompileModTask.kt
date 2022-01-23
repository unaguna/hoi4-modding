package jp.unaguna.hoi4modding.gradle.task

import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import java.io.File

open class CompileModTask: Copy() {
    private val distDir = distDir(project)

    init {
        outputs.dir(distDir)

        this.from(CompileModPlaneTask.distDir(project))
        this.from(CompileModJavaTask.distDir(project))
        this.into(distDir)
    }

    companion object {
        const val NAME = "compileMod"

        internal fun distDir(project: Project): File = project.buildDir.resolve(NAME)
    }
}
