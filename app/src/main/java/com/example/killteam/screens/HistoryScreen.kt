package com.example.killteam.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.killteam.firebase.DatabaseViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.killteam.Screen
import com.example.killteam.firebase.UserData
import com.example.killteam.ui.theme.KTColors

@Composable
fun GameList(navController: NavController,dbViewModel: DatabaseViewModel)
{
    val gameInfo = dbViewModel.data
    Column(horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(modifier = Modifier.padding(5.dp),
            onClick = { navController.navigate(Screen.ScoreScreen.ScoreScreenRoute()) },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = KTColors.Orange)
        )
        {
            Text("Current Game")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth())
        {
            val size = gameInfo?.games?.size ?: 0
            itemsIndexed(gameInfo?.games ?: emptyList()) { index, gameItem ->
                GameButton(navController, dbViewModel, (size-1) - index)
            }
        }
    }
}

@Composable
fun GameButton(navController: NavController,dbViewModel: DatabaseViewModel,index: Int)
{
    Row(modifier = Modifier.fillMaxWidth().height(75.dp).padding(5.dp).border(2.dp,KTColors.Blue,RoundedCornerShape(5.dp)),
        verticalAlignment = Alignment.CenterVertically)
    {
        Box(modifier = Modifier.weight(0.4f),
            contentAlignment = Alignment.Center
        )
        {
            Text(text = dbViewModel.getDataByIndex(index).redPlayer.teamName,
                modifier = Modifier.fillMaxHeight().padding(5.dp).wrapContentHeight(Alignment.CenterVertically),
                fontSize = 18.sp,
                textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.weight(0.05f))
        Text(text ="${dbViewModel.getDataByIndex(index).redPlayer.score}:${dbViewModel.getDataByIndex(index).bluePlayer.score}",
            modifier = Modifier.weight(0.1f),
            fontSize = 18.sp,
            textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.weight(0.05f))
        Box(modifier = Modifier.weight(0.4f),
            contentAlignment = Alignment.Center
        )
        {
            Text(
                text = dbViewModel.getDataByIndex(index).bluePlayer.teamName,
                modifier = Modifier.fillMaxHeight().padding(5.dp).wrapContentHeight(Alignment.CenterVertically),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}