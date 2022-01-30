package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.AsHoi4String
import jp.unaguna.hoi4modding.hoi4file.Hoi4String

interface IOob: AsHoi4String {
    val oobName: String

    override fun asHoi4String(): Hoi4String = Hoi4String(oobName)
}
