package com.example.midterm_project.data.repository.list

import com.example.midterm_project.data.common.HandleResponse
import com.example.midterm_project.data.common.Resource
import com.example.midterm_project.data.mapper.base.asResource
import com.example.midterm_project.data.mapper.list.toDomain
import com.example.midterm_project.data.service.list.GenresService
import com.example.midterm_project.domain.model.list.GenresList
import com.example.midterm_project.domain.repository.list.GenresRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenresRepositoryImp @Inject constructor(
    private val genresService: GenresService,
    private val handleResponse: HandleResponse
) : GenresRepository {
    override suspend fun getGenres(): Flow<Resource<GenresList>> {
        return handleResponse.safeApiCall {
            genresService.getGenres()
        }.asResource {
            it.toDomain()
        }
    }
}
