package com.example.otploginapp.data

import kotlin.random.Random

data class OtpData(
    val otp: String,
    val createdAt: Long,
    val attemptsLeft: Int
)

class OtpManager {

    private val otpStore = mutableMapOf<String, OtpData>()
    private val expiryMillis = 60_000L

    fun generateOtp(email: String): OtpData {
        val otp = Random.nextInt(100000, 999999).toString()
        val data = OtpData(
            otp = otp,
            createdAt = System.currentTimeMillis(),
            attemptsLeft = 3
        )
        otpStore[email] = data
        return data
    }

    fun validateOtp(email: String, enteredOtp: String): Result {
        val data = otpStore[email] ?: return Result.Invalid

        if (isExpired(data)) {
            otpStore.remove(email)
            return Result.Expired
        }

        return if (enteredOtp == data.otp) {
            otpStore.remove(email)
            Result.Success
        } else {
            val updated = data.copy(attemptsLeft = data.attemptsLeft - 1)
            if (updated.attemptsLeft <= 0) {
                otpStore.remove(email)
                Result.AttemptsExceeded
            } else {
                otpStore[email] = updated
                Result.Wrong(updated.attemptsLeft)
            }
        }
    }

    fun getRemainingSeconds(email: String): Int {
        val data = otpStore[email] ?: return 0
        val elapsed = System.currentTimeMillis() - data.createdAt
        return ((expiryMillis - elapsed) / 1000).toInt().coerceAtLeast(0)
    }

    private fun isExpired(data: OtpData): Boolean {
        return System.currentTimeMillis() - data.createdAt > expiryMillis
    }

    sealed class Result {
        object Success : Result()
        object Expired : Result()
        object Invalid : Result()
        object AttemptsExceeded : Result()
        data class Wrong(val attemptsLeft: Int) : Result()
    }
}
