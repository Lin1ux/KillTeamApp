package com.example.killteam

import Objects.Operator
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
    NavHost(navController = navController, startDestination = Screen.ScoreScreen.route)
    {
        composable(route = Screen.ScoreScreen.route)
        {
            ShowScoreScreen(navController = navController,viewModel = viewModel)
        }
        composable(route = Screen.FractionScreen.route, //route to Fraction Screen
            arguments = listOf(navArgument("RedPlayer") //It requires boolean argument is Red Player
            {
                type = NavType.BoolType
                defaultValue = true
            }))
        { entry ->
            val RedPlayer = entry.arguments?.getBoolean("RedPlayer") ?: false
            ShowFractionScreen(navController,viewModel, RedPlayer)
        }
        composable(route = Screen.UnitScreen.route, //route to Fraction Screen
            arguments = listOf(navArgument("RedPlayer") //It requires boolean argument is Red Player
            {
                type = NavType.BoolType
                defaultValue = true
            }))
        { entry ->
            val RedPlayer = entry.arguments?.getBoolean("RedPlayer") ?: false
            ShowUnitScreen(navController,viewModel, RedPlayer)
        }
        composable(route = Screen.UnitPreview.route, //route to Fraction Screen
            arguments = listOf(navArgument("RedPlayer") //It requires boolean argument is Red Player
            {
                type = NavType.BoolType
                defaultValue = true
            },
                navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        )
        { entry ->
            val RedPlayer = entry.arguments?.getBoolean("RedPlayer") ?: false
            val index = entry.arguments!!.getInt("index")
            ShowPreviewScreen(navController,viewModel, RedPlayer,index)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowScoreScreen(navController : NavController,viewModel: ScoreViewModel)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val windowSizeClass = calculateWindowSizeClass(activity)
    Scaffold(

        topBar = {
            TopAppBar(
                modifier = Modifier.height(75.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KTColors.Infiltration,
                    titleContentColor = Color.White
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
            ScoreScreen(viewModel = viewModel,navController)
        }
    }
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowFractionScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val windowSizeClass = calculateWindowSizeClass(activity)
    Scaffold(

        topBar = {
            TopAppBar(
                modifier = Modifier.height(75.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KTColors.Infiltration,
                    titleContentColor = Color.White
                ),
                title = { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                {
                    Text("${viewModel.GetPlayer(firstPlayer).GetTeam().name}")}
                }
            )
        }
    )
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            FractionScreen(viewModel,firstPlayer)
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowUnitScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val windowSizeClass = calculateWindowSizeClass(activity)

    if(viewModel.GetPlayer(firstPlayer).IsTroopsSelected())
    {
        Scaffold(

            topBar = {
                TopAppBar(
                    modifier = Modifier.height(75.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = KTColors.Infiltration,
                        titleContentColor = Color.White
                    ),
                    title = { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                    {
                        Text("${viewModel.GetPlayer(firstPlayer).GetTeam().name}")}
                    }
                )
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                UnitScreen(navController,viewModel,firstPlayer)
            }
        }
    }
    else
    {
        Scaffold(

            topBar = {
                TopAppBar(
                    modifier = Modifier.height(75.dp),
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = KTColors.Infiltration,
                        titleContentColor = Color.White
                    ),
                    title = { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                    {
                        Text("${viewModel.GetPlayer(firstPlayer).GetTeam().name}")}
                    }
                )
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                UnitSelectionScreen(navController,viewModel,firstPlayer)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowPreviewScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean,index : Int)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    val windowSizeClass = calculateWindowSizeClass(activity)
    Scaffold(

        topBar = {
            TopAppBar(
                modifier = Modifier.height(75.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = KTColors.Infiltration,
                    titleContentColor = Color.White
                ),
                title = { Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
                {
                    Text("${viewModel.GetPlayer(firstPlayer).GetTeam().name}")}
                }
            )
        }
    )
    { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            PreviewScreen(viewModel,firstPlayer,index)
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
