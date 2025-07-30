package com.example.killteam

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.killteam.Objects.KillTeams
import com.example.killteam.Objects.TeamInfo
import com.example.killteam.firebase.DatabaseViewModel
import com.example.killteam.firebase.GoogleAuthUIClient
import com.example.killteam.firebase.SignInViewModel
import com.example.killteam.firebase.UserData
import com.example.killteam.screens.AttackScreen
import com.example.killteam.screens.DiceScreen
import com.example.killteam.screens.FactionRules
import com.example.killteam.screens.FractionScreen
import com.example.killteam.screens.GameList
import com.example.killteam.screens.GamePreviewScreen
import com.example.killteam.screens.PreviewScreen
import com.example.killteam.screens.ProfileScreen
import com.example.killteam.screens.ScoreScreen
import com.example.killteam.screens.SignInScreen
import com.example.killteam.screens.TacopScreen
import com.example.killteam.screens.TeamDataScreen
import com.example.killteam.screens.TeamRulesScreen
import com.example.killteam.screens.UnitScreen
import com.example.killteam.screens.UnitSelectionScreen
import com.example.killteam.screens.WeaponeRules
import com.example.killteam.ui.theme.KTColors
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

@Composable
fun Navigation()
{
    val viewModel: ScoreViewModel = viewModel()
    val loginViewModel = viewModel<SignInViewModel>()

    val dbViewModel = viewModel<DatabaseViewModel>()

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.ScoreScreen.route)
    {
        composable(route = Screen.ScoreScreen.route)
        {
            ShowScoreScreen(navController = navController,viewModel = viewModel,dbViewModel = dbViewModel)
        }
        composable(route = Screen.DiceScreen.route)
        {
            ShowDiceScreen(navController)
        }
        composable(route = Screen.LoginScreen.route)
        {
            ShowLoginScreen(navController,loginViewModel)
        }
        composable(route = Screen.ProfileScreen.route)
        {
            ShowProfileScreen(navController,loginViewModel,dbViewModel)
        }
        composable(route = Screen.TeamDataScreen.route)
        {
            ShowTeamDataScreen(navController,dbViewModel)
        }
        composable(route = Screen.TeamRulesScreen.route)
        {
            ShowTeamRulesScreen(navController)
        }
        composable(route = Screen.WeaponeRuleScreen.route)
        {
            ShowWeaponRulesScreen(navController)
        }
        composable(route = Screen.TacopScreen.route)
        {
            ShowTacopScreen(navController)
        }
        composable(route = Screen.HistoryListScreen.route)
        {
            ShowHistoryScreen(navController,dbViewModel)
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
        composable(route = Screen.UnitPreviewScreen.route, //route to Fraction Screen
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
        composable(route = Screen.GamePreviewScreen.route, //route to Fraction Screen
            arguments = listOf(navArgument("index") {
                    type = NavType.IntType
                    defaultValue = 0
                }
            )
        )
        { entry ->
            val index = entry.arguments!!.getInt("index")
            ShowGamePreviewScreen(navController,dbViewModel,index)
        }
        composable(route = Screen.FractionRulesScreen.route, //route to Fraction Screen
            arguments = listOf(navArgument("teamName") {
                type = NavType.StringType
                defaultValue = "Angels Of Death"
            }
            )
        )
        { entry ->
            val teamName = entry.arguments!!.getString("teamName") ?: "Angels Of Death"
            ShowFractionRulesScreen(navController, teamName)
        }
        composable(route = Screen.UnitAttack.route, //route to unit attack Screen
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
fun ShowScoreScreen(navController : NavController,viewModel: ScoreViewModel,dbViewModel: DatabaseViewModel)
{
    val context = LocalContext.current
    val activity = context as ComponentActivity

    val googleAuthUiClient by lazy {
        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
    }

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
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                ScoreScreen(viewModel = viewModel,dbViewModel , navController,googleAuthUiClient.isUserSignedIn())
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
                AppBar(navController,"Dice Roller",true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                DiceScreen()
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun ShowWeaponRulesScreen(navController : NavController)
{
    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Weapon rules",true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.fillMaxHeight().padding(innerPadding).background(KTColors.Background)) {
                WeaponeRules()
            }
        }
    }
}
//Show tacop Screen
@Composable
fun ShowTacopScreen(navController : NavController)
{
    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Tacops",true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.fillMaxHeight().padding(innerPadding).background(KTColors.Background)) {
                TacopScreen()
            }
        }
    }
}

@Composable
fun ShowLoginScreen(navController : NavController,loginViewModel: SignInViewModel)
{
    //Google Login
    val context = LocalContext.current
    val state by loginViewModel.state.collectAsStateWithLifecycle()

    val launcherScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
    }

    LaunchedEffect(key1 = Unit)
    {
        if(googleAuthUiClient.getSignedInUser() != null)
        {
            navController.navigate(Screen.ProfileScreen.route)
        }
    }

    val loginScope = rememberCoroutineScope()
    //Menu Drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == RESULT_OK) {
                loginScope.launch {
                    val signInResult = googleAuthUiClient.SignInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    loginViewModel.onSignInResult(signInResult)
                }
            }
        }
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Login",false,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background)) {
                LaunchedEffect(key1 = state.isSignInSuccessful)
                {
                    if(state.isSignInSuccessful)
                    {
                        Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
                        navController.navigate(Screen.ProfileScreen.route)
                    }
                }
                SignInScreen(state = state,
                            onSignInClick = {
                                launcherScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    launcher.launch(
                                        IntentSenderRequest.Builder(signInIntentSender?: return@launch).build())
                                }
                            })
            }
        }
    }
}

