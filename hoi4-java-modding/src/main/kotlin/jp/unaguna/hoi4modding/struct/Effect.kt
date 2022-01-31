package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct
import jp.unaguna.hoi4modding.struct.common.Scope

abstract class Effect<S: Scope>: AbstractStruct() {
    val setGlobalFlag = adjustableFlag("set_global_flag")
}
