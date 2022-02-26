package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileWord
import jp.unaguna.hoi4modding.struct.ConditionCountry
import jp.unaguna.hoi4modding.struct.ConditionState
import jp.unaguna.hoi4modding.struct.CountryEvent
import jp.unaguna.hoi4modding.struct.CountryEventOption
import jp.unaguna.hoi4modding.struct.EffectCountry
import jp.unaguna.hoi4modding.struct.EffectCountryHistory
import jp.unaguna.hoi4modding.struct.EffectState
import jp.unaguna.hoi4modding.struct.ICharacter
import jp.unaguna.hoi4modding.struct.ICountry
import jp.unaguna.hoi4modding.struct.ICountryEvent
import jp.unaguna.hoi4modding.struct.IFlag
import jp.unaguna.hoi4modding.struct.IGfx
import jp.unaguna.hoi4modding.struct.IIdea
import jp.unaguna.hoi4modding.struct.IIdeology
import jp.unaguna.hoi4modding.struct.INamespace
import jp.unaguna.hoi4modding.struct.IOob
import jp.unaguna.hoi4modding.struct.IState
import jp.unaguna.hoi4modding.struct.ITooltip
import jp.unaguna.hoi4modding.struct.variable.IVariable
import jp.unaguna.hoi4modding.struct.IdeologyIntStruct
import jp.unaguna.hoi4modding.struct.TechnologyIntStruct

class FieldFactory(private val struct: MutableStruct) {
    fun <V: Value<*>> adjustableTightField(parameterName: String): AdjustableField<V, Nothing, Nothing> {
        return AdjustableTightField(parameterName, struct)
    }

    fun adjustableStruct(parameterName: String): AdjustableField<Hoi4Struct, Nothing, Nothing> {
        return AdjustableImmutableStruct(parameterName, struct)
    }

    fun adjustableEffectCountry(parameterName: String): AdjustableField<EffectCountry, EffectCountry.()->Unit, Nothing> {
        return AdjustableEffectCountry(parameterName, struct)
    }

    fun adjustableEffectCountryHistory(parameterName: String): AdjustableField<EffectCountryHistory, EffectCountryHistory.()->Unit, Nothing> {
        return AdjustableEffectCountryHistory(parameterName, struct)
    }

    fun adjustableCountryEventOption(parameterName: String): AdjustableField<CountryEventOption, Nothing, Nothing> {
        return AdjustableCountryEventOption(parameterName, struct)
    }

    fun adjustableConditionCountry(parameterName: String): AdjustableField<ConditionCountry, ConditionCountry.()->Unit, Nothing> {
        return AdjustableConditionCountry(parameterName, struct)
    }

    fun adjustableEffectState(parameterName: String): AdjustableField<EffectState, EffectState.()->Unit, Nothing> {
        return AdjustableEffectState(parameterName, struct)
    }

    fun adjustableConditionState(parameterName: String): AdjustableField<ConditionState, ConditionState.()->Unit, Nothing> {
        return AdjustableConditionState(parameterName, struct)
    }

    fun adjustableBool(parameterName: String): AdjustableField<Hoi4Bool, Boolean, Nothing> {
        return AdjustableBool(parameterName, struct)
    }

    fun adjustableInt(parameterName: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return AdjustableInteger(parameterName, struct)
    }

    fun comparableInt(parameterName: String): ComparableField<Hoi4Number, Int, Label<Hoi4Number>> {
        return ComparableInteger(parameterName, struct)
    }

    fun adjustableWord(parameterName: String): AdjustableField<Hoi4Word, String, Nothing> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableDate(parameterName: String): AdjustableField<Hoi4Date, Nothing, Nothing> {
        return AdjustableDate(parameterName, struct)
    }

    fun adjustableCountryEvent(parameterName: String): AdjustableField<Hoi4Word, String, ICountryEvent> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableCountry(parameterName: String): AdjustableField<Hoi4Word, String, ICountry> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableState(parameterName: String): AdjustableField<Hoi4Word, String, IState> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableFlag(parameterName: String): AdjustableField<Hoi4String, String, IFlag> {
        return AdjustableString(parameterName, struct)
    }

    fun adjustableIdeology(parameterName: String): AdjustableField<Hoi4Word, String, IIdeology> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableOob(parameterName: String): AdjustableField<Hoi4String, String, IOob> {
        return AdjustableString(parameterName, struct)
    }

    fun adjustableCharacter(parameterName: String): AdjustableField<Hoi4Word, String, ICharacter> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableTooltip(parameterName: String): AdjustableField<Hoi4Word, String, ITooltip> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableGfx(parameterName: String): AdjustableField<Hoi4Word, String, IGfx> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableNamespace(parameterName: String): AdjustableField<Hoi4Word, String, INamespace> {
        return AdjustableWord(parameterName, struct)
    }

    fun adjustableIdeaList(parameterName: String): AdjustableField<ValueList<Hoi4FileWord, Hoi4Word>, List<IIdea>, Nothing> {
        return AdjustableList(parameterName, struct)
    }

    fun adjustableCountryEventDef(parameterName: String): AdjustableField<CountryEvent, Nothing, Nothing> {
        return AdjustableTightField(parameterName, struct)
    }

    fun adjustableTechnologyIntStruct(parameterName: String): AdjustableField<TechnologyIntStruct, TechnologyIntStruct.()->Unit, Nothing> {
        return AdjustableTechnologyIntStruct(parameterName, struct)
    }

    fun adjustableIdeologyIntStruct(parameterName: String): AdjustableField<IdeologyIntStruct, IdeologyIntStruct.()->Unit, Nothing> {
        return AdjustableIdeologyIntStruct(parameterName, struct)
    }

    fun adjustableVariable(parameterName: String): AdjustableField<Hoi4Word, Nothing, IVariable<*, *, *, *>> {
        return AdjustableVariable(parameterName, struct)
    }
}