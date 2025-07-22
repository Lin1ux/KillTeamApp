package com.example.killteam.screens

import com.example.killteam.Objects.Action
import com.example.killteam.Objects.Weapon
import com.example.killteam.Objects.WeaponType
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.Actions
import com.example.killteam.ConfirmDialog
import com.example.killteam.ConvertWeaponRulesToString
import com.example.killteam.FormatTextWithMarkers
import com.example.killteam.GetOrderColor
import com.example.killteam.GetOrderIcon
import com.example.killteam.InjureWorsenMove
import com.example.killteam.InteractableWeaponsList
import com.example.killteam.IsInjured
import com.example.killteam.R
import com.example.killteam.RemoveKeyWord
import com.example.killteam.ScoreViewModel
import com.example.killteam.SelectColorByInjuring
import com.example.killteam.WeaponsList
import com.example.killteam.WeaponsListSmall
import com.example.killteam.WorsenHitStat
import com.example.killteam.ui.theme.KTColors

//Show information about Operator
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PreviewScreen(navController: NavController,viewModel: ScoreViewModel, firstPlayer : Boolean, index : Int)
{
    LazyColumn(modifier = Modifier.fillMaxSize())
    {
        item() //Operator's name label
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(GetOrderColor(viewModel.GetPlayer(firstPlayer).GetTroopSelectionByIndex(index),false))
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("${viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).name.RemoveKeyWord(viewModel,firstPlayer)}",
                    style = TextStyle(fontSize = 32.sp),
                    color = Color.White,
                    textAlign = TextAlign.Center)
            }
        }
        item() //Operator interactions
        {
            OperatorInteractions(viewModel,firstPlayer,index)
        }
        item() //Operator statistics
        {
            OperatorStats(viewModel,firstPlayer,index)
        }
        item() //Operator's Weapons
        {
            InteractableWeaponsList(navController,viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).weapons,IsInjured(viewModel.GetPlayer(firstPlayer).GetTroopSelectionByIndex(index)),firstPlayer,index)
        }
        item() //Operator's Additional rules
        {
            AddiotionalRules(viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).additionalRules)
        }
        item() //Operator's additional actions
        {
            Actions(viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).actions)
        }
    }
}

