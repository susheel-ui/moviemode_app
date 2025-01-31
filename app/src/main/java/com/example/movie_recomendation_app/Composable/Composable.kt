package com.example.movie_recomendation_app.Composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movie_recomendation_app.RetrofitRepos.Entities.HomeResponse.Title
import com.example.movie_recomendation_app.ViewModels.HomeViewModel
import com.example.movie_recomendation_app.enums.Show

@Composable
public fun HomeScreen(viewModel:HomeViewModel,itemclicked: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
//    TopBar(Modifier
//        .fillMaxWidth()
//        .background(Color.Black, RectangleShape)
//    )
        TopAppBar()
        togglePage(viewModel,itemclicked)
        // here toggle specs
    }
}


@Composable
fun togglePage(viewModel: HomeViewModel,itemclicked: (Int) -> Unit) {
    var selectedTab by remember { mutableStateOf("Movies") }


    Scaffold(
        topBar = {
            ToggleTopBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }
    ) { innerPadding ->
        // Show composable based on selected tab
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTab) {
                "Movies" -> MoviesScreen(viewModel,itemclicked)
                "TV" -> TVScreen(viewModel,itemclicked)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "My App",
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { /* Handle navigation icon click */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle action click */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More"
                )
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToggleTopBar(selectedTab: String, onTabSelected: (String) -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            RowToggleButtons(
                selectedTab = selectedTab,
                onTabSelected = onTabSelected
            )
        }
    )
}

@Composable
fun RowToggleButtons(selectedTab: String, onTabSelected: (String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = { onTabSelected("Movies") },
            colors = ButtonDefaults.textButtonColors(
                containerColor = if (selectedTab == "Movies") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                contentColor = if (selectedTab == "Movies") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier.padding(4.dp)
        ) {
            Text("Movies", fontSize = 16.sp)
        }

        TextButton(
            onClick = { onTabSelected("TV") },
            colors = ButtonDefaults.textButtonColors(
                containerColor = if (selectedTab == "TV") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                contentColor = if (selectedTab == "TV") MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            ),
            shape = RoundedCornerShape(50),
            modifier = Modifier.padding(4.dp)
        ) {
            Text("TV Shows", fontSize = 16.sp)
        }
    }
}

@Composable
fun MoviesScreen(viewModel: HomeViewModel,itemclicked:(Int)->Unit) {

    val Movielist:List<Title> by viewModel.Movies.collectAsState()
    var _isLoading by remember { mutableStateOf(false) }

    if(!_isLoading){
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            columns = GridCells.Adaptive(200.dp)
        ) {
            items(10){
                showSimmerEffact(
                    Modifier.size(200.dp, 300.dp)
                        .clip(RoundedCornerShape(40.dp))
                        .padding(10.dp)
                )
            }

        }

    }
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            columns = GridCells.Adaptive(200.dp)
        ) {

            items(Movielist) {
                _isLoading = true
                ShowItem(it, itemclicked)

            }

        }

}

@Composable
fun TVScreen(viewModel: HomeViewModel ,itemclicked:(Int)->Unit) {
    val tVlist :List<Title> by viewModel.TVShows.collectAsState()
    val _isLoading by viewModel.state.collectAsState()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(tVlist) {
            if(_isLoading)
                ShowItem(it,itemclicked)
            else{
                showSimmerEffact(Modifier.size(200.dp,300.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .padding(10.dp))
            }
        }

    }
}

@Composable
fun ShowItem(show: Title, clicked:(Int)->Unit) {

    Card(
        modifier = Modifier
            .height(400.dp)
            .padding(10.dp)
            .clickable { clicked(show.id) }
        ,


    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f)
                    .padding(10.dp)
                    .drawBehind {
                        drawRoundRect(
                            color = Color.White,
                            cornerRadius = CornerRadius(20f, 20f)
                        )
                    },


                )
            {
                Text(
                    text = show.title,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = show.title,
                    )
                Text(
                    text = show.year.toString(),
                )
            }


        }

    }

}



