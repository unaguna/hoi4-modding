package jp.unaguna.hoi4modding.struct.variable

import jp.unaguna.hoi4modding.struct.common.Hoi4Number
import jp.unaguna.hoi4modding.struct.common.Hoi4String
import jp.unaguna.hoi4modding.struct.common.Hoi4Word
import jp.unaguna.hoi4modding.struct.common.Label


abstract class CountryIntVariable<L: Label<Hoi4Number>> : ICountryVariable<Hoi4Number, Int, L> {
    override fun convValue(value: Int) = Hoi4Number(value)
}
abstract class StateIntVariable<L: Label<Hoi4Number>> : IStateVariable<Hoi4Number, Int, L> {
    override fun convValue(value: Int) = Hoi4Number(value)
}
abstract class CountryStringVariable<L: Label<Hoi4String>> : ICountryVariable<Hoi4String, String, L> {
    override fun convValue(value: String) = Hoi4String(value)
}
abstract class StateStringVariable<L: Label<Hoi4String>> : IStateVariable<Hoi4String, String, L> {
    override fun convValue(value: String) = Hoi4String(value)
}
abstract class CountryWordVariable<L: Label<Hoi4Word>> : ICountryVariable<Hoi4Word, String, L> {
    override fun convValue(value: String) = Hoi4Word(value)
}
abstract class StateWordVariable<L: Label<Hoi4Word>> : IStateVariable<Hoi4Word, String, L> {
    override fun convValue(value: String) = Hoi4Word(value)
}
