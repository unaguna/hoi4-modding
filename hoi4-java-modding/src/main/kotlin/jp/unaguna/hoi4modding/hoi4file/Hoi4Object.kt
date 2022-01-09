package jp.unaguna.hoi4modding.hoi4file

sealed class Hoi4Object {
    fun serialize(): String = serialize(0)
    abstract fun serialize(baseIndent: Int): String
}

data class Hoi4Bool(private val bool: Boolean) : Hoi4Object(), Hoi4ListElement, Hoi4RelationRight {
    override fun toString(): String = if (this.bool) "yes" else "no"
    override fun serialize(baseIndent: Int): String = this.toString()
}

data class Hoi4String(private val string: String): Hoi4Object(), Hoi4ListElement, Hoi4RelationRight {
    override fun toString(): String = this.string
    override fun serialize(baseIndent: Int): String = "\"${this.string}\""
}

data class Hoi4Number(private val number: Double): Hoi4Object(), Hoi4ListElement, Hoi4RelationRight {
    override fun toString(): String = this.number.toString()
    override fun serialize(baseIndent: Int): String = this.toString()
}

interface Hoi4ListElement {
    fun serialize(baseIndent: Int): String
}

class Hoi4List<E: Hoi4ListElement>(private val list: List<E>): Hoi4Object(), Hoi4RelationRight {
    override fun serialize(baseIndent: Int): String {
        return buildString {
            for(element in list) {
                append(INDENT_UNIT.repeat(baseIndent))
                appendLine(element.serialize(baseIndent))
            }
        }
    }
}
