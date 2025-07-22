package com.example.killteam.screens

import com.example.killteam.Objects.PloyType
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.Actions
import com.example.killteam.FormatTextWithMarkers
import com.example.killteam.GetAlphaFromPloySelecion
import com.example.killteam.GetOneTypePloysSelection
import com.example.killteam.InteractableWeaponsList
import com.example.killteam.IsInjured
import com.example.killteam.ScoreViewModel
import com.example.killteam.WeaponsListSmall
import com.example.killteam.eqSelection
import com.example.killteam.ploySelection
import com.example.killteam.ployToColor
import com.example.killteam.ui.theme.KTColors

//Show Fraction values
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FractionScreen(navController : NavController,viewModel: ScoreViewModel, firstPlayer: Boolean) {
    LazyColumn(modifier = Modifier.fillMaxSize().background(KTColors.Background)) {

        item()  //Strategy Ploy Label
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Fraction Rules",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item()
        {
            viewModel.GetPlayer(firstPlayer).GetTeamRulesData().forEach { teamRule ->
                teamRule.rule.Draw(viewModel,firstPlayer)
            }
        }
        item()  //Strategy Ploy Label
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Strategy Ploys",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item()  //Strategy Ploys button
        {
            GetOneTypePloysSelection(
                viewModel.GetPlayer(firstPlayer).GetPloysBySelection(),
                PloyType.STRATEGY
            ).forEach { element ->
                Ploys(viewModel, firstPlayer, element)
            }
        }
        item() //Firefight ploy label
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Firefight Ploys",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item() //Firefight Ploys button
        {
            GetOneTypePloysSelection(
                viewModel.GetPlayer(firstPlayer).GetPloysBySelection(),
                PloyType.FIREFIGHT
            ).forEach { element ->
                Ploys(viewModel, firstPlayer, element)
            }
        }
        item() //Selected Equipment label
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Selected Equipment",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item() //Equipment buttons
        {
            var selectedAmount : Int = 0
            viewModel.GetPlayer(firstPlayer).GetEqBySelection().forEach { element ->

                if(element.selected)
                {
                    selectedAmount++
                    Equipment(navController,viewModel, firstPlayer, element)
                }
            }
            if(selectedAmount == 0) //Spacer to prevent merging of to labels
            {
                Spacer(modifier = Modifier.fillMaxWidth().padding(bottom = 5.dp))
            }
        }
        item() //Equipment label
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Equipment",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item() //equipment buttons
        {
            viewModel.GetPlayer(firstPlayer).GetEqBySelection().forEach { element ->
                if(!element.selected)
                {
                    Equipment(navController,viewModel, firstPlayer, element)
                }
            }
        }
        item() //Spacer to make space between last equipment button and system navigation
        {
            Spacer(modifier = Modifier.fillMaxWidth().padding(10.dp))
        }
    }
}

