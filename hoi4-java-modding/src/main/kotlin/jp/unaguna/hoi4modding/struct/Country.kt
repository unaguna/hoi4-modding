package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4Object
import jp.unaguna.hoi4modding.hoi4file.numberList
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class Country : ToFile, ICountry {
    abstract val color: Color
    open val history: CountryHistory? = null

    override fun toString(): String {
        return "${javaClass.name}($tag)"
    }

    override fun fileList(): List<Pair<String, Hoi4Object>> {
        val fileList: MutableList<Pair<String, Hoi4Object>> = mutableListOf()

        val country = "common/countries/$tag.txt" to relationList {
            // TODO: Make values not constant
            "graphical_culture" eq "commonwealth_gfx"
            "graphical_culture_2d" eq "commonwealth_2d"
            "color" eq color
        }
        fileList.add(country)
        fileList.add("common/country_tags/$tag.txt" to relationList {
            tag eq country.first
        })
        history?.let { history ->
            fileList.addAll(history.fileList())
        }

        return fileList
    }
}
