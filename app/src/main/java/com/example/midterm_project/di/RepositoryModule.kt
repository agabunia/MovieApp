package com.example.midterm_project.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.repository.authentification.AuthRepositoryImpl
import com.example.midterm_project.data.repository.datastore.DatastoreRepositoryImp
import com.example.midterm_project.data.repository.list.GenresRepositoryImp
import com.example.midterm_project.data.repository.list.MovieFilterRepositoryImp
import com.example.midterm_project.data.repository.list.SearchMoviesRepositoryImpl
import com.example.midterm_project.data.repository.login.LoginRepositoryImp
import com.example.midterm_project.data.repository.main.MainRepositoryImp
import com.example.midterm_project.data.repository.movie_detailed.MovieDetailedRepositoryImp
import com.example.midterm_project.data.repository.registration.RegistrationRepositoryImp
import com.example.midterm_project.data.service.list.GenresService
import com.example.midterm_project.data.service.list.MovieFilterService
import com.example.midterm_project.data.service.list.SearchMoviesService
import com.example.midterm_project.data.service.main.MainService
import com.example.midterm_project.data.service.movie_detailed.MovieDetailedService
import com.example.midterm_project.domain.repository.authentification.AuthRepository
import com.example.midterm_project.domain.repository.datastore.DatastoreRepository
import com.example.midterm_project.domain.repository.list.GenresRepository
import com.example.midterm_project.domain.repository.list.MovieFilterRepository
import com.example.midterm_project.domain.repository.list.SearchMoviesRepository
import com.example.midterm_project.domain.repository.login.LoginRepository
import com.example.midterm_project.domain.repository.main.MainRepository
import com.example.midterm_project.domain.repository.movie_detailed.MovieDetailedRepository
import com.example.midterm_project.domain.repository.registration.RegistrationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRegistrationRepository(impl: RegistrationRepositoryImp): RegistrationRepository =
        impl

    @Provides
    @Singleton
    fun provideLoginRepository(impl: LoginRepositoryImp): LoginRepository = impl

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DatastoreRepository {
        return DatastoreRepositoryImp(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideMainRepository(
        mainService: MainService,
        handleResponse: HandleResponse
    ): MainRepository {
        return MainRepositoryImp(mainService = mainService, handleResponse = handleResponse)
    }

    @Provides
    @Singleton
    fun provideMovieDetailedRepository(
        movieDetailedService: MovieDetailedService,
        handleResponse: HandleResponse
    ): MovieDetailedRepository {
        return MovieDetailedRepositoryImp(
            movieDetailedService = movieDetailedService,
            handleResponse = handleResponse
        )
    }

    @Provides
    @Singleton
    fun provideGenresRepository(
        genresService: GenresService,
        handleResponse: HandleResponse
    ): GenresRepository {
        return GenresRepositoryImp(genresService = genresService, handleResponse = handleResponse)
    }

    @Provides
    @Singleton
    fun provideMovieFilterRepository(
        movieFilterService: MovieFilterService,
        handleResponse: HandleResponse
    ): MovieFilterRepository {
        return MovieFilterRepositoryImp(movieFilterService = movieFilterService, handleResponse = handleResponse)
    }

    @Provides
    @Singleton
    fun provideSearchMoviesRepository(
        searchMoviesService: SearchMoviesService,
        handleResponse: HandleResponse
    ): SearchMoviesRepository {
        return SearchMoviesRepositoryImpl(searchMoviesService = searchMoviesService, handleResponse = handleResponse)
    }
}
