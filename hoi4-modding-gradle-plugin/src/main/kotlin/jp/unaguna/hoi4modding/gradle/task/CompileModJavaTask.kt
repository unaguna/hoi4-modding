package jp.unaguna.hoi4modding.gradle.task

import jp.unaguna.hoi4modding.gradle.javarunner.JavaRunner
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import java.io.File


open class CompileModJavaTask: Exec() {
    @get:InputDirectory
    @get:SkipWhenEmpty
    val inputDir: File = project.file("build/classes")

    @get:OutputDirectory
    val distDir = distDir(project)

    private val classPathWhenSync: Collection<File>

    init {
        classPathWhenSync = JavaRunner.currentClassPaths()
        this.executable("java")
    }

    @TaskAction
    override fun exec() {
        initCommand()
        logging.captureStandardOutput(LogLevel.DEBUG)
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
        const val NAME = "compileModJava"

        internal fun distDir(project: Project): File = project.buildDir.resolve(NAME)
    }
}