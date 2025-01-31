package com.example.movie_recomendation_app.ViewModels.DetailsScreenViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie_recomendation_app.RetrofitRepos.MovieModeRepo

class DetailsVMFactory(val repo:MovieModeRepo,val id:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(repo,id) as T
    }
}