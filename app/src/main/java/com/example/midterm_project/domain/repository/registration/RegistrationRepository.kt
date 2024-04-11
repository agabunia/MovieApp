package com.example.midterm_project.domain.repository.registration

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.register.RegisterModel
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface RegistrationRepository {
    suspend fun register(email: String, password: String): Flow<Resource<RegisterModel>>
}