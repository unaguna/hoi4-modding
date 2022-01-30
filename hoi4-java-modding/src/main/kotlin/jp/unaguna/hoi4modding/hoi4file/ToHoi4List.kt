package jp.unaguna.hoi4modding.hoi4file

interface ToHoi4List<E: Hoi4ListElement> {
    fun toHoi4List(): Hoi4List<E>
}
