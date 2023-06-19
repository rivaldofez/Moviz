package com.ykbintang.moviz.ui.screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ykbintang.moviz.data.MovieRepository
import com.ykbintang.moviz.model.Movie
import com.ykbintang.moviz.ui.helper.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Movie>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<Movie>>> get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query


    fun updateQueryValue(query: String){
        _query.value = query
    }

    fun searchCurrentMovieData() = viewModelScope.launch {
        Log.d("Testong", "called on search view model")
        if(query.value.isEmpty()){
            getMovieNowPlayig()
        } else {
            try {
                movieRepository.getSearchMovie(_query.value)
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                        Log.d("Testin", "catch collect get search movie")
                    }
                    .collect {
                        _uiState.value = UiState.Success(it)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message.toString())
                Log.d("Testin", "catch error get search movie")
                Log.d("Testin", e.message.toString())
            }
        }
    }

    fun getMovieNowPlayig() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                movieRepository.getMovieNowPlaying()
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                        Log.d("Testin", "catch collect get now play")
                    }
                    .collect {
                        _uiState.value = UiState.Success(it)
                    }
            } catch (e: Exception){
                _uiState.value = UiState.Error(e.message.toString())
                Log.d("Testin", "catch error get now play movie")
            }
        }
    }
}