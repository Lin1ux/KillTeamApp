package com.example.killteam

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
//import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
//import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.killteam.screens.AttackScreen
import com.example.killteam.screens.DiceScreen
import com.example.killteam.screens.FractionScreen
import com.example.killteam.screens.PreviewScreen
import com.example.killteam.screens.ScoreScreen
import com.example.killteam.screens.UnitScreen
import com.example.killteam.screens.UnitSelectionScreen
import com.example.killteam.ui.theme.KTColors
import kotlinx.coroutines.launch

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
        composable(route = Screen.DiceScreen.route)
        {
            ShowDiceScreen(navController)
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
        composable(route = Screen.UnitAttack.route, //route to Fraction Screen
            arguments = listOf(
                navArgument("RedPlayer") //It requires boolean argument is Red Player
                {
                    type = NavType.BoolType
                    defaultValue = true
                },
                navArgument("index")
                {
                    type = NavType.IntType
                    defaultValue = 0
                },
                navArgument("weaponIndex")
                {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        )
        { entry ->
            val RedPlayer = entry.arguments?.getBoolean("RedPlayer") ?: false
            val index = entry.arguments!!.getInt("index")
            val weaponIndex = entry.arguments!!.getInt("weaponIndex")
            ShowAttackScreen(navController,viewModel, RedPlayer,index,weaponIndex)
        }
    }
}

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowScoreScreen(navController : NavController,viewModel: ScoreViewModel)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    //val windowSizeClass = calculateWindowSizeClass(activity)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedIndex = remember {mutableStateOf(0)}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
    }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Score Screen",false,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                ScoreScreen(viewModel = viewModel, navController)
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowDiceScreen(navController : NavController)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Dice Roller",false,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                DiceScreen()
            }
        }
    }
}


//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowFractionScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    //val windowSizeClass = calculateWindowSizeClass(activity)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedIndex = remember {mutableStateOf(0)}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        })
    {
        Scaffold(

            topBar = {
                AppBar(navController, viewModel.GetPlayer(firstPlayer).GetTeam().name,true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                FractionScreen(navController,viewModel, firstPlayer)
            }
        }
    }

}

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowUnitScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    //val windowSizeClass = calculateWindowSizeClass(activity)

    if(viewModel.GetPlayer(firstPlayer).IsTroopsSelected())
    {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedIndex = remember {mutableStateOf(0)}

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                NavigationMenu(navController,getMenuItem())
            })
        {
            Scaffold(
                topBar = {
                    AppBar(navController, viewModel.GetPlayer(firstPlayer).GetTeam().name,true,{ scope.launch { drawerState.open()}})
                },
            )
            { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    UnitScreen(navController, viewModel, firstPlayer)
                }
            }
        }
    }
    else
    {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedIndex = remember {mutableStateOf(0)}

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                NavigationMenu(navController,getMenuItem())
            })
        {
            Scaffold(

                topBar = {
                    AppBar(navController, viewModel.GetPlayer(firstPlayer).GetTeam().name,true,{ scope.launch { drawerState.open()}})
                }
            )
            { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    UnitSelectionScreen(navController, viewModel, firstPlayer)
                }
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowPreviewScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean,index : Int)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    //val windowSizeClass = calculateWindowSizeClass(activity)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedIndex = remember {mutableStateOf(0)}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        })
    {
        Scaffold(
            topBar = {
                AppBar(navController, viewModel.GetPlayer(firstPlayer).GetTeam().name,true,{ scope.launch { drawerState.open()}})
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = KTColors.Infiltration
                )
                {
                    Row(Modifier.fillMaxWidth())
                    {
                        //Select next and previous unit
                        var previousIndex by remember { mutableStateOf(index) }
                        var nextIndex by remember { mutableStateOf(index) }
                        if (index == 0) {
                            previousIndex =
                                viewModel.GetPlayer(firstPlayer).GetSelectedTroops().size - 1
                        } else {
                            previousIndex--
                        }
                        if (index == viewModel.GetPlayer(firstPlayer)
                                .GetSelectedTroops().size - 1
                        ) {
                            nextIndex = 0
                        } else {
                            nextIndex++
                        }
                        //Button for selecting next operator
                        Button(
                            modifier = Modifier.weight(1.0f).fillMaxHeight().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = KTColors.Orange),
                            onClick = {
                                navController.popBackStack()
                                navController.navigate(
                                    Screen.UnitPreview.UnitPreviewRoute(
                                        firstPlayer,
                                        previousIndex
                                    )
                                )
                            }
                        )
                        {
                            Text(
                                "${
                                    viewModel.GetPlayer(firstPlayer)
                                        .GetTroopByIndex(previousIndex).name.RemoveKeyWord(
                                        viewModel,
                                        firstPlayer
                                    )
                                }", textAlign = TextAlign.Center
                            )
                        }
                        //Button for selecting previous operator
                        Button(
                            modifier = Modifier.weight(1.0f).fillMaxHeight().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = KTColors.Orange),
                            enabled = viewModel.GetPlayer(firstPlayer).ValidateTeam(),
                            onClick = {
                                navController.popBackStack()
                                navController.navigate(
                                    Screen.UnitPreview.UnitPreviewRoute(
                                        firstPlayer,
                                        nextIndex
                                    )
                                )
                            }
                        )
                        {
                            Text(
                                "${
                                    viewModel.GetPlayer(firstPlayer)
                                        .GetTroopByIndex(nextIndex).name.RemoveKeyWord(
                                        viewModel,
                                        firstPlayer
                                    )
                                }", textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                PreviewScreen(navController, viewModel, firstPlayer, index)
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowAttackScreen(navController : NavController,viewModel: ScoreViewModel,firstPlayer : Boolean, unitIndex : Int, weaponIndex : Int)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity
    //val windowSizeClass = calculateWindowSizeClass(activity)

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedIndex = remember {mutableStateOf(0)}

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        })
    {
        Scaffold(

            topBar = {
                AppBar(
                    navController,
                    viewModel.GetPlayer(firstPlayer).GetTeam().name,
                    true,
                    { scope.launch { drawerState.open() } })
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AttackScreen(navController, viewModel, firstPlayer, unitIndex, weaponIndex)
            }
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
