package com.example.otploginapp.viewmodel

sealed class AuthState {

    object EmailInput : AuthState()

    data class OtpInput(
        val email: String,
        val attemptsLeft: Int,
        val secondsLeft: Int,
        val debugOtp: String
    ) : AuthState()

    data class Session(
        val startTime: Long
    ) : AuthState()
}
