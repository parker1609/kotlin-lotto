package me.parker.nextstep.kotlinlotto.calculator

object StringCalculator {

    private const val DEFAULT_SEPARATOR_COMMA = ","
    private const val DEFAULT_SEPARATOR_COLON = ":"

    private const val CUSTOM_SEPARATOR_REGEX = "//(.)\n(.*)"

    fun add(input: String): Int {
        if (input.isEmpty()) return 0

        val matchedResult = Regex(CUSTOM_SEPARATOR_REGEX).find(input)
        val customSeparator = matchedResult?.groupValues?.get(1)
        val textAfterNewline = matchedResult?.groupValues?.get(2)

        if (!customSeparator.isNullOrEmpty() && !textAfterNewline.isNullOrEmpty()) {
            return add(textAfterNewline, DEFAULT_SEPARATOR_COMMA, DEFAULT_SEPARATOR_COLON, customSeparator)
        }

        return add(input, DEFAULT_SEPARATOR_COMMA, DEFAULT_SEPARATOR_COLON)
    }

    private fun add(formula: String, vararg separators: String) =
        formula.split(*separators)
            .sumOf {
                val number = it.toInt()
                if (number < 0) throw RuntimeException("음수는 입력할 수 없습니다.")
                number
            }
}
