package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileObject
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class CountryHistory: EffectCountryHistory() {
    abstract val country: ICountry

    override fun toHoi4FileObject(): Hoi4FileList<Hoi4FileRelation> {
        return relationList {
            appendAll(super.toHoi4FileObject())
        }
    }

    fun fileList(): List<Pair<String, Hoi4FileObject>> {
        return listOf("history/countries/${country.tag} - ${country.countryName}.txt" to this.toHoi4FileObject())
    }
}
