package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileBool

enum class Hoi4Bool(private val value: Boolean) : Value<Hoi4FileBool> {
    YES(true),
    NO(false),
    ;
    override fun toHoi4FileObject() = Hoi4FileBool(value)

    companion object {
        fun of(value: Boolean) : Hoi4Bool {
            return when(value) {
                true -> YES
                false -> NO
            }
        }
    }
}
