package com.example.midterm_project.data.model.movie_detailed

import com.squareup.moshi.Json

data class MovieDetailedDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "original_language")
    val originalLanguage: String,
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "vote_average")
    val voteAverage: Float,
    @Json(name = "runtime")
    val runtime: Int,
    @Json(name = "budget")
    val budget: Int,
    @Json(name = "revenue")
    val revenue: Int,
    @Json(name = "poster_path")
    val poster: String
)
