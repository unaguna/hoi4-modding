package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationRight
import jp.unaguna.hoi4modding.hoi4file.ToHoi4FileRelationRight

interface Value<H: Hoi4FileRelationRight> : ToHoi4FileRelationRight<H> {
    override fun toHoi4FileObject(): H
}
