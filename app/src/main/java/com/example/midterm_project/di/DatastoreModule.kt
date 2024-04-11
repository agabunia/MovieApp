package com.example.midterm_project.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.midterm_project.domain.user_data_key.PreferenceKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideDataSore(@ApplicationContext context: Context): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = {
                    emptyPreferences()
                }
            ),
            produceFile = { context.preferencesDataStoreFile("settings") })
    }

    @Provides
    @Singleton
    fun provideAuthToken(dataStore: DataStore<Preferences>): Flow<String?> {
        return dataStore.data
            .map { preferences ->
                preferences[PreferenceKeys.TOKEN]
            }
    }
}
