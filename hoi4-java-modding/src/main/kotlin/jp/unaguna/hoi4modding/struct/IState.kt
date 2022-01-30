package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.AsHoi4Number
import jp.unaguna.hoi4modding.hoi4file.Hoi4Number

interface IState : AsHoi4Number {
    val stateNumber: Int

    override fun asHoi4Number(): Hoi4Number = Hoi4Number(stateNumber)
}
