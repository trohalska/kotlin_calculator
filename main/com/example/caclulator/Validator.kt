package com.example.caclulator

import java.util.regex.Matcher
import java.util.regex.Pattern

object Validator {

    fun validateCalculatorExpression(input: String) {
        input.containsEquation()
        input.containsForbiddenSymbols()
        input.containsUnequalBrackets()
        input.containsEmptyOperandOrExtraOperator()
    }

    private fun String.containsEquation() {
        if (this.isEmpty()) {
            throw IllegalArgumentException(EX_IS_EMPTY);
        }
    }

    private fun String.containsForbiddenSymbols() {
        this.forEach { c ->
            if (!ALLOWED_CHARACTERS.contains(c)) {
                throw IllegalArgumentException(EX_CONTAINS_FORBIDDEN_SYMBOLS);
            }
        }
    }

    private fun String.containsUnequalBrackets() {
        val first = this.count{ it == '(' }
        val second = this.count{ it == ')' }
        if (first != second) {
            throw IllegalArgumentException(EX_CONTAINS_UNEQUAL_BRACKETS);
        }
    }

    private fun String.containsEmptyOperandOrExtraOperator() {
        val p: Pattern = Pattern.compile("([+\\-*/]{2,})")
        val m: Matcher = p.matcher(this)
        if (m.find() || SUPPORTED_OPERATIONS.contains(this.last()) || this.contains("()")) {
            throw IllegalArgumentException(EX_CONTAINS_EMPTY_OPERAND_OR_OPERATOR);
        }
    }
}