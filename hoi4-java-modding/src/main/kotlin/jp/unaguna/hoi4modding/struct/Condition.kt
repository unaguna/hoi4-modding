package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Scope

abstract class Condition<S: Scope>: MutableStruct() {
    abstract val or: AdjustableField<out Condition<S>, in Condition<S>.()->Unit, Nothing>
    abstract val not: AdjustableField<out Condition<S>, in Condition<S>.()->Unit, Nothing>

    fun state(state: IState): AdjustableField<ConditionState, ConditionState.() -> Unit, Nothing> {
        return fieldFactory.adjustableConditionState(state.stateNumber.toString())
    }
    fun state(state: Int): AdjustableField<ConditionState, ConditionState.() -> Unit, Nothing> {
        return fieldFactory.adjustableConditionState(state.toString())
    }

    fun country(country: ICountry): AdjustableField<ConditionCountry, ConditionCountry.() -> Unit, Nothing> {
        return fieldFactory.adjustableConditionCountry(country.tag)
    }
    fun country(country: String): AdjustableField<ConditionCountry, ConditionCountry.() -> Unit, Nothing> {
        return fieldFactory.adjustableConditionCountry(country)
    }
}
