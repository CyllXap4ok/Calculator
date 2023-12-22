package com.cyllxapk.calculator

import android.content.res.Resources

class Component {

    private fun getScreenInput(res: Resources): ExpressionEntering {
        return ExpressionEntering(res)
    }

    fun inject(mainActivity: MainActivity) {
        mainActivity.expressionEntering = getScreenInput(mainActivity.resources)
    }

}