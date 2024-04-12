package com.example.midterm_project.presentation.model.list

data class MovieFilterModel(
    val results: List<MovieFilterModel>
) {
    data class MovieFilterModel(
        val id: Int,
        val title: String,
        val poster: String?,
        val vote: String,
        val releaseDate: String,
        val genresId: List<Int>
    )
}
