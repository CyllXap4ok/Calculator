package com.cyllxapk.calculator

fun Char.isComma() = this == Constants.res.getString(R.string.comma)[0]

fun Char.isLeftBracket() = this == Constants.res.getString(R.string.left_bracket)[0]

fun Char.isRightBracket() = this == Constants.res.getString(R.string.right_bracket)[0]

fun Char.isBracket() = this.isLeftBracket() || this.isRightBracket()

fun Char.isOperator(): Boolean {
    return this == Constants.res.getString(R.string.times)[0] ||
            this == Constants.res.getString(R.string.div)[0] ||
            this == Constants.res.getString(R.string.plus)[0] ||
            this == Constants.res.getString(R.string.minus)[0]
}

fun String.isCommaInText() = this.any { it.isComma() }

fun String.isZeroFirst() = this.firstOrNull() == Constants.res.getString(R.string._0)[0]

fun String.lastIsMathSymbol() = this.last() in Constants.mathSymbols

fun String.lastIsDigit() = this.lastOrNull()?.isDigit() == true

fun String.areMoreLeftBrackets(): Boolean {
        return this.count { it.isLeftBracket() } > this.count { it.isRightBracket() }
    }

fun String.isOperatorInText() = this.any { it.isOperator() }

fun String.isLeftBracketInText() = this.any { it.isLeftBracket() }