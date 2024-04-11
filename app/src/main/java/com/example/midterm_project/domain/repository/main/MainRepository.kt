package com.example.midterm_project.domain.repository.main

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.main.MovieFragment
import com.example.midterm_project.domain.model.main.MovieList
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getMovieList(fragmentJsonName: String): Flow<Resource<MovieList>>
}