//Create table with list of weapons and their statistics
@Composable
fun OperatorStats(viewModel: ScoreViewModel, firstPlayer : Boolean, index : Int)
{
    val operator by remember { mutableStateOf(viewModel.GetPlayer(firstPlayer).GetTroopSelectionByIndex(index)) }

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
                Text("${viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).APL}",style = TextStyle(fontSize = 36.sp),color = Color.White, textAlign =  TextAlign.Center)
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
                Text("${InjureWorsenMove(viewModel.GetPlayer(firstPlayer).GetTroopSelectionByIndex(index))}\"",style = TextStyle(fontSize = 36.sp),color = SelectColorByInjuring(Color.White,
                    KTColors.LightInjured,operator), textAlign =  TextAlign.Center) //if injured value would be lower
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
                Text("${viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).save}+",style = TextStyle(fontSize = 36.sp),color = Color.White, textAlign =  TextAlign.Center)
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
                Text("${viewModel.GetPlayer(firstPlayer).GetCurrentWounds(index)}/${viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).wounds}",
                    style = TextStyle(fontSize = 36.sp),
                    color = SelectColorByInjuring(Color.White, KTColors.LightInjured,operator),
                    textAlign =  TextAlign.Center)
            }
        }
    }
}
//Create Box with buttons to change some information (values) about operator
@Composable
fun OperatorInteractions(viewModel: ScoreViewModel, firstPlayer : Boolean, index : Int)
{
    var operator by remember { mutableStateOf(viewModel.GetPlayer(firstPlayer).GetTroopSelectionByIndex(index)) }
    var showDialog by remember { mutableStateOf(false) }
    Column()
    {
        Row(
            modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp))
        {
            //Box which works like button. It trigger alert dialog to ask user is he sure his action
            Box(modifier = Modifier.weight(0.4f).height(75.dp).background(GetOrderColor(operator,false)).clickable{
                showDialog = true
            },
                contentAlignment = Alignment.Center)
            {
                Text("Kill",style = TextStyle(fontSize = 20.sp), color = Color.White, textAlign = TextAlign.Center)
                if(showDialog)  //triggering alert dialog window
                {
                    ConfirmDialog("Warning!","Are you sure you want to kill this operator?","KILL",
                        { showDialog = false},
                        { viewModel.GetPlayer(firstPlayer).KillOperator(index)
                            showDialog = false})
                }
            }
            //Switch state of operators readiness
            Box(modifier = Modifier.weight(0.4f).height(75.dp).background(GetOrderColor(operator,false)).clickable{
                viewModel.GetPlayer(firstPlayer).SwitchOperatorReadiness(index)
            },
                contentAlignment = Alignment.Center)
            {
                Text(if(operator.ready.value) {"Ready"} else {"Expanded"},style = TextStyle(fontSize = 20.sp), color = Color.White, textAlign = TextAlign.Center)
            }
            //Switch order (state) of operator
            Box(modifier = Modifier.weight(0.2f).height(75.dp).background(GetOrderColor(operator,false)).clickable{
                viewModel.GetPlayer(firstPlayer).SwitchOperatorOrder(index)
            },
                contentAlignment = Alignment.Center)
            {
                Image(
                    modifier = Modifier.align(Alignment.Center).padding(5.dp),
                    contentDescription = "Order Image",
                    painter = painterResource(id = GetOrderIcon(operator)),
                    contentScale = ContentScale.Fit
                )
            }
        }
        //Used to change values of operators wounds
        Row(modifier = Modifier.padding(5.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            var textState by remember { mutableStateOf("1") }

            Spacer(modifier = Modifier.weight(0.175f).fillMaxHeight())
            //Button for decreasing value of wounds
            Button(modifier = Modifier.weight(0.2f).fillMaxHeight(),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = GetOrderColor(operator,false)),
                onClick = { viewModel.GetPlayer(firstPlayer).DecreaseWound(textState.toIntOrNull() ?: 1,index) })
            {
                Text("-", style = TextStyle(fontSize = 36.sp))
            }
            val focusManager = LocalFocusManager.current
            val focusRequester = remember { FocusRequester() }
            //Text Field for entering value to increament or decreament wounds
            TextField(
                modifier = Modifier
                    .weight(0.25f)
                    .fillMaxHeight()
                    .border(2.dp, GetOrderColor(operator, false), RectangleShape)
                    .focusRequester(focusRequester).onFocusChanged { focusState ->  //Removes text if focus is on it
                        if(focusState.isFocused) {
                            textState = ""
                        }
                    },
                value = textState,
                onValueChange = { textState = it }, //save value
                textStyle = TextStyle(fontSize = 28.sp, textAlign = TextAlign.Center),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.Transparent,
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal, imeAction = ImeAction.Done), //Set Keyboard with Decimal numbers
                keyboardActions = KeyboardActions(onDone = {focusManager.clearFocus()})                             //Removing focus after accepting
            )
            //Button for increasing value of wounds
            Button(modifier = Modifier.weight(0.175f).fillMaxHeight().border(5.dp,GetOrderColor(operator,false),RectangleShape),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(containerColor = GetOrderColor(operator,false)),
                onClick = { viewModel.GetPlayer(firstPlayer).IncreaseWound(textState.toIntOrNull() ?: 1,index) })
            {
                Text("+", style = TextStyle(fontSize = 36.sp))
            }
            Spacer(modifier = Modifier.weight(0.2f).fillMaxHeight())
        }
    }
}


//Create Box which show formated text used for operators special rules
@Composable
fun AddiotionalRules(additionalRules : String)
{
    Box(modifier = Modifier.fillMaxWidth().padding(5.dp), contentAlignment = Alignment.Center)
    {
        Text(FormatTextWithMarkers(additionalRules), style = TextStyle(fontSize =  14.sp), textAlign = TextAlign.Justify)
    }
}




