package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Scope

abstract class Effect<S: Scope>: MutableStruct() {
    val setGlobalFlag = fieldFactory.adjustableFlag("set_global_flag")

    fun state(state: IState): AdjustableField<EffectState, EffectState.() -> Unit, Nothing> {
        return fieldFactory.adjustableEffectState(state.stateNumber.toString())
    }
    fun state(state: Int): AdjustableField<EffectState, EffectState.() -> Unit, Nothing> {
        return fieldFactory.adjustableEffectState(state.toString())
    }
}
