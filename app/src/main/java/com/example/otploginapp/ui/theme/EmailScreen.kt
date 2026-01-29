package com.example.otploginapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmailScreen(onSendOtp: (String) -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Enter Email")
        Spacer(Modifier.height(8.dp))
        BasicTextField(email, { email = it }, Modifier.fillMaxWidth())
        Spacer(Modifier.height(16.dp))
        Button(onClick = { onSendOtp(email) }) {
            Text("Send OTP")
        }
    }
}
