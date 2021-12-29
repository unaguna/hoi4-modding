package jp.unaguna.hoi4modding.gradle

import jp.unaguna.hoi4modding.gradle.task.CompileModTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin

class Hoi4ModdingPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(BasePlugin::class.java)

        project.tasks.create(CompileModTask.NAME, CompileModTask::class.java)
    }
}
