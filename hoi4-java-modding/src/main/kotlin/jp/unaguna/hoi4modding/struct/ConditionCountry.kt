package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct

abstract class ConditionCountry: AbstractStruct() {
    val originalResearchSlots = comparableInt("original_research_slots")
}

internal class ConcreteConditionCountry(initialize: ConditionCountry.() -> Unit): ConditionCountry() {
    init {
        initialize(this)
    }
}
