package jp.unaguna.hoi4modding.struct.vanilla

import jp.unaguna.hoi4modding.struct.IGfx

enum class VEventPicture : IGfx {
    GenericSignTreaty1 {
        override val gfxId = "GFX_report_event_generic_sign_treaty1"
    },
    GenericSignTreaty2 {
        override val gfxId = "GFX_report_event_generic_sign_treaty2"
    },
    JapaneseChineseSurrender {
        override val gfxId = "GFX_report_event_japanese_chinese_surrender"
    },
}
