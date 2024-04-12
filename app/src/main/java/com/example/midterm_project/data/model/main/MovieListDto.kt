package com.example.midterm_project.data.model.main

import com.squareup.moshi.Json

data class MovieListDto(
    @Json(name = "results")
    val results: List<Detail>
) {
    data class Detail(
        @Json(name = "id")
        val id: Int,
        @Json(name = "title")
        val title: String,
        @Json(name = "poster_path")
        val poster: String?,
        @Json(name = "vote_average")
        val vote: String,
        @Json(name = "release_date")
        val releaseDate: String
    )
}
