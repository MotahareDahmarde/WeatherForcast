package com.example.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforcast.screens.main.MainScreen
import com.example.weatherforcast.screens.main.MainViewModel
import com.example.weatherforcast.screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation () {
    val navController = rememberNavController()// hatman bayad dakhel composable bashe
    NavHost(navController = navController, 
        startDestination = WeatherScreens.SplashScreen.name){
        composable(route = WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        composable(route = WeatherScreens.MainScreen.name){
            val mainViewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController = navController, mainViewModel)
        }

    }
    
}