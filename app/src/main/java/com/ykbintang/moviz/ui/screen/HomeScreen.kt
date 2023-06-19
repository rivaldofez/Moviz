package com.ykbintang.moviz.ui.screen

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.ykbintang.moviz.ui.helper.UiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getMovieNowPlayig()
            }
            is UiState.Success -> {
                Log.d("Tag", "Result " + uiState.data.toString())
                
                Text(text = "Test View Model Get Data")
            }
            
            is UiState.Error -> {
                Log.d("Tag", "Error")
            }
        }
    }
}