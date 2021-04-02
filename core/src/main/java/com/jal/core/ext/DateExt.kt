package com.jal.core.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 *
 * @author: aljiang
 * @date: 2021/4/2 9:54
 * @desc:
 */

enum class FormatType {
    yyyy, yyyyMM, yyyyMMdd, yyyyMMddHHmm, yyyyMMddHHmmss, MMdd, HHmm, MM, dd, MMddHHmm, HHmmss
}

fun Long.dateFormat(type: FormatType): String {
    return Date(this).format(type)
}

fun Date.format(type: FormatType): String {
    val sdf = getSimpleDateFormat(type)
    return sdf.format(this)
}

/**
 * 增/减 offset 后的日期
 */
fun Date.addAndSubtractDate(offset: Int, unit: Int): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    if (unit == Calendar.MONTH) {
        calendar[Calendar.DATE] = 1
    }
    calendar[unit] = calendar[unit] + offset
    return calendar.time
}

/**
 * 计算两个日期之间相差的天数
 */
fun Date.daysBetween(bDate: Date): Int {
    var smdate = this
    var bdate = bDate
    val sdf = SimpleDateFormat("yyyy-MM-dd")
    smdate = sdf.parse(sdf.format(smdate))
    bdate = sdf.parse(sdf.format(bdate))
    val cal = Calendar.getInstance()
    cal.time = smdate
    val time1 = cal.timeInMillis
    cal.time = bdate
    val time2 = cal.timeInMillis
    val betweenDays = (time2 - time1) / (1000 * 3600 * 24)
    return betweenDays.toInt()
}

/**
 * 将字符串转换成date
 */
fun String.parseTime(type: FormatType): Date? {
    var date: Date? = null
    val sdf = getSimpleDateFormat(type)
    try {
        date = sdf.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return date
}

private fun getSimpleDateFormat(type: FormatType?): SimpleDateFormat {
    return when (type) {
        FormatType.yyyy -> SimpleDateFormat("yyyy")
        FormatType.yyyyMM -> SimpleDateFormat("yyyy-MM")
        FormatType.yyyyMMdd -> SimpleDateFormat("yyyy-MM-dd")
        FormatType.yyyyMMddHHmm -> SimpleDateFormat("yyyy-MM-dd HH:mm")
        FormatType.yyyyMMddHHmmss -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        FormatType.MMdd -> SimpleDateFormat("MM-dd")
        FormatType.HHmm -> SimpleDateFormat("HH:mm")
        FormatType.MM -> SimpleDateFormat("MM")
        FormatType.dd -> SimpleDateFormat("dd")
        FormatType.MMddHHmm -> SimpleDateFormat("MM-dd HH:mm")
        FormatType.HHmmss -> SimpleDateFormat("HH:mm:ss")
        else -> SimpleDateFormat("yyyy-MM-dd")
    }
}