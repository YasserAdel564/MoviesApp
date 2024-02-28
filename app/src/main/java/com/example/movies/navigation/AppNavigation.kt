package com.example.movies.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movies.navigation.NavigationKeys.Route.MOVIES_DETAILS_SCREEN
import com.example.movies.presentation.ui.moviedetails.MovieDetailsScreen
import com.example.movies.presentation.ui.movies.MoviesScreen

const val MOVIES_DETAILS_ARGUMENT_KEY = "movieId"

@Composable
fun ApplicationNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = NavigationKeys.Route.MOVIES_SCREEN) {
        composable(route = NavigationKeys.Route.MOVIES_SCREEN) {
            MoviesScreen(navController)
        }
        composable(
            "$MOVIES_DETAILS_SCREEN/{$MOVIES_DETAILS_ARGUMENT_KEY}",
            arguments = listOf(navArgument(MOVIES_DETAILS_ARGUMENT_KEY) { type = NavType.LongType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getLong(MOVIES_DETAILS_ARGUMENT_KEY) ?: 0L
            MovieDetailsScreen(navController, movieId)
        }
    }
}