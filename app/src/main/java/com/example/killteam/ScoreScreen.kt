package com.example.killteam

import android.text.Spanned
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import com.example.killteam.ui.theme.KTColors

@Composable
fun ScoreScreen()
{
    LazyColumn()
    {
        item()
        {
            RoundBar()
        }
        item()
        {
            PlayerInfo(KTColors.Red)
        }
    }
}

@Composable
fun PlayerInfo(color : Color)
{
    Column()
    {
        TeamSelection(color)
    }

}

@Composable
fun RoundBar()
{
    Box(
        modifier = Modifier.fillMaxWidth().padding(15.dp).height(100.dp).border(2.dp, KTColors.Orange, RectangleShape),
        contentAlignment = Alignment.Center
    )
    {
        Row(
            modifier = Modifier.fillMaxSize().background(Color.LightGray),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            //Spacer(modifier = Modifier.weight(1.0f).fillMaxWidth())
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {}
            )
            {
                    Text("1", style = TextStyle(color = KTColors.Orange, fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {}
            )
            {
                Text("2",style = TextStyle(color = KTColors.Orange, fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {}
            )
            {
                Text("3",style = TextStyle(color = KTColors.Orange, fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {}
            )
            {
                Text("4",style = TextStyle(color = KTColors.Orange, fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(1.0f).fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                onClick = {}
            )
            {
                Text("End",style = TextStyle(color = KTColors.Orange, fontSize = 16.sp))
            }
            //Spacer(modifier = Modifier.weight(1.0f).fillMaxWidth())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSelection(
    color : Color
)
{

}
