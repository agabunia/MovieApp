package com.example.midterm_project.domain.useCases.registration

import android.util.Log.d
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.register.RegisterModel
import com.example.midterm_project.domain.repository.registration.RegistrationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val repository: RegistrationRepository) {

    suspend operator fun invoke(email: String, password: String): Flow<Resource<RegisterModel>> {
        d("RegistrationUseCase", "invoke function called")
        return repository.register(email = email, password = password)
    }

}
