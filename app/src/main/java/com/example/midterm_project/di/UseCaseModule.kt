package com.example.midterm_project.di

import com.example.midterm_project.domain.repository.datastore.DatastoreRepository
import com.example.midterm_project.domain.repository.login.LoginRepository
import com.example.midterm_project.domain.repository.registration.RegistrationRepository
import com.example.midterm_project.domain.useCases.datastore.ClearDatastoreUseCase
import com.example.midterm_project.domain.useCases.datastore.ReadDatastoreUseCase
import com.example.midterm_project.domain.useCases.datastore.SaveDatastoreUseCase
import com.example.midterm_project.domain.useCases.login.LoginUseCase
import com.example.midterm_project.domain.useCases.registration.RegistrationUseCase
import com.example.midterm_project.domain.useCases.validator.EmailValidatorUseCase
import com.example.midterm_project.domain.useCases.validator.PasswordReenterValidatorUseCase
import com.example.midterm_project.domain.useCases.validator.PasswordValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideSaveTokenUseCase(
        dataStoreRepository: DatastoreRepository
    ): SaveDatastoreUseCase {
        return SaveDatastoreUseCase(datastoreRepository = dataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideClearDataStoreUseCase(
        dataStoreRepository: DatastoreRepository
    ): ClearDatastoreUseCase {
        return ClearDatastoreUseCase(datastoreRepository = dataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideReadUseCase(
        dataStoreRepository: DatastoreRepository
    ): ReadDatastoreUseCase {
        return ReadDatastoreUseCase(datastoreRepository = dataStoreRepository)
    }

    @Singleton
    @Provides
    fun provideRegistrationUseCase(
        repository: RegistrationRepository
    ): RegistrationUseCase {
        return RegistrationUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideLoginUseCase(
        repository: LoginRepository
    ): LoginUseCase {
        return LoginUseCase(loginRepository = repository)
    }

    @Singleton
    @Provides
    fun provideEmailValidatorUseCase(): EmailValidatorUseCase {
        return EmailValidatorUseCase()
    }

    @Singleton
    @Provides
    fun providePasswordValidatorUseCase(): PasswordValidatorUseCase {
        return PasswordValidatorUseCase()
    }

    @Singleton
    @Provides
    fun providePasswordReenterValidatorUseCase(): PasswordReenterValidatorUseCase {
        return PasswordReenterValidatorUseCase()
    }
}