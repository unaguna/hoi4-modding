package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4Number
import jp.unaguna.hoi4modding.hoi4file.Hoi4Relation
import jp.unaguna.hoi4modding.hoi4file.Hoi4RelationEq
import jp.unaguna.hoi4modding.hoi4file.Hoi4RelationGt
import jp.unaguna.hoi4modding.hoi4file.Hoi4RelationLt
import jp.unaguna.hoi4modding.hoi4file.Hoi4RelationRight

interface Parameter<T : Hoi4RelationRight> {
    fun toHoi4Relation(): Hoi4Relation?
}

interface AdjustableParameter<T : Hoi4RelationRight, U : Any> : Parameter<T> {
    infix fun eq(value: T)
    infix fun eq(value: U)
}

interface ComparableParameter<T : Hoi4RelationRight, U : Any> : Parameter<T>, AdjustableParameter<T, U> {
    infix fun lt(value: T)
    infix fun lt(value: U)
    infix fun gt(value: T)
    infix fun gt(value: U)
}

internal abstract class AbstractParameter<T : Hoi4RelationRight>(protected val parameterName: String) : Parameter<T>

internal abstract class AbstractAdjustableParameter<T : Hoi4RelationRight, U : Any>(parameterName: String) :
    AbstractParameter<T>(parameterName), AdjustableParameter<T, U> {

    private var value: T? = null

    override infix fun eq(value: T) {
        this.value = value
    }

    override fun toHoi4Relation(): Hoi4Relation? {
        return value?.let { value -> Hoi4RelationEq(parameterName, value) }
    }
}

internal abstract class AbstractComparableParameter<T : Hoi4RelationRight, U : Any>(parameterName: String) :
    AbstractParameter<T>(parameterName), AdjustableParameter<T, U>, ComparableParameter<T, U> {
    private var value: T? = null
    private var operator: Operator? = null

    override infix fun eq(value: T) {
        this.value = value
        this.operator = Operator.EQ
    }

    override infix fun lt(value: T) {
        this.value = value
        this.operator = Operator.LT
    }

    override infix fun gt(value: T) {
        this.value = value
        this.operator = Operator.GT
    }

    override fun toHoi4Relation(): Hoi4Relation? {
        val value = this.value ?: return null

        return when (operator) {
            Operator.EQ -> Hoi4RelationEq(parameterName, value)
            Operator.LT -> Hoi4RelationLt(parameterName, value)
            Operator.GT -> Hoi4RelationGt(parameterName, value)
            null -> null
        }
    }

    private enum class Operator {
        EQ, LT, GT
    }
}

internal class ConcreteAdjustableParameter<T : Hoi4RelationRight>(parameterName: String) :
    AbstractAdjustableParameter<T, Nothing>(parameterName) {
    override infix fun eq(value: Nothing) {
        throw UnsupportedOperationException()
    }
}

internal class AdjustableInteger(parameterName: String) : AbstractAdjustableParameter<Hoi4Number, Int>(parameterName) {
    override infix fun eq(value: Int) {
        super.eq(Hoi4Number(value))
    }
}

internal class ComparableInteger(parameterName: String) : AbstractComparableParameter<Hoi4Number, Int>(parameterName) {
    override infix fun eq(value: Int) {
        super.eq(Hoi4Number(value))
    }

    override infix fun lt(value: Int) {
        super.lt(Hoi4Number(value))
    }

    override infix fun gt(value: Int) {
        super.gt(Hoi4Number(value))
    }
}
