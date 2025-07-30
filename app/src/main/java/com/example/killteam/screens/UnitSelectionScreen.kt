package com.example.killteam.screens

import com.example.killteam.Objects.Operator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.RemoveKeyWord
import com.example.killteam.ScoreViewModel
import com.example.killteam.ui.theme.KTColors

//Componet for selecting and removing operators
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun UnitSelectionScreen(navController : NavController, viewModel: ScoreViewModel, firstPlayer : Boolean)
{
    Column()
    {
        Box(modifier = Modifier.weight(0.9f).fillMaxWidth())
        {
            LazyColumn(modifier = Modifier.fillMaxSize())
            {
                item()
                {
                    var noTroops by remember { mutableStateOf(10.dp) }

                    Box(
                        modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center)
                    {
                        Text("Selected Operators",style = TextStyle(fontSize = 32.sp),color = Color.White)
                    }
                    if(viewModel.GetPlayer(firstPlayer).GetSelectedTroops().isEmpty())
                    {
                        Spacer(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp))
                    }
                }
                item()
                {
                    viewModel.GetPlayer(firstPlayer).GetSelectedTroops().forEach { operator ->
                        OperatorButton(viewModel,firstPlayer,operator.operator,false)
                    }
                }
                item()
                {
                    Box(
                        modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center)
                    {
                        Text("Operators",style = TextStyle(fontSize = 32.sp),color = Color.White)
                    }
                }
                item()
                {
                    viewModel.GetPlayer(firstPlayer).GetTeam().operators.forEach { operator ->
                        if(operator.specialist && !viewModel.GetPlayer(firstPlayer).isOperatorSelected(operator)) //if show only unselected operators
                        {
                            OperatorButton(viewModel,firstPlayer,operator,true)
                        }
                        if(!operator.specialist)
                        {
                            OperatorButton(viewModel,firstPlayer,operator,true)
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.weight(0.1f).fillMaxWidth())
        {
            Row(Modifier.fillMaxWidth())
            {
                Button(
                    modifier = Modifier.weight(1.0f).fillMaxHeight().padding(5.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = KTColors.Orange),
                    onClick = {
                        navController.popBackStack()
                        viewModel.GetPlayer(firstPlayer).ClearTroopSelection()}
                )
                {
                    Text("Cancel")
                }
                Button(
                    modifier = Modifier.weight(1.0f).fillMaxHeight().padding(5.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = KTColors.Orange),
                    enabled = viewModel.GetPlayer(firstPlayer).ValidateTeam(),
                    onClick = { viewModel.GetPlayer(firstPlayer).selectTeam()}
                )
                {
                    Text("Confirm")
                }
            }
        }
    }
}

@Composable
fun OperatorButton(viewModel: ScoreViewModel, firstPlayer : Boolean, operator: Operator, Adding : Boolean)
{
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth().padding(10.dp).background(KTColors.Operator),
        contentAlignment = Alignment.CenterStart)
    {
        Button(
            modifier = Modifier.fillMaxSize().background(Color.Transparent).padding(5.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.White),
            onClick = { showDialog = true }
        )
        {
            if(operator.leader)
            {
                Text("${operator.name.RemoveKeyWord(viewModel,firstPlayer)}", style = TextStyle(fontSize = 22.sp), textAlign = TextAlign.Center,fontWeight = FontWeight.Bold)
            }
            else
            {
                Text("${operator.name.RemoveKeyWord(viewModel,firstPlayer)}", style = TextStyle(fontSize = 20.sp), textAlign = TextAlign.Center)
            }
            if(showDialog)
            {
                TroopSelectionInfoDialog(
                    KTColors.Orange,firstPlayer, operator,viewModel,Adding,
                    {showDialog = false},   //On Dismiss
                    { finish -> if(finish)  //On Accept (Add Troop)
                        {
                            //Add new troop
                            viewModel.GetPlayer(firstPlayer).AddTroop(operator)
                            //.SwitchEqPlacement(eqSelection.index)
                        }
                        showDialog = false
                    },
                    { finish -> if(finish)  //On Accept (Remove Troop)
                        {
                            //Remove troop
                            viewModel.GetPlayer(firstPlayer).RemoveTroop(operator)
                            //viewModel.GetPlayer(firstPlayer).SwitchEqPlacement(eqSelection.index)
                        }
                        showDialog = false
                    })
            }
        }
    }
}

//Dialog Window which ask player is he want to confirm selected troops
@Composable
fun TroopSelectionInfoDialog(
    color : Color,
    firstPlayer : Boolean,
    operator: Operator,
    viewModel: ScoreViewModel,
    Adding : Boolean,
    onDismiss: () -> Unit,
    AddTroop: (Boolean) -> Unit,
    RemoveTroop: (Boolean) -> Unit
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
                Text("${operator.name.RemoveKeyWord(viewModel,firstPlayer)}", textAlign = TextAlign.Center)
            }
        },
        text = {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    if(Adding)
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                            onClick = {AddTroop(true)}
                        )
                        {
                            Text("Add")
                        }
                    }
                    else
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                            onClick = {RemoveTroop(true)}
                        )
                        {
                            Text("Remove")
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}