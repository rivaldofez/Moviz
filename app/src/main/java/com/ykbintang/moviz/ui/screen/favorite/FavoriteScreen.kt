package com.ykbintang.moviz.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ykbintang.moviz.model.MovieDetail
import com.ykbintang.moviz.ui.helper.UiState
import com.ykbintang.moviz.ui.theme.Shapes


@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
){
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getFavoriteMovies()
            }
            is UiState.Success -> {
                MovieFavoriteContent(favoriteMovies = uiState.data, navigateToDetail = navigateToDetail)
            }
            is UiState.Error -> {

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFavoriteContent(
    favoriteMovies: List<MovieDetail>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
){
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(title = { Text("Explore Now Playing Movie")})
    }

    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(favoriteMovies, key = { it.id }){ movie ->
            MovieFavoriteItem(
                image = movie.posterPath!!,
                title = movie.title,
                release = movie.releaseDate,
                synopsis = movie.overview,
                modifier = Modifier.clickable { navigateToDetail(movie.id) }
            )
            Divider()
        }
    }
}

@Composable
fun MovieFavoriteItem(
    image: String,
    title: String,
    release: String,
    synopsis: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(data = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )

            Text(
                text = release,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium,
            )

            Text(
                text = synopsis,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}