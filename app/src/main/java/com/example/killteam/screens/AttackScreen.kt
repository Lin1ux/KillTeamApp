package com.example.killteam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.killteam.FormatTextWithMarkers
import com.example.killteam.GetOrderColor
import com.example.killteam.IsInjured
import com.example.killteam.Objects.Weapon
import com.example.killteam.RemoveKeyWord
import com.example.killteam.ScoreViewModel
import com.example.killteam.Screen
import com.example.killteam.WeaponsListSmall
import com.example.killteam.ui.theme.KTColors

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AttackScreen(navController: NavController, viewModel: ScoreViewModel, firstPlayer : Boolean, unitIndex : Int, weaponIndex : Int)
{
    val weapon : Weapon = viewModel.GetPlayer(firstPlayer).GetWeaponById(unitIndex,weaponIndex)
    val attackViewModel: AttackViewModel = viewModel()

    LazyColumn(modifier = Modifier.fillMaxSize())
    {
        item() //Label with section name
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Conceal).padding(10.dp),
                contentAlignment = Alignment.Center)
            {
                Text(weapon.name,style = TextStyle(fontSize = 24.sp),color = Color.White, textAlign = TextAlign.Center)
            }
        }
        item()
        {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item() //Buttons with Selected operators
        {
            WeaponsListSmall(listOf(weapon),IsInjured(viewModel.GetPlayer(firstPlayer).GetTroopSelectionByIndex(unitIndex)))
        }
        item()
        {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item()
        {
            WeaponRules(weapon)
        }
        item()
        {
            Spacer(modifier = Modifier.height(5.dp))
        }
        item()
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Conceal).padding(10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Dice",style = TextStyle(fontSize = 24.sp),color = Color.White, textAlign = TextAlign.Center)
            }
        }
        item()
        {
            DiceCounter(attackViewModel,firstPlayer,weapon)
        }
        item() //Label with section name
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Conceal).padding(10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Select Target",style = TextStyle(fontSize = 24.sp),color = Color.White, textAlign = TextAlign.Center)
            }
        }
        item()
        {
            TargetList(navController,viewModel,attackViewModel,firstPlayer)
        }
    }
}

//Weapon Rules
@Composable
fun WeaponRules(weapon: Weapon)
{
    var showRules by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(containerColor = KTColors.Orange),
            onClick = {showRules = !showRules}
        )
        {
            if(showRules)
            {
                Text("Hide Weapon Rules")
            }
            else
            {
                Text("Show Weapon Rules")
            }
        }
        if(showRules)
        {
            weapon.WeaponRulesList.forEach { weaponRule ->
                Box(modifier = Modifier.fillMaxWidth().padding(5.dp), contentAlignment = Alignment.Center)
                {
                    Text(FormatTextWithMarkers("**${weaponRule.name}:**  ${weaponRule.description}"), style = TextStyle(fontSize =  14.sp), textAlign = TextAlign.Start)
                }
            }
        }
    }
}

