package jp.unaguna.hoi4modding.hoi4file


interface Hoi4RelationRight {
    fun serialize(baseIndent: Int): String
}

sealed class Hoi4Relation(private val left: String, private val right: Hoi4RelationRight) : Hoi4Object(), Hoi4ListElement {
    abstract val relationString: String

    override fun serialize(baseIndent: Int): String {
        return if(right is Hoi4List<*>) {
            buildString {
                append(left)
                append(" ")
                append(relationString)
                append(" {")
                appendLine()
                append(right.serialize(baseIndent+1))
                repeat(baseIndent) { append(INDENT_UNIT) }
                append("}")
            }
        } else {
            "$left $relationString ${right.serialize(baseIndent+1)}"
        }
    }
}

class Hoi4RelationEq(left: String, right: Hoi4RelationRight): Hoi4Relation(left, right) {
    override val relationString: String = "="
}

class Hoi4RelationLt(left: String, right: Hoi4RelationRight): Hoi4Relation(left, right) {
    override val relationString: String = "<"
}

class Hoi4RelationGt(left: String, right: Hoi4RelationRight): Hoi4Relation(left, right) {
    override val relationString: String = ">"
}
