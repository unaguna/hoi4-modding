package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileWord

data class Hoi4Word(private val value: String) : Value<Hoi4FileWord> {
    override fun toHoi4FileObject() = Hoi4FileWord(value)
}
