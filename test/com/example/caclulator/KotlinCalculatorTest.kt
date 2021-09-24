package com.example.caclulator

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class KotlinCalculatorTest : StringSpec({
    val calc = Calculator()

    "24-12.2" { calc.calculate("24-12.2") shouldBe "11.8" }
    "24+12.2" { calc.calculate("24+12.2") shouldBe "36.2" }
    "24*2" { calc.calculate("24*2") shouldBe "48" }
    "24/2" { calc.calculate("24/2") shouldBe "12" }

    "+2+2" { calc.calculate("+2+2") shouldBe "4" }
    "-1-2" { calc.calculate("-1-2") shouldBe "-3" }

    "1-2+3" { calc.calculate("1-2+3") shouldBe "2" }
    "-1+2+3" { calc.calculate("-1+2+3") shouldBe "4" }
    "2*5/3" { calc.calculate("2*5/3") shouldBe "3.3333" }
    "2/3" { calc.calculate("2/3") shouldBe "0.6667" }

    "0/3" { calc.calculate("0/3") shouldBe "0" }

    "(2+2)*3" { calc.calculate("(2+2)*3") shouldBe "12" }
    "2+2*5/3" { calc.calculate("2+2*5/3") shouldBe "5.3333" }
    "1-3*(5/2)" { calc.calculate("1-3*(5/2)") shouldBe "-6.5" }

    "-(+2*2)" { calc.calculate("-(+2*2)") shouldBe "-4" }
    "+(-2*2)" { calc.calculate("-(+2*2)") shouldBe "-4" }

    "complex" { calc.calculate("-((1+2.9)*(3+4.56757657))/(-5.0+6.567)+0.1") shouldBe "-18.7345" }

    "Division by zero" {
        val exception = shouldThrowExactly<UnsupportedOperationException> {
            calc.calculate("3/0")
        }
        exception.message shouldBe EX_DIVISION_BY_ZERO
    }
    "Unknown expression symbols" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("(2+2)s*3")
        }
        exception.message shouldBe EX_CONTAINS_FORBIDDEN_SYMBOLS
    }
    "Unknown expression symbols ','" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("(2,2+2)*3,1")
        }
        exception.message shouldBe EX_CONTAINS_FORBIDDEN_SYMBOLS
    }
    "Empty argument" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("2+2+")
        }
        exception.message shouldBe EX_CONTAINS_EMPTY_OPERAND_OR_OPERATOR
    }
    "Empty argument (expression start with '*' or '/'" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("*2+2")
        }
        exception.message shouldBe EX_CONTAINS_EMPTY_OPERAND_OR_OPERATOR
    }
    "Extra operator" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("2+2+*3")
        }
        exception.message shouldBe EX_CONTAINS_EMPTY_OPERAND_OR_OPERATOR
    }
    "Empty expression" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("")
        }
        exception.message shouldBe EX_IS_EMPTY
    }
    "Expression with whitespaces only" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("         \t \n \r  ")
        }
        exception.message shouldBe EX_IS_EMPTY
    }

    "Unequal number of brackets" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("(2+20)-3))")
        }
        exception.message shouldBe EX_CONTAINS_UNEQUAL_BRACKETS
    }
    "Empty brackets" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            calc.calculate("((2+20)-3)()")
        }
        exception.message shouldBe EX_CONTAINS_EMPTY_OPERAND_OR_OPERATOR
    }

})
