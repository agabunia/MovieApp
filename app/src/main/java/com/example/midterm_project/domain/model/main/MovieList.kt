package com.example.midterm_project.domain.model.main

data class MovieList(
    val results: List<Detail>
) {
    data class Detail(
        val id: Int,
        val title: String,
        val poster: String,
        val vote: String,
        val releaseDate: String
    )
}
