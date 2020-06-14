package com.fixer.app.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.roundToTwoDecimalNumbers() =
    DecimalFormat("#.##").apply { roundingMode = RoundingMode.HALF_UP }.format(this).toDouble()