//List of operators to target
@Composable
fun TargetList(navController : NavController,viewModel: ScoreViewModel,attackViewModel : AttackViewModel, firstPlayer : Boolean)
{
    if(viewModel.GetPlayer(!firstPlayer).IsTroopsSelected())
    {
        Column(modifier = Modifier.fillMaxWidth())
        {
            viewModel.GetPlayer(!firstPlayer).GetSelectedTroops().forEachIndexed { index,troop ->
                if(troop.currentWounds.value > 0)
                {
                    Row(modifier = Modifier.clickable{
                        if(attackViewModel.allDamage == 0)
                        {
                            navController.navigate(Screen.UnitPreviewScreen.UnitPreviewRoute(!firstPlayer,index))
                        }
                        else
                        {
                            viewModel.GetPlayer(!firstPlayer).DecreaseWound(attackViewModel.allDamage,index)
                            attackViewModel.resetDice()
                        }
                    })
                    {
                        //Operator's Name
                        Box(modifier = Modifier.weight(0.8f).padding(5.dp).border(2.dp, GetOrderColor(troop,true,true),RectangleShape))
                        {
                            Text(troop.operator.name.RemoveKeyWord(viewModel,!firstPlayer),
                                style = TextStyle(fontSize = 20.sp),
                                color = Color.Black,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding(5.dp))
                        }
                        //Operators Wounds (HP)
                        Box(modifier = Modifier.weight(0.2f).padding(5.dp).border(2.dp, GetOrderColor(troop,true,true),RectangleShape),
                                contentAlignment = Alignment.Center)
                        {
                            Text("${troop.currentWounds.value}/${troop.operator.wounds}",
                                style = TextStyle(fontSize = 20.sp),
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(5.dp))
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun DiceCounter(attackViewModel: AttackViewModel,firstPlayer: Boolean,weapon: Weapon)
{

    Column(modifier = Modifier.fillMaxWidth())
    {
        Row(modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {

            Box(modifier = Modifier.weight(0.45f),
                contentAlignment = Alignment.Center)
            {
                Text("Normal Successes",
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 15.dp))
            }
            //Button for decreasing value of success dice
            Box(
                modifier = Modifier.
                    weight(0.15f).
                    fillMaxHeight().
                    background(KTColors.Orange).
                    clickable
                    {
                        if (attackViewModel.normalDamage > 0) attackViewModel.normalDamage -= 1
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("-", style = TextStyle(fontSize = 36.sp),color = Color.White)
            }
            //Show normal successes
            Box(modifier = Modifier.
                weight(0.2f).
                fillMaxHeight().
                border(2.dp, KTColors.Orange, RectangleShape),
                contentAlignment = Alignment.Center)
            {
                Text("${attackViewModel.normalDamage}",style = TextStyle(fontSize = 28.sp), textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))
            }
            //Button for increasing value of success dice
            Box(
                modifier = Modifier.
                    weight(0.15f).
                    fillMaxHeight().
                    background(KTColors.Orange).
                    clickable
                    {
                        if(attackViewModel.normalDamage + attackViewModel.criticalDamage < weapon.Atk) attackViewModel.normalDamage += 1
                    },
                contentAlignment = Alignment.Center
            ) {
                Text("+", style = TextStyle(fontSize = 36.sp), color = Color.White)
            }
        }
        Row(modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {

            Box(modifier = Modifier.weight(0.45f),
                contentAlignment = Alignment.Center)
            {
                Text("Critical Successes",
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(vertical = 15.dp))
            }
            //Button for decreasing value of success dice
            Box(
                modifier = Modifier.
                weight(0.15f).
                fillMaxHeight().
                background(KTColors.Orange).
                clickable
                {
                    if (attackViewModel.criticalDamage > 0) attackViewModel.criticalDamage -= 1
                },
                contentAlignment = Alignment.Center
            ) {
                Text("-", style = TextStyle(fontSize = 36.sp),color = Color.White)
            }
            //Show normal successes
            Box(modifier = Modifier.
            weight(0.2f).
            fillMaxHeight().
            border(2.dp, KTColors.Orange, RectangleShape),
                contentAlignment = Alignment.Center)
            {
                Text("${attackViewModel.criticalDamage}",style = TextStyle(fontSize = 28.sp), textAlign = TextAlign.Center, modifier = Modifier.padding(5.dp))
            }
            //Button for increasing value of success dice
            Box(
                modifier = Modifier.
                weight(0.15f).
                fillMaxHeight().
                background(KTColors.Orange).
                clickable
                {
                    if(attackViewModel.normalDamage + attackViewModel.criticalDamage < weapon.Atk) attackViewModel.criticalDamage += 1
                },
                contentAlignment = Alignment.Center
            ) {
                Text("+", style = TextStyle(fontSize = 36.sp), color = Color.White)
            }
        }
        Box(modifier = Modifier.fillMaxWidth().padding(5.dp),
            contentAlignment = Alignment.Center)
        {
            Text("Total damage: ${attackViewModel.countDamage(weapon)}", style = TextStyle(fontSize = 18.sp), color = Color.Black)
        }
    }
}

class AttackViewModel : ViewModel()
{
    var normalDamage by mutableStateOf(0)
    var criticalDamage  by mutableStateOf(0)
    var allDamage by mutableStateOf(0)

    fun countDamage(weapon: Weapon) : Int
    {
        allDamage = normalDamage * weapon.Dmg + criticalDamage * weapon.CritDmg
        return allDamage
    }
    fun resetDice()
    {
        normalDamage = 0
        criticalDamage = 0
        allDamage = 0
    }
}

