package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.relationList
import jp.unaguna.hoi4modding.struct.ConditionCountry
import jp.unaguna.hoi4modding.struct.EffectCountry

abstract class AbstractStruct : Value {
    private val parameterList: MutableList<Parameter> = mutableListOf()

    override fun toHoi4FileObject(): Hoi4FileList<Hoi4FileRelation> {
        val relationList = parameterList.map { it.toHoi4FileObject() }
        return relationList {
            appendAll(relationList)
        }
    }

    internal fun addParameter(parameter: Parameter) {
        parameterList.add(parameter)
    }

    protected fun adjustableEffectCountry(parameterName: String): AdjustableField<EffectCountry, EffectCountry.()->Unit, Nothing> {
        return AdjustableEffectCountry(parameterName, this)
    }

    protected fun adjustableConditionCountry(parameterName: String): AdjustableField<ConditionCountry, ConditionCountry.()->Unit, Nothing> {
        return AdjustableConditionCountry(parameterName, this)
    }

    protected fun adjustableInt(parameterName: String): AdjustableField<Hoi4Number, Int, Label<Hoi4Number>> {
        return AdjustableInteger(parameterName, this)
    }

    protected fun comparableInt(parameterName: String): ComparableField<Hoi4Number, Int, Label<Hoi4Number>> {
        return ComparableInteger(parameterName, this)
    }
}
