package jp.unaguna.hoi4modding.hoi4file

internal fun indentedLine(size: Int): String = INDENT_UNIT.repeat(size)
internal fun indentedLine(size: Int, line: String): String = indentedLine(size) + line

internal const val INDENT_UNIT = "\t"
