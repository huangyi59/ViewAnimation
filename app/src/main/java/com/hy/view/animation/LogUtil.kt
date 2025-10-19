package com.hy.view.animation

import android.media.tv.AitInfo
import android.util.Log

/**
 * @author yi.huang
 * 时间：   2025/10/19 14:55
 * 描述：   日志工具类
 */

fun logD(secondTag: String, msg: String) {
    Log.d("[${AnimApplication.APP_TAG}][$secondTag]", msg)
}

fun logI(secondTag: String, msg: String) {
    Log.i("[${AnimApplication.APP_TAG}][$secondTag]", msg)
}

fun logW(secondTag: String, msg: String) {
    Log.w("[${AnimApplication.APP_TAG}][$secondTag]", msg)
}

fun logE(secondTag: String, msg: String) {
    Log.e("[${AnimApplication.APP_TAG}][$secondTag]", msg)
}