package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.Scope
import jp.unaguna.hoi4modding.struct.common.Value
import jp.unaguna.hoi4modding.struct.variable.IStateVariable

sealed class AbstractEffectCountry: Effect<Scope.Country>() {
    val limit = fieldFactory.adjustableConditionCountry("limit")
    val setPopularities = fieldFactory.adjustableIdeologyIntStruct("set_popularities")
    val setResearchSlots = fieldFactory.adjustableInt("set_research_slots")
    val addIdeas = fieldFactory.adjustableIdeaList("add_ideas")
    val setTechnology = fieldFactory.adjustableTechnologyIntStruct("set_technology")

    private val setPolitics = fieldFactory.adjustableStruct("set_politics")
    private val countryEvent = fieldFactory.adjustableStruct("country_event")

    fun setPolitics(rulingParty: IIdeology, action: SetPoliticsBuilder.()->Unit) {
        setPolitics eq SetPoliticsBuilder(rulingParty).apply(action).build()
    }

    fun countryEvent(event: ICountryEvent, action: FireCountryEventBuilder.()->Unit = {}) {
        countryEvent eq FireCountryEventBuilder(event).apply(action).build()
    }

    fun countryEvent(event: Hoi4Word, action: FireCountryEventBuilder.()->Unit = {}) {
        countryEvent eq FireCountryEventBuilder(event).apply(action).build()
    }

    fun countryEvent(event: String, action: FireCountryEventBuilder.()->Unit = {}) {
        countryEvent eq FireCountryEventBuilder(event).apply(action).build()
    }

    @Deprecated("It is not recommended to use state variables in the country scope.")
    override fun <V : Value<*>, U, L : Label<V>> setVariable(
        variable: IStateVariable<V, U, L>,
        value: V,
        action: SetVariableBuilder<V, U, L>.() -> Unit
    ) {
        super.setVariable(variable, value, action)
    }

    @Deprecated("It is not recommended to use state variables in the country scope.")
    override fun <V : Value<*>, U, L : Label<V>> setVariable(
        variable: IStateVariable<V, U, L>,
        value: U,
        action: SetVariableBuilder<V, U, L>.() -> Unit
    ) {
        super.setVariable(variable, value, action)
    }

    @Deprecated("It is not recommended to use state variables in the country scope.")
    override fun <V : Value<*>, U, L : Label<V>> setVariable(
        variable: IStateVariable<V, U, L>,
        value: L,
        action: SetVariableBuilder<V, U, L>.() -> Unit
    ) {
        super.setVariable(variable, value, action)
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
    val recruitCharacter = fieldFactory.adjustableCharacter("recruit_character")

    fun recruitCharacters(vararg characters: ICharacter) {
        for (character in characters) {
            recruitCharacter eq character
        }
    }
}

internal class ConcreteEffectCountryHistory(initialize: EffectCountryHistory.() -> Unit): EffectCountryHistory() {
    init {
        initialize(this)
    }
}

abstract class CountryEventOption(val optionName: String): AbstractEffectCountry() {
    init {
        fieldFactory.adjustableWord("name") eq optionName
    }
}

internal class ConcreteCountryEventOption(
    optionName: String,
    initialize: CountryEventOption.() -> Unit,
): CountryEventOption(optionName) {
    init {
        initialize(this)
    }
}
