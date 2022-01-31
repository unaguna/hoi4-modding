package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class EffectCountry: Effect<Scope.Companion.COUNTRY>() {
    val iff = adjustableEffectCountry("if")
    val limit = adjustableConditionCountry("limit")
    val setResearchSlots = adjustableInt("set_research_slots")
}

internal class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}
