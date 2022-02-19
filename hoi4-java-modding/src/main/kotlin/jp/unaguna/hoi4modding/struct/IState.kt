package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Number
import jp.unaguna.hoi4modding.struct.common.Label

interface IState : Label<Hoi4Number> {
    val stateNumber: Int
    override val label: Hoi4Number
        get() = Hoi4Number(stateNumber)
}
