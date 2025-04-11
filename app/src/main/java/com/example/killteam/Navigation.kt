package com.example.killteam

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.killteam.ui.theme.KTColors

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
    }
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ScoreScreen(navController : NavController,viewModel: ScoreViewModel)
{


    val context = LocalContext.current
    val activity = context as ComponentActivity
    val windowSizeClass = calculateWindowSizeClass(activity)    //Pobranie klasy ekranu
    Scaffold(

        topBar = {
            TopAppBar(
                modifier = Modifier.height(75.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KTColors.Infiltration,
                    titleContentColor = Color.White // Dopasuj kolor tekstu do tła
                ),
                title = { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                {
                    Text("Score Screen")}
                }
            )
        }
    )
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            ScoreScreen(viewModel = viewModel)
        }
    }
}

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
