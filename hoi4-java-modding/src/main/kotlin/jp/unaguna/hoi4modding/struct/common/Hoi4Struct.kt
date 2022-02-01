package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.relationList

interface Hoi4Struct : Value<Hoi4FileList<Hoi4FileRelation>> {
    val parameterList: List<Parameter>

    override fun toHoi4FileObject(): Hoi4FileList<Hoi4FileRelation> {
        val relationList = parameterList.map { it.toHoi4FileObject() }
        return relationList {
            appendAll(relationList)
        }
    }

    fun toImmutable(): Hoi4Struct {
        return ImmutableStruct(parameterList)
    }
}
