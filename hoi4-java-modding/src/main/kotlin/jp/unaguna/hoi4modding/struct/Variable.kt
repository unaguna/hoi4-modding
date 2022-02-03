package jp.unaguna.hoi4modding.struct

import jp.unaguna.hoi4modding.struct.common.Hoi4Number
import jp.unaguna.hoi4modding.struct.common.Hoi4String
import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.Scope
import jp.unaguna.hoi4modding.struct.common.Value

interface IVariable<S: Scope, V: Value<*>, U, L: Label<V>> : Label<Hoi4Word> {
    val varName: String
    override val label: Hoi4Word
        get() = Hoi4Word(varName)

    fun convValue(value: U): V
}

interface CountryVariable<V: Value<*>, U, L: Label<V>> : IVariable<Scope.Country, V, U, L>
interface StateVariable<V: Value<*>, U, L: Label<V>> : IVariable<Scope.State, V, U, L>

abstract class CountryIntVariable<L: Label<Hoi4Number>> : IVariable<Scope.Country, Hoi4Number, Int, L>, CountryVariable<Hoi4Number, Int, L> {
    override fun convValue(value: Int) = Hoi4Number(value)
}
abstract class StateIntVariable<L: Label<Hoi4Number>> : IVariable<Scope.State, Hoi4Number, Int, L>, StateVariable<Hoi4Number, Int, L> {
    override fun convValue(value: Int) = Hoi4Number(value)
}
abstract class CountryStringVariable<L: Label<Hoi4String>> : IVariable<Scope.Country, Hoi4String, String, L>, CountryVariable<Hoi4String, String, L> {
    override fun convValue(value: String) = Hoi4String(value)
}
abstract class StateStringVariable<L: Label<Hoi4String>> : IVariable<Scope.State, Hoi4String, String, L>, StateVariable<Hoi4String, String, L> {
    override fun convValue(value: String) = Hoi4String(value)
}
abstract class CountryWordVariable<L: Label<Hoi4Word>> : IVariable<Scope.Country, Hoi4Word, String, L>, CountryVariable<Hoi4Word, String, L> {
    override fun convValue(value: String) = Hoi4Word(value)
}
abstract class StateWordVariable<L: Label<Hoi4Word>> : IVariable<Scope.State, Hoi4Word, String, L>, StateVariable<Hoi4Word, String, L> {
    override fun convValue(value: String) = Hoi4Word(value)
}
