package com.example.midterm_project.domain.repository.movie_detailed

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.movie_detailed.MovieDetailed
import kotlinx.coroutines.flow.Flow

interface MovieDetailedRepository {
    suspend fun getMovieDetailed(id: Int): Flow<Resource<MovieDetailed>>
}