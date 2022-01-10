package jp.unaguna.hoi4modding.gradle.task

import io.github.classgraph.ClassGraph
import jp.unaguna.hoi4modding.struct.ModFile
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar
import java.io.File
import java.net.URLClassLoader


open class CompileJavaModTask: DefaultTask() {
    private val log: Logger = Logging.getLogger(CompileJavaModTask::class.java)

    private val inputDir = project.file("build/classes")
    private val distDir = distDir(project)

    init {
        // TODO: class lorder で使っている sourceSet を @CompileClasspath として登録する
        inputs.dir(inputDir)
        outputs.dir(distDir)
    }

    @TaskAction
    fun exec() {
        // JAR ファイルを取得してクラスローダーを作成
        // TODO: JAR タスクがない場合にエラーになってしまう。Java-plugin を前提にしてよさそう。
        val ownJar = project.tasks.getByName("jar").let { jar ->
            jar as Jar
            jar.archiveFile.get().asFile
        }
        val ownDependenciesLoader = URLClassLoader(arrayOf(ownJar.toURI().toURL(), *project.extensions.getByType(JavaPluginExtension::class.java).sourceSets.getByName("main").runtimeClasspath.map{it.toURI().toURL()}.toTypedArray()), ClassLoader.getPlatformClassLoader())
        val ownJarLoader = URLClassLoader(arrayOf(ownJar.toURI().toURL()))

        ClassGraph().addClassLoader(ownDependenciesLoader).enableClassInfo().enableAnnotationInfo().scan().use { scanResult ->
            ClassGraph().overrideClassLoaders(ownJarLoader).acceptJars(ownJar.name).enableClassInfo().scan().use { ownScanResult ->
                val modFileClassList = scanResult.getClassesWithAnnotation(ModFile::class.java)

                for (classInfo in modFileClassList) {
                    log.debug("A class annotated with ModFile is found: ${classInfo.name}")

                    // 当該プロジェクト固有のクラス以外を無視する
                    if(ownScanResult.getClassInfo(classInfo.name) == null) {
                        log.debug("Ignore external class ${classInfo.name}")
                        continue
                    } else {
                        log.debug("Compile to mod file from ${classInfo.name}")
                    }

                    // TODO: classInfo が ToFile を実装していないクラスであれば例外

                    // インスタンス作成
                    val instance = classInfo.loadClass().getDeclaredConstructor().newInstance()
                    log.debug("The instance of ${classInfo.name} has been constructed: $instance")

                    // TODO: modファイルへ書き出す
                }
            }
        }
    }

    companion object {
        const val NAME = "compileJavaMod"

        internal fun distDir(project: Project): File = project.buildDir.resolve(NAME)
    }
}