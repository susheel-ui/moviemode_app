package com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse

data class Home_Response(
    val page: Int?,
    val titles: List<Title>,
    val total_pages: Int,
    val total_results: Int
)