package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileNumber
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationEq
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationGt
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationLt
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationRight

interface Parameter<T : Hoi4FileRelationRight> {
    fun toHoi4Relation(): Hoi4FileRelation?
}

interface AdjustableParameter<T : Hoi4FileRelationRight, U : Any> : Parameter<T> {
    infix fun eq(value: T)
    infix fun eq(value: U)
}

interface ComparableParameter<T : Hoi4FileRelationRight, U : Any> : Parameter<T>, AdjustableParameter<T, U> {
    infix fun lt(value: T)
    infix fun lt(value: U)
    infix fun gt(value: T)
    infix fun gt(value: U)
}

internal abstract class AbstractParameter<T : Hoi4FileRelationRight>(protected val parameterName: String) : Parameter<T>

internal abstract class AbstractAdjustableParameter<T : Hoi4FileRelationRight, U : Any>(parameterName: String) :
    AbstractParameter<T>(parameterName), AdjustableParameter<T, U> {

    private var value: T? = null

    override infix fun eq(value: T) {
        this.value = value
    }

    override fun toHoi4Relation(): Hoi4FileRelation? {
        return value?.let { value -> Hoi4FileRelationEq(parameterName, value) }
    }
}

internal abstract class AbstractComparableParameter<T : Hoi4FileRelationRight, U : Any>(parameterName: String) :
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

    override fun toHoi4Relation(): Hoi4FileRelation? {
        val value = this.value ?: return null

        return when (operator) {
            Operator.EQ -> Hoi4FileRelationEq(parameterName, value)
            Operator.LT -> Hoi4FileRelationLt(parameterName, value)
            Operator.GT -> Hoi4FileRelationGt(parameterName, value)
            null -> null
        }
    }

    private enum class Operator {
        EQ, LT, GT
    }
}

internal class ConcreteAdjustableParameter<T : Hoi4FileRelationRight>(parameterName: String) :
    AbstractAdjustableParameter<T, Nothing>(parameterName) {
    override infix fun eq(value: Nothing) {
        throw UnsupportedOperationException()
    }
}

internal class AdjustableInteger(parameterName: String) : AbstractAdjustableParameter<Hoi4FileNumber, Int>(parameterName) {
    override infix fun eq(value: Int) {
        super.eq(Hoi4FileNumber(value))
    }
}

internal class ComparableInteger(parameterName: String) : AbstractComparableParameter<Hoi4FileNumber, Int>(parameterName) {
    override infix fun eq(value: Int) {
        super.eq(Hoi4FileNumber(value))
    }

    override infix fun lt(value: Int) {
        super.lt(Hoi4FileNumber(value))
    }

    override infix fun gt(value: Int) {
        super.gt(Hoi4FileNumber(value))
    }
}
