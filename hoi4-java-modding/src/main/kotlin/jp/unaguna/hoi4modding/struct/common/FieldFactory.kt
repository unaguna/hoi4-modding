package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileString
import jp.unaguna.hoi4modding.struct.ConditionCountry
import jp.unaguna.hoi4modding.struct.ConditionState
import jp.unaguna.hoi4modding.struct.EffectCountry
import jp.unaguna.hoi4modding.struct.EffectState
import jp.unaguna.hoi4modding.struct.IFlag
import jp.unaguna.hoi4modding.struct.IIdea
import jp.unaguna.hoi4modding.struct.IIdeology
import jp.unaguna.hoi4modding.struct.TechnologyIntStruct

class FieldFactory(private val struct: MutableStruct) {
    fun adjustableStruct(parameterName: String): AdjustableField<Hoi4Struct, Nothing, Nothing> {
        return AdjustableImmutableStruct(parameterName, struct)
    }

    fun adjustableEffectCountry(parameterName: String): AdjustableField<EffectCountry, EffectCountry.()->Unit, Nothing> {
        return AdjustableEffectCountry(parameterName, struct)
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

    fun adjustableDate(parameterName: String): AdjustableField<Hoi4Date, Nothing, Nothing> {
        return AdjustableDate(parameterName, struct)
    }

    fun adjustableFlag(parameterName: String): AdjustableField<Hoi4String, String, IFlag> {
        return AdjustableString(parameterName, struct)
    }

    fun adjustableIdeology(parameterName: String): AdjustableField<Hoi4String, String, IIdeology> {
        return AdjustableString(parameterName, struct)
    }

    fun adjustableIdeaList(parameterName: String): AdjustableField<ValueList<Hoi4FileString, Hoi4String>, List<IIdea>, Nothing> {
        return AdjustableList(parameterName, struct)
    }

    fun adjustableTechnologyIntStruct(parameterName: String): AdjustableField<TechnologyIntStruct, TechnologyIntStruct.()->Unit, Nothing> {
        return AdjustableTechnologyIntStruct(parameterName, struct)
    }
}