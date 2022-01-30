package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4List
import jp.unaguna.hoi4modding.hoi4file.Hoi4Number
import jp.unaguna.hoi4modding.hoi4file.Hoi4Relation
import jp.unaguna.hoi4modding.hoi4file.ToHoi4List
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class AbstractStruct : ToHoi4List<Hoi4Relation> {
    private val parameterList: MutableList<Parameter<*>> = mutableListOf()

    override fun toHoi4List(): Hoi4List<Hoi4Relation> {
        val relationList = parameterList.mapNotNull { it.toHoi4Relation() }
        return relationList {
            appendAll(relationList)
        }
    }

    protected fun adjustableRelationList(parameterName: String): AdjustableParameter<Hoi4List<Hoi4Relation>, Nothing> {
        return ConcreteAdjustableParameter<Hoi4List<Hoi4Relation>>(parameterName).also { parameterList.add(it) }
    }

    protected fun adjustableInt(parameterName: String): AdjustableParameter<Hoi4Number, Int> {
        return AdjustableInteger(parameterName).also { parameterList.add(it) }
    }

    protected fun comparableInt(parameterName: String): ComparableParameter<Hoi4Number, Int> {
        return ComparableInteger(parameterName).also { parameterList.add(it) }
    }
}
