package com.example.midterm_project.domain.repository.authentification

import com.example.midterm_project.data.common.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun register(email: String, password: String): Resource<FirebaseUser>
    fun logout()
}
