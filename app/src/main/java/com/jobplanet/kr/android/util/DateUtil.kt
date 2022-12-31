package com.jobplanet.kr.android.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val inputPattern = "yyyy-MM-dd'T'HH:mm:ss"
    const val outputPattern = "yyyy.MM"

    fun getDateString(utcDateString: String?, inputPtn: String = inputPattern, outputPtn: String = outputPattern): String {

        // 한국시간을 서버에서 받아옵니다.
        val inputDateFormat = SimpleDateFormat(inputPtn, Locale.KOREA)
        var inputDate = inputDateFormat.parse(utcDateString)

        val localDate = getLocalDateFromUTCDate(inputDate)

        inputDate = inputDateFormat.parse(inputDateFormat.format(localDate))

        // 그걸 현지 시간로 바꿈
        val dateFormat = SimpleDateFormat(outputPtn, Locale.getDefault())
        val currentDateString = dateFormat.format(inputDate)

        return currentDateString
    }

    private fun getLocalDateFromUTCDate(utcDate : Date) : Date {
        val cal = Calendar.getInstance()
        cal.time = utcDate
        cal.add(Calendar.HOUR, 9)

        return cal.time
    }
}