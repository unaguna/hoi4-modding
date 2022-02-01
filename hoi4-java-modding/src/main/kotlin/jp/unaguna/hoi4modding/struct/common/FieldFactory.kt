package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileString
import jp.unaguna.hoi4modding.struct.ConditionCountry
import jp.unaguna.hoi4modding.struct.ConditionState
import jp.unaguna.hoi4modding.struct.EffectCountry
import jp.unaguna.hoi4modding.struct.EffectState
import jp.unaguna.hoi4modding.struct.IFlag
import jp.unaguna.hoi4modding.struct.IIdea
import jp.unaguna.hoi4modding.struct.SetTechnology

class FieldFactory(private val struct: AbstractStruct) {
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

    fun adjustableInt(parameterName: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return AdjustableInteger(parameterName, struct)
    }

    fun comparableInt(parameterName: String): ComparableField<Hoi4Number, Int, Label<Hoi4Number>> {
        return ComparableInteger(parameterName, struct)
    }

    fun adjustableFlag(parameterName: String): AdjustableField<Hoi4String, String, IFlag> {
        return AdjustableString(parameterName, struct)
    }

    fun adjustableIdeaList(parameterName: String): AdjustableField<ValueList<Hoi4FileString, Hoi4String>, List<IIdea>, Nothing> {
        return AdjustableList(parameterName, struct)
    }

    fun adjustSetTechnology(parameterName: String): AdjustableField<SetTechnology, SetTechnology.()->Unit, Nothing> {
        return AdjustableSetTechnology(parameterName, struct)
    }
}