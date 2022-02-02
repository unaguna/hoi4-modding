package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Scope

sealed class AbstractEffectCountry: Effect<Scope.Companion.COUNTRY>() {
    val limit = fieldFactory.adjustableConditionCountry("limit")
    val setPopularities = fieldFactory.adjustableIdeologyIntStruct("set_popularities")
    val setResearchSlots = fieldFactory.adjustableInt("set_research_slots")
    val addIdeas = fieldFactory.adjustableIdeaList("add_ideas")
    val setTechnology = fieldFactory.adjustableTechnologyIntStruct("set_technology")

    private val setPolitics = fieldFactory.adjustableStruct("set_politics")

    fun setPolitics(rulingParty: IIdeology, action: SetPoliticsBuilder.()->Unit) {
        setPolitics eq SetPoliticsBuilder(rulingParty).apply(action).build()
    }
}

abstract class EffectCountry: AbstractEffectCountry() {
    val iff = fieldFactory.adjustableEffectCountry("if")
}

internal class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}

abstract class EffectCountryHistory: AbstractEffectCountry() {
    val iff = fieldFactory.adjustableEffectCountryHistory("if")
    val capital = fieldFactory.adjustableState("capital")
    val oob = fieldFactory.adjustableOob("OOB")
    val setConvoys = fieldFactory.adjustableInt("set_convoys")
}

internal class ConcreteEffectCountryHistory(initialize: EffectCountryHistory.() -> Unit): EffectCountryHistory() {
    init {
        initialize(this)
    }
}
