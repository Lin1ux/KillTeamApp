package com.example.killteam

import Objects.Operator
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.ui.theme.KTColors

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun UnitScreen(navController: NavController,viewModel: ScoreViewModel,firstPlayer : Boolean)
{
    LazyColumn(modifier = Modifier.fillMaxSize())
    {
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
            viewModel.GetPlayer(firstPlayer).GetSelectedTroops().forEachIndexed { index,troop ->
                SelectedOperatorButton(navController,viewModel,firstPlayer,troop,index)
            }
        }
    }
}

@Composable
fun SelectedOperatorButton(navController: NavController,
                           viewModel: ScoreViewModel,
                           firstPlayer : Boolean,
                           operator: selectedOperators,
                           index : Int)
{
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth().background(Color.Transparent).padding(5.dp).border(2.dp,GetOrderColor(operator),RectangleShape)
        .clickable { navController.navigate(Screen.UnitPreview.UnitPreviewRoute(firstPlayer,index)) })
    {
        Row(modifier = Modifier.fillMaxSize())
        {
            Box(modifier = Modifier.weight(0.9f).fillMaxHeight().padding(vertical = 5.dp))
            {
                Text("${operator.operator.name.RemoveKeyWord(viewModel,firstPlayer)}",
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Start,
                        color = Color.Black)
            }
            Box(modifier = Modifier.weight(0.1f).fillMaxHeight().background(GetOrderColor(operator)))
            {
                Image(
                        modifier = Modifier.align(Alignment.Center), // Wyśrodkuj obraz
                        contentDescription = "Order Image",
                        painter = painterResource(id = R.drawable.conceal),
                        contentScale = ContentScale.FillWidth,
                        alpha = GetReadyAlpha(operator.ready)
                )
            }
        }

    }
}