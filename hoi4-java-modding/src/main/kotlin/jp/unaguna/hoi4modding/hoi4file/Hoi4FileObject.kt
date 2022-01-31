package jp.unaguna.hoi4modding.hoi4file

interface Hoi4FileObject {
    fun serialize(): String = serialize(0)
    fun serialize(baseIndent: Int): String
}

sealed class AbstractHoi4FileObject: Hoi4FileObject

data class Hoi4FileBool(private val bool: Boolean) : AbstractHoi4FileObject(), Hoi4FileListElement, Hoi4FileRelationRight {
    override fun toString(): String = if (this.bool) "yes" else "no"
    override fun serialize(baseIndent: Int): String = this.toString()
}

data class Hoi4FileString(private val string: String): AbstractHoi4FileObject(), Hoi4FileListElement, Hoi4FileRelationRight {
    override fun toString(): String = this.string
    override fun serialize(baseIndent: Int): String = "\"${this.string}\""
}

data class Hoi4FileNumber(private val number: Number): AbstractHoi4FileObject(), Hoi4FileListElement, Hoi4FileRelationRight {
    override fun toString(): String = this.number.toString()
    override fun serialize(baseIndent: Int): String = this.toString()
}

interface Hoi4FileListElement {
    fun serialize(baseIndent: Int): String
}

class Hoi4FileList<E: Hoi4FileListElement>(private val list: List<E>): AbstractHoi4FileObject(), Hoi4FileRelationRight, Collection<E> by list {
    override fun serialize(baseIndent: Int): String {
        return buildString {
            for(element in list) {
                append(INDENT_UNIT.repeat(baseIndent))
                appendLine(element.serialize(baseIndent))
            }
        }
    }
}

sealed class Hoi4FileListBuilder<E: Hoi4FileListElement> {
    private val list: MutableList<E> = ArrayList()

    fun append(element: E) : Hoi4FileListBuilder<E> {
        list.add(element)
        return this
    }
    fun appendAll(elements: Collection<E>) : Hoi4FileListBuilder<E> {
        list.addAll(elements)
        return this
    }
    fun build(): Hoi4FileList<E> = Hoi4FileList(list)
}

class Hoi4FileRelationListBuilder: Hoi4FileListBuilder<Hoi4FileRelation>() {
    infix fun String.eq(right: Hoi4FileRelationRight) {
        append(Hoi4FileRelationEq(this, right))
    }
    infix fun String.eq(right: AsHoi4Number) {
        append(Hoi4FileRelationEq(this, right.asHoi4Number()))
    }
    infix fun String.eq(right: AsHoi4String) {
        append(Hoi4FileRelationEq(this, right.asHoi4String()))
    }
    infix fun String.eq(right: Number) {
        append(Hoi4FileRelationEq(this, Hoi4FileNumber(right)))
    }
    infix fun String.eq(right: String) {
        append(Hoi4FileRelationEq(this, Hoi4FileString(right)))
    }
    infix fun String.eq(right: ToHoi4FileRelationRight<*>) {
        append(Hoi4FileRelationEq(this, right.toHoi4FileObject()))
    }
    infix fun String.lt(right: Hoi4FileRelationRight) {
        append(Hoi4FileRelationLt(this, right))
    }
    infix fun String.lt(right: AsHoi4Number) {
        append(Hoi4FileRelationLt(this, right.asHoi4Number()))
    }
    infix fun String.lt(right: AsHoi4String) {
        append(Hoi4FileRelationLt(this, right.asHoi4String()))
    }
    infix fun String.lt(right: Number) {
        append(Hoi4FileRelationLt(this, Hoi4FileNumber(right)))
    }
    infix fun String.lt(right: String) {
        append(Hoi4FileRelationLt(this, Hoi4FileString(right)))
    }
    infix fun String.lt(right: ToHoi4FileRelationRight<*>) {
        append(Hoi4FileRelationLt(this, right.toHoi4FileObject()))
    }
    infix fun String.gt(right: Hoi4FileRelationRight) {
        append(Hoi4FileRelationGt(this, right))
    }
    infix fun String.gt(right: AsHoi4Number) {
        append(Hoi4FileRelationGt(this, right.asHoi4Number()))
    }
    infix fun String.gt(right: AsHoi4String) {
        append(Hoi4FileRelationGt(this, right.asHoi4String()))
    }
    infix fun String.gt(right: Number) {
        append(Hoi4FileRelationGt(this, Hoi4FileNumber(right)))
    }
    infix fun String.gt(right: String) {
        append(Hoi4FileRelationGt(this, Hoi4FileString(right)))
    }
    infix fun String.gt(right: ToHoi4FileRelationRight<*>) {
        append(Hoi4FileRelationGt(this, right.toHoi4FileObject()))
    }
}

class Hoi4FileStringListBuilder: Hoi4FileListBuilder<Hoi4FileString>() {
    fun append(element: String) : Hoi4FileStringListBuilder {
        append(Hoi4FileString(element))
        return this
    }
}

class Hoi4FileNumberListBuilder: Hoi4FileListBuilder<Hoi4FileNumber>() {
    fun append(element: Number) : Hoi4FileNumberListBuilder {
        append(Hoi4FileNumber(element.toDouble()))
        return this
    }
}

fun relationList(closer: Hoi4FileRelationListBuilder.() -> Unit): Hoi4FileList<Hoi4FileRelation> {
    val builder = Hoi4FileRelationListBuilder()
    closer(builder)
    return builder.build()
}

fun numberList(closer: Hoi4FileNumberListBuilder.() -> Unit): Hoi4FileList<Hoi4FileNumber> {
    val builder = Hoi4FileNumberListBuilder()
    closer(builder)
    return builder.build()
}

fun stringList(closer: Hoi4FileStringListBuilder.() -> Unit): Hoi4FileList<Hoi4FileString> {
    val builder = Hoi4FileStringListBuilder()
    closer(builder)
    return builder.build()
}