@Composable
fun ShowProfileScreen(navController : NavController,loginViewModel: SignInViewModel,dbViewModel: DatabaseViewModel)
{
    val context = LocalContext.current

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val launcherScope = rememberCoroutineScope()

    val googleAuthUiClient by lazy {
        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
    }
    val loginScope = rememberCoroutineScope()

    val state by loginViewModel.state.collectAsStateWithLifecycle()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if(result.resultCode == RESULT_OK) {
                loginScope.launch {
                    val signInResult = googleAuthUiClient.SignInWithIntent(
                        intent = result.data ?: return@launch
                    )
                    loginViewModel.onSignInResult(signInResult)
                    navController.navigate(Screen.ProfileScreen.route)
                }
            }
        }
    )
    //Loading data from database
    LaunchedEffect(Unit) {
        dbViewModel.getUserData(googleAuthUiClient.getSignedInUser())
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Account",false,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                LaunchedEffect(key1 = state.isSignInSuccessful)
                {
                    Toast.makeText(context, "Sign in successful", Toast.LENGTH_LONG).show()
                }
                ProfileScreen(
                    userData = googleAuthUiClient.getSignedInUser(),
                    dbViewModel = dbViewModel,
                    navController = navController,
                    onSignOutClick = {
                        launcherScope.launch {
                            googleAuthUiClient.signOut()
                            Toast.makeText(context, "Sign out", Toast.LENGTH_LONG).show()
                            navController.navigate(Screen.LoginScreen.route)
                            loginViewModel.resetState()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ShowTeamDataScreen(navController : NavController,dbViewModel: DatabaseViewModel)
{
    val context = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
    }

    if(!googleAuthUiClient.isUserSignedIn())
    {
        navController.navigate(Screen.LoginScreen.route)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    //val dbViewModel = viewModel<DatabaseViewModel>()

    LaunchedEffect(Unit) {
        dbViewModel.getUserData(googleAuthUiClient.getSignedInUser())
    }

    //dbViewModel.saveUserData(googleAuthUiClient.getSignedInUser())
    //dbViewModel.getUserData(googleAuthUiClient.getSignedInUser())



    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Team Data",true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight())
            {
                TeamDataScreen(navController,dbViewModel)
            }
        }
    }
}

@Composable
fun ShowTeamRulesScreen(navController : NavController)
{
    /*val context = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
    }

    if(!googleAuthUiClient.isUserSignedIn())
    {
        navController.navigate(Screen.LoginScreen.route)
    }
    */
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Faction Rules",true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight())
            {
                TeamRulesScreen(navController)
            }
        }
    }
}

@Composable
fun ShowHistoryScreen(navController : NavController,dbViewModel: DatabaseViewModel)
{
    val context = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
    }

    if(!googleAuthUiClient.isUserSignedIn())
    {
        navController.navigate(Screen.LoginScreen.route)
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()


    //val dbViewModel = viewModel<DatabaseViewModel>()

    LaunchedEffect(Unit) {
        dbViewModel.getUserData(googleAuthUiClient.getSignedInUser())
    }

    //dbViewModel.saveUserData(googleAuthUiClient.getSignedInUser())
    //dbViewModel.getUserData(googleAuthUiClient.getSignedInUser())



    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        }) {
        Scaffold(
            topBar = {
                AppBar(navController,"Game history",true,{ scope.launch { drawerState.open()}})
            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight())
            {
                GameList(navController,dbViewModel)
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
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
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
                Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
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
                Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
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
                                    Screen.UnitPreviewScreen.UnitPreviewRoute(
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
                                    Screen.UnitPreviewScreen.UnitPreviewRoute(
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
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                PreviewScreen(navController, viewModel, firstPlayer, index)
            }
        }
    }
}

@Composable
fun ShowGamePreviewScreen(navController : NavController,dbViewModel: DatabaseViewModel,index : Int)
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
                AppBar(navController, "Game Preview",true,{ scope.launch { drawerState.open()}})
            },
            bottomBar = {

            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                GamePreviewScreen(navController,dbViewModel,index)
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
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                AttackScreen(navController, viewModel, firstPlayer, unitIndex, weaponIndex)
            }
        }
    }
}

@Composable
fun ShowFractionRulesScreen(navController : NavController,teamName: String)
{
    var factionTeam : TeamInfo = KillTeams.AngelsOfDeath

    KillTeams.teamList.forEach { team ->
        if(team.name == teamName)
        {
            factionTeam = team
        }
    }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            NavigationMenu(navController,getMenuItem())
        })
    {
        Scaffold(
            topBar = {
                AppBar(navController,teamName,true,{ scope.launch { drawerState.open()}})
            },
            bottomBar = {

            }
        )
        { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding).background(KTColors.Background).fillMaxHeight()) {
                FactionRules(navController,factionTeam)
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
