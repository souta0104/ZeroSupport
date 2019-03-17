package com.risakokato.zerosupport.ext

import java.text.SimpleDateFormat
import java.util.*

fun Date.getToday(): String =
        SimpleDateFormat("yyyy-M-d", Locale.JAPANESE).format(this)
