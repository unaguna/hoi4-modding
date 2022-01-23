package jp.unaguna.hoi4modding.gradle

import jp.unaguna.hoi4modding.gradle.task.CompileModJavaTask
import jp.unaguna.hoi4modding.gradle.task.CompileModPlaneTask
import jp.unaguna.hoi4modding.gradle.task.HotDeployTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.BasePlugin
import org.gradle.api.plugins.JavaPlugin

class Hoi4ModdingPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply(BasePlugin::class.java)
        project.plugins.apply(JavaPlugin::class.java)

        project.extensions.create(Hoi4ModExtension.NAME, Hoi4ModExtension::class.java)

        project.tasks.register(CompileModPlaneTask.NAME, CompileModPlaneTask::class.java)
        val hodDeployTaskProvider = project.tasks.register(HotDeployTask.NAME, HotDeployTask::class.java) {
            it.dependsOn(CompileModPlaneTask.NAME)
        }
        project.tasks.getByName("assemble").dependsOn(CompileModPlaneTask.NAME)


        project.tasks.register(CompileModJavaTask.NAME, CompileModJavaTask::class.java) {
            it.dependsOn("jar")

            // TODO: 依存指定が機能していない。
            hodDeployTaskProvider.get().dependsOn(it)
        }
    }
}
