package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileNumber

class Hoi4Number(private val value: Number) : Value<Hoi4FileNumber> {
    override fun toHoi4FileObject() = Hoi4FileNumber(value)
}
