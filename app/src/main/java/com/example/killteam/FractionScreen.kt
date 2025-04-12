package com.example.killteam

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.killteam.ui.theme.KTColors

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FractionScreen(viewModel: ScoreViewModel, firstPlayer: Boolean) {
    // Zmieniamy LazyColumn na Column - lepiej sprawdza się z weight
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        //Ploys(viewModel, firstPlayer)
        item()
        {
            Box(
                modifier = Modifier.fillMaxWidth().background(KTColors.Orange).padding(vertical = 10.dp),
                contentAlignment = Alignment.Center)
            {
                Text("Ploys",style = TextStyle(fontSize = 32.sp),color = Color.White)
            }
        }
        item()
        {
            viewModel.GetTeam(firstPlayer).ploys.forEachIndexed { index,element ->
                Ploys(viewModel, firstPlayer,index)
            }
        }
    }
}

@Composable
fun Ploys(viewModel: ScoreViewModel, firstPlayer: Boolean,index: Int)
{
    val color by remember { mutableStateOf( ployToColor(viewModel.GetTeam(firstPlayer).ploys[index].type)) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 20.dp, bottom = 10.dp,end = 20.dp,start = 10.dp).drawBehind
    {
        val strokeWidth = 10f
        val x = size.width - strokeWidth
        //val y = size.height - strokeWidth

        //top line
        drawLine(
            color = color,
            start = Offset(0f, -strokeWidth*2), //(0,0) at top-left point of the box
            end = Offset(x+strokeWidth*3, -strokeWidth*2), //top-right point of the box
            strokeWidth = strokeWidth
        )
    }.drawBehind
    {
        val strokeWidth = 10f
        val x = size.width - strokeWidth
        val y = size.height - strokeWidth

        //Right line
        drawLine(
            color = color,
            start = Offset(x+strokeWidth*3, -strokeWidth*2), //top-right point of the box
            end = Offset(x+strokeWidth*3, y+strokeWidth), //top-right point of the box
            strokeWidth = strokeWidth
        )
    },
        contentAlignment = Alignment.Center)
    {
        Box(
            modifier = Modifier.fillMaxSize().background(color).padding(10.dp),
            contentAlignment = Alignment.Center)
        {
            Row(modifier = Modifier.fillMaxSize().background(color))
            {
                Box(
                    modifier = Modifier.weight(0.75f).fillMaxSize().background(color),
                    contentAlignment = Alignment.CenterStart

                )
                {
                    Text("${viewModel.GetTeam(firstPlayer).ploys[index].name}",
                        style = TextStyle(fontSize = 20.sp),
                        color = Color.White,textAlign = TextAlign.End,
                        softWrap = false,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Box(
                    modifier = Modifier.weight(0.25f).fillMaxSize().background(color),
                    contentAlignment = Alignment.CenterEnd
                )
                {
                    Text("${viewModel.GetTeam(firstPlayer).ploys[index].cost}",style = TextStyle(fontSize = 16.sp),color = Color.White, textAlign = TextAlign.End,)
                }

            }

        }
    }
}