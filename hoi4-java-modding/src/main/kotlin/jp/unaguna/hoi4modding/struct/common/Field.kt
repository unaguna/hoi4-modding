package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.struct.ConcreteConditionCountry
import jp.unaguna.hoi4modding.struct.ConcreteEffectCountry
import jp.unaguna.hoi4modding.struct.ConditionCountry
import jp.unaguna.hoi4modding.struct.EffectCountry

interface Field<T : Value> {
    val fieldName: String
}

interface AdjustableField<T : Value, U : Any> : Field<T> {
    infix fun eq(value: T)
    infix fun eq(value: U)
}

interface ComparableField<T : Value, U : Any> : Field<T> {
    infix fun eq(value: T)
    infix fun eq(value: U)
    infix fun lt(value: T)
    infix fun lt(value: U)
    infix fun gt(value: T)
    infix fun gt(value: U)
}

internal abstract class AbstractField<T : Value>(override val fieldName: String, protected val struct: AbstractStruct) : Field<T> {
    internal fun registerParameter(operator: Operator, value: T) {
        val parameter = Parameter(this, operator, value)
        struct.addParameter(parameter)
    }
}

internal abstract class AbstractAdjustableField<T : Value, U : Any>(fieldName: String, struct: AbstractStruct) :
    AbstractField<T>(fieldName, struct), AdjustableField<T, U> {

    override infix fun eq(value: T) {
        registerParameter(Operator.EQ, value)
    }
}

internal abstract class AbstractComparableField<T : Value, U : Any>(fieldName: String, struct: AbstractStruct) :
    AbstractField<T>(fieldName, struct), AdjustableField<T, U>, ComparableField<T, U> {

    override infix fun eq(value: T) {
        registerParameter(Operator.EQ, value)
    }

    override infix fun lt(value: T) {
        registerParameter(Operator.LT, value)
    }

    override infix fun gt(value: T) {
        registerParameter(Operator.GT, value)
    }
}

internal class AdjustableInteger(fieldName: String, struct: AbstractStruct) : AbstractAdjustableField<Hoi4Number, Int>(fieldName, struct) {
    override infix fun eq(value: Int) {
        super.eq(Hoi4Number(value))
    }
}

internal class ComparableInteger(fieldName: String, struct: AbstractStruct) : AbstractComparableField<Hoi4Number, Int>(fieldName, struct) {
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

internal class AdjustableEffectCountry(fieldName: String, struct: AbstractStruct) : AbstractAdjustableField<EffectCountry, EffectCountry.()->Unit>(fieldName, struct) {
    override infix fun eq(value: EffectCountry.()->Unit) {
        super.eq(ConcreteEffectCountry(value))
    }
}

internal class AdjustableConditionCountry(fieldName: String, struct: AbstractStruct) : AbstractAdjustableField<ConditionCountry, ConditionCountry.()->Unit>(fieldName, struct) {
    override infix fun eq(value: ConditionCountry.()->Unit) {
        super.eq(ConcreteConditionCountry(value))
    }
}
