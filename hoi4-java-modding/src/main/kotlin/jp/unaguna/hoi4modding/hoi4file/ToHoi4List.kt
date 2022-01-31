package jp.unaguna.hoi4modding.hoi4file

interface ToHoi4List<E: Hoi4FileListElement> {
    fun toHoi4List(): Hoi4FileList<E>
}
