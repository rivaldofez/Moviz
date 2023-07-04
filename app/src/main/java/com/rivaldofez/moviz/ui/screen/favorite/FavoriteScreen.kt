package com.rivaldofez.moviz.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.rivaldofez.moviz.R
import com.rivaldofez.moviz.model.MovieDetail
import com.rivaldofez.moviz.ui.components.MessageComponent
import com.rivaldofez.moviz.ui.components.TopBarComponent
import com.rivaldofez.moviz.ui.helper.UiState

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteMovies()
            }

            is UiState.Success -> {
                Column {
                    TopBarComponent(title = stringResource(id = R.string.title_favorite_movies))
                    if (uiState.data.isEmpty()) {
                        MessageComponent(
                            message = stringResource(id = R.string.err_data_empty),
                            image = R.drawable.img_empty_placeholder
                        )
                    } else {
                        MovieFavoriteContent(
                            favoriteMovies = uiState.data,
                            navigateToDetail = navigateToDetail
                        )
                    }
                }
            }

            is UiState.Error -> {
                MessageComponent(
                    message = stringResource(id = R.string.error_message),
                    image = R.drawable.img_error_placeholder
                )
            }
        }
    }
}

@Composable
fun MovieFavoriteContent(
    favoriteMovies: List<MovieDetail>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        LazyColumn(
            contentPadding = PaddingValues(16.dp)
        ) {
            items(favoriteMovies, key = { it.id }) { movie ->
                MovieFavoriteItem(
                    image = movie.posterPath,
                    title = movie.title,
                    release = movie.releaseDate,
                    synopsis = movie.overview,
                    modifier = Modifier.clickable { navigateToDetail(movie.id) }
                )
                Divider()
            }
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
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(data = image),
            contentDescription = stringResource(id = R.string.cd_movie_item, title),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))

        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 1,
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