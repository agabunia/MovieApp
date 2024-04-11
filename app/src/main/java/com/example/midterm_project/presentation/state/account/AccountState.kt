package com.example.midterm_project.presentation.state.account

data class AccountState(
    val data: String? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
