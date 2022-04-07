package jp.unaguna.hoi4modding.struct.vanilla

import jp.unaguna.hoi4modding.struct.IWargoal

enum class VWargoal : IWargoal {
    AnnexEverything {
        override val wargoalId = "annex_everything"
    },
}
