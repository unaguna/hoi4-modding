package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class ConditionCountry: Condition<Scope.Country>() {
    val originalResearchSlots = fieldFactory.comparableInt("original_research_slots")
}

internal class ConcreteConditionCountry(initialize: ConditionCountry.() -> Unit): ConditionCountry() {
    init {
        initialize(this)
    }
}