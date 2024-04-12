package com.example.midterm_project.domain.useCases.main

import com.example.midterm_project.domain.repository.main.MainRepository
import javax.inject.Inject

class GetMainMoviesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(fragmentJsonName: String) = mainRepository.getMovieList(fragmentJsonName = fragmentJsonName)
}