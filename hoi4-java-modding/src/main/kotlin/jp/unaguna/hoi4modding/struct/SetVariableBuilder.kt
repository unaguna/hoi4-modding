package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Value
import jp.unaguna.hoi4modding.struct.variable.IVariable

class SetVariableBuilder<V: Value<*>, U, L: Label<V>>(variable: IVariable<*, V, U, L>, value: V) : MutableStruct() {
    constructor(variable: IVariable<*, V, U, L>, value: U): this(variable, variable.convValue(value))
    constructor(variable: IVariable<*, V, U, L>, value: L): this(variable, value.label)

    private val variable = fieldFactory.adjustableVariable("var")
    private val value = fieldFactory.adjustableTightField<V>("value")
    val tooltip = fieldFactory.adjustableTooltip("tooltip")

    init {
        this.variable eq variable
        this.value eq value
    }

    fun build(): Hoi4Struct {
        return this.toImmutable()
    }
}
