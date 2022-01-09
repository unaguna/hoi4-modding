package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4Object

interface ToFile {
    fun fileList(): List<Pair<String, Hoi4Object>>
}
