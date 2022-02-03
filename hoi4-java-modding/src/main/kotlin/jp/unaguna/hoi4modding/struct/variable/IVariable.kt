package jp.unaguna.hoi4modding.struct.variable

import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.Scope
import jp.unaguna.hoi4modding.struct.common.Value

interface IVariable<S: Scope, V: Value<*>, U, L: Label<V>> : Label<Hoi4Word> {
    val varName: String
    override val label: Hoi4Word
        get() = Hoi4Word(varName)

    fun convValue(value: U): V
}
