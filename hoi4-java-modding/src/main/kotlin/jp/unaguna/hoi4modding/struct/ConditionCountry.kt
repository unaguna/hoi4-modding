package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class ConditionCountry: Condition<Scope.Country>() {
    override val or = fieldFactory.adjustableConditionCountry("OR")
    override val not = fieldFactory.adjustableConditionCountry("NOT")
    val originalTag = fieldFactory.adjustableCountry("original_tag")
    val isSubjectOf = fieldFactory.adjustableCountry("is_subject_of")
    val hasWar = fieldFactory.adjustableBool("has_war")
    val hasWarWith = fieldFactory.adjustableCountry("has_war_with")
    val originalResearchSlots = fieldFactory.comparableInt("original_research_slots")
    val anyNeighborCountry = fieldFactory.adjustableConditionCountry("any_neighbor_country")
    val ownsState = fieldFactory.adjustableState("owns_state")
}

internal class ConcreteConditionCountry(initialize: ConditionCountry.() -> Unit): ConditionCountry() {
    init {
        initialize(this)
    }
}

abstract class AiChanceModifier: ConditionCountry() {
    val factor = fieldFactory.adjustableNumber("factor")
    val add = fieldFactory.adjustableNumber("add")
}

internal class ConcreteAiChanceModifier(
    initialize: AiChanceModifier.() -> Unit,
): AiChanceModifier() {
    init {
        initialize(this)
    }
}
