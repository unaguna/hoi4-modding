package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class ConditionState: Condition<Scope.State>() {
    override val or = fieldFactory.adjustableConditionState("OR")
    val owner = fieldFactory.adjustableConditionCountry("OWNER")
    val controller = fieldFactory.adjustableConditionCountry("CONTROLLER")
    val isClaimedBy = fieldFactory.adjustableCountry("is_claimed_by")
}

internal class ConcreteConditionState(initialize: ConditionState.() -> Unit): ConditionState() {
    init {
        initialize(this)
    }
}
