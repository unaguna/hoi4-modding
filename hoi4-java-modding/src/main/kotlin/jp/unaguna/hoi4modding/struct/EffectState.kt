package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class EffectState: Effect<Scope.Companion.STATE>() {
    val iff = adjustableEffectState("if")
    val limit = adjustableConditionState("limit")
}

internal class ConcreteEffectState(initialize: EffectState.() -> Unit): EffectState() {
    init {
        initialize(this)
    }
}
