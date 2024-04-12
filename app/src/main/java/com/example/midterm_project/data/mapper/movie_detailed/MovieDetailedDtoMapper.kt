package com.example.midterm_project.data.mapper.movie_detailed

import com.example.midterm_project.data.model.movie_detailed.MovieDetailedDto
import com.example.midterm_project.domain.model.movie_detailed.MovieDetailed

fun MovieDetailedDto.toDomain(): MovieDetailed {
    return MovieDetailed(
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
        backdrop = backdrop
    )
}