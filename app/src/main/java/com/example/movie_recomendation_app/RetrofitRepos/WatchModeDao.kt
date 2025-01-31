package com.example.movie_recomendation_app.RetrofitRepos

import com.example.movie_recomendation_app.RetrofitRepos.Entities.DetailsResponse.DetailsResponse
import com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse.Home_Response
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface WatchModeDao {
    @GET("list-titles/")
    suspend fun getMovies(): Response<Home_Response>

    @GET("title/{title_id}/details/")
    suspend fun getDetails(@Path("title_id") title_id: Int): Response<DetailsResponse>

}