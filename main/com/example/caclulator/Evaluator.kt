package com.example.caclulator

class Evaluator {

    fun evaluate(expression: List<Operand>): Operand {

        for (i in expression.lastIndex downTo 0) {
            val item = expression[i]
            if (item.operator in "*/" && i == 0) {
                throw IllegalArgumentException(EX_CONTAINS_EMPTY_OPERAND_OR_OPERATOR)
            }
            when (item.operator) {
                "*" -> expression[i - 1] * item
                "/" -> expression[i - 1] / item
            }
        }

        val res: Operand = expression.fold(Operand(operand = 0.0)) { it, next ->
            when(next.operator) {
                "+" -> it + next
                "-" -> it - next
                else -> it
            }
        }
        return res
    }

}