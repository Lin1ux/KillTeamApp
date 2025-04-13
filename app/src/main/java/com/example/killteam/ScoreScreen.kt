package com.example.killteam

import android.graphics.Paint.Align
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.ui.theme.KTColors
import java.nio.file.WatchEvent

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun ScoreScreen(viewModel: ScoreViewModel,navController : NavController)
{
    LazyColumn(modifier = Modifier.fillMaxSize().background(KTColors.Background).padding(horizontal = 5.dp))
    {
        item()
        {
            GameResult(viewModel)   //Result of the game

        }
        item()
        {
            RoundBar(viewModel)    //Show which is current round
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
fun RoundBar(viewModel: ScoreViewModel)
{

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
            //Spacer(modifier = Modifier.weight(1.0f).fillMaxWidth())
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(1)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(1) }
            )
            {
                    Text("1", style = TextStyle(color = viewModel.GetTextRoundColor(1), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(2)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(2) }
            )
            {
                Text("2",style = TextStyle(color = viewModel.GetTextRoundColor(2), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(3)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(3) }
            )
            {
                Text("3",style = TextStyle(color = viewModel.GetTextRoundColor(3), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(4)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(4) }
            )
            {
                Text("4",style = TextStyle(color = viewModel.GetTextRoundColor(4), fontSize = 16.sp))
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
        Text("${viewModel.GetAllPoints(true)}:${viewModel.GetAllPoints(false)}",style = TextStyle(fontSize = 48.sp))
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
                value = viewModel.GetTeam(firstPlayer).name,
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
                            viewModel.SetTeam(firstPlayer,team)
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
    viewModel: ScoreViewModel)
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
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,0) },
                        contentPadding = PaddingValues(0.dp)
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,0))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,0) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,1) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,1))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,1) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }

                    }
                }

            }
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,2) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,2))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,2) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,3) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,3))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,3) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
               // Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,4) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,4))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,4) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,5) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,5))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,5) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
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
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,0) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,0))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,0) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,1) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,1))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,1) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }
                    }
                }

            }
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,2) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,2))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,2) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,3) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,3))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,3) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                // Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,4) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,4))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,4) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,5) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,5))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,5) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
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
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,0))
                        )
                    }
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,1))
                        )
                    }
                }

            }
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,2))
                        )
                    }
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,3))
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.background(KTColors.Background).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                // Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,4))
                        )
                    }
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,5))
                        )

                    }
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
                    Text("${viewModel.GetCP(firstPlayer)}",style = TextStyle(fontSize = 48.sp))
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
                            onClick = { viewModel.IncreaseCP(firstPlayer) },
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
                            onClick = { viewModel.DecreaseCP(firstPlayer) },

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
                            value = viewModel.GetTacOp(firstPlayer).name,
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
                            getMissions(viewModel.GetTeam(firstPlayer)).forEachIndexed()
                            {   index, mission ->
                                DropdownMenuItem(
                                    text = { Text(mission.name) },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    onClick = {
                                        viewModel.SetTacOp(firstPlayer,mission)
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
                    enabled = !viewModel.IsPrimaryOpSelected(firstPlayer),
                    onClick = { showDialog = true }
                )
                {
                    Text(viewModel.GetPrimaryInfo(firstPlayer),style = TextStyle(fontSize = 16.sp,color = Color.White),textAlign = TextAlign.Center)

                    //Ask Player for Primary Op
                    if(showDialog && !viewModel.gameFinished)
                    {
                        PrimaryMissionDialogWindow(
                            color,
                            {showDialog = false},
                            {
                                pointType -> viewModel.SetPrimaryOp(firstPlayer, pointType)
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
