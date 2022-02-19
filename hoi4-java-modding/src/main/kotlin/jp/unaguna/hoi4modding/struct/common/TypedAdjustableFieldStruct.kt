package jp.unaguna.hoi4modding.struct.common

abstract class TypedAdjustableFieldStruct<T, V: Value<*>, U : Any, L : Label<V>> : MutableStruct() {
    internal abstract fun fieldName(field: T) : String
    internal abstract fun field(field: String) : AdjustableField<V, U, L>

    internal fun field(field: T) : AdjustableField<V, U, L> {
        return field(fieldName(field))
    }

    infix fun T.eq(value: V) {
        field(this) eq value
    }

    infix fun T.eq(value: U) {
        field(this) eq value
    }

    infix fun T.eq(value: L) {
        field(this) eq value
    }

    infix fun String.eq(value: V) {
        field(this) eq value
    }

    infix fun String.eq(value: U) {
        field(this) eq value
    }

    infix fun String.eq(value: L) {
        field(this) eq value
    }
}
