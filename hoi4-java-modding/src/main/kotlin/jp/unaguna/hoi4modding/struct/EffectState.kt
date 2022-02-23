package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.Scope
import jp.unaguna.hoi4modding.struct.common.Value
import jp.unaguna.hoi4modding.struct.variable.ICountryVariable

abstract class EffectState: Effect<Scope.State>() {
    override val effectTooltip = fieldFactory.adjustableEffectState("effect_tooltip")
    val iff = fieldFactory.adjustableEffectState("if")
    val limit = fieldFactory.adjustableConditionState("limit")

    @Deprecated("It is not recommended to use country variables in the state scope.")
    override fun <V : Value<*>, U, L : Label<V>> setVariable(
        variable: ICountryVariable<V, U, L>,
        value: V,
        action: SetVariableBuilder<V, U, L>.() -> Unit
    ) {
        super.setVariable(variable, value, action)
    }

    @Deprecated("It is not recommended to use country variables in the state scope.")
    override fun <V : Value<*>, U, L : Label<V>> setVariable(
        variable: ICountryVariable<V, U, L>,
        value: U,
        action: SetVariableBuilder<V, U, L>.() -> Unit
    ) {
        super.setVariable(variable, value, action)
    }

    @Deprecated("It is not recommended to use country variables in the state scope.")
    override fun <V : Value<*>, U, L : Label<V>> setVariable(
        variable: ICountryVariable<V, U, L>,
        value: L,
        action: SetVariableBuilder<V, U, L>.() -> Unit
    ) {
        super.setVariable(variable, value, action)
    }
}

internal class ConcreteEffectState(initialize: EffectState.() -> Unit): EffectState() {
    init {
        initialize(this)
    }
}
