package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.relationList
import jp.unaguna.hoi4modding.struct.common.AbstractStruct

abstract class EffectCountry: AbstractStruct() {
    val setResearchSlots = adjustableInt("set_research_slots")

    fun iif(limit: ConditionCountry.()->Unit, effect: EffectCountry.()->Unit) {
        adjustableRelationList("if") eq relationList {
            "limit" eq ConcreteConditionCountry(limit)
            appendAll(ConcreteEffectCountry(effect).toHoi4List())
        }
    }
}

private class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}
