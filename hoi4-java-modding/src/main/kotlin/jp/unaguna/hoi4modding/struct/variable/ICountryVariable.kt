package jp.unaguna.hoi4modding.struct.variable

import jp.unaguna.hoi4modding.struct.common.Label
import jp.unaguna.hoi4modding.struct.common.Scope
import jp.unaguna.hoi4modding.struct.common.Value

interface ICountryVariable<V: Value<*>, U, L: Label<V>> : IVariable<Scope.Country, V, U, L>
