package com.example.killteam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.killteam.InfoPopUp
import com.example.killteam.Objects.Equipment
import com.example.killteam.Objects.Operator
import com.example.killteam.Objects.Ploy
import com.example.killteam.Objects.PloyType
import com.example.killteam.Objects.TeamInfo
import com.example.killteam.RemoveKeyWord
import com.example.killteam.WeaponsList
import com.example.killteam.WeaponsListSmall
import com.example.killteam.ployToColor
import com.example.killteam.ui.theme.KTColors


//Show informations about faction
@Composable
fun FactionRules(navController: NavController,team: TeamInfo)
{
    var selected by remember { mutableStateOf(FactionRulesPage.Rules) }

    LazyColumn()
    {
        item()
        {
            Row()
            {
                Button(modifier = Modifier.padding(2.5.dp).weight(0.25f),
                    onClick = { selected = FactionRulesPage.Rules },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(selected == FactionRulesPage.Rules) KTColors.Orange else KTColors.Background,
                        contentColor = if(selected == FactionRulesPage.Rules) Color.White else Color.Black
                    ),
                    contentPadding = PaddingValues(1.dp)
                )
                {
                    Text("Rules")
                }
                Button(modifier = Modifier.padding(2.5.dp).weight(0.25f),
                    onClick = { selected = FactionRulesPage.Operators },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(selected == FactionRulesPage.Operators) KTColors.Orange else KTColors.Background,
                        contentColor = if(selected == FactionRulesPage.Operators) Color.White else Color.Black
                    ),
                    contentPadding = PaddingValues(1.dp)
                )
                {
                    Text("Operators")
                }
                Button(modifier = Modifier.padding(2.5.dp).weight(0.25f),
                    onClick = { selected = FactionRulesPage.Tacoops },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(selected == FactionRulesPage.Tacoops) KTColors.Orange else KTColors.Background,
                        contentColor = if(selected == FactionRulesPage.Tacoops) Color.White else Color.Black
                    ),
                    contentPadding = PaddingValues(1.dp)
                )
                {
                    Text("Tacoops")
                }
                Button(modifier = Modifier.padding(2.5.dp).weight(0.25f),
                    onClick = { selected = FactionRulesPage.Equipment },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if(selected == FactionRulesPage.Equipment) KTColors.Orange else KTColors.Background,
                        contentColor = if(selected == FactionRulesPage.Equipment) Color.White else Color.Black),
                    contentPadding = PaddingValues(1.dp)
                )
                {
                    Text("Equipment")
                }
            }
        }
        item()
        {
            Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Orange))
        }
        item()
        {
            if(selected == FactionRulesPage.Rules)
            {
                PloysAndRules(team)
            }
            if(selected == FactionRulesPage.Operators)
            {
                OperatorsRules(team)
            }
            if(selected == FactionRulesPage.Tacoops)
            {
                Column()
                {
                    team.archetypes.forEach { archetype ->
                        TacopRule(archetype)
                    }
                }
            }
            if(selected == FactionRulesPage.Equipment)
            {
                FactionEquipment(team)
            }
        }
    }
}
//Show Ploys and Faction rules
@Composable
fun PloysAndRules(team : TeamInfo)
{
    Column()
    {
        //draw faction rules
        Box(
            modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Faction Rules",style = TextStyle(fontSize = 32.sp),color = Color.White)
        }
        team.teamRules.forEach { teamRule ->
            teamRule.DrawNoSelectable()
        }
        //Draw strategy ploys
        Box(
            modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Strategy Ploys",style = TextStyle(fontSize = 32.sp),color = Color.White)
        }
        team.ploys.forEach { ploy ->
            if(ploy.type == PloyType.STRATEGY)
            {
                ShowPloy(ploy)
            }
        }
        //Draw firefight ploys
        Box(
            modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Firefight Ploys",style = TextStyle(fontSize = 32.sp),color = Color.White)
        }
        team.ploys.forEach { ploy ->
            if(ploy.type == PloyType.FIREFIGHT)
            {
                ShowPloy(ploy)
            }
        }
    }
}
//Draw button which show description of ploy
@Composable
fun ShowPloy(ploy: Ploy)
{
    var color by remember { mutableStateOf( ployToColor(ploy.type)) }
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
                    Text("${ploy.name}",
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
                    Text("${ploy.cost} ${if(ploy.changable_cost) {"+"} else {""}}",style = TextStyle(fontSize = 16.sp),color = Color.White, textAlign = TextAlign.End,)
                }
            }
            if(showDialog)
            {
                InfoPopUp(
                    title = ploy.name,
                    description = ploy.description,
                    onDismiss = { showDialog = false }
                )
            }
        }
    }
}

