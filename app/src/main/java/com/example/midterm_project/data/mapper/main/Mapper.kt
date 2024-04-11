package com.example.midterm_project.data.mapper.main

import com.example.midterm_project.data.model.main.MovieFragmentDto
import com.example.midterm_project.domain.model.main.MovieFragment

fun MovieFragmentDto.toDomain(): MovieFragment {
    return MovieFragment(
        fragmentName = fragmentName,
        fragmentNameJson = fragmentNameJson,
        fragmentMovieList = fragmentMovieList.toDomain()
    )
}