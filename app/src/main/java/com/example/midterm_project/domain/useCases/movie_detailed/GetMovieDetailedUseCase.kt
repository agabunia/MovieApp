package com.example.midterm_project.domain.useCases.movie_detailed

import com.example.midterm_project.domain.repository.movie_detailed.MovieDetailedRepository
import javax.inject.Inject

class GetMovieDetailedUseCase @Inject constructor(
    private val movieDetailedRepository: MovieDetailedRepository
) {
    suspend operator fun invoke(id: Int) = movieDetailedRepository.getMovieDetailed(id = id)
}