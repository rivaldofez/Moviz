package com.ykbintang.moviz.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object MovieDetail : Screen("home/{movieId}"){
        fun createRoute(movieId: Int) = "home/$movieId"
    }
}