package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.AsHoi4Number
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileNumber

interface IState : AsHoi4Number {
    val stateNumber: Int

    override fun asHoi4Number(): Hoi4FileNumber = Hoi4FileNumber(stateNumber)
}
