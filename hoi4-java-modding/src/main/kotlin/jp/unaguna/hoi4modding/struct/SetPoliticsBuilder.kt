package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.AbstractStruct
import jp.unaguna.hoi4modding.struct.common.Hoi4String

class SetPoliticsBuilder(rulingParty: Hoi4String) : AbstractStruct() {
    constructor(rulingParty: String): this(Hoi4String(rulingParty))
    constructor(rulingParty: IIdeology): this(rulingParty.label)

    private val rulingParty = fieldFactory.adjustableIdeology("ruling_party")
    val electionsAllowed = fieldFactory.adjustableBool("elections_allowed")
    val lastElection = fieldFactory.adjustableDate("last_election")
    val electionFrequency = fieldFactory.adjustableInt("election_frequency")

    init {
        this.rulingParty eq rulingParty
    }

    fun build(): AbstractStruct {
        return this.toImmutable()
    }
}
