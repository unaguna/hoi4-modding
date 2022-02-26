package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.MutableStruct
import jp.unaguna.hoi4modding.struct.common.Parameter

abstract class CountryEvent(final override val eventId: String): Hoi4Struct, ICountryEvent {
    private val struct by lazy { EventStruct() }
    protected abstract val optionList: List<Pair<String?, CountryEventOption.() -> Unit>>
    open val titleKey: String = "$eventId.t"
    open val descriptionKey: String = "$eventId.desc"
    abstract val isTriggeredOnly: Boolean
    open val picture: IGfx? = null

    override val parameterList: List<Parameter> by lazy { struct.parameterList }

    protected fun option(
        definition: CountryEventOption.() -> Unit
    ): Pair<String?, CountryEventOption.() -> Unit> {
        return option(null, definition)
    }

    protected fun option(
        optionName: String?,
        definition: CountryEventOption.() -> Unit
    ): Pair<String?, CountryEventOption.() -> Unit> {
        return optionName to definition
    }

    /** A class just for CountryEvent.parameterList() */
    private inner class EventStruct: MutableStruct() {
        private val id = fieldFactory.adjustableWord("id")
        private val title = fieldFactory.adjustableWord("title")
        private val desc = fieldFactory.adjustableWord("desc")
        private val picture = fieldFactory.adjustableGfx("picture")
        private val isTriggeredOnly = fieldFactory.adjustableBool("is_triggered_only")
        private val option = fieldFactory.adjustableCountryEventOption("option")

        init {
            id eq eventId
            title eq titleKey
            desc eq descriptionKey
            this@CountryEvent.picture?.let { picture eq it }
            isTriggeredOnly eq this@CountryEvent.isTriggeredOnly

            for((index, o) in optionList.withIndex()) {
                val (optionName_, optionDefinition) = o
                val optionName = optionName_ ?: "$eventId.${optionIndexToStr(index)}"

                option eq ConcreteCountryEventOption(optionName, optionDefinition)
            }
        }

        private fun optionIndexToStr(index: Int): String {
            if(index >= OPTION_INDEX_CHARS.length) {
                throw IllegalArgumentException("No more than ${OPTION_INDEX_CHARS.length} options can be set to an event.")
            }

            return OPTION_INDEX_CHARS[index].toString()
        }
    }

    companion object {
        const val OPTION_INDEX_CHARS = "abcdefghijklmnopqrs"
    }
}
