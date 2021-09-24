package com.example.caclulator

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.math.floor
import kotlin.math.roundToInt

class Calculator: KotlinCalculator {
    private lateinit var value: String

    override fun calculate(input: String): String {

        removeWhitespacesAndValidate(input)
        evaluateBrackets()
        val res: Double = parseAndEvaluateWithoutBrackets()

        return prettifyResultToIntOrRoundToFourDigitsAfterComma(res)
    }

    private fun removeWhitespacesAndValidate(input: String) {
        value = input.filter { !it.isWhitespace() }
        Validator.validateCalculatorExpression(value)
    }

    private fun evaluateBrackets() {
        while (value.contains('(')) {
            val regex = "(\\([^()]*\\))".toRegex()
            val expr = regex.find(value)!!.value
            value = regex.replaceFirst(value, calculate(expr.substring(1, expr.length - 1)))
        }
    }

    private fun parseAndEvaluateWithoutBrackets(): Double {
        val list: List<Operand> = parseEquationToList(value)
        val ev = Evaluator()
        return ev.evaluate(list).operand
    }

    private fun prettifyResultToIntOrRoundToFourDigitsAfterComma(d: Double): String {
        if (floor(d) == d) {
            return "${d.toInt()}"
        }
        return "${((d * 10000).roundToInt() / 10000.0)}"
    }

    private fun parseEquationToList(s: String): List<Operand> {
        val res = ArrayList<Operand>()

        val p: Pattern = Pattern.compile("([+\\-*/]?)(\\d+(\\.\\d+)?)")
        val m: Matcher = p.matcher(s)
        while (m.find()) {
            val item: String = m.group()
            val firstChar = item[0]
            when {
                firstChar.isDigit() -> res.add(
                    Operand(
                    operand = item.toDouble())
                )
                firstChar in SUPPORTED_OPERATIONS -> res.add(
                    Operand(
                    operator = firstChar.toString(),
                    operand = item.substring(1).toDouble())
                )
            }
        }
        return res
    }

}