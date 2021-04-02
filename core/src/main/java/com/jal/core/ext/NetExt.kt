package com.jal.core.common

import android.content.Context
import android.net.ConnectivityManager
import com.jal.core.getContext

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 16:51
 * @desc:
 */
fun Context.checkNet(): Boolean {
    return isWifiConnection(this) || isStationConnection(this)
}

/**
 * 是否使用基站联网
 *
 * @param context
 * @return
 */
fun isStationConnection(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo =
        manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
    return if (networkInfo != null) {
        networkInfo.isAvailable && networkInfo.isConnected
    } else false
}

/**
 * 是否使用WIFI联网
 *
 * @param context
 * @return
 */
fun isWifiConnection(context: Context): Boolean {
    val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo =
        manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    return if (networkInfo != null) {
        networkInfo.isAvailable && networkInfo.isConnected
    } else false
}