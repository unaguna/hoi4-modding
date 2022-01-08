package jp.unaguna.hoi4modding.gradle

import jp.unaguna.hoi4modding.gradle.task.CompileModTask
import jp.unaguna.hoi4modding.gradle.task.HotDeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

class Hoi4ModdingPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(BasePlugin::class.java)

        project.extensions.create(Hoi4ModExtension.NAME, Hoi4ModExtension::class.java)

        project.tasks.register(CompileModTask.NAME, CompileModTask::class.java)
        project.tasks.register(HotDeployTask.NAME, HotDeployTask::class.java) {
            it.dependsOn(CompileModTask.NAME)
        }
        project.tasks.getByName("assemble").dependsOn(CompileModTask.NAME)
    }
}