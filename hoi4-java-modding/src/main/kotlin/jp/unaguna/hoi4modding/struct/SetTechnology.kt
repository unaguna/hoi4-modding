package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct
import jp.unaguna.hoi4modding.struct.common.AdjustableField
import jp.unaguna.hoi4modding.struct.common.Hoi4Number

abstract class SetTechnology : AbstractStruct() {
    fun tech(tech: ITechnology): AdjustableField<Hoi4Number, Int, Nothing> {
        return fieldFactory.adjustableInt(tech.technologyName)
    }
    fun tech(tech: String): AdjustableField<Hoi4Number, Int, Nothing> {
        return fieldFactory.adjustableInt(tech)
    }
}

internal class ConcreteSetTechnology(initialize: SetTechnology.() -> Unit): SetTechnology() {
    init {
        initialize(this)
    }
}
