package com.example.midterm_project.presentation.event.login

sealed class LoginEvent {
    data class Login(val email: String, val password: String) : LoginEvent()
    object Register: LoginEvent()
    object ResetErrorMessage : LoginEvent()
}