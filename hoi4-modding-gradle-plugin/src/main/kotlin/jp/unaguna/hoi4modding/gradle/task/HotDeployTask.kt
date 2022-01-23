package jp.unaguna.hoi4modding.gradle.task

import jp.unaguna.hoi4modding.gradle.Hoi4ModExtension
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.TaskAction
import java.io.File

open class HotDeployTask: DefaultTask() {
    @get:InputDirectory
    @get:SkipWhenEmpty
    val deploySrcDir: File = CompileModTask.distDir(project)

    @TaskAction
    fun exec() {
        val hoi4ModExtension = project.extensions.getByType(Hoi4ModExtension::class.java)
        val deployDest = hoi4ModExtension.moddir
            ?: throw InvalidUserDataException("Task '$name' needs the property '${Hoi4ModExtension.NAME}.moddir'")

        deploySrcDir.copyRecursively(deployDest, overwrite = true)
    }

    companion object {
        const val NAME = "hotDeploy"
    }
}
