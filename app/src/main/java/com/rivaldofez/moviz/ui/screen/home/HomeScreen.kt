package com.rivaldofez.moviz.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rivaldofez.moviz.R
import com.rivaldofez.moviz.model.Movie
import com.rivaldofez.moviz.ui.components.MessageComponent
import com.rivaldofez.moviz.ui.components.MovieItem
import com.rivaldofez.moviz.ui.components.SearchBar
import com.rivaldofez.moviz.ui.components.TopBarComponent
import com.rivaldofez.moviz.ui.helper.UiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.searchCurrentMovieData()
            }

            is UiState.Success -> {
                Column {
                    TopBarComponent(title = stringResource(id = R.string.title_explore_movie))

                    SearchBar(
                        query = query,
                        onSubmit = viewModel::searchCurrentMovieData,
                        onValueChanged = viewModel::updateQueryValue
                    )

                    if (uiState.data.isEmpty()) {
                        MessageComponent(
                            message = stringResource(id = R.string.err_data_empty),
                            image = R.drawable.img_empty_placeholder
                        )
                    } else {
                        HomeContent(
                            movies = uiState.data,
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
fun HomeContent(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3),
            contentPadding = PaddingValues(0.dp),
            horizontalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
                .testTag("lvg_movie")
        ) {
            items(movies, key = { it.id }) {
                MovieItem(
                    image = it.posterPath,
                    title = it.title,
                    release = it.releaseDate,
                    modifier = Modifier.clickable {
                        navigateToDetail(it.id)
                    })
            }
        }
    }
}