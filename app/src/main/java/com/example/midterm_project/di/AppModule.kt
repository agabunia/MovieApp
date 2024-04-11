package com.example.midterm_project.di

import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.service.list.GenresService
import com.example.midterm_project.data.service.list.MovieFilterService
import com.example.midterm_project.data.service.list.SearchMoviesService
import com.example.midterm_project.data.service.main.MainService
import com.example.midterm_project.data.service.movie_detailed.MovieDetailedService
import com.google.firebase.auth.FirebaseAuth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    private const val BASE_URL = "https://run.mocky.io/v3/"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authTokenFlow: Flow<String?>, loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder
            .addInterceptor { chain ->
                val authToken = runBlocking { authTokenFlow.first() }
                val newRequest = if (!authToken.isNullOrBlank()) {
                    chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer $authToken")
                        .addHeader("accept", "application/json")
                        .build()
                } else {
                    chain.request()
                }
                chain.proceed(newRequest)
            }
        clientBuilder.addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }

    @Singleton
    @Provides
    fun provideMainService(retrofit: Retrofit): MainService {
        return retrofit.create(MainService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieDetailedService(retrofit: Retrofit): MovieDetailedService {
        return retrofit.create(MovieDetailedService::class.java)
    }

    @Singleton
    @Provides
    fun provideGenresService(retrofit: Retrofit): GenresService {
        return retrofit.create(GenresService::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieFilterService(retrofit: Retrofit): MovieFilterService {
        return retrofit.create(MovieFilterService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchMoviesService(retrofit: Retrofit): SearchMoviesService {
        return retrofit.create(SearchMoviesService::class.java)
    }
}
