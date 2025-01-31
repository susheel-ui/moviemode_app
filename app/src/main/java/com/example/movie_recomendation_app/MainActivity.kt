package com.example.movie_recomendation_app

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModelProvider
import com.example.movie_recomendation_app.ui.theme.Movie_Recomendation_AppTheme
import com.example.movie_recomendation_app.Composable.*
import com.example.movie_recomendation_app.RetrofitRepos.MovieModeRepo
import com.example.movie_recomendation_app.ViewModels.HomeViewModel
import com.example.movie_recomendation_app.ViewModels.HomeViewModelFactory

class MainActivity : ComponentActivity() {
    lateinit var viewModel:HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this,HomeViewModelFactory(MovieModeRepo())).get(HomeViewModel::class.java)
        setContent {
            Movie_Recomendation_AppTheme {
                    HomeScreen(viewModel,{
//on click function

                        val intent = Intent(this,DetailsPage::class.java)
                        intent.putExtra("_id",it)
                        Log.d(TAG, "onCreate: $it")
                        startActivity(intent)
                    })

            }
        }


    }
}



