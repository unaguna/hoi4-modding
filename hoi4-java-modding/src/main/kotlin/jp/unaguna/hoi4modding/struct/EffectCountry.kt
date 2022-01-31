package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct

abstract class EffectCountry: AbstractStruct() {
    val iff = adjustableEffectCountry("if")
    val limit = adjustableConditionCountry("limit")
    val setGlobalFlag = adjustableFlag("set_global_flag")
    val setResearchSlots = adjustableInt("set_research_slots")
}

internal class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}
