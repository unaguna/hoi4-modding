package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label

interface IState : Label<Hoi4Word> {
    val stateNumber: Int
    override val label: Hoi4Word
        get() = Hoi4Word(stateNumber.toString())
}
