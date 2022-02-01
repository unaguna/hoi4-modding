package jp.unaguna.hoi4modding.struct.common

class ImmutableStruct(parameterList: List<Parameter>) : AbstractStruct() {
    init {
        this.addParameters(parameterList)
    }
}
