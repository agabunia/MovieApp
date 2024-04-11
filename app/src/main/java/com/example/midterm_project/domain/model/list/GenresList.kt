package com.example.midterm_project.domain.model.list

data class GenresList(
    val genres: List<Genres>
) {
    data class Genres(
        val id: Int,
        val name: String
    )
}
