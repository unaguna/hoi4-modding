package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Struct
import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.MutableStruct

class SetPoliticsBuilder(rulingParty: Hoi4Word) : MutableStruct() {
    constructor(rulingParty: String): this(Hoi4Word(rulingParty))
    constructor(rulingParty: IIdeology): this(rulingParty.label)

    private val rulingParty = fieldFactory.adjustableIdeology("ruling_party")
    val electionsAllowed = fieldFactory.adjustableBool("elections_allowed")
    val lastElection = fieldFactory.adjustableDate("last_election")
    val electionFrequency = fieldFactory.adjustableInt("election_frequency")

    init {
        this.rulingParty eq rulingParty
    }

    fun build(): Hoi4Struct {
        return this.toImmutable()
    }
}
