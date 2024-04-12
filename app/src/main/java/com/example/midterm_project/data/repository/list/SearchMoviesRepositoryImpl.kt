package com.example.midterm_project.data.repository.list

import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.common.createAbsoluteUrl
import com.example.midterm_project.data.mapper.base.asResource
import com.example.midterm_project.data.mapper.list.toDomain
import com.example.midterm_project.data.service.list.SearchMoviesService
import com.example.midterm_project.domain.model.list.MovieFilterList
import com.example.midterm_project.domain.repository.list.SearchMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val searchMoviesService: SearchMoviesService
) : SearchMoviesRepository {
    override suspend fun searchMovies(title: String): Flow<Resource<MovieFilterList>> {
        return handleResponse.safeApiCall {
            searchMoviesService.searchMovies(title = title)
        }.asResource {
            it.toDomain().copy(results = it.toDomain().results.map { movieFilter ->
                movieFilter.copy(poster = movieFilter.poster?.let { poster -> createAbsoluteUrl(poster) })
            })
        }
    }
}