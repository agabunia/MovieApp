package com.example.midterm_project.presentation.mapper.movie_detailed

import com.example.midterm_project.domain.model.movie_detailed.MovieDetailed
import com.example.midterm_project.presentation.model.movie_detailed.MovieDetailedModel

fun MovieDetailed.toPresenter(): MovieDetailedModel {
    return MovieDetailedModel(
        id = id,
        title = title,
        originalLanguage = originalLanguage,
        tagline = tagline,
        overview = overview,
        status = status,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        runtime = runtime,
        budget = budget,
        revenue = revenue,
        poster = poster
    )
}