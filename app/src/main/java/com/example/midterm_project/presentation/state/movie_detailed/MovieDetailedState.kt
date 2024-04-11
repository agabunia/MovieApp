package com.example.midterm_project.presentation.state.movie_detailed

import com.example.midterm_project.domain.model.movie_detailed.MovieDetailed

data class MovieDetailedState(
    val movieDetailed: MovieDetailed? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
)
