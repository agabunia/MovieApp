package com.example.midterm_project.data.repository.main

import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.mapper.base.asResource
import com.example.midterm_project.data.mapper.main.toDomain
import com.example.midterm_project.data.service.main.MainService
import com.example.midterm_project.domain.model.main.MovieFragment
import com.example.midterm_project.domain.model.main.MovieList
import com.example.midterm_project.domain.repository.main.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImp @Inject constructor(
    private val mainService: MainService,
    private val handleResponse: HandleResponse
) : MainRepository {
    private val baseUrl = "https://image.tmdb.org/t/p/w500"
    override suspend fun getMovieList(
        fragmentJsonName: String
    ): Flow<Resource<MovieList>> {
        return handleResponse.safeApiCall {
            mainService.getMovieList(filter = fragmentJsonName)
        }.asResource {
            it.toDomain().copy(results = it.toDomain().results.map { movie ->
                movie.copy(poster = createAbsoluteUrl(movie.poster))
            })
        }
    }

    private fun createAbsoluteUrl(relativeUrl: String): String {
        return baseUrl + relativeUrl
    }

}
