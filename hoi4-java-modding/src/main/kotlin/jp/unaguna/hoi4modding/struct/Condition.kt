package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Scope

abstract class Condition<S: Scope>: MutableStruct() {
    fun state(state: IState): AdjustableField<ConditionState, ConditionState.() -> Unit, Nothing> {
        return fieldFactory.adjustableConditionState(state.stateNumber.toString())
    }
    fun state(state: Int): AdjustableField<ConditionState, ConditionState.() -> Unit, Nothing> {
        return fieldFactory.adjustableConditionState(state.toString())
    }
}
