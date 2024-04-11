package com.example.midterm_project.domain.repository.datastore

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.protobuf.StringValue
import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun saveString(key: Preferences.Key<String>, value: String)

    fun readString(key: Preferences.Key<String>): Flow<String>

    suspend fun clear()
}