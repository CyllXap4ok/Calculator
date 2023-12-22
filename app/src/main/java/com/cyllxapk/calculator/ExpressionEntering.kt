package com.cyllxapk.calculator

import android.content.res.Resources
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class ExpressionEntering(val res: Resources) {

    private var expressionInput: String = ""
    private var expressionResult: String = ""

    fun printDigit(btnText: String, inputView: TextView, resultView: TextView) {

        findLastNumber(expressionInput).let {

            expressionInput =
                if (!it.isZeroFirst() || it.containsComma()) expressionInput + btnText
                else expressionInput.dropLast(1) + btnText
        }

        setText(inputView, resultView)
    }

    fun printOperator(btnText: String, inputView: TextView, resultView: TextView) {

        expressionInput.let {

            if (it.lastIsDigit() || it.lastIsRightBracket() ||
                btnText == res.getString(R.string.minus) && (it.isEmpty() || it.lastIsLeftBracket())
                ) {

                expressionInput += btnText
            }
        }

        setText(inputView, resultView)
    }

    fun printComma(btnText: String, inputView: TextView, resultView: TextView) {

        findLastNumber(expressionInput).let {
            if (it.lastIsDigit() && !it.containsComma()) expressionInput += btnText
        }

        setText(inputView, resultView)
    }

    fun printLeftBracket(btnText: String, inputView: TextView, resultView: TextView) {

        expressionInput.let { if (it.isEmpty() || !it.lastIsComma()) expressionInput += btnText }

        setText(inputView, resultView)
    }

    fun printRightBracket(btnText: String, inputView: TextView, resultView: TextView) {

        if (expressionInput.areMoreLeftBrackets()) expressionInput += btnText

        setText(inputView, resultView)
    }

    fun deleteLast(inputView: TextView, resultView: TextView) {

        expressionInput.let {

            if (it.isNotEmpty()) {

                expressionInput = expressionInput.dropLast(1)
                expressionResult = ""

            }
        }

        setText(inputView, resultView)
    }

    fun clearAll(inputView: TextView, resultView: TextView) {

        expressionInput = ""
        expressionResult = ""

        setText(inputView, resultView)
    }

    fun setResult(inputView: TextView, resultView: TextView) {

        if (expressionResult.lastIsDigit()) {

            expressionInput = expressionResult
            expressionResult = ""

        }

        setText(inputView, resultView)
    }

    private fun setText(inputView: TextView, resultView: TextView) {

        if (expressionInput.containsOperator() || expressionInput.containsLeftBracket()) {
            try {

                val stringFormatExpression = expressionInput
                    .replace(res.getString(R.string.times)[0], '*')
                    .replace(res.getString(R.string.div)[0], '/')
                    .replace(res.getString(R.string.comma)[0], '.')

                val result = ExpressionBuilder(stringFormatExpression).build().evaluate()

                expressionResult =
                    if (result.toLong().toDouble() == result) "%.0f".format(result)
                    else "$result".replace('.', res.getString(R.string.comma)[0])

            } catch (e: ArithmeticException) {
                expressionResult = res.getString(R.string.arithmetical_exception)
            } catch (e: Exception) {
                expressionResult = ""
            }
        }

        inputView.text = expressionInput
        resultView.text = expressionResult
    }

    private fun findLastNumber(str: String): String {

        var num = ""

        for (i in str.length - 1 downTo 0) {
            if (str[i].isDigit() || str[i].isComma()) num = str[i] + num
            else break
        }

        return num
    }

    private fun Char.isMathSymbol() = !this.isDigit() && !this.isComma()

    private fun Char.isOperator() = this.isMathSymbol() && !this.isBracket()

    private fun String.containsOperator() = this.any { it.isOperator() }

    private fun Char.isBracket() = this.isLeftBracket() || this.isRightBracket()

    private fun Char.isLeftBracket() = this == res.getString(R.string.left_bracket)[0]

    private fun Char.isRightBracket() = this == res.getString(R.string.right_bracket)[0]

    private fun String.lastIsLeftBracket() = this.lastOrNull()?.isLeftBracket()?: false

    private fun String.lastIsRightBracket() = this.lastOrNull()?.isRightBracket()?: false

    private fun Char.isComma() = this == res.getString(R.string.comma)[0]

    private fun String.lastIsComma() = this.lastOrNull()?.isComma()?: false

    private fun String.containsComma() = this.any { it.isComma() }

    private fun String.isZeroFirst() = this.firstOrNull() == res.getString(R.string._0)[0]

    private fun String.lastIsDigit() = this.lastOrNull()?.isDigit()?: false

    private fun String.containsLeftBracket() = this.any { it.isLeftBracket() }

    private fun String.areMoreLeftBrackets(): Boolean {
        return this.count { it.isLeftBracket() } > this.count { it.isRightBracket() }
    }

}