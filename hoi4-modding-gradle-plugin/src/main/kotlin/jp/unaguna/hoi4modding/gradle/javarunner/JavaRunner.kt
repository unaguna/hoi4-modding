package jp.unaguna.hoi4modding.gradle.javarunner

import java.io.File
import java.net.URLClassLoader
import java.nio.file.NoSuchFileException

class JavaRunner {

    companion object {
        private val os by lazy { System.getProperty("os.name") }
        private val classPathSep by lazy { if(isWindows()) ";" else ":" }

        fun isWindows(): Boolean {
            return os.toLowerCase().startsWith("windows")
        }

        fun currentJavaHome(): File {
            return File(System.getProperty("java.home"))
        }

        fun currentJava(): File {
            val bin = currentJavaHome().resolve("bin")
            if(!bin.exists()) {
                throw NoSuchFileException(file = bin)
            }

            val javawExe = bin.resolve("javaw.exe")
            val javaExe = bin.resolve("java.exe")
            val java = bin.resolve("java")

            return if(javawExe.exists()) {
                javawExe
            } else if(javaExe.exists()) {
                javaExe
            } else if(java.exists()) {
                java
            } else {
                throw NoSuchFileException("java")
            }
        }

        fun currentClassPaths(): List<File> {
            val classLoader = Thread.currentThread().contextClassLoader as URLClassLoader
            return classLoader.urLs.map { File(it.toURI()) }.toList()
        }

        fun classPathsToCmdArg(classPaths: Collection<File>): String {
            return classPaths.joinToString(separator = classPathSep)
        }
    }
}