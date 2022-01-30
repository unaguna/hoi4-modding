package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4List
import jp.unaguna.hoi4modding.hoi4file.Hoi4Object
import jp.unaguna.hoi4modding.hoi4file.Hoi4Relation
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class CountryHistory: EffectCountry() {
    abstract val country: ICountry
    abstract val capital: IState?
    abstract val oob: IOob?

    override fun toHoi4List(): Hoi4List<Hoi4Relation> {
        return relationList {
            capital?.let { capital ->
                "capital" eq capital
            }
            oob?.let { oob ->
                "OOB" eq oob
            }
            appendAll(super.toHoi4List())
        }
    }

    fun fileList(): List<Pair<String, Hoi4Object>> {
        return listOf("history/countries/${country.tag} - ${country.countryName}.txt" to this.toHoi4List())
    }
}
