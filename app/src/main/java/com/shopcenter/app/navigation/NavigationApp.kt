package com.shopcenter.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shopcenter.app.login.ui.LoginScreen
import com.shopcenter.app.splash.ui.SplashScreen
import com.shopcenter.app.util.Screen


@Composable
fun NavigationApp(){

   val navHostController = rememberNavController()
    NavHost(navController = navHostController, Screen.Splash.route){
        composable(Screen.Splash.route){
            SplashScreen(navHostController)
        }

        composable(route = Screen.Login.route){
            //LoginScreen(navHostController)
            LoginScreen()
        }
    }

}