package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class AbstractStruct : Value<Hoi4FileList<Hoi4FileRelation>> {
    private val parameterList: MutableList<Parameter> = mutableListOf()
    protected val fieldFactory by lazy { FieldFactory(this) }

    override fun toHoi4FileObject(): Hoi4FileList<Hoi4FileRelation> {
        val relationList = parameterList.map { it.toHoi4FileObject() }
        return relationList {
            appendAll(relationList)
        }
    }

    internal fun addParameter(parameter: Parameter) {
        parameterList.add(parameter)
    }
}
