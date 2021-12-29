package jp.unaguna.hoi4modding.gradle

import org.gradle.api.provider.Property
import java.io.File

abstract class Hoi4ModExtension {
    /** The root directory of individual mods in the HOI4 mod directory */
    abstract val moddir: Property<File>

    /**
     * The target directory of deployment
     *
     * The location of the files in the module, specified relative to moddir.
     * Example: `"common/national_focus"`.
     */
    abstract val target: Property<String>

    companion object {
        const val NAME = "hoi4mod"
    }
}