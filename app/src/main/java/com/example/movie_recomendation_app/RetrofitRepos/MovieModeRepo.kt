package com.example.movie_recomendation_app.RetrofitRepos

import com.example.movie_recomendation_app.RetrofitRepos.Entities.DetailsResponse.DetailsResponse
import com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse.Home_Response
import retrofit2.Response

class MovieModeRepo() {
    private val network:WatchModeDao
    init {
        network = WatchModeApiHelper.getInstance().create(WatchModeDao::class.java)
    }
    suspend fun getList(): Response<Home_Response> {
        return network.getMovies()
    }
    suspend fun getDetails(id:Int):Response<DetailsResponse>{
        return network.getDetails(id)
    }
}