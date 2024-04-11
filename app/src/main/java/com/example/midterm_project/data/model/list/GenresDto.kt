package com.example.midterm_project.data.model.list

import com.squareup.moshi.Json

data class GenresDto(
    @Json(name = "genres")
    val genres: List<Genres>
) {
    data class Genres(
        @Json(name = "id")
        val id: Int,
        @Json(name = "name")
        val name: String
    )
}