package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.MutableStruct

abstract class AiChance : MutableStruct() {
    val base = fieldFactory.adjustableNumber("base")
    val factor = fieldFactory.adjustableNumber("factor")
    val modifier = fieldFactory.adjustableAiChanceModifier("modifier")
}

class ConcreteAiChance(initialize: AiChance.()->Unit) : AiChance() {
    init {
        initialize(this)
    }
}
