package jp.unaguna.hoi4modding.struct.vanilla

import jp.unaguna.hoi4modding.struct.IIdeology

enum class Ideologies : IIdeology {
    Democratic {
        override val ideologyName = "democratic"
    },
    Neutrality {
        override val ideologyName = "neutrality"
    },
    Fascism {
        override val ideologyName = "fascism"
    },
    Communism {
        override val ideologyName = "communism"
    },
}