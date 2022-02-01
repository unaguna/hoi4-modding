package jp.unaguna.hoi4modding.struct.common

open class MutableStruct : Hoi4Struct {
    override val parameterList: MutableList<Parameter> = mutableListOf()
    protected val fieldFactory by lazy { FieldFactory(this) }

    internal fun addParameter(parameter: Parameter) {
        parameterList.add(parameter)
    }

    internal fun addParameters(parameter: List<Parameter>) {
        parameterList.addAll(parameter)
    }
}
