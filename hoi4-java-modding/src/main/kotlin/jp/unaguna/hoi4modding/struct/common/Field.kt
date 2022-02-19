package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationRight
import jp.unaguna.hoi4modding.struct.ConcreteConditionCountry
import jp.unaguna.hoi4modding.struct.ConcreteConditionState
import jp.unaguna.hoi4modding.struct.ConcreteEffectCountry
import jp.unaguna.hoi4modding.struct.ConcreteEffectCountryHistory
import jp.unaguna.hoi4modding.struct.ConcreteEffectState
import jp.unaguna.hoi4modding.struct.ConcreteIdeologyIntStruct
import jp.unaguna.hoi4modding.struct.ConcreteTechnologyIntStruct
import jp.unaguna.hoi4modding.struct.ConditionCountry
import jp.unaguna.hoi4modding.struct.ConditionState
import jp.unaguna.hoi4modding.struct.EffectCountry
import jp.unaguna.hoi4modding.struct.EffectCountryHistory
import jp.unaguna.hoi4modding.struct.EffectState
import jp.unaguna.hoi4modding.struct.variable.IVariable
import jp.unaguna.hoi4modding.struct.IdeologyIntStruct
import jp.unaguna.hoi4modding.struct.TechnologyIntStruct

interface Field<T : Value<*>> {
    val fieldName: String
}

interface AdjustableField<T : Value<*>, U : Any, L: Label<T>> : Field<T> {
    infix fun eq(value: T)
    infix fun eq(value: U)
    infix fun eq(value: L) {
        this eq value.label
    }
}

interface ComparableField<T : Value<*>, U : Any, L: Label<T>> : Field<T> {
    infix fun eq(value: T)
    infix fun eq(value: U)
    infix fun eq(value: L) {
        this eq value.label
    }
    infix fun lt(value: T)
    infix fun lt(value: U)
    infix fun lt(value: L) {
        this lt value.label
    }
    infix fun gt(value: T)
    infix fun gt(value: U)
    infix fun gt(value: L) {
        this gt value.label
    }
}

internal abstract class AbstractField<T : Value<*>, L: Label<T>>(
    override val fieldName: String,
    protected val struct: MutableStruct,
) : Field<T> {
    internal fun registerParameter(operator: Operator, value: T) {
        val parameter = Parameter(this, operator, value)
        struct.addParameter(parameter)
    }
}

internal abstract class AbstractAdjustableField<T : Value<*>, U : Any, L: Label<T>>(
    fieldName: String,
    struct: MutableStruct
) : AbstractField<T, L>(fieldName, struct), AdjustableField<T, U, L> {

    override infix fun eq(value: T) {
        registerParameter(Operator.EQ, value)
    }
}

internal abstract class AbstractComparableField<T : Value<*>, U : Any, L: Label<T>>(
    fieldName: String,
    struct: MutableStruct,
) : AbstractField<T, L>(fieldName, struct), ComparableField<T, U, L> {

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

internal class AdjustableTightField<V: Value<*>>(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<V, Nothing, Nothing>(fieldName, struct) {
    override fun eq(value: Nothing) {
        throw UnsupportedOperationException()
    }
}

internal class AdjustableBool(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4Bool, Boolean, Nothing>(fieldName, struct) {
    override infix fun eq(value: Boolean) {
        super.eq(Hoi4Bool.of(value))
    }
}

internal class AdjustableInteger<L : Label<Hoi4Number>>(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4Number, Int, L>(fieldName, struct) {
    override infix fun eq(value: Int) {
        super.eq(Hoi4Number(value))
    }
}

internal class ComparableInteger<L : Label<Hoi4Number>>(fieldName: String, struct: MutableStruct) : AbstractComparableField<Hoi4Number, Int, L>(fieldName, struct) {
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

internal class AdjustableString<L: Label<Hoi4String>>(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4String, String, L>(fieldName, struct) {
    override infix fun eq(value: String) {
        super.eq(Hoi4String(value))
    }
}

internal class AdjustableWord<L: Label<Hoi4Word>>(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4Word, String, L>(fieldName, struct) {
    override infix fun eq(value: String) {
        super.eq(Hoi4Word(value))
    }
}

internal class AdjustableDate(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4Date, Nothing, Nothing>(fieldName, struct) {
    override infix fun eq(value: Nothing) {
        throw UnsupportedOperationException()
    }
}

internal class AdjustableEffectCountry(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<EffectCountry, EffectCountry.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: EffectCountry.()->Unit) {
        super.eq(ConcreteEffectCountry(value))
    }
}

internal class AdjustableEffectCountryHistory(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<EffectCountryHistory, EffectCountryHistory.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: EffectCountryHistory.()->Unit) {
        super.eq(ConcreteEffectCountryHistory(value))
    }
}

internal class AdjustableConditionCountry(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<ConditionCountry, ConditionCountry.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: ConditionCountry.()->Unit) {
        super.eq(ConcreteConditionCountry(value))
    }
}

internal class AdjustableEffectState(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<EffectState, EffectState.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: EffectState.()->Unit) {
        super.eq(ConcreteEffectState(value))
    }
}

internal class AdjustableConditionState(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<ConditionState, ConditionState.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: ConditionState.()->Unit) {
        super.eq(ConcreteConditionState(value))
    }
}

internal class AdjustableTechnologyIntStruct(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<TechnologyIntStruct, TechnologyIntStruct.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: TechnologyIntStruct.()->Unit) {
        super.eq(ConcreteTechnologyIntStruct(value))
    }
}

internal class AdjustableIdeologyIntStruct(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<IdeologyIntStruct, IdeologyIntStruct.()->Unit, Nothing>(fieldName, struct) {
    override infix fun eq(value: IdeologyIntStruct.()->Unit) {
        super.eq(ConcreteIdeologyIntStruct(value))
    }
}

internal class AdjustableImmutableStruct(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4Struct, Nothing, Nothing>(fieldName, struct) {
    override infix fun eq(value: Nothing) {
        throw UnsupportedOperationException()
    }
}

internal class AdjustableList<H: Hoi4FileRelationRight, E: Value<H>, L: Label<E>>(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<ValueList<H, E>, List<L>, Nothing>(fieldName, struct) {
    override infix fun eq(value: List<L>) {
        super.eq(ValueList(value.map { it.label }))
    }
}

internal class AdjustableVariable(fieldName: String, struct: MutableStruct) : AbstractAdjustableField<Hoi4Word, Nothing, IVariable<*, *, *, *>>(fieldName, struct) {
    override infix fun eq(value: Nothing) {
        throw UnsupportedOperationException()
    }
}
