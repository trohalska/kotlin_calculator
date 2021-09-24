package com.example.caclulator

import java.lang.UnsupportedOperationException

data class Operand(val operator: String = "+", var operand: Double = 0.0) {

    operator fun plus(op: Operand): Operand {
        this.operand += op.operand
        return this
    }

    operator fun minus(op: Operand): Operand {
        this.operand -= op.operand
        return this
    }

    operator fun times(op: Operand) {
        this.operand *= op.operand
    }

    operator fun div(op: Operand) {
        if (op.operand == 0.0) {
            throw UnsupportedOperationException(EX_DIVISION_BY_ZERO)
        }
        this.operand /= op.operand
    }
}
