package jp.unaguna.hoi4modding.gradle.task

import jp.unaguna.hoi4modding.gradle.javarunner.JavaRunner
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import java.io.File


open class CompileJavaModTask: Exec() {
    private val log: Logger = Logging.getLogger(CompileJavaModTask::class.java)

    private val inputDir = project.file("build/classes")
    private val distDir = distDir(project)

    private val classPathWhenSync: Collection<File>

    init {
        // TODO: class lorder で使っている sourceSet を @CompileClasspath として登録する
        inputs.dir(inputDir)
        outputs.dir(distDir)

        classPathWhenSync = JavaRunner.currentClassPaths()
        executable("java")
    }

    @TaskAction
    override fun exec() {
        initCommand()
        super.exec()
    }

    private fun initCommand() {
        val ownJar = project.tasks.getByName("jar").let { jar ->
            jar as Jar
            jar.archiveFile.get().asFile
        }
        val classPaths = mutableListOf<File>().apply {
            addAll(project.extensions.getByType(SourceSetContainer::class.java).getByName("main").runtimeClasspath)
            addAll(classPathWhenSync)
            addAll(JavaRunner.currentClassPaths())
            add(ownJar)
        }

        commandLine(mutableListOf<String>().apply {
            val javaCmd = JavaRunner.currentJava()
            val mainClassName = "jp.unaguna.hoi4modding.compile.ModCompiler"

            if (JavaRunner.isWindows()) {
                add("cmd")
                add("/c")
            }

            add(javaCmd.toString())

            add(mainClassName)
            add(ownJar.toString())
            add(distDir.toString())
        })

        environment("CLASSPATH", JavaRunner.classPathsToCmdArg(classPaths))
    }

    companion object {
        const val NAME = "compileJavaMod"

        internal fun distDir(project: Project): File = project.buildDir.resolve(NAME)
    }
}