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

data class Hoi4Number(private val number: Number): Hoi4Object(), Hoi4ListElement, Hoi4RelationRight {
    override fun toString(): String = this.number.toString()
    override fun serialize(baseIndent: Int): String = this.toString()
}

interface Hoi4ListElement {
    fun serialize(baseIndent: Int): String
}

class Hoi4List<E: Hoi4ListElement>(private val list: List<E>): Hoi4Object(), Hoi4RelationRight, Collection<E> by list {
    override fun serialize(baseIndent: Int): String {
        return buildString {
            for(element in list) {
                append(INDENT_UNIT.repeat(baseIndent))
                appendLine(element.serialize(baseIndent))
            }
        }
    }
}

sealed class Hoi4ListBuilder<E: Hoi4ListElement> {
    private val list: MutableList<E> = ArrayList()

    fun append(element: E) : Hoi4ListBuilder<E> {
        list.add(element)
        return this
    }
    fun appendAll(elements: Collection<E>) : Hoi4ListBuilder<E> {
        list.addAll(elements)
        return this
    }
    fun build(): Hoi4List<E> = Hoi4List(list)
}

class Hoi4RelationListBuilder: Hoi4ListBuilder<Hoi4Relation>() {
    infix fun String.eq(right: Hoi4RelationRight) {
        append(Hoi4RelationEq(this, right))
    }
    infix fun String.eq(right: AsHoi4Number) {
        append(Hoi4RelationEq(this, right.asHoi4Number()))
    }
    infix fun String.eq(right: AsHoi4String) {
        append(Hoi4RelationEq(this, right.asHoi4String()))
    }
    infix fun String.eq(right: Number) {
        append(Hoi4RelationEq(this, Hoi4Number(right)))
    }
    infix fun String.eq(right: String) {
        append(Hoi4RelationEq(this, Hoi4String(right)))
    }
    infix fun String.eq(right: ToHoi4List<*>) {
        append(Hoi4RelationEq(this, right.toHoi4List()))
    }
    infix fun String.lt(right: Hoi4RelationRight) {
        append(Hoi4RelationLt(this, right))
    }
    infix fun String.lt(right: AsHoi4Number) {
        append(Hoi4RelationLt(this, right.asHoi4Number()))
    }
    infix fun String.lt(right: AsHoi4String) {
        append(Hoi4RelationLt(this, right.asHoi4String()))
    }
    infix fun String.lt(right: Number) {
        append(Hoi4RelationLt(this, Hoi4Number(right)))
    }
    infix fun String.lt(right: String) {
        append(Hoi4RelationLt(this, Hoi4String(right)))
    }
    infix fun String.lt(right: ToHoi4List<*>) {
        append(Hoi4RelationLt(this, right.toHoi4List()))
    }
    infix fun String.gt(right: Hoi4RelationRight) {
        append(Hoi4RelationGt(this, right))
    }
    infix fun String.gt(right: AsHoi4Number) {
        append(Hoi4RelationGt(this, right.asHoi4Number()))
    }
    infix fun String.gt(right: AsHoi4String) {
        append(Hoi4RelationGt(this, right.asHoi4String()))
    }
    infix fun String.gt(right: Number) {
        append(Hoi4RelationGt(this, Hoi4Number(right)))
    }
    infix fun String.gt(right: String) {
        append(Hoi4RelationGt(this, Hoi4String(right)))
    }
    infix fun String.gt(right: ToHoi4List<*>) {
        append(Hoi4RelationGt(this, right.toHoi4List()))
    }
}

class Hoi4StringListBuilder: Hoi4ListBuilder<Hoi4String>() {
    fun append(element: String) : Hoi4StringListBuilder {
        append(Hoi4String(element))
        return this
    }
}

class Hoi4NumberListBuilder: Hoi4ListBuilder<Hoi4Number>() {
    fun append(element: Number) : Hoi4NumberListBuilder {
        append(Hoi4Number(element.toDouble()))
        return this
    }
}

fun relationList(closer: Hoi4RelationListBuilder.() -> Unit): Hoi4List<Hoi4Relation> {
    val builder = Hoi4RelationListBuilder()
    closer(builder)
    return builder.build()
}

fun numberList(closer: Hoi4NumberListBuilder.() -> Unit): Hoi4List<Hoi4Number> {
    val builder = Hoi4NumberListBuilder()
    closer(builder)
    return builder.build()
}

fun stringList(closer: Hoi4StringListBuilder.() -> Unit): Hoi4List<Hoi4String> {
    val builder = Hoi4StringListBuilder()
    closer(builder)
    return builder.build()
}
