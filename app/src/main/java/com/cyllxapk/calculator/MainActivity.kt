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
    private lateinit var screenInput: ScreenInput

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        Constants.res = applicationContext.resources
        screenInput = ScreenInput(applicationContext)
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    fun onClickDigit(button: View) {
        button as Button
        screenInput.printDigit(button.text[0])
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickOperator(button: View) {
        button as Button
        screenInput.printOperator(button.text[0])
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickComma(button: View) {
        button as Button
        screenInput.printComma(button.text[0])
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickLeftBracket(button: View) {
        button as Button
        screenInput.printLeftBracket(button.text[0])
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickRightBracket(button: View) {
        button as Button
        screenInput.printRightBracket(button.text[0])
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickDelete(v: View) {
        screenInput.delete()
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickClear(v: View) {
        screenInput.clear()
        screenInput.setText(mainView.tInput, mainView.tResult)
    }

    fun onClickResult(v: View) {
        screenInput.result(mainView.tInput, mainView.tResult)
    }
}