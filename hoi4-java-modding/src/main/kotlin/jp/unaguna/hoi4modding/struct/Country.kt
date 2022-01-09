package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4Object
import jp.unaguna.hoi4modding.hoi4file.numberList
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class Country : ToFile {
    abstract val tag: String
    abstract val color: Color

    override fun toString(): String {
        return "${javaClass.name}($tag)"
    }

    override fun fileList(): List<Pair<String, Hoi4Object>> {
        val country = "common/countries/$tag.txt" to relationList {
            // TODO: Make values not constant
            "graphical_culture" eq "commonwealth_gfx"
            "graphical_culture_2d" eq "commonwealth_2d"
            "color" eq numberList {
                append(color.red)
                append(color.green)
                append(color.blue)
            }
        }
        val countryTags = "common/country_tags/$tag.txt" to relationList {
            tag eq country.first
        }

        return listOf(country, countryTags)
    }
}
