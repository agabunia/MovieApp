package com.example.midterm_project.domain.useCases.validator

class PasswordValidatorUseCase {
    operator fun invoke(password: String): Boolean = password.isNotBlank()
}