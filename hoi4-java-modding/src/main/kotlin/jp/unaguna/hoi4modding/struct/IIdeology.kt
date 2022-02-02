package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label

interface IIdeology : Label<Hoi4Word> {
    val ideologyName: String
    override val label: Hoi4Word
        get() = Hoi4Word(ideologyName)
}
