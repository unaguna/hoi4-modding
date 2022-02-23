package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class ConditionCountry: Condition<Scope.Country>() {
    override val or = fieldFactory.adjustableConditionCountry("OR")
    val originalTag = fieldFactory.adjustableCountry("original_tag")
    val isSubjectOf = fieldFactory.adjustableCountry("is_subject_of")
    val originalResearchSlots = fieldFactory.comparableInt("original_research_slots")
}

internal class ConcreteConditionCountry(initialize: ConditionCountry.() -> Unit): ConditionCountry() {
    init {
        initialize(this)
    }
}
