package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

abstract class EffectCountry: Effect<Scope.Companion.COUNTRY>() {
    val iff = fieldFactory.adjustableEffectCountry("if")
    val limit = fieldFactory.adjustableConditionCountry("limit")
    val setResearchSlots = fieldFactory.adjustableInt("set_research_slots")
    val addIdeas = fieldFactory.adjustableIdeaList("add_ideas")
    val setTechnology = fieldFactory.adjustableTechnologyIntStruct("set_technology")

    private val setPolitics = fieldFactory.adjustableStruct("set_politics")

    fun setPolitics(rulingParty: IIdeology, action: SetPoliticsBuilder.()->Unit) {
        setPolitics eq SetPoliticsBuilder(rulingParty).apply(action).build()
    }
}

internal class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}
