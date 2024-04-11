package com.example.midterm_project.domain.repository.list

import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.domain.model.list.GenresList
import kotlinx.coroutines.flow.Flow

interface GenresRepository {
    suspend fun getGenres(): Flow<Resource<GenresList>>
}
