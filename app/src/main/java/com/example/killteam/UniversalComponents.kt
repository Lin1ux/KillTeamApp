package com.example.killteam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.Objects.Action
import com.example.killteam.Objects.Passive
import com.example.killteam.Objects.SelectionRuleList
import com.example.killteam.Objects.TeamRule
import com.example.killteam.Objects.Weapon
import com.example.killteam.Objects.WeaponType
import com.example.killteam.RuleSelectionInfoDialog
import com.example.killteam.ui.theme.KTColors
import kotlin.collections.forEach

//Dialog Window which ask player is sure he want to do something
@Composable
fun ConfirmDialog(
    title : String,
    description : String,
    confirmText : String,
    onDismiss: () -> Unit,
    onAccept: () -> Unit
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
                Text(title, textAlign = TextAlign.Center)
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
                        Text(description,style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }
                    Button(
                        modifier = Modifier.fillMaxWidth().padding(5.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = KTColors.Orange),
                        onClick = {onAccept()}
                    )
                    {
                        Text("${confirmText}")
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}

//Create Box which show action and it's rules
@Composable
fun Actions(actionList : List<Action>,TitleSize : TextUnit = 24.sp)
{
    //If empty don't create
    if(actionList.isEmpty())
    {
        return
    }
    //creating boxes of every action in list
    actionList.forEach { action ->
        Column(modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Orange,
            RectangleShape
        ))
        {
            //Basic information (name and cost) about action
            Row(modifier = Modifier.fillMaxWidth().background(KTColors.Orange),
                verticalAlignment = Alignment.CenterVertically)
            {
                //label with action name
                Box(modifier = Modifier.weight(0.7f).fillMaxHeight().padding(5.dp))
                {
                    Text(action.name,style = TextStyle(fontSize = TitleSize),color = Color.White, textAlign = TextAlign.Start)
                }
                //label with cost of action (in APL)
                Box(modifier = Modifier.weight(0.3f).fillMaxHeight().padding(5.dp),
                    contentAlignment = Alignment.CenterEnd)
                {
                    Text("${action.cost}APL",style = TextStyle(fontSize = TitleSize),color = Color.White, textAlign = TextAlign.End)
                }
            }
            //Description of action
            Row(modifier = Modifier.fillMaxWidth())
            {
                //Image used as list point
                Box(modifier = Modifier.weight(0.1f).fillMaxHeight().padding(5.dp))
                {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = "Correct Image",
                        painter = painterResource(id = R.drawable.correct),
                        contentScale = ContentScale.FillWidth
                    )
                }
                //Formatted description of action
                Box(modifier = Modifier.weight(0.9f).fillMaxHeight().padding(5.dp))
                {
                    Text(text = FormatTextWithMarkers(action.description),
                        modifier = Modifier.padding(end = 10.dp),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Justify)
                }
            }
            Row(modifier = Modifier.fillMaxWidth())
            {
                //Image used as list point
                Box(modifier = Modifier.weight(0.1f).fillMaxHeight().padding(5.dp))
                {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = "Wrong image",
                        painter = painterResource(id = R.drawable.wrong),
                        contentScale = ContentScale.FillWidth
                    )
                }
                //Formated description of action limits
                Box(modifier = Modifier.weight(0.9f).fillMaxHeight().padding(5.dp))
                {
                    Text(
                        text = FormatTextWithMarkers(action.limitation),
                        modifier = Modifier.padding(end = 10.dp),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Justify)
                }
            }
        }
    }

}

