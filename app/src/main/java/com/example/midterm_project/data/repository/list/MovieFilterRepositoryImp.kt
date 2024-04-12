package com.example.midterm_project.data.repository.list

import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.common.createAbsoluteUrl
import com.example.midterm_project.data.mapper.base.asResource
import com.example.midterm_project.data.mapper.list.toDomain
import com.example.midterm_project.data.service.list.MovieFilterService
import com.example.midterm_project.domain.model.list.MovieFilterList
import com.example.midterm_project.domain.repository.list.MovieFilterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieFilterRepositoryImp @Inject constructor(
    private val movieFilterService: MovieFilterService,
    private val handleResponse: HandleResponse
) : MovieFilterRepository {
    override suspend fun getMovies(id: Int?): Flow<Resource<MovieFilterList>> {
        return handleResponse.safeApiCall {
            movieFilterService.getMovieList(genreId = id)
        }.asResource {
            it.toDomain().copy(results = it.toDomain().results.map { movieFilter ->
                movieFilter.copy(poster = movieFilter.poster?.let { poster -> createAbsoluteUrl(poster) })
            })
        }
    }
}