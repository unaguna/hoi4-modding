package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileString

class Hoi4Date(private val year: Int, private val month: Int, private val day: Int) : Value<Hoi4FileString> {
    override fun toHoi4FileObject() = Hoi4FileString("$year.$month.$day")
}
