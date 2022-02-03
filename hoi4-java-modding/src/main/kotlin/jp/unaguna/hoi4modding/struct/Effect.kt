package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Scope
import jp.unaguna.hoi4modding.struct.common.Value

abstract class Effect<S: Scope>: MutableStruct() {
    val setGlobalFlag = fieldFactory.adjustableFlag("set_global_flag")

    private val setVariable = fieldFactory.adjustableStruct("set_variable")

    private fun <V: Value<*>, U, L: Label<V>> setAnyScopeVariable(
        variable: IVariable<*, V, U, L>,
        value: V,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setVariable eq SetVariableBuilder(variable, value).apply(action).build()
    }

    private fun <V: Value<*>, U, L: Label<V>> setAnyScopeVariable(
        variable: IVariable<*, V, U, L>,
        value: U,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setVariable eq SetVariableBuilder(variable, value).apply(action).build()
    }

    private fun <V: Value<*>, U, L: Label<V>> setAnyScopeVariable(
        variable: IVariable<*, V, U, L>,
        value: L,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setVariable eq SetVariableBuilder(variable, value).apply(action).build()
    }

    open fun <V: Value<*>, U, L: Label<V>> setVariable(
        variable: CountryVariable<V, U, L>,
        value: V,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setAnyScopeVariable(variable, value, action)
    }

    open fun <V: Value<*>, U, L: Label<V>> setVariable(
        variable: CountryVariable<V, U, L>,
        value: U,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setAnyScopeVariable(variable, value, action)
    }

    open fun <V: Value<*>, U, L: Label<V>> setVariable(
        variable: CountryVariable<V, U, L>,
        value: L,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setAnyScopeVariable(variable, value, action)
    }

    open fun <V: Value<*>, U, L: Label<V>> setVariable(
        variable: StateVariable<V, U, L>,
        value: V,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setAnyScopeVariable(variable, value, action)
    }

    open fun <V: Value<*>, U, L: Label<V>> setVariable(
        variable: StateVariable<V, U, L>,
        value: U,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setAnyScopeVariable(variable, value, action)
    }

    open fun <V: Value<*>, U, L: Label<V>> setVariable(
        variable: StateVariable<V, U, L>,
        value: L,
        action: SetVariableBuilder<V, U, L>.()->Unit = {}
    ) {
        setAnyScopeVariable(variable, value, action)
    }

    fun state(state: IState): AdjustableField<EffectState, EffectState.() -> Unit, Nothing> {
        return fieldFactory.adjustableEffectState(state.stateNumber.toString())
    }
    fun state(state: Int): AdjustableField<EffectState, EffectState.() -> Unit, Nothing> {
        return fieldFactory.adjustableEffectState(state.toString())
    }
}
