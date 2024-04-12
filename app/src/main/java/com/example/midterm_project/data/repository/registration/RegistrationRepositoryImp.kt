package com.example.midterm_project.data.repository.registration

import android.util.Log.d
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.common.await
import com.example.midterm_project.domain.model.register.RegisterModel
import com.example.midterm_project.domain.repository.registration.RegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegistrationRepositoryImp @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): RegistrationRepository {
    override suspend fun register(email: String, password: String): Flow<Resource<RegisterModel>> {
        d("RegistrationRepository", "register method called")
        return flow {
            try {
                val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                d("RegistrationRepository", "register method is called successfully")
                emit(Resource.Success(RegisterModel(result.user!!)))
            } catch (e: Exception) {
                e.printStackTrace()
                d("RegistrationRepository", e.message.toString())
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

}