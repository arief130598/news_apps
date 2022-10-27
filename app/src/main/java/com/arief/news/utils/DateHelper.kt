package com.arief.news.utils

import android.annotation.SuppressLint
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    @SuppressLint("SimpleDateFormat")
    fun changeFormatTime(time: String): String {
        val date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.US)
            simpleDateFormat.parse(time)
        } else {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US)
            simpleDateFormat.parse(time.replace("Z", "+000"))
        }

        return SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date!!)
    }
}