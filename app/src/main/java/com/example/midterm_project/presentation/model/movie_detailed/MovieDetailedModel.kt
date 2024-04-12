package com.example.midterm_project.presentation.model.movie_detailed

data class MovieDetailedModel(
    val id: Int,
    val title: String,
    val originalLanguage: String,
    val tagline: String,
    val overview: String,
    val status: String,
    val releaseDate: String,
    val voteAverage: Float,
    val runtime: Int,
    val budget: Int,
    val revenue: Int,
    val backdrop: String?
)
