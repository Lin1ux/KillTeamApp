package com.example.killteam.screens

import com.example.killteam.Objects.KillTeams
import com.example.killteam.Objects.PointType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.R
import com.example.killteam.ScoreViewModel
import com.example.killteam.Screen
import com.example.killteam.firebase.DatabaseViewModel
import com.example.killteam.firebase.GoogleAuthUIClient
import com.example.killteam.getMissions
import com.example.killteam.ui.theme.KTColors
import com.google.android.gms.auth.api.identity.Identity

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ScoreScreen(viewModel: ScoreViewModel,dbViewModel: DatabaseViewModel, navController : NavController,isLogedIn : Boolean = false)
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        NewGameButton(navController,viewModel, dbViewModel,isLogedIn,viewModel.isGameSaved())
        LazyColumn(modifier = Modifier.fillMaxSize().background(KTColors.Background).padding(horizontal = 5.dp))
        {
            item()
            {
                GameResult(viewModel)   //Result of the game

            }
            item()
            {
                RoundBar(viewModel,dbViewModel)    //Show which is current round
            }
            item()
            {
                PlayerInfo(KTColors.Red,true,viewModel,navController)     //Show info about Red Player
            }
            item()
            {
                PlayerInfo(KTColors.Blue,false,viewModel,navController)   //Show info about Blue Player
            }
        }
    }
}

//Show Info about player
@Composable
fun PlayerInfo(color : Color,
               firstPlayer : Boolean,
               viewModel: ScoreViewModel,
               navController : NavController)
{
    Column(modifier = Modifier.padding(top = 5.dp).background(KTColors.Background))
    {
        Box()
        {
            TeamSelection(color,firstPlayer,viewModel)  //Selection of team
        }
        Box()
        {
            ScorePoints(color,firstPlayer,viewModel)    //Show Points which can be selected during game
        }
        Box()
        {
            PlayerSelection(color,firstPlayer,viewModel)    // Show other info like Command Points, Tac ops and Primary Objective
        }
        Box()
        {
            PlayerTeamInfo(color,firstPlayer,viewModel,navController)     //Buttons which navigates to team info sections
        }

    }

}

//Creates bar for managing rounds
@Composable
fun RoundBar(viewModel: ScoreViewModel,dbViewModel: DatabaseViewModel)
{

    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp).border(2.dp, KTColors.Orange, RectangleShape)
    )
    {
        Row(
            modifier = Modifier.fillMaxSize().background(KTColors.Background),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            var showDialog by remember { mutableStateOf(false) }
            var buttonValue by remember { mutableStateOf(1) }
            var showInitiativeDialog by remember { mutableStateOf(false) }

            for(i in 1..4)
            {
                Button(
                    modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                    colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(i)),
                    shape = RectangleShape,
                    onClick = {
                        showInitiativeDialog = true
                        buttonValue = i
                    }
                )
                {
                    Text("${i}", style = TextStyle(color = viewModel.GetTextRoundColor(i), fontSize = 16.sp))
                }
            }
            if(showInitiativeDialog)
            {
                InitiativeDialogWindow(
                    viewModel = viewModel,
                    onDismiss =  {
                        viewModel.ChangeRound(buttonValue)
                        showInitiativeDialog = false},
                    onAccept = { finish ->
                        viewModel.ChangeRound(buttonValue)
                        viewModel.SetInitiative(finish,buttonValue-1)
                        showInitiativeDialog = false
                    }
                )
            }
            Button(
                modifier = Modifier.weight(1.0f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(5)),
                shape = RectangleShape,
                onClick = { showDialog = true }
            )
            {
                Text("End",style = TextStyle(color = viewModel.GetTextRoundColor(5), fontSize = 16.sp))
                if(showDialog && !viewModel.gameFinished)
                {
                    EndGameDialogWindow(
                        KTColors.Orange,{showDialog = false},
                        { finish -> if(finish)
                            {
                                viewModel.ChangeRound(5)
                                viewModel.FinishGame()

                                val googleAuthUiClient by lazy {
                                    GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
                                }
                                if(googleAuthUiClient.isUserSignedIn())
                                {
                                    viewModel.SaveGame(dbViewModel,googleAuthUiClient.getSignedInUser())
                                }
                            }
                            showDialog = false
                        })

                }
            }
        }
    }
}

