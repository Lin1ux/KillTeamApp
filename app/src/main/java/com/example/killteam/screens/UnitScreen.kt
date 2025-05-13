package com.example.killteam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.GetOrderColor
import com.example.killteam.GetOrderIcon
import com.example.killteam.GetReadyAlpha
import com.example.killteam.R
import com.example.killteam.RemoveKeyWord
import com.example.killteam.ScoreViewModel
import com.example.killteam.Screen
import com.example.killteam.selectedOperators
import com.example.killteam.ui.theme.KTColors

//List of selected operators
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun UnitScreen(navController: NavController, viewModel: ScoreViewModel, firstPlayer : Boolean)
{
    LazyColumn(modifier = Modifier.fillMaxSize())
    {
        item() //Label with section name
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Operators",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item() //Buttons with Selected operators
        {
            viewModel.GetPlayer(firstPlayer).GetSelectedTroops().forEachIndexed { index,troop ->
                SelectedOperatorButton(navController,viewModel,firstPlayer,troop,index)
            }
        }
    }
}

//Button which show basic information about operator
@Composable
fun SelectedOperatorButton(navController: NavController,
                           viewModel: ScoreViewModel,
                           firstPlayer : Boolean,
                           operator: selectedOperators,
                           index : Int)
{
    //Box which is used as button to open preview Screen
    Box(modifier = Modifier.fillMaxWidth().background(Color.Transparent).padding(5.dp).border(2.dp,
        GetOrderColor(operator = operator, checkWounds = true),RectangleShape)
        .clickable { navController.navigate(Screen.UnitPreview.UnitPreviewRoute(firstPlayer,index)) })
    {
        Row(modifier = Modifier.fillMaxSize().padding(start = 5.dp))
        {
            //Name of Operator
            Box(modifier = Modifier.weight(0.9f).fillMaxHeight().padding(vertical = 5.dp))
            {
                if (operator.currentWounds.value == 0)
                {
                    Text("${operator.operator.name.RemoveKeyWord(viewModel,firstPlayer)}",
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Incapacitated)
                }
                else
                {
                    Text("${operator.operator.name.RemoveKeyWord(viewModel,firstPlayer)}",
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Start,
                        color = Color.Black.copy(alpha = GetReadyAlpha(operator.ready.value)))
                }

            }
            //Order Icon
            Box(modifier = Modifier.weight(0.1f).fillMaxHeight().background(GetOrderColor(operator = operator, checkWounds = true)))
            {
                Image(
                        modifier = Modifier.align(Alignment.Center),
                        contentDescription = "Order Image",
                        painter = painterResource(id = GetOrderIcon(operator)),
                        contentScale = ContentScale.FillWidth,
                        alpha = GetReadyAlpha(operator.ready.value)
                )
            }
        }
    }
}