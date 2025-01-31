package com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse

data class Title(
    val id: Int,
    val imdb_id: String,
    val title: String,
    val tmdb_id: Int,
    val tmdb_type: String,
    val type: String,
    val year: Int
)