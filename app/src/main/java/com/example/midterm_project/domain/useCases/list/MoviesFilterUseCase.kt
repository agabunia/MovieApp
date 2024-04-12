package com.example.midterm_project.domain.useCases.list

import com.example.midterm_project.domain.repository.list.MovieFilterRepository
import javax.inject.Inject

class MoviesFilterUseCase @Inject constructor(
    private val movieFilterRepository: MovieFilterRepository
) {
    suspend operator fun invoke(id: Int? = null) = movieFilterRepository.getMovies(id = id)
}