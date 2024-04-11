package com.example.midterm_project.data.repository.authentification

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.utils.await
import com.example.midterm_project.domain.repository.authentification.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun register(email: String, password: String): Resource<FirebaseUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            Resource.Success(result.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}
