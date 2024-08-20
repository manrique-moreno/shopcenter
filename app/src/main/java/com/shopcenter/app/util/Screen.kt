package com.shopcenter.app.util

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
}