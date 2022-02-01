package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class EffectState: Effect<Scope.Companion.STATE>() {
    val iff = fieldFactory.adjustableEffectState("if")
    val limit = fieldFactory.adjustableConditionState("limit")
}

internal class ConcreteEffectState(initialize: EffectState.() -> Unit): EffectState() {
    init {
        initialize(this)
    }
}
