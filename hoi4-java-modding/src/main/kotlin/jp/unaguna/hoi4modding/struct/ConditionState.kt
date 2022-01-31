package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class ConditionState: Condition<Scope.Companion.STATE>()

internal class ConcreteConditionState(initialize: ConditionState.() -> Unit): ConditionState() {
    init {
        initialize(this)
    }
}
