package com.ykbintang.moviz.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailGame : Screen("home/{gameId}"){
        fun createRoute(gameId: Int) = "home/$gameId"
    }
}