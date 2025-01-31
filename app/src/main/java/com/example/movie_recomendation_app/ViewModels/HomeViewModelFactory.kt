package com.example.movie_recomendation_app.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie_recomendation_app.RetrofitRepos.MovieModeRepo

class HomeViewModelFactory(private val repo: MovieModeRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo) as T
    }
}