package com.example.otploginapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.otploginapp.viewmodel.AuthState

@Composable
fun OtpScreen(
    state: AuthState.OtpInput,
    onVerify: (String) -> Unit,
    onResend: () -> Unit
) {
    var otp by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("DEBUG OTP: ${state.debugOtp}")
        Text("Time left: ${state.secondsLeft}s")
        Text("Attempts left: ${state.attemptsLeft}")

        Spacer(Modifier.height(8.dp))
        BasicTextField(otp, { otp = it }, Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))
        Button(onClick = { onVerify(otp) }) {
            Text("Verify OTP")
        }

        Spacer(Modifier.height(8.dp))
        Button(onClick = onResend) {
            Text("Resend OTP")
        }
    }
}
