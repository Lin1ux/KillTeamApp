package com.example.killteam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.killteam.Actions
import com.example.killteam.FormatTextWithMarkers
import com.example.killteam.Objects.Archetype
import com.example.killteam.Objects.Mission
import com.example.killteam.Objects.TacOps
import com.example.killteam.ui.theme.KTColors

//Screen with list of Tacops rules
@Composable
fun TacopScreen()
{
    LazyColumn()
    {
        item()
        {
            TacopRule(TacOps.Recon)
        }
        item()
        {
            TacopRule(TacOps.SeekDestroy)
        }
        item()
        {
            TacopRule(TacOps.Security)
        }
        item()
        {
            TacopRule(TacOps.Infiltration)
        }
    }
}

//Show Tacop List based on archetype
@Composable
fun TacopRule(archetype : Archetype)
{
    var showDialog by remember { mutableStateOf(false) }
    var selectedTacop by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.
        padding(5.dp).
        fillMaxWidth().
        border(shape = RectangleShape,width = 2.dp,color = archetype.color)
    )
    {
        Box(
            modifier = Modifier.fillMaxWidth().background(archetype.color),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                modifier = Modifier.padding(5.dp),
                text = archetype.name,
                color = Color.White,
                style = TextStyle(fontSize = 32.sp)
            )
        }
        archetype.missionList.forEachIndexed()
        { index, mission ->
            Box(
                modifier = Modifier.padding(5.dp).
                fillMaxWidth().
                border(shape = RoundedCornerShape(5.dp), width = 2.dp, color = archetype.color).
                clickable
                {
                    showDialog = true
                    selectedTacop = index
                },
                contentAlignment = Alignment.Center
            )
            {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = mission.name,
                    style = TextStyle(fontSize = 32.sp)
                )
            }
        }
        if(showDialog)
        {
            TacOpPopUp(
                mission = archetype.missionList[selectedTacop],
                color = archetype.color,
                onDismiss = {showDialog = false}
            )
        }
    }
}

//Show Info about Tacop
@Composable
fun TacOpPopUp(
    mission : Mission,
    color : Color,
    onDismiss: () -> Unit
)
{
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = {
            Box(
                modifier = Modifier.fillMaxWidth().background(color,RoundedCornerShape(5.dp)).border(shape = RoundedCornerShape(5.dp), width = 2.dp, color = color),
                contentAlignment = Alignment.Center,
            )
            {
                Text(mission.name, textAlign = TextAlign.Center,color = Color.White)
            }
        },
        text = {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally)
            {
                //Reveal
                item()
                {
                    Column(modifier = Modifier.border(shape = RectangleShape,width = 2.dp,color = KTColors.Blue))
                    {
                        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).background(KTColors.Blue)
                            , contentAlignment = Alignment.Center)
                        {
                            Text("Reveal",style = TextStyle(fontSize = 20.sp), textAlign = TextAlign.Center,color = Color.White)
                        }
                        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp,start = 5.dp,end = 5.dp), contentAlignment = Alignment.Center)
                        {
                            Text(FormatTextWithMarkers(mission.reveal),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                        }
                    }
                }
                //Additional rules
                if(mission.additionalRules != ""){
                    item()
                    {
                        Column(modifier = Modifier.border(shape = RectangleShape,width = 2.dp,color = KTColors.Blue))
                        {
                            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).background(KTColors.Blue),
                                contentAlignment = Alignment.Center)
                            {
                                Text("Additional Rules",style = TextStyle(fontSize = 20.sp), textAlign = TextAlign.Center,color = Color.White)
                            }
                            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp,start = 5.dp,end = 5.dp), contentAlignment = Alignment.Center)
                            {
                                Text(FormatTextWithMarkers(mission.additionalRules),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                            }
                        }
                    }
                }
                //Mission Action
                if(mission.actions.isNotEmpty())
                {
                    item()
                    {
                        Column(modifier = Modifier.border(shape = RectangleShape,width = 2.dp,color = KTColors.Blue))
                        {
                            Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).background(KTColors.Blue),
                                contentAlignment = Alignment.Center)
                            {
                                Text("Mission Action",style = TextStyle(fontSize = 20.sp), textAlign = TextAlign.Center,color = Color.White)
                            }
                            Actions(mission.actions,20.sp)
                        }
                    }
                }
                //Victory Points
                item()
                {
                    Column(modifier = Modifier.border(shape = RectangleShape,width = 2.dp,color = KTColors.Blue))
                    {
                        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp).background(KTColors.Blue),
                            contentAlignment = Alignment.Center)
                        {
                            Text("Victory Points", style = TextStyle(fontSize = 20.sp), textAlign = TextAlign.Center,color = Color.White)
                        }
                        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp,start = 5.dp,end = 5.dp), contentAlignment = Alignment.Center)
                        {
                            Text(FormatTextWithMarkers(mission.victoryPoints), style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                        }
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}