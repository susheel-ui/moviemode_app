package com.example.movie_recomendation_app.RetrofitRepos

//import androidx.privacysandbox.tools.core.generator.build
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WatchModeApiHelper {
    private const val BASE_URL = "https://api.watchmode.com/v1/"
    private const val API_KEY = "iMnKXVhEm9VKQOUiOxAFagMczLpgzs9lTuoC7TUa"

    private fun addApiKeyToRequests(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apiKey", API_KEY).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
    fun getInstance():Retrofit{
        val client = OkHttpClient.Builder()
            .addInterceptor{ chain -> return@addInterceptor addApiKeyToRequests(chain)}
            .build()
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    fun getApiKey():String{
        return API_KEY
    }
}

