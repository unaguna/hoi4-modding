package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4String
import jp.unaguna.hoi4modding.struct.common.Label

interface IIdeology : Label<Hoi4String> {
    val ideologyName: String
    override val label: Hoi4String
        get() = Hoi4String(ideologyName)
}
