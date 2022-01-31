package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationRight

class ValueList<H: Hoi4FileRelationRight, V: Value<H>>(private val list: List<V>) : List<V> by list, Value<Hoi4FileList<H>> {
    override fun toHoi4FileObject(): Hoi4FileList<H> {
        return Hoi4FileList(list.map { it.toHoi4FileObject() })
    }
}
