package jp.unaguna.hoi4modding.struct.common

import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelation
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationEq
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationGt
import jp.unaguna.hoi4modding.hoi4file.Hoi4FileRelationLt
import jp.unaguna.hoi4modding.hoi4file.ToHoi4FileObject

data class Parameter(
    val field: Field<*>,
    val operator: Operator,
    val value: Value,
) : ToHoi4FileObject<Hoi4FileRelation> {

    override fun toHoi4FileObject(): Hoi4FileRelation {
        return when(operator) {
            Operator.EQ -> Hoi4FileRelationEq(field.fieldName, value)
            Operator.LT -> Hoi4FileRelationLt(field.fieldName, value)
            Operator.GT -> Hoi4FileRelationGt(field.fieldName, value)
        }
    }

}

enum class Operator {
    EQ, LT, GT,
}
