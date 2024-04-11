package com.example.midterm_project.domain.repository.list

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.list.MovieFilterList
import kotlinx.coroutines.flow.Flow

interface SearchMoviesRepository {
    suspend fun searchMovies(title: String): Flow<Resource<MovieFilterList>>
}