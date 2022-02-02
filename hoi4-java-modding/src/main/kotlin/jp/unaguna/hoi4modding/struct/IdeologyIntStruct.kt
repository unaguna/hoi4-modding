package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.Hoi4Number
import jp.unaguna.hoi4modding.struct.common.TypedAdjustableFieldStruct

abstract class IdeologyIntStruct : TypedAdjustableFieldStruct<IIdeology, Hoi4Number, Int, Nothing>() {
    override fun fieldName(field: IIdeology) = field.ideologyName

    override fun field(field: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return fieldFactory.adjustableInt(field)
    }

    fun ideology(ideology: IIdeology): AdjustableField<Hoi4Number, Int, Nothing> {
        return field(ideology)
    }

    fun ideology(ideology: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return field(ideology)
    }
}

internal class ConcreteIdeologyIntStruct(initialize: IdeologyIntStruct.() -> Unit): IdeologyIntStruct() {
    init {
        initialize(this)
    }
}
