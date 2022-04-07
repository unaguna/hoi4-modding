package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.MutableStruct

class FireCountryEventBuilder(eventId: Hoi4Word) : MutableStruct() {
    constructor(eventId: String): this(Hoi4Word(eventId))
    constructor(event: ICountryEvent): this(event.label)

    private val id = fieldFactory.adjustableCountryEvent("id")
    val days = fieldFactory.adjustableInt("days")
    val hours = fieldFactory.adjustableInt("hours")
    val random = fieldFactory.adjustableInt("random")
    val randomDays = fieldFactory.adjustableInt("random_days")

    init {
        id eq eventId
    }

    fun build(): Hoi4Struct {
        return this.toImmutable()
    }
}
