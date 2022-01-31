package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationRight
import jp.unaguna.hoi4modding.hoi4file.ToHoi4FileRelationRight

interface Value : ToHoi4FileRelationRight<Hoi4FileRelationRight> {
    override fun toHoi4FileObject(): Hoi4FileRelationRight
}
