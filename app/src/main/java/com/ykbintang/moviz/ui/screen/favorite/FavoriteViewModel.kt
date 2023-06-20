package com.ykbintang.moviz.ui.screen.favorite

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
class FavoriteViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<MovieDetail>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<MovieDetail>>> get() = _uiState


    fun getFavoriteMovies(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading

            try {
                movieRepository.getFavoriteMovies()
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        _uiState.value = UiState.Success(it)
                    }
            } catch (e: Exception){
                _uiState.value = UiState.Error(e.message.toString())
            }
        }
    }

}