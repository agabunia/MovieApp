package com.example.midterm_project.presentation.event.account

sealed class AccountEvent {
    object Logout : AccountEvent()
    object NavigateToMain : AccountEvent()
}