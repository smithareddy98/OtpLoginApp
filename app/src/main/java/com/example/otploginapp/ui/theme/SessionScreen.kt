package com.example.otploginapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun SessionScreen(
    startTime: Long,
    onLogout: () -> Unit
) {
    var elapsedSeconds by remember { mutableStateOf(0L) }

    LaunchedEffect(startTime) {
        while (true) {
            delay(1000)
            elapsedSeconds = (System.currentTimeMillis() - startTime) / 1000
        }
    }

    val minutes = elapsedSeconds / 60
    val seconds = elapsedSeconds % 60
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("✅ Login Successful", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("Session started at: ${formatter.format(Date(startTime))}")
        Spacer(Modifier.height(8.dp))
        Text(String.format("Session duration: %02d:%02d", minutes, seconds))
        Spacer(Modifier.height(16.dp))
        Button(onClick = onLogout) {
            Text("Logout")
        }
    }
}
