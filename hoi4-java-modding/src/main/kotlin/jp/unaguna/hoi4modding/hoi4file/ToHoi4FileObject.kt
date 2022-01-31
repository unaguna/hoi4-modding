package jp.unaguna.hoi4modding.hoi4file

interface ToHoi4FileObject<H: Hoi4FileObject> {
    fun toHoi4FileObject(): H
}

interface ToHoi4FileRelationRight<H: Hoi4FileRelationRight> : ToHoi4FileObject<H> {
    override fun toHoi4FileObject(): H
}
