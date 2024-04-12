package com.example.midterm_project.domain.useCases.list

import com.example.midterm_project.domain.repository.list.SearchMoviesRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieFilter: SearchMoviesRepository
) {
    suspend operator fun invoke(title: String) = movieFilter.searchMovies(title = title)
}