@Composable
fun GameResult(viewModel : ScoreViewModel)
{
    Box(
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text("${viewModel.GetPlayer(true).GetAllPoints()}:${viewModel.GetPlayer(false).GetAllPoints()}",style = TextStyle(fontSize = 48.sp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSelection(
    color : Color,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel
)
{
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().background(color).padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().background(color),
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        )
        {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                value = viewModel.GetPlayer(firstPlayer).GetTeam().name,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedContainerColor = color,
                    focusedContainerColor = color
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )

            ExposedDropdownMenu(
                expanded = isExpanded && !viewModel.gameFinished,
                onDismissRequest = { isExpanded = false }
            )
            {
                KillTeams.teamList.forEachIndexed()
                {   index, team ->
                    DropdownMenuItem(
                        text = { Text(team.name) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        onClick = {
                            viewModel.GetPlayer(firstPlayer).SetTeam(team)
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ScorePoints(
    color : Color,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel
)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
    )
    {
        //Description
        Row()
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("Ops",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(color).weight(0.2f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(color).weight(0.2f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(color).weight(0.2f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
        }
        //Crit Ops
        Row(
            modifier = Modifier.height(75.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Crit",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            for(i in 0..2)
            {
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    ScoreButton(viewModel,color,firstPlayer,2+i,0+2*i, PointType.CRITOP)
                }
            }
        }
        //Tac Ops
        Row(
            modifier = Modifier.height(75.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Tac",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            for(i in 0..2)
            {
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    ScoreButton(viewModel,color,firstPlayer,2+i,0+2*i, PointType.TACOP)
                }
            }
        }
        //Kill Ops
        Row(
            modifier = Modifier.height(75.dp).drawBehind
            {
                val strokeWidth = 10f
                val x = size.width - strokeWidth

                //top line
                drawLine(
                    color = color,
                    start = Offset(0f, 0f), //(0,0) at top-left point of the box
                    end = Offset(x, 0f), //top-right point of the box
                    strokeWidth = strokeWidth
                )
            }
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Kill",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            for(i in 0..2)
            {
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    KillScoreButton(viewModel,color,firstPlayer,0+i*2)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerSelection(
    color : Color,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel
)
{
    Row(
        modifier = Modifier.fillMaxWidth().padding(5.dp)
    )
    {
        //Command Points panel
        Column(
            modifier = Modifier.weight(1f).fillMaxWidth().background(KTColors.Background).border(2.dp,color,RectangleShape))
        {
            //CP Label
            Box(
                Modifier.fillMaxWidth().background(color).padding(top = 5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("CP",style = TextStyle(fontSize = 16.sp,color=Color.White))
            }
            //CP Manager
            Row(
                Modifier.fillMaxWidth().padding(top = 5.dp)
            )
            {
                //CP Value
                Box(
                    modifier = Modifier.weight(2.0f).fillMaxWidth().padding(15.dp),
                    contentAlignment = Alignment.Center
                )
                {
                    Text("${viewModel.GetPlayer(firstPlayer).GetCP()}",style = TextStyle(fontSize = 48.sp))
                }
                //CP Buttons
                Column(
                    modifier = Modifier.weight(1.0f).fillMaxWidth()
                )
                {
                    //Increment Button
                    Box(
                        modifier = Modifier.fillMaxSize().background(color),
                        contentAlignment = Alignment.Center
                    )
                    {

                        Button(
                            modifier = Modifier.fillMaxSize(),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            onClick = { viewModel.GetPlayer(firstPlayer).IncreaseCP() },
                        ){}
                        Text("+",style = TextStyle(fontSize = 20.sp,color=Color.White))
                    }
                    //Decrement Button
                    Box(
                        modifier = Modifier.fillMaxSize().background(color),
                        contentAlignment = Alignment.Center)
                    {

                        Button(
                            modifier = Modifier.fillMaxSize(),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            onClick = { viewModel.GetPlayer(firstPlayer).DecreaseCP() },

                        ){}
                        Text("-", style = TextStyle(fontSize = 20.sp, color = Color.White))
                    }
                }
            }
        }
        //Missions
        Column(
            modifier = Modifier.weight(1.0f).fillMaxSize().padding(start = 5.dp)
        )
        {
            //TacOp Selection
            Box(
                modifier = Modifier.fillMaxWidth().background(viewModel.GetTacOpColor(firstPlayer)),
                contentAlignment = Alignment.Center
                )
            {
                var isExpanded by remember { mutableStateOf(false) }

                Column(
                    modifier = Modifier.fillMaxWidth().background(viewModel.GetTacOpColor(firstPlayer)).padding(horizontal = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    ExposedDropdownMenuBox(
                        modifier = Modifier.fillMaxWidth().background(viewModel.GetTacOpColor(firstPlayer)),
                        expanded = isExpanded,
                        onExpandedChange = {
                            isExpanded = !isExpanded
                        }
                    )
                    {
                        TextField(
                            modifier = Modifier.menuAnchor().fillMaxWidth(),
                            value = viewModel.GetPlayer(firstPlayer).GetTacOp().name,
                            onValueChange = {},
                            readOnly = true,
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                            colors = TextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                unfocusedContainerColor = viewModel.GetTacOpColor(firstPlayer),
                                focusedContainerColor = viewModel.GetTacOpColor(firstPlayer)
                            ),
                            textStyle = TextStyle(fontSize = 16.sp)
                        )

                        ExposedDropdownMenu(
                            expanded = isExpanded && !viewModel.gameFinished,
                            onDismissRequest = { isExpanded = false }
                        )
                        {
                            getMissions(viewModel.GetPlayer(firstPlayer).GetTeam()).forEachIndexed()
                            {   index, mission ->
                                DropdownMenuItem(
                                    text = { Text(mission.name) },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    onClick = {
                                        viewModel.GetPlayer(firstPlayer).SetTacOp(mission)
                                        isExpanded = false
                                    }
                                )
                            }
                        }
                    }
                }
            }
            //Primary Ops Selection
            Box(
                modifier = Modifier.fillMaxSize().padding(top = 15.dp).background(color),
                contentAlignment = Alignment.BottomCenter
            )
            {
                var showDialog by remember { mutableStateOf(false) }
                Button(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    enabled = !viewModel.GetPlayer(firstPlayer).IsPrimaryOpSelected(),
                    onClick = { showDialog = true }
                )
                {
                    Text(viewModel.GetPlayer(firstPlayer).GetPrimaryInfo(),style = TextStyle(fontSize = 16.sp,color = Color.White),textAlign = TextAlign.Center)

                    //Ask Player for Primary Op
                    if(showDialog && !viewModel.gameFinished)
                    {
                        PrimaryMissionDialogWindow(
                            color,
                            {showDialog = false},
                            {
                                pointType -> viewModel.GetPlayer(firstPlayer).SetPrimaryOp(pointType)
                                showDialog = false
                            })
                    }
                }
            }
        }
    }
}

//Creates box for navigating to sections with team informations
@Composable
fun PlayerTeamInfo(
    color : Color,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel,
    navController : NavController)
{
    Row(
        modifier = Modifier.fillMaxWidth().height(75.dp).padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        //Button which change to fraction screen
        Button(
            modifier = Modifier.weight(1.0f).fillMaxSize().padding(horizontal = 5.dp, vertical = 2.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Color.White),
            onClick = { navController.navigate(Screen.FractionScreen.FractionRoute(firstPlayer)) }
        )
        { Text("Ploys/Equipment") }
        //Button which change to unit screen
        Button(
            modifier = Modifier.weight(1.0f).fillMaxSize().padding(horizontal = 5.dp, vertical = 2.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Color.White),
            onClick = { navController.navigate(Screen.UnitScreen.UnitRoute(firstPlayer)) }
        )
        { Text("Operators") }

    }
}
//Dialog which starts new game
@Composable
fun NewGameButton(
    navController : NavController,
    viewModel: ScoreViewModel,
    dbViewModel: DatabaseViewModel,
    isLogedIn : Boolean = false,
    isGameSave : Boolean = false
)
    {
    //Onlu show when game was finished
    if(viewModel.gameFinished)
    {
        val context = LocalContext.current
        var showDialog by remember { mutableStateOf(false) }
        Row(modifier = Modifier.fillMaxWidth().background(KTColors.Background),
            horizontalArrangement = Arrangement.Center)
        {
            Button(modifier = Modifier.padding(5.dp),
                onClick = { showDialog = true },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = KTColors.Orange)
            )
            {
                Text("New Game")
            }
        }
        if(showDialog)
        {
            NewGameDialogWindow(
                color = KTColors.Orange,
                isLogedIn = isLogedIn,
                isSaved = isGameSave,
                onDismiss = { showDialog = false },
                onLogin = { navController.navigate(Screen.LoginScreen.LoginScreenRoute()) },
                onAccept = {
                                showDialog = false
                                //Save game if wasn't saved
                                if (!isGameSave)
                                {
                                    val googleAuthUiClient by lazy {
                                        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
                                    }
                                    if(googleAuthUiClient.isUserSignedIn())
                                    {
                                        viewModel.SaveGame(dbViewModel,googleAuthUiClient.getSignedInUser())
                                    }
                                }
                                //new Game
                                viewModel.newGame()
                           },

            )
        }

    }
}

//Dialog Window which allows user to select Primary Op
@Composable
fun PrimaryMissionDialogWindow(
    color : Color,
    onDismiss: () -> Unit,
    onAccept: (PointType) -> Unit
)
{
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Select Primary Op")
            }
             },
        text = {
            Column()
            {
                Button(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                    onClick = {onAccept(PointType.CRITOP)}
                )
                {
                    Text("Crit Op")
                }
                Button(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                    onClick = {onAccept(PointType.TACOP)}
                )
                {
                    Text("Tac Op")
                }
                Button(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                    onClick = {onAccept(PointType.KILLOP)}
                )
                {
                    Text("Kill Op")
                }
            }
        },
        confirmButton = {

        },
    )

}
//Dialog Window which ask player is game should be ended
@Composable
fun EndGameDialogWindow(
    color : Color,
    onDismiss: () -> Unit,
    onAccept: (Boolean) -> Unit
)
{
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Do you want finish game?")
            }
        },
        text = {
            Column()
            {
                Text("Finish game would remove possibility to change points, cp, etc.")
                Button(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                    onClick = {onAccept(true)}
                )
                {
                    Text("Confirm")
                }
            }
        },
        confirmButton = {

        }
    )
}

//Dialog Window which ask player is he want to start new game
@Composable
fun NewGameDialogWindow(
    color : Color,
    isLogedIn : Boolean,
    isSaved : Boolean,
    onDismiss: () -> Unit,
    onLogin: () -> Unit,
    onAccept: (Boolean) -> Unit
)
{
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Do you want start new game?")
            }
        },
        text = {
            Column()
            {
                if(isLogedIn)   //Is user logged in
                {
                    if(isSaved) //Is game saved
                    {
                        Text("Game has been saved")
                    }
                    else
                    {
                        Text("Game would be saved when you confirm")
                    }

                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                        onClick = {onAccept(true)}
                    )
                    {
                        Text("Confirm")
                    }
                }
                else
                {
                    Text("If you not logged in, data from this game would be lost")
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                        onClick = {onLogin()}
                    )
                    {
                        Text("Log in")
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                        onClick = {onAccept(true)}
                    )
                    {
                        Text("Confirm")
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}

//Dialog Window which ask player who have initiative
@Composable
fun InitiativeDialogWindow(
    viewModel: ScoreViewModel,
    onDismiss: () -> Unit,
    onAccept: (Boolean) -> Unit
)
{
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Who got initiative?")
            }
        },
        text = {
            Column()
            {
                Text("Select team which would have initiative and will make first activation", textAlign = TextAlign.Center)
                Row()
                {

                    Button(
                        modifier = Modifier.weight(1.0f).padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Red),
                        onClick = {onAccept(true)}
                    )
                    {
                        Text(viewModel.GetPlayer(true).GetTeam().name, minLines = 2, textAlign = TextAlign.Center)
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Blue),
                        onClick = {onAccept(false)}
                    )
                    {
                        Text(viewModel.GetPlayer(false).GetTeam().name, minLines = 2, textAlign = TextAlign.Center)
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}



//Score button with 2 buttons selected to round
@Composable
fun ScoreButton(viewModel : ScoreViewModel,color: Color,firstPlayer : Boolean,unlockedRound : Int,pointIndex : Int,type : PointType)
{
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        for (i in 0..1)
        {
            Button(
                modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp)
                    .border(2.dp, color.copy(viewModel.GetAlphaByRound(unlockedRound)), RectangleShape),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = { if (type == PointType.CRITOP) { viewModel.GetPlayer(firstPlayer).SwitchCritPoints(pointIndex + i) }
                          else { viewModel.GetPlayer(firstPlayer).SwitchTacPoints(pointIndex + i) } },
                contentPadding = PaddingValues(0.dp)
            )
            {
                Box(
                    modifier = Modifier.height(50.dp).fillMaxWidth().background(
                        viewModel.GetButtonPointColor(
                            type, firstPlayer, pointIndex + i
                        )
                    )
                )
                {
                    if (viewModel.GetPlayer(firstPlayer).GetPoint(type, pointIndex + i) == 1) {
                        Image(
                            modifier = Modifier.fillMaxSize().padding(5.dp),
                            contentDescription = "Skull Point",
                            painter = painterResource(id = R.drawable.skull),
                            alpha = viewModel.GetAlphaByRound(unlockedRound)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun KillScoreButton(viewModel : ScoreViewModel,color: Color,firstPlayer : Boolean,pointIndex : Int)
{
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    )
    {
        for(i in 0..1)
        {
            Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
            {
                Box(
                    modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(
                        PointType.KILLOP,firstPlayer,pointIndex + i))
                )
                {
                    if(viewModel.GetPlayer(firstPlayer).GetPoint(PointType.KILLOP,pointIndex + i) == 1)
                    {
                        Image(
                            modifier = Modifier.fillMaxSize().padding(5.dp),
                            contentDescription = "Skull Point",
                            painter = painterResource(id = R.drawable.skull),
                            alpha = viewModel.GetAlphaByRound(5)
                        )
                    }
                }
            }
        }
    }
}
