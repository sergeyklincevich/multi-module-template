package com.klinserg.news.core

import android.util.Log
import javax.inject.Inject

interface Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String)
}

class AndroidLogcatLogger @Inject constructor() : Logger {
    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}