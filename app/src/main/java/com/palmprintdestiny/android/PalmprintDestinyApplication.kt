package com.palmprintdestiny.android

import android.app.Application
import android.annotation.SuppressLint
import android.content.Context

class PalmprintDestinyApplication : Application() {
    companion object {
        const val APPCODE = "df73d9c058e24ef08d08ebe552c46f9d"
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}