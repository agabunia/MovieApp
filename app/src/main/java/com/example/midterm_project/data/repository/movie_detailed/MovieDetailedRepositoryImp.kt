package com.example.midterm_project.data.repository.movie_detailed

import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.mapper.base.asResource
import com.example.midterm_project.data.mapper.main.toDomain
import com.example.midterm_project.data.mapper.movie_detailed.toDomain
import com.example.midterm_project.data.service.movie_detailed.MovieDetailedService
import com.example.midterm_project.domain.model.movie_detailed.MovieDetailed
import com.example.midterm_project.domain.repository.movie_detailed.MovieDetailedRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailedRepositoryImp @Inject constructor(
    private val movieDetailedService: MovieDetailedService,
    private val handleResponse: HandleResponse
): MovieDetailedRepository {
    private val baseUrl = "https://image.tmdb.org/t/p/w500"
    override suspend fun getMovieDetailed(id: Int): Flow<Resource<MovieDetailed>> {
        return handleResponse.safeApiCall {
            movieDetailedService.getMovieDetailed(id = id)
        }.asResource {
            it.toDomain().copy(poster = createAbsoluteUrl(it.poster))
        }
    }

    private fun createAbsoluteUrl(relativeUrl: String): String {
        return baseUrl + relativeUrl
    }
}