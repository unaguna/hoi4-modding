package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Parameter

abstract class CountryEvent(final override val eventId: String): Hoi4Struct, IEvent {
    private val struct by lazy { EventStruct() }
    open val titleKey: String = "$eventId.t"
    open val descriptionKey: String = "$eventId.desc"

    override val parameterList: List<Parameter> by lazy { struct.parameterList }

    /** A class just for CountryEvent.parameterList() */
    private inner class EventStruct: MutableStruct() {
        private val id = fieldFactory.adjustableWord("id")
        private val title = fieldFactory.adjustableWord("title")
        private val desc = fieldFactory.adjustableWord("desc")

        init {
            id eq eventId
            title eq titleKey
            desc eq descriptionKey
        }
    }
}
