package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileList
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileNumber
import jp.unaguna.hoi4modding.hoi4file.ToHoi4FileRelationRight
import jp.unaguna.hoi4modding.hoi4file.numberList

data class Color(val red: Int, val green: Int, val blue: Int): ToHoi4FileRelationRight<Hoi4FileList<Hoi4FileNumber>> {
    override fun toHoi4FileObject(): Hoi4FileList<Hoi4FileNumber> {
        return numberList {
            append(red)
            append(green)
            append(blue)
        }
    }
}
