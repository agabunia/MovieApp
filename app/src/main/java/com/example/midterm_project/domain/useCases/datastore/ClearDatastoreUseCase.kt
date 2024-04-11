package com.example.midterm_project.domain.useCases.datastore

import com.example.midterm_project.domain.repository.datastore.DatastoreRepository
import javax.inject.Inject

class ClearDatastoreUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) {
    suspend operator fun invoke() {
        datastoreRepository.clear()
    }
}