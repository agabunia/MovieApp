package com.example.midterm_project.data.repository.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.midterm_project.domain.repository.datastore.DatastoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatastoreRepositoryImp @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DatastoreRepository {
    override suspend fun saveString(key: Preferences.Key<String>, value: String) {
        dataStore.edit {
            it[key] = value
        }
    }

    override fun readString(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data.map {
            it[key] ?: ""
        }
    }

    override suspend fun clear() {
        dataStore.edit {
            it.clear()
        }
    }
}