package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileString

data class Hoi4String(private val value: String) : Value<Hoi4FileString> {
    override fun toHoi4FileObject() = Hoi4FileString(value)
}
