package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.MutableStruct

class CreateWargoalBuilder(target: Hoi4Word, type: Hoi4Word) : MutableStruct() {
    constructor(target: String, type: String): this(Hoi4Word(target), Hoi4Word(type))
    constructor(target: String, type: IWargoal): this(Hoi4Word(target), type.label)
    constructor(target: ICountry, type: IWargoal): this(target.label, type.label)

    private val target = fieldFactory.adjustableCountry("target")
    private val type = fieldFactory.adjustableWargoal("type")

    init {
        this.target eq target
        this.type eq type
    }

    fun build(): Hoi4Struct {
        return this.toImmutable()
    }
}
