package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.hoi4file.Hoi4List
import jp.unaguna.hoi4modding.hoi4file.Hoi4Relation
import jp.unaguna.hoi4modding.hoi4file.ToHoi4List
import jp.unaguna.hoi4modding.hoi4file.relationList

abstract class EffectCountry: ToHoi4List<Hoi4Relation> {
    private val contentList: MutableList<Hoi4Relation> = mutableListOf()

    var setResearchSlots: Int? = null

    override fun toHoi4List(): Hoi4List<Hoi4Relation> {
        return relationList {
            setResearchSlots?.let { "set_research_slots" eq it }

            appendAll(contentList)
        }
    }

    fun iif(limit: ConditionCountry.()->Unit, effect: EffectCountry.()->Unit) {
        contentList.addAll(relationList {
            "if" eq relationList {
                "limit" eq ConcreteConditionCountry(limit)
                appendAll(ConcreteEffectCountry(effect).toHoi4List())
            }
        })
    }
}

private class ConcreteEffectCountry(initialize: EffectCountry.() -> Unit): EffectCountry() {
    init {
        initialize(this)
    }
}
