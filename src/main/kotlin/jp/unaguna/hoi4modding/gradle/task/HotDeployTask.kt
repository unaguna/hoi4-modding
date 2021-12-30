package jp.unaguna.hoi4modding.gradle.task

import jp.unaguna.hoi4modding.gradle.Hoi4ModExtension
import org.gradle.api.DefaultTask
import org.gradle.api.InvalidUserDataException
import org.gradle.api.tasks.TaskAction

open class HotDeployTask: DefaultTask() {
    @TaskAction
    fun exec() {
        val hoi4ModExtension = project.extensions.getByType(Hoi4ModExtension::class.java)
        val deployDest = hoi4ModExtension.moddir.orNull
            ?: throw InvalidUserDataException("Task '$name' needs the property '${Hoi4ModExtension.NAME}.moddir'")
        val deployTarget = hoi4ModExtension.target.orNull
            ?: throw InvalidUserDataException("Task '$name' needs the property '${Hoi4ModExtension.NAME}.target'")

        val deploySrcDir = CompileModTask.distDir(project)
        val deployTargetDir = deployDest.resolve(deployTarget)

        deploySrcDir.copyRecursively(deployTargetDir, overwrite = true)
    }

    companion object {
        const val NAME = "hotDeploy"
    }
}
