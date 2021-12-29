package jp.unaguna.hoi4modding.gradle

import jp.unaguna.hoi4modding.gradle.task.CompileModTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class Hoi4ModdingPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.tasks.create(CompileModTask.NAME, CompileModTask::class.java)
    }
}
