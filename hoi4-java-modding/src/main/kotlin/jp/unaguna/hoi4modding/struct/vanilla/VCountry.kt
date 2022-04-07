package jp.unaguna.hoi4modding.struct.vanilla

import jp.unaguna.hoi4modding.struct.ICountry

enum class VCountry : ICountry {
    ENG {
        override val tag = "ENG"
        override val countryName = "britain"
    },
    FRA {
        override val tag = "FRA"
        override val countryName = "france"
    },
    GER {
        override val tag = "GER"
        override val countryName = "germany"
    },
    HOL {
        override val tag = "HOL"
        override val countryName = "holland"
    },
    ITA {
        override val tag = "ITA"
        override val countryName = "italy"
    },
    JAP {
        override val tag = "JAP"
        override val countryName = "japan"
    },
    SOV {
        override val tag = "SOV"
        override val countryName = "Soviet"
    },
    USA {
        override val tag = "USA"
        override val countryName = "america"
    },
}