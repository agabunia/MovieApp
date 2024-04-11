package com.example.midterm_project.domain.useCases.datastore

import com.example.midterm_project.domain.repository.datastore.DatastoreRepository
import com.example.midterm_project.domain.user_data_key.PreferenceKeys
import javax.inject.Inject

class SaveDatastoreUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke(token: String) {
        datastoreRepository.saveString(key = PreferenceKeys.TOKEN, value = token)
    }
}