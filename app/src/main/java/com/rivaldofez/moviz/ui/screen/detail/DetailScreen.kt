package com.rivaldofez.moviz.ui.screen.detail

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.rivaldofez.moviz.R
import com.rivaldofez.moviz.ui.components.MessageComponent
import com.rivaldofez.moviz.ui.helper.UiState
import com.rivaldofez.moviz.ui.theme.MovizTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    movieId: Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMovieDetail(movieId = movieId)
            }

            is UiState.Success -> {
                val data = uiState.data
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            data.isFavorite = !data.isFavorite
                            viewModel.insertFavoriteMovie(movieDetail = data)
                            if (data.isFavorite) {
                                showToast(
                                    context = context,
                                    message = R.string.added_to_favorite
                                )
                            } else {
                                showToast(
                                    context = context,
                                    message = R.string.removed_from_favorite
                                )
                            }
                        }, modifier = Modifier.testTag("favorite_button")
                        ) {
                            if (data.isFavorite) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_favorite_filled),
                                    contentDescription = stringResource(id = R.string.cd_icon_fav_filled),
                                    tint = Color.White
                                )
                            } else {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_favorite_unfilled),
                                    contentDescription = stringResource(id = R.string.cd_icon_fav_unfilled),
                                    tint = Color.White
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    DetailContent(
                        title = data.title,
                        release = data.releaseDate,
                        duration = data.runtime.toString(),
                        synopsis = data.overview,
                        originalTitle = data.originalTitle,
                        genres = data.genres,
                        languages = data.spokenLanguages,
                        posterPath = data.posterPath,
                        backdropPath = data.backdropPath,
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = navigateBack
                    )
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

private fun showToast(context: Context, message: Int) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun DetailContent(
    title: String,
    release: String,
    duration: String,
    synopsis: String,
    originalTitle: String,
    genres: String,
    languages: String,
    posterPath: String,
    backdropPath: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.height(400.dp)
            ) {

                Column {
                    Image(
                        painter = rememberImagePainter(data = backdropPath),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(300.dp)
                            .fillMaxWidth()
                    )
                }

                Row(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    Image(
                        painter = rememberImagePainter(data = posterPath),
                        contentDescription = stringResource(id = R.string.cd_movie_item, title),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(200.dp)
                            .width(150.dp)
                            .padding(start = 16.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Column {
                        Text(
                            text = title,
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontWeight = FontWeight.ExtraBold
                            ),
                            maxLines = 2,
                            modifier = Modifier
                                .offset(y = 100.dp)
                                .padding(start = 16.dp, top = 8.dp, end = 16.dp)
                        )

                        Text(
                            text = release,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal
                            ),
                            modifier = Modifier
                                .offset(y = 100.dp)
                                .padding(start = 16.dp, end = 16.dp)
                        )
                    }
                }

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.cd_icon_back_button),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                        .clip(RoundedCornerShape(size = 8.dp))
                        .background(color = Color.White)
                        .padding(8.dp)
                        .testTag("back_button")

                )
            }

            Text(
                text = stringResource(id = R.string.title_synopsis),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = synopsis,
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.title_detail_information),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.title_original_title),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = originalTitle,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.title_duration),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.pl_duration, duration),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.title_genre),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = genres,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = stringResource(id = R.string.title_language),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .padding(top = 4.dp, start = 16.dp, end = 16.dp)
            )

            Text(
                text = languages,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .padding(top = 2.dp, start = 16.dp, end = 16.dp, bottom = 16.dp)
            )


        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    MovizTheme {
        DetailContent(
            title = "Harry Potter",
            release = "Kamis, 11 Oktober 2023",
            duration = "100 Menit",
            synopsis = "Lorem ipsum dolor sit amet proques, ena fote amane lois horris nan vet on laq dja mu siqua sehopks",
            originalTitle = "Harry Potter and The Chamber of secret",
            genres = "Horror, Adventure",
            languages = "English, Indonesia",
            posterPath = "https://alternativemovieposters.com/wp-content/uploads/2023/01/Julian-De-Lio_Azkaban.jpg",
            backdropPath = "https://w0.peakpx.com/wallpaper/344/757/HD-wallpaper-harry-potter-hogwarts-castle.jpg",
            onBackClick = {}
        )
    }
}