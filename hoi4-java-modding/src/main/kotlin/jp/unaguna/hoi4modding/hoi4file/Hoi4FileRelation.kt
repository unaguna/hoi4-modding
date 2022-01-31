package jp.unaguna.hoi4modding.hoi4file


interface Hoi4FileRelationRight : Hoi4FileObject, ToHoi4FileRelationRight<Hoi4FileRelationRight> {
    override fun toHoi4FileObject() = this
}

sealed class Hoi4FileRelation(private val left: String, right: ToHoi4FileRelationRight<*>) : AbstractHoi4FileObject(), Hoi4FileListElement {
    private val right = right.toHoi4FileObject()
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

class Hoi4FileRelationEq(left: String, right: ToHoi4FileRelationRight<*>): Hoi4FileRelation(left, right) {
    override val relationString: String = "="
}

class Hoi4FileRelationLt(left: String, right: ToHoi4FileRelationRight<*>): Hoi4FileRelation(left, right) {
    override val relationString: String = "<"
}

class Hoi4FileRelationGt(left: String, right: ToHoi4FileRelationRight<*>): Hoi4FileRelation(left, right) {
    override val relationString: String = ">"
}
