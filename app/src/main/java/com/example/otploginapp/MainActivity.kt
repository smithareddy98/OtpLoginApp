package com.example.otploginapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.otploginapp.ui.*
import com.example.otploginapp.ui.theme.OtpLoginAppTheme
import com.example.otploginapp.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OtpLoginAppTheme {
                val vm: AuthViewModel = viewModel()

                when (val state = vm.uiState) {
                    is AuthState.EmailInput ->
                        EmailScreen { vm.onEmailSubmitted(it) }

                    is AuthState.OtpInput ->
                        OtpScreen(state, vm::onOtpEntered, vm::resendOtp)

                    is AuthState.Session ->
                        SessionScreen(state.startTime, vm::logout)
                }
            }
        }
    }
}
