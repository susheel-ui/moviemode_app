package com.example.movie_recomendation_app

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.example.movie_recomendation_app.ui.theme.Movie_Recomendation_AppTheme
import com.example.movie_recomendation_app.Composable.*
import com.example.movie_recomendation_app.RetrofitRepos.Entities.DetailsResponse.DetailsResponse
import com.example.movie_recomendation_app.RetrofitRepos.MovieModeRepo
import com.example.movie_recomendation_app.ViewModels.DetailsScreenViewModel.DetailsVMFactory
import com.example.movie_recomendation_app.ViewModels.DetailsScreenViewModel.DetailsViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DetailsPage : ComponentActivity() {
    lateinit var viewModel: DetailsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra("_id", 0)
        Log.d(TAG, "onCreate: DETAILS_PAGE $id")

        viewModel = ViewModelProvider(
            this,
            DetailsVMFactory(MovieModeRepo(), id)
        ).get(DetailsViewModel::class.java)

        setContent {
            Movie_Recomendation_AppTheme {
                detailsComp(viewModel)

            }
        }
    }
}


@Composable
fun detailsComp(viewModel: DetailsViewModel) {
    val details = viewModel.details.collectAsState()
    val data by remember {
        mutableStateOf(false)
    }
    Column {
        TopAppBar()
        details.value?.let {
            true
            ShowDetails(it)
        }
        if(!data){
            showSimmerEffact(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.5f)
                    .clip(RoundedCornerShape(20.dp))
                    .padding(20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            LazyColumn {
                items(10){
                    showSimmerEffact(Modifier
                        .size(500.dp,40.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                    )
                }
            }

        }

    }
}


@Composable
private fun ShowDetails(detailsResponse: DetailsResponse) {
    var imgLoader by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
    ) {
        if (imgLoader) {
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .clip(RoundedCornerShape(20.dp))
                .padding(20.dp)
                .align(Alignment.CenterHorizontally)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f),

            ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.Center)
            ) {

                AsyncImage(
                    model = detailsResponse.posterLarge,
                    contentDescription = "poseter imageView",
                    onSuccess = {
                        imgLoader = false
                    },
                    modifier = Modifier.fillMaxSize(),
                )


            }

        }
        Column() {

            Text(
                text = detailsResponse.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.poppins_black)),
                modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 0.dp)
            )
            Text(
                text = detailsResponse.original_title,
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                "Rating: ${detailsResponse.user_rating}",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)

            )


            Text(
                text = "Year : ${detailsResponse.release_date}",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Genre : ${detailsResponse.genre_names}",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Type : ${detailsResponse.type}",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            )
            Text(
                text = "Runtime :${detailsResponse.runtime_minutes}",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            )


            Row() {
                Text(
                    text = "Plot",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_black)),
                    modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 0.dp)
                )
                Text(
                    text = ": ${detailsResponse.plot_overview}",
                    fontSize = 15.sp,
                    fontFamily = FontFamily(Font(R.font.poppin_regular)),
                    modifier = Modifier.padding(10.dp, 20.dp, 0.dp, 0.dp)
                )

            }
            Text(
                text = "Watch Trailer",
                fontSize = 15.sp,
                fontFamily = FontFamily(Font(R.font.poppin_regular)),
                modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
            )
        }


    }
}




