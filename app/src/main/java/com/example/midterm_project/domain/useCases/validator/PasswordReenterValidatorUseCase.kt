package com.example.midterm_project.domain.useCases.validator

class PasswordReenterValidatorUseCase {
    operator fun invoke(password: String, passwordChecker: String): Boolean {
        return password == passwordChecker
    }
}