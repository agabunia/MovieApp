package com.example.midterm_project.domain.repository.login

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.login.LoginModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(email: String, password: String): Flow<Resource<LoginModel>>
}