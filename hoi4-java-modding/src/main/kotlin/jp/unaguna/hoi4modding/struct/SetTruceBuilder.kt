package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Number
import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.MutableStruct

class SetTruceBuilder(target: Hoi4Word, days: Hoi4Number) : MutableStruct() {
    constructor(target: String, days: Int): this(Hoi4Word(target), Hoi4Number(days))
    constructor(target: ICountry, days: Int): this(target.label, Hoi4Number(days))

    private val target = fieldFactory.adjustableCountry("target")
    private val days = fieldFactory.adjustableInt("days")

    init {
        this.target eq target
        this.days eq days
    }

    fun build(): Hoi4Struct {
        return this.toImmutable()
    }
}
