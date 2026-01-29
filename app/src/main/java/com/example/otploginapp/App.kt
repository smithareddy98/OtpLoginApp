package com.example.otploginapp

import android.app.Application
import com.example.otploginapp.analytics.AnalyticsLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AnalyticsLogger.init()
    }
}
