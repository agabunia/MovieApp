package com.example.midterm_project.domain.model.movie_detailed

data class MovieDetailed(
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
    val poster: String
)
