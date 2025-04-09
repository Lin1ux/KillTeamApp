package com.example.killteam

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation()
{
    val viewModel: ScoreViewModel = viewModel()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route)
    {
        composable(route = Screen.MainScreen.route)
        {
            ScoreScreen(navController = navController,viewModel = viewModel)
            //MainScreen(navController = navController)
        }
        /*composable (
            route = Screen.DetailScreen.route + "/{name}/{description}",
            arguments = listOf(
                navArgument("name")
                {
                    type = NavType.StringType
                    defaultValue = "Drink"
                    nullable = true
                }
            )
        )
        {   entry ->
            /*SelectDetailScreen(
                title = entry.arguments?.getString("name"),
                description = entry.arguments?.getString("description"))*/
        }*/
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ScoreScreen(navController : NavController,viewModel: ScoreViewModel)
{


    val context = LocalContext.current
    val activity = context as ComponentActivity
    val windowSizeClass = calculateWindowSizeClass(activity)    //Pobranie klasy ekranu
    ScoreScreen(viewModel = viewModel)
    /*when (windowSizeClass.widthSizeClass)   //when działa podobnie do switcha
    {
        //Kompaktowy rozmiar
        WindowWidthSizeClass.Compact -> {
            ContentList(getDrinkList(),navController) //Wyświetlanie listy
        }
        //Rozszerzony rozmiar
        WindowWidthSizeClass.Medium, WindowWidthSizeClass.Expanded -> {
            TabletContentList(getDrinkList(),navController)
        }
    }*/
}