package com.example.midterm_project.domain.useCases.login

import com.example.midterm_project.domain.repository.login.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend operator fun invoke(email: String, password: String) =
        loginRepository.login(email = email, password = password)
}