package com.rino.popularmovies.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toStringFormat(format: String = "dd.MM.yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}
