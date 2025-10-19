package com.hy.view.animation

import android.app.Application

/**
 * @author yi.huang
 * 时间：   2025/10/19 14:52
 * 描述：   The application of anim.
 */
class AnimApplication : Application() {

    companion object {
        const val APP_TAG = "AnimApplication"
        lateinit var instance:AnimApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

}