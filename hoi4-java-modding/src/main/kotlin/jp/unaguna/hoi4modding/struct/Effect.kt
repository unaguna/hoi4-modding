package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct
import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.Scope

abstract class Effect<S: Scope>: AbstractStruct() {
    val setGlobalFlag = adjustableFlag("set_global_flag")

    fun state(state: IState): AdjustableField<EffectState, EffectState.() -> Unit, Nothing> {
        return adjustableEffectState(state.stateNumber.toString())
    }
    fun state(state: Int): AdjustableField<EffectState, EffectState.() -> Unit, Nothing> {
        return adjustableEffectState(state.toString())
    }
}