//Create table with weapons and it's statistics
@Composable
fun WeaponsList(weapons : List<Weapon>,injured : Boolean)
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(KTColors.Orange)
                .padding(vertical = 5.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Weapons",
                style = TextStyle(fontSize = 16.sp),
                color = Color.White)
        }
        //Row with Labels of statistics name
        Row()
        {
            Spacer(modifier = Modifier
                .weight(0.06f)
                .fillMaxHeight())
            Box(modifier = Modifier
                .weight(0.25f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Name",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.09f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Atk",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.09f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Hit",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.11f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Dmg",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.48f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("WR",style = TextStyle(fontSize = 16.sp))
            }
        }
        //Little Box to seperate statistics name from weapons
        Box(modifier = Modifier.fillMaxWidth()
            .height(2.dp)
            .background(KTColors.Orange))
        //Creating table with weapon informations
        weapons.forEach { weapon ->
            //Row with weapon's statistics
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                //Image of Weapon type (melee or ranged)
                Box(modifier = Modifier
                    .weight(0.06f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    Image(
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = "Weapon Type",
                        painter = painterResource(id = if (weapon.type == WeaponType.RANGED) {R.drawable.ranged} else {R.drawable.melee}),
                        contentScale = ContentScale.FillWidth
                    )
                }
                Box(modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    Text("${weapon.name}",style = TextStyle(fontSize = 15.sp), textAlign = TextAlign.Center)
                }
                Box(modifier = Modifier
                    .weight(0.09f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    Text("${weapon.Atk}",style = TextStyle(fontSize = 15.sp))
                }
                //Hit stat which can be modified if operator is injured (injuring changes also color)
                Box(modifier = Modifier
                    .weight(0.09f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    Text("${weapon.Hit.WorsenHitStat(injured)}+",style = TextStyle(fontSize = 15.sp),color = SelectColorByInjuring(Color.Black,
                        KTColors.DarkInjured,injured))
                }
                Box(modifier = Modifier
                    .weight(0.11f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    //weapon's dmg/critical damage
                    Text("${weapon.Dmg}/${weapon.CritDmg}",style = TextStyle(fontSize = 15.sp))
                }
                Box(modifier = Modifier
                    .weight(0.48f)
                    .fillMaxHeight(),
                    contentAlignment = Alignment.Center)
                {
                    Text("${ConvertWeaponRulesToString(weapon.WeaponRulesList)}",style = TextStyle(fontSize = 15.sp), textAlign = TextAlign.Center)
                }
            }
            Box(modifier = Modifier.fillMaxWidth()
                .height(2.dp)
                .background(KTColors.Orange))
        }
    }
}

//Create table with weapons and it's statistics
@Composable
fun WeaponsListSmall(weapons : List<Weapon>,injured : Boolean)
{
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp))
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(KTColors.Orange)
                .padding(vertical = 5.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Weapons",
                style = TextStyle(fontSize = 16.sp),
                color = Color.White)
        }
        //Row with Labels of statistics name
        Row()
        {
            Spacer(modifier = Modifier
                .weight(0.06f)
                .fillMaxHeight())
            Box(modifier = Modifier
                .weight(0.49f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Name",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Atk",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Hit",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Dmg",style = TextStyle(fontSize = 16.sp))
            }
        }
        //Little Box to seperate statistics name from weapons
        Box(modifier = Modifier.fillMaxWidth()
            .height(2.dp)
            .background(KTColors.Orange))
        //Creating table with weapon informations
        weapons.forEach { weapon ->
            Column()
            {
                //Row with weapon's statistics
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    //Image of Weapon type (melee or ranged)
                    Box(modifier = Modifier
                        .weight(0.06f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            contentDescription = "Weapon Type",
                            painter = painterResource(id = if (weapon.type == WeaponType.RANGED) {R.drawable.ranged} else {R.drawable.melee}),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Box(modifier = Modifier
                        .weight(0.49f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart)
                    {
                        Text("${weapon.name}",style = TextStyle(fontSize = 15.sp), textAlign = TextAlign.Start,fontWeight = FontWeight.Bold)
                    }
                    Box(modifier = Modifier
                        .weight(0.15f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        Text("${weapon.Atk}",style = TextStyle(fontSize = 15.sp))
                    }
                    //Hit stat which can be modified if operator is injured (injuring changes also color)
                    Box(modifier = Modifier
                        .weight(0.15f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        Text("${weapon.Hit.WorsenHitStat(injured)}+",style = TextStyle(fontSize = 15.sp),color = SelectColorByInjuring(Color.Black,
                            KTColors.DarkInjured,injured))
                    }
                    Box(modifier = Modifier
                        .weight(0.15f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        //weapon's dmg/critical damage
                        Text("${weapon.Dmg}/${weapon.CritDmg}",style = TextStyle(fontSize = 15.sp))
                    }
                }
                //Little Box to seperate statistics name from weapons
                Box(modifier = Modifier.fillMaxWidth()
                    .height(2.dp)
                    .background(KTColors.Operator.copy(alpha = 0.25f)))
                //Weapon Rules
                Box(modifier = Modifier
                    .fillMaxWidth().padding(start = 5.dp),
                    contentAlignment = Alignment.CenterStart)
                {
                    Text("WR: ${ConvertWeaponRulesToString(weapon.WeaponRulesList)}",style = TextStyle(fontSize = 15.sp), textAlign = TextAlign.Start)
                }
            }
            Box(modifier = Modifier.fillMaxWidth()
                .height(2.dp)
                .background(KTColors.Orange))
        }
    }
}

@Composable
fun InteractableWeaponsList(navController: NavController,weapons : List<Weapon>,injured : Boolean,firstPlayer : Boolean, unitIndex : Int)
{
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp))
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(KTColors.Orange)
                .padding(vertical = 5.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Weapons",
                style = TextStyle(fontSize = 16.sp),
                color = Color.White)
        }
        //Row with Labels of statistics name
        Row()
        {
            Spacer(modifier = Modifier
                .weight(0.06f)
                .fillMaxHeight())
            Box(modifier = Modifier
                .weight(0.49f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Name",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Atk",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Hit",style = TextStyle(fontSize = 16.sp))
            }
            Box(modifier = Modifier
                .weight(0.15f)
                .fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Dmg",style = TextStyle(fontSize = 16.sp))
            }
        }
        //Little Box to seperate statistics name from weapons
        Box(modifier = Modifier.fillMaxWidth()
            .height(2.dp)
            .background(KTColors.Orange))
        //Creating table with weapon informations
        weapons.forEachIndexed { index, weapon ->
            Column(modifier = Modifier.clickable{
                navController.navigate(Screen.UnitAttack.UnitAttackRoute(firstPlayer,unitIndex,index))
            })
            {
                //Row with weapon's statistics
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    //Image of Weapon type (melee or ranged)
                    Box(modifier = Modifier
                        .weight(0.06f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            contentDescription = "Weapon Type",
                            painter = painterResource(id = if (weapon.type == WeaponType.RANGED) {R.drawable.ranged} else {R.drawable.melee}),
                            contentScale = ContentScale.FillWidth
                        )
                    }
                    Box(modifier = Modifier
                        .weight(0.49f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.CenterStart)
                    {
                        Text("${weapon.name}",style = TextStyle(fontSize = 15.sp), textAlign = TextAlign.Start,fontWeight = FontWeight.Bold)
                    }
                    Box(modifier = Modifier
                        .weight(0.15f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        Text("${weapon.Atk}",style = TextStyle(fontSize = 15.sp))
                    }
                    //Hit stat which can be modified if operator is injured (injuring changes also color)
                    Box(modifier = Modifier
                        .weight(0.15f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        Text("${weapon.Hit.WorsenHitStat(injured)}+",style = TextStyle(fontSize = 15.sp),color = SelectColorByInjuring(Color.Black,
                            KTColors.DarkInjured,injured))
                    }
                    Box(modifier = Modifier
                        .weight(0.15f)
                        .fillMaxHeight(),
                        contentAlignment = Alignment.Center)
                    {
                        //weapon's dmg/critical damage
                        Text("${weapon.Dmg}/${weapon.CritDmg}",style = TextStyle(fontSize = 15.sp))
                    }
                }
                //Little Box to seperate statistics name from weapons
                Box(modifier = Modifier.fillMaxWidth()
                    .height(2.dp)
                    .background(KTColors.Operator.copy(alpha = 0.25f)))
                //Weapon Rules
                Box(modifier = Modifier
                    .fillMaxWidth().padding(start = 5.dp),
                    contentAlignment = Alignment.CenterStart)
                {
                    Text("WR: ${ConvertWeaponRulesToString(weapon.WeaponRulesList)}",style = TextStyle(fontSize = 15.sp), textAlign = TextAlign.Start)
                }
            }
            Box(modifier = Modifier.fillMaxWidth()
                .height(2.dp)
                .background(KTColors.Orange))
        }
    }
}

@Composable
fun InfoPopUp(
    title: String,
    description: String,
    onDismiss: () -> Unit
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
                Text(title, textAlign = TextAlign.Center)
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
                        Text(FormatTextWithMarkers(description),style = TextStyle(fontSize = 14.sp), textAlign = TextAlign.Justify)
                    }
                }
            }
        },
        confirmButton = {

        }
    )
}