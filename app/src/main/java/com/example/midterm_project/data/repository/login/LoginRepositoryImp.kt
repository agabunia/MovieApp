package com.example.midterm_project.data.repository.login

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.common.await
import com.example.midterm_project.domain.model.login.LoginModel
import com.example.midterm_project.domain.repository.login.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : LoginRepository {
    override suspend fun login(email: String, password: String): Flow<Resource<LoginModel>> {
        return flow {
            try {
                val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
                emit(Resource.Success(LoginModel(user = result.user!!)))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}