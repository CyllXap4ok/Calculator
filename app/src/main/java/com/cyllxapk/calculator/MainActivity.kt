package com.cyllxapk.calculator

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.cyllxapk.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainView: ActivityMainBinding
    lateinit var expressionEntering: ExpressionEntering

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        Component().inject(this)

        mainView.apply {

            bComma.setOnClickListener {
                expressionEntering.printComma(bComma.text.toString(), expressionInput, expressionResult)
            }

            bLeftBracket.setOnClickListener {
                expressionEntering.printLeftBracket(bLeftBracket.text.toString(), expressionInput, expressionResult)
            }

            bRightBracket.setOnClickListener {
                expressionEntering.printRightBracket(bRightBracket.text.toString(), expressionInput, expressionResult)
            }

            bDelete.setOnClickListener {
                expressionEntering.deleteLast(expressionInput, expressionResult)
            }

            bClear.setOnClickListener {
                expressionEntering.clearAll(expressionInput, expressionResult)
            }

            bResult.setOnClickListener {
                expressionEntering.setResult(expressionInput, expressionResult)
            }

        }
    }

    fun onClickDigit(button: View) {
        button as Button
        expressionEntering.printDigit(button.text.toString(), mainView.expressionInput, mainView.expressionResult)
    }

    fun onClickOperator(button: View) {
        button as Button
        expressionEntering.printOperator(button.text.toString(), mainView.expressionInput, mainView.expressionResult)
    }

}