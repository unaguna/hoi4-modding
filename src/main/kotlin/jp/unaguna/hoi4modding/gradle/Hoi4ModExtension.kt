package jp.unaguna.hoi4modding.gradle

import java.io.File

open class Hoi4ModExtension {
    /** The root directory of individual mods in the HOI4 mod directory */
    var moddir: File? = null

    /**
     * The target directory of deployment
     *
     * The location of the files in the module, specified relative to moddir.
     * Example: `"common/national_focus"`.
     */
    var target: String? = null

    companion object {
        const val NAME = "hoi4mod"
    }
}