package com.cyllxapk.calculator

import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.ArithmeticException

class ScreenInput(context: Context) {
    private val res: Resources = context.resources
    private var inputText: String = ""
    private var resultText: String = ""
    private var calculatedText: String = ""
    private var indexOfLastMathSymbol = -1

    fun printDigit(btnText: Char) {
        calculatedText = findLastNumber(inputText)
        inputText = if (!calculatedText.isZeroFirst() || calculatedText.isCommaInText()) {
            inputText + btnText
        } else {
            inputText.dropLast(1) + btnText
        }
    }

    fun printOperator(btnText: Char) {
        if (inputText.lastIsDigit() || inputText.lastOrNull()?.isRightBracket() == true) {
            inputText += btnText
            indexOfLastMathSymbol = inputText.lastIndex
        } else if (btnText == res.getString(R.string.minus)[0]) {
            if (inputText.isEmpty() || inputText.last().isLeftBracket()) {
                inputText += btnText
                indexOfLastMathSymbol = inputText.lastIndex
            }
        }
    }

    fun printComma(btnText: Char) {
        calculatedText = findLastNumber(inputText)
        if (inputText.lastIsDigit() && !calculatedText.isCommaInText()) {
            inputText += btnText
        }
    }

    fun printLeftBracket(btnText: Char) {
        if (inputText.isEmpty()) {
            inputText += btnText
            indexOfLastMathSymbol ++
        } else {
            if (!inputText.last().isComma()) {
                inputText += btnText
                indexOfLastMathSymbol ++
            }
        }
    }

    fun printRightBracket(btnText: Char) {
        if (inputText.areMoreLeftBrackets()) {
            inputText += btnText
            indexOfLastMathSymbol ++
        }
    }

    fun delete() {
        if (inputText.isNotEmpty()) {
            if (inputText.lastIsMathSymbol()) {
                indexOfLastMathSymbol = findLastMathSymbolIndex(inputText)
            }
            inputText = inputText.dropLast(1)
            resultText = ""
        }
    }

    fun clear() {
        inputText = ""
        resultText = ""
        indexOfLastMathSymbol = -1
    }

    fun result(input: TextView, result: TextView) {
        if (result.text.toString().lastIsDigit()) {
            inputText = resultText
            input.text = inputText
            result.text = ""
            indexOfLastMathSymbol = -1
        }
    }

    fun setText(input: TextView, output: TextView) {
        if (inputText.isOperatorInText() || inputText.isLeftBracketInText()) {

            try {
                val stringFormatExpression = inputText
                    .replace(res.getString(R.string.times)[0], '*')
                    .replace(res.getString(R.string.div)[0], '/')
                    .replace(res.getString(R.string.comma)[0], '.')

                val result = ExpressionBuilder(stringFormatExpression).build().evaluate()

                resultText = if (result.toLong().toDouble() == result) {
                    "%.0f".format(result)
                } else {
                    "$result".replace('.', res.getString(R.string.comma)[0])
                }
            } catch (e: ArithmeticException) {
                resultText = res.getString(R.string.arithmetical_exception)
            } catch (e: Exception) {
                resultText = ""
            }

        }

        input.text = inputText
        output.text = resultText
    }

    private fun findLastNumber(str: String): String {
        var num = ""
        for (i in str.length - 1 downTo 0) {
            if (str[i].isDigit() || str[i].isComma()) num = str[i] + num
        }
        return num
    }

    private fun findLastMathSymbolIndex(str: String) = str.indexOfLast { it in Constants.mathSymbols }

}