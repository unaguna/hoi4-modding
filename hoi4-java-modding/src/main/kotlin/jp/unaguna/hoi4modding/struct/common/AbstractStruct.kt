package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileNumber
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.ToHoi4List
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class AbstractStruct : ToHoi4List<Hoi4FileRelation> {
    private val parameterList: MutableList<Parameter<*>> = mutableListOf()

    override fun toHoi4List(): Hoi4FileList<Hoi4FileRelation> {
        val relationList = parameterList.mapNotNull { it.toHoi4Relation() }
        return relationList {
            appendAll(relationList)
        }
    }

    protected fun adjustableRelationList(parameterName: String): AdjustableParameter<Hoi4FileList<Hoi4FileRelation>, Nothing> {
        return ConcreteAdjustableParameter<Hoi4FileList<Hoi4FileRelation>>(parameterName).also { parameterList.add(it) }
    }

    protected fun adjustableInt(parameterName: String): AdjustableParameter<Hoi4FileNumber, Int> {
        return AdjustableInteger(parameterName).also { parameterList.add(it) }
    }

    protected fun comparableInt(parameterName: String): ComparableParameter<Hoi4FileNumber, Int> {
        return ComparableInteger(parameterName).also { parameterList.add(it) }
    }
}
