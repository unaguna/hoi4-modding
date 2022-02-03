package jp.unaguna.hoi4modding.struct.common

sealed class Scope {
    object Country : Scope()
    object State : Scope()
}
