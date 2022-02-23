package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileObject
import jp.unaguna.hoi4modding.struct.common.MutableStruct

abstract class EventSpace(override val namespace: String): ToFile, INamespace {
    abstract val eventList: List<CountryEvent>
    private val struct by lazy { EventSpaceStruct() }

    override fun fileList(): List<Pair<String, Hoi4FileObject>> {
        return listOf("events/${namespace}.txt" to struct.toHoi4FileObject())
    }

    /** A class just for EventSpace.fileList() */
    private inner class EventSpaceStruct: MutableStruct() {
        private val addNamespace = fieldFactory.adjustableNamespace("add_namespace")
        private val countryEvent = fieldFactory.adjustableCountryEventDef("country_event")

        init {
            addNamespace eq this@EventSpace
            for(event in eventList) {
                countryEvent eq event
            }
        }
    }
}
