package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.Hoi4Number
import jp.unaguna.hoi4modding.struct.common.TypedAdjustableFieldStruct

abstract class TechnologyIntStruct : TypedAdjustableFieldStruct<ITechnology, Hoi4Number, Int, Nothing>() {
    override fun fieldName(field: ITechnology) = field.technologyName

    override fun field(field: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return fieldFactory.adjustableInt(field)
    }

    fun tech(tech: ITechnology): AdjustableField<Hoi4Number, Int, Nothing> {
        return field(tech)
    }

    fun tech(tech: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return field(tech)
    }
}

internal class ConcreteTechnologyIntStruct(initialize: TechnologyIntStruct.() -> Unit): TechnologyIntStruct() {
    init {
        initialize(this)
    }
}
