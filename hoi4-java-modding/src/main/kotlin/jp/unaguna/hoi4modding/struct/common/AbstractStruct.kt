package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class AbstractStruct : Value {
    private val parameterList: MutableList<Parameter<*>> = mutableListOf()

    override fun toHoi4FileObject(): Hoi4FileList<Hoi4FileRelation> {
        val relationList = parameterList.mapNotNull { it.toHoi4Relation() }
        return relationList {
            appendAll(relationList)
        }
    }

    protected fun adjustableRelationList(parameterName: String): AdjustableParameter<AbstractStruct, Nothing> {
        return ConcreteAdjustableParameter<AbstractStruct>(parameterName).also { parameterList.add(it) }
    }

    protected fun adjustableInt(parameterName: String): AdjustableParameter<Hoi4Number, Int> {
        return AdjustableInteger(parameterName).also { parameterList.add(it) }
    }

    protected fun comparableInt(parameterName: String): ComparableParameter<Hoi4Number, Int> {
        return ComparableInteger(parameterName).also { parameterList.add(it) }
    }
}
