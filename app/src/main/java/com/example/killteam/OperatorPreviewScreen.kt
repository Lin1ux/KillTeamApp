package com.example.killteam

import Objects.Operator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.ui.theme.KTColors

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PreviewScreen(viewModel: ScoreViewModel,firstPlayer : Boolean,index : Int)
{
    LazyColumn(modifier = Modifier.fillMaxSize())
    {
        item()
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("${viewModel.GetPlayer(firstPlayer).GetTroopByIndex(index).name.RemoveKeyWord(viewModel,firstPlayer)}",
                    style = TextStyle(fontSize = 32.sp),
                    color = Color.White)
            }
        }
        /*item()
        {
            viewModel.GetPlayer(firstPlayer).GetSelectedTroops().forEach { troop ->
                SelectedOperatorButton(viewModel,firstPlayer,troop)
            }
        }*/
    }
}