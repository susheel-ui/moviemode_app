package com.example.movie_recomendation_app.ViewModels.DetailsScreenViewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_recomendation_app.RetrofitRepos.Entities.DetailsResponse.DetailsResponse
import com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse.Title
import com.example.movie_recomendation_app.RetrofitRepos.MovieModeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class DetailsViewModel(val repo: MovieModeRepo, val id: Int) : ViewModel() {
    var details  = MutableStateFlow<DetailsResponse?>(null)
    var dataLoader = MutableStateFlow(false)

    init {
        if (id != 0) {
            getDetails(id)
        } else {
        }

    }

    fun getDetails(id: Int) {
        viewModelScope.launch {
            try {
                val result = repo.getDetails(id)
                if(result.isSuccessful){
                    Log.d(TAG, "getDetails: ${result.body()}")
                    details.value = result.body()
                    dataLoader = MutableStateFlow(true)
                }else{
                    Log.d(TAG, "getDetails: responce error ${result.message()}")
                }
            }catch (e:Exception){
                Log.d(TAG, "getDetails: HTTP error ${e.message}")
            }

        }
    }
}