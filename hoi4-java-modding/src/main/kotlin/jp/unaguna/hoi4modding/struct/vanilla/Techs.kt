package jp.unaguna.hoi4modding.struct.vanilla

import jp.unaguna.hoi4modding.struct.ITechnology

enum class Techs : ITechnology {
    InfantryWeapons {
        override val technologyName = "infantry_weapons"
    },
    MotorisedInfantry {
        override val technologyName = "motorised_infantry"
    },
    TechSupport {
        override val technologyName = "tech_support"
    },
    TechRecon {
        override val technologyName = "tech_recon"
    },
    FuelSilos {
        override val technologyName = "fuel_silos"
    },
    BasicTrain {
        override val technologyName = "basic_train"
    },
}