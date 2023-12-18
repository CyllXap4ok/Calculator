package com.cyllxapk.calculator

import android.content.res.Resources

object Constants {
    lateinit var res: Resources
    val mathSymbols = hashSetOf<Char>()

    init {
        if (::res.isInitialized) {
            fill(res)
        }
    }

    private fun fill(res: Resources) {
        mathSymbols.apply {
            add(res.getString(R.string.times)[0])
            add(res.getString(R.string.div)[0])
            add(res.getString(R.string.plus)[0])
            add(res.getString(R.string.minus)[0])
            add(res.getString(R.string.left_bracket)[0])
            add(res.getString(R.string.right_bracket)[0])
        }
    }
}