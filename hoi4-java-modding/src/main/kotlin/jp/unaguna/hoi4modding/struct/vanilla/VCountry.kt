package jp.unaguna.hoi4modding.struct.vanilla

import jp.unaguna.hoi4modding.struct.ICountry

enum class VCountry : ICountry {
    JAP {
        override val tag = "JAP"
        override val countryName = "japan"
    },
}