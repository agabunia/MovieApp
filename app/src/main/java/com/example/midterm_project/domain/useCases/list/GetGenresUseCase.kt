package com.example.midterm_project.domain.useCases.list

import com.example.midterm_project.domain.repository.list.GenresRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genresRepository: GenresRepository
) {
    suspend operator fun invoke() = genresRepository.getGenres()
}