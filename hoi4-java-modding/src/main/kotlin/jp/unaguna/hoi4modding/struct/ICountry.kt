package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label

interface ICountry: Label<Hoi4Word> {
    val tag: String
    val countryName: String

    override val label: Hoi4Word
        get() = Hoi4Word(tag)
}