@Composable
fun Ploys(viewModel: ScoreViewModel, firstPlayer: Boolean, ploySelection: ploySelection)
{
    var color by remember { mutableStateOf( ployToColor(ploySelection.ploy.type).copy(alpha = GetAlphaFromPloySelecion(
        ploySelection
    )
    )) }
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 10.dp,end = 20.dp,start = 10.dp).drawBehind //top line
    {
        val strokeWidth = 10f
        val x = size.width - strokeWidth


        drawLine(
            color = color,
            start = Offset(0f, -strokeWidth*2), //(0,0) at top-left point of the box
            end = Offset(x+strokeWidth*3, -strokeWidth*2), //top-right point of the box
            strokeWidth = strokeWidth
        )
    }.drawBehind //Right line
    {
        val strokeWidth = 10f
        val x = size.width - strokeWidth
        val y = size.height - strokeWidth


        drawLine(
            color = color,
            start = Offset(x+strokeWidth*3, -strokeWidth*2), //top-right point of the box
            end = Offset(x+strokeWidth*3, y+strokeWidth), //top-right point of the box
            strokeWidth = strokeWidth
        )
    },
        contentAlignment = Alignment.Center)
    {
        Button(
            modifier = Modifier.fillMaxSize().background(color),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { showDialog = true }
            )
        {
            Row(modifier = Modifier.fillMaxSize().background(Color.Transparent))
            {
                Box(
                    modifier = Modifier.weight(0.75f).fillMaxSize().background(Color.Transparent),
                    contentAlignment = Alignment.CenterStart

                )
                {
                    Text("${ploySelection.ploy.name}",
                        style = TextStyle(fontSize = 20.sp),
                        color = Color.White,textAlign = TextAlign.End,
                        softWrap = false,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Box(
                    modifier = Modifier.weight(0.25f).fillMaxSize().background(Color.Transparent),
                    contentAlignment = Alignment.CenterEnd
                )
                {
                    Text("${ploySelection.ploy.cost} ${if(ploySelection.ploy.changable_cost) {"+"} else {""}}",style = TextStyle(fontSize = 16.sp),color = Color.White, textAlign = TextAlign.End,)
                }
            }
            if(showDialog)
            {
                PloyInfoDialog(
                    KTColors.Orange,firstPlayer,ploySelection,viewModel,
                    {showDialog = false},   //On Dismiss
                    { finish -> if(finish)  //On Accept for Free
                        {
                            viewModel.GetPlayer(firstPlayer).SwitchPloyActivation(ploySelection.index)
                            color =  ployToColor(ploySelection.ploy.type).copy(alpha = GetAlphaFromPloySelecion(
                                viewModel.GetPlayer(firstPlayer)
                                    .GetPloysBySelection()[ploySelection.index]
                            )
                            )
                        }
                        showDialog = false
                    })

            }
        }
    }
}
//Dialog Window which ask player is game should be ended
@Composable
fun PloyInfoDialog(
    color : Color,
    firstPlayer : Boolean,
    ploySelection: ploySelection,
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
                Text("${ploySelection.ploy.name}", textAlign = TextAlign.Center)
            }
        },
        text = {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.Center)
                    {
                        Text(FormatTextWithMarkers(ploySelection.ploy.description),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center)
                    {
                            Text("Remained: ${viewModel.GetPlayer(firstPlayer).GetCP()}CP",style = TextStyle(fontSize = 16.sp))
                    }
                    if(ploySelection.selected)
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                            onClick = {onAccept(true)}
                        )
                        {
                            Text("Turn Off")
                        }
                    }
                    else
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                            onClick = {onAccept(true)}
                        )
                        {
                            Text("Play for 0CP")
                        }
                        if(viewModel.GetPlayer(firstPlayer).GetCP() > 0)
                        {
                            Button(
                                modifier = Modifier.fillMaxWidth().padding(5.dp),
                                shape = RectangleShape,
                                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                                onClick = {
                                    onAccept(true)
                                    viewModel.GetPlayer(firstPlayer).DecreaseCP()
                                }
                            )
                            {
                                Text("Play for 1CP")
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )

}

//Component giving information about selected equipment
@Composable
fun Equipment(navController: NavController,viewModel: ScoreViewModel, firstPlayer: Boolean, eqSelection: eqSelection)
{
    Box(
        modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Equipment,RectangleShape)
    )
    {
        var showDialog by remember { mutableStateOf(false) }

        Button(
            modifier = Modifier.fillMaxSize().background(Color.Transparent),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            onClick = { showDialog = true }
        )
        {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            )
            {
                Text("${viewModel.GetPlayer(firstPlayer).GetTeam().equipment[eqSelection.index].name}",
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Start,
                    color = KTColors.Equipment)
            }
            if(showDialog)
            {
                EquipmentInfoDialog(
                    color = KTColors.Orange,
                    eqSelection = eqSelection,
                    onDismiss =  {showDialog = false},   //On Dismiss
                    onAccept =  { finish -> if(finish)  //On Accept
                        {
                            viewModel.GetPlayer(firstPlayer).SwitchEqPlacement(eqSelection.index)
                        }
                        showDialog = false
                    })
            }
        }
    }
}

//Dialog Window which allows player to take or remove equipment
@Composable
fun EquipmentInfoDialog(
    color : Color,
    eqSelection: eqSelection,
    onDismiss: () -> Unit,
    onAccept: (Boolean) -> Unit,
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
                Text("${eqSelection.eq.name}", textAlign = TextAlign.Center)
            }
        },
        text = {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                item()
                {
                    //Description
                    Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp), contentAlignment = Alignment.Center)
                    {
                        Text(FormatTextWithMarkers(eqSelection.eq.description),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }
                    //Weapons
                    if(!eqSelection.eq.weapons.isEmpty())
                    {
                        WeaponsListSmall(eqSelection.eq.weapons,false)
                        //Check if team was selected
                        /*if(viewModel.GetPlayer(firstPlayer).troopsSelected)
                        {
                            InteractableWeaponsList(navController,eqSelection.eq.weapons,false,firstPlayer,0)
                        }
                        else
                        {

                        }*/

                        //
                    }
                    //Actions
                    if(!eqSelection.eq.actions.isEmpty())
                    {
                        Actions(eqSelection.eq.actions)
                    }
                    if(eqSelection.selected)    //Remove Button
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                            onClick = {onAccept(true)}
                        )
                        {
                            Text("Remove")
                        }
                    }
                    else    //Add Button
                    {
                        Button(
                            modifier = Modifier.fillMaxWidth().padding(5.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = color),
                            onClick = {onAccept(true)}
                        )
                        {
                            Text("Add")
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}