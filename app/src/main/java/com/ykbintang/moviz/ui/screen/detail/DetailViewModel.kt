package com.ykbintang.moviz.ui.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykbintang.moviz.data.MovieRepository
import com.ykbintang.moviz.model.MovieDetail
import com.ykbintang.moviz.ui.helper.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {
    private var _uiState: MutableStateFlow<UiState<MovieDetail>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetail>> get() = _uiState

    fun getMovieDetail(movieId: Int) {
        try {
            viewModelScope.launch {
                movieRepository.getMovieDetail(movieId = movieId)
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect { movieDetail ->
                        if(movieDetail == null){
                            _uiState.value = UiState.Error("Empty Data")
                        } else {
                            _uiState.value = UiState.Success(movieDetail)
                        }
                    }
            }
        } catch (e: Exception){
            _uiState.value = UiState.Error(e.message.toString())
        }
    }
}