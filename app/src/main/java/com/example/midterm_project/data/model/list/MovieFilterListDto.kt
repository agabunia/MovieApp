package com.example.midterm_project.data.model.list

import com.squareup.moshi.Json

data class MovieFilterListDto(
    @Json(name = "results")
    val results: List<MovieFilterDto>
) {
    data class MovieFilterDto(
        @Json(name = "id")
        val id: Int,
        @Json(name = "title")
        val title: String,
        @Json(name = "poster_path")
        val poster: String,
        @Json(name = "vote_average")
        val vote: String,
        @Json(name = "release_date")
        val releaseDate: String,
        @Json(name = "genre_ids")
        val genresId: List<Int>
    )
}
