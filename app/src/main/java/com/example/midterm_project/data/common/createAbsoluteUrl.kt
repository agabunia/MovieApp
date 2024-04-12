package com.example.midterm_project.data.common

fun createAbsoluteUrl(relativeUrl: String): String {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    return baseUrl + relativeUrl
}