package com.example.otploginapp.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun init() {
        Timber.plant(Timber.DebugTree())
    }

    fun otpGenerated() {
        Timber.d("OTP generated")
    }

    fun otpSuccess() {
        Timber.d("OTP validation success")
    }

    fun otpFailure() {
        Timber.d("OTP validation failure")
    }

    fun logout() {
        Timber.d("User logged out")
    }
}
