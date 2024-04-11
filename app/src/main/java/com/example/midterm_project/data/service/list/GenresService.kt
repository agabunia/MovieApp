package com.example.midterm_project.data.service.list

import com.example.midterm_project.data.model.list.GenresDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GenresService {
    @GET("https://api.themoviedb.org/3/genre/movie/list?language=en")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNmQ1ZTlhNGFlODBiODJjMDU5YTcwYzlhMzY3YjYwYSIsInN1YiI6IjY1YTRjYWQ0MGQxMWYyMDEyMzI3MjM4NSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.i4YQ-eB5M4Zuekw7hR1C4kBXDpLYwo5ZU-1UTXemuoQ"
    )
    suspend fun getGenres(): Response<GenresDto>
}