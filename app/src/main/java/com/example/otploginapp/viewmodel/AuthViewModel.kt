package com.example.otploginapp.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.otploginapp.data.OtpManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val otpManager = OtpManager()

    var uiState by mutableStateOf<AuthState>(AuthState.EmailInput)
        private set

    private var currentEmail = ""
    private var lastGeneratedOtp = ""

    private var otpTimerJob: Job? = null

    fun onEmailSubmitted(email: String) {
        currentEmail = email

        val otpData = otpManager.generateOtp(email)
        lastGeneratedOtp = otpData.otp

        uiState = AuthState.OtpInput(
            email = email,
            attemptsLeft = 3,
            secondsLeft = 60,
            debugOtp = lastGeneratedOtp
        )

        startOtpTimer()
    }

    fun onOtpEntered(otp: String) {
        when (val result = otpManager.validateOtp(currentEmail, otp)) {

            is OtpManager.Result.Success -> {
                otpTimerJob?.cancel()   // 🔴 STOP OTP TIMER
                uiState = AuthState.Session(System.currentTimeMillis())
            }

            is OtpManager.Result.Wrong -> {
                uiState = AuthState.OtpInput(
                    email = currentEmail,
                    attemptsLeft = result.attemptsLeft,
                    secondsLeft = otpManager.getRemainingSeconds(currentEmail),
                    debugOtp = lastGeneratedOtp
                )
            }

            else -> {
                uiState = AuthState.OtpInput(
                    email = currentEmail,
                    attemptsLeft = 0,
                    secondsLeft = 0,
                    debugOtp = lastGeneratedOtp
                )
            }
        }
    }

    fun resendOtp() {
        val otpData = otpManager.generateOtp(currentEmail)
        lastGeneratedOtp = otpData.otp

        uiState = AuthState.OtpInput(
            email = currentEmail,
            attemptsLeft = 3,
            secondsLeft = 60,
            debugOtp = lastGeneratedOtp
        )

        startOtpTimer()
    }

    fun logout() {
        otpTimerJob?.cancel()
        currentEmail = ""
        uiState = AuthState.EmailInput
    }

    private fun startOtpTimer() {
        otpTimerJob?.cancel()

        otpTimerJob = viewModelScope.launch {
            while (uiState is AuthState.OtpInput) {
                delay(1000)

                val remaining = otpManager.getRemainingSeconds(currentEmail)

                if (remaining <= 0) {
                    uiState = AuthState.OtpInput(
                        email = currentEmail,
                        attemptsLeft = 0,
                        secondsLeft = 0,
                        debugOtp = lastGeneratedOtp
                    )
                    break
                } else {
                    val current = uiState as AuthState.OtpInput
                    uiState = current.copy(secondsLeft = remaining)
                }
            }
        }
    }
}
