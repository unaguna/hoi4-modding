package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4List
import jp.unaguna.hoi4modding.hoi4file.Hoi4Relation
import jp.unaguna.hoi4modding.hoi4file.ToHoi4List
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class ConditionCountry: ToHoi4List<Hoi4Relation> {
    private val contentList: MutableList<Hoi4Relation> = mutableListOf()

    // TODO: 等しいだけでなく、以下・以上も指定できるように
    var originalResearchSlots: Int? = null

    override fun toHoi4List(): Hoi4List<Hoi4Relation> {
        return relationList {
            originalResearchSlots?.let { "original_research_slots" eq it }

            appendAll(contentList)
        }
    }
}

internal class ConcreteConditionCountry(initialize: ConditionCountry.() -> Unit): ConditionCountry() {
    init {
        initialize(this)
    }
}