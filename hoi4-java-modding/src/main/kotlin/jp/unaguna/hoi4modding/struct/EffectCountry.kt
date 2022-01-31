package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct

abstract class EffectCountry: AbstractStruct() {
    val limit = adjustableRelationList("limit")
    val setResearchSlots = adjustableInt("set_research_slots")

    fun iif(limit_: ConditionCountry.()->Unit, effect: EffectCountry.()->Unit) {
        adjustableRelationList("if") eq ConcreteEffectCountry {
            limit eq ConcreteConditionCountry(limit_)
            effect(this)
        }
    }
}

private class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}
