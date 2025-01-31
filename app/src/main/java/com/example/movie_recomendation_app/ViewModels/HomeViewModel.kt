package com.example.movie_recomendation_app.ViewModels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_recomendation_app.RetrofitRepos.MovieModeRepo
import com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse.Title
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.http.Tag

class HomeViewModel(private val repo: MovieModeRepo):ViewModel(){
        val apiResult = MutableStateFlow<List<Title>>(emptyList())
        var Movies = MutableStateFlow<List<Title>>(emptyList())
        val TVShows = MutableStateFlow<List<Title>>(emptyList())
    val others =MutableStateFlow<List<Title>>(emptyList())
    var state = MutableStateFlow(false)
    init {
        getData()
    }
    fun getData(){
            viewModelScope.launch{
                val deferredResult = async(Dispatchers.IO) {
                   try {
                       val result =  repo.getList()
                       if(result.isSuccessful){
                           val thismovies = result.body()?.titles?.filter { it.type == "movie" }
                           val thistvShows = result.body()?.titles?.filter { it.type == "tv_series" }

                           Log.d(TAG, "getData tv shows: ${thistvShows}")
                           Log.d(TAG, "getData Movies: ${thismovies}")

                           Movies.value = thismovies?: emptyList()
                           TVShows.value = thistvShows?: emptyList()
                           state.value = true
                       }else{
                            // handle api error
                           Log.d(TAG, "getData: ERROR ${result.message()}")
                       }
                   }catch (e:Exception){
                       // error
                       Log.d(TAG, "getData Exception: ${e.message} ")
                   }
                }.invokeOnCompletion {

                }


//                val result = deferredResult.await()
//
//                val resultthrd = async(Dispatchers.IO) {
//                    for(i in result.body()?.titles!!){
//                        if(i.type =="movie"){
//                            Movies
//                        }else if(i.type == "tv_series"){
//                            TVShows.add(i)
//                        }else{
//                            others.add(i)
//                        }
//                    }
//                }.invokeOnCompletion {
//                    state = mutableSetOf(true)
//                }

            }

        }

}