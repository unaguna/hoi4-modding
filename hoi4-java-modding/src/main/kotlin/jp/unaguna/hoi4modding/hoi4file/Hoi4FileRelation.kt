package jp.unaguna.hoi4modding.hoi4file


interface Hoi4FileRelationRight {
    fun serialize(baseIndent: Int): String
}

sealed class Hoi4FileRelation(private val left: String, private val right: Hoi4FileRelationRight) : Hoi4FileObject(), Hoi4FileListElement {
    abstract val relationString: String

    override fun serialize(baseIndent: Int): String {
        return if(right is Hoi4FileList<*>) {
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

class Hoi4FileRelationEq(left: String, right: Hoi4FileRelationRight): Hoi4FileRelation(left, right) {
    override val relationString: String = "="
}

class Hoi4FileRelationLt(left: String, right: Hoi4FileRelationRight): Hoi4FileRelation(left, right) {
    override val relationString: String = "<"
}

class Hoi4FileRelationGt(left: String, right: Hoi4FileRelationRight): Hoi4FileRelation(left, right) {
    override val relationString: String = ">"
}