//Show List of operators
@Composable
fun OperatorsRules(team : TeamInfo)
{
    team.operators.forEach { operator ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(KTColors.Blue)
                .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center)
        {
            Text("${operator.name.RemoveKeyWord(team.name)}",
                style = TextStyle(fontSize = 32.sp),
                color = Color.White,
                textAlign = TextAlign.Center)
        }
        OperatorStaticStats(operator)
        WeaponsList(operator.weapons,false)
        AddiotionalRules(operator.additionalRules)
        Actions(operator.actions)
    }
}

@Composable
fun OperatorStaticStats(operator: Operator)
{
    //Stats
    Row(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    )
    {
        //APL stat
        Column(modifier =  Modifier
            .weight(0.2f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 15.dp)))
        {
            //APL Label
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("APL",style = TextStyle(fontSize = 14.sp),color = Color.White, textAlign =  TextAlign.Center)
            }
            //APL Value
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("${operator.APL}",style = TextStyle(fontSize = 36.sp),color = Color.White, textAlign =  TextAlign.Center)
            }
        }
        //Move stat
        Column(modifier =  Modifier
            .weight(0.25f)
            .fillMaxHeight())
        {
            //move label
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("MOVE",style = TextStyle(fontSize = 14.sp),color = Color.White, textAlign =  TextAlign.Center)
            }
            //move value
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("${operator.move}\"",style = TextStyle(fontSize = 36.sp),color = Color.White)
            }
        }
        //Save stat
        Column(modifier =  Modifier
            .weight(0.25f)
            .fillMaxHeight())
        {
            //Save Label
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("SAVE",style = TextStyle(fontSize = 14.sp),color = Color.White, textAlign =  TextAlign.Center)
            }
            //save value
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("${operator.save}+",style = TextStyle(fontSize = 36.sp),color = Color.White, textAlign =  TextAlign.Center)
            }
        }
        //Wound stat
        Column(modifier =  Modifier
            .weight(0.3f)
            .fillMaxHeight()
            .clip(RoundedCornerShape(topEnd = 15.dp)))
        {
            //wound label
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Wound",style = TextStyle(fontSize = 14.sp),color = Color.White, textAlign =  TextAlign.Center)
            }
            Box(modifier =  Modifier
                .fillMaxSize()
                .background(KTColors.PreviewOperator)
                .padding(5.dp),
                contentAlignment = Alignment.Center)
            {
                //current wound value / max wounds value
                Text("${operator.wounds}",
                    style = TextStyle(fontSize = 36.sp),
                    color = Color.White,
                    textAlign =  TextAlign.Center)
            }
        }
    }
}

//Show Faction's equipment
@Composable
fun FactionEquipment(team : TeamInfo)
{
    var showDialog by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(0) }

    team.equipment.forEachIndexed { index, equipment ->
        Box(
            modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Equipment,RectangleShape)
        )
        {

            Button(
                modifier = Modifier.fillMaxSize().background(Color.Transparent),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = { showDialog = true
                            selected = index
                }
            )
            {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                )
                {
                    Text("${equipment.name}",
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment)
                }
                if(showDialog && selected == index)
                {
                    EquipmentInfoPopUp(eq = equipment, onDismiss =  {showDialog = false})
                }
            }
        }
    }
}
//Dialog Window show information about equipment
@Composable
fun EquipmentInfoPopUp(
    eq: Equipment,
    onDismiss: () -> Unit,
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
                Text("${eq.name}", textAlign = TextAlign.Center)
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
                        Text(FormatTextWithMarkers(eq.description),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }
                    //Weapons
                    if(!eq.weapons.isEmpty())
                    {
                        WeaponsListSmall(eq.weapons,false)
                    }
                    //Actions
                    if(!eq.actions.isEmpty())
                    {
                        Actions(eq.actions)
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}

//Enum class for selected information
enum class FactionRulesPage{
    Rules,
    Operators,
    Tacoops,
    Equipment
}

