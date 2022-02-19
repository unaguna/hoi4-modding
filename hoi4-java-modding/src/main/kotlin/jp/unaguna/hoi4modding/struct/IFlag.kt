package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4String
import jp.unaguna.hoi4modding.struct.common.Label

interface IFlag: Label<Hoi4String> {
    val flagName: String
    override val label: Hoi4String
        get() = Hoi4String(flagName)
}
