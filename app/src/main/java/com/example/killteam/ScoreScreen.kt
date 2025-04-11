package com.example.killteam

import android.text.Spanned
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import com.example.killteam.ui.theme.KTColors
import androidx.compose.ui.platform.LocalDensity

@Composable
fun ScoreScreen(viewModel: ScoreViewModel)
{
    LazyColumn(modifier = Modifier.fillMaxSize().padding(vertical = 35.dp, horizontal = 5.dp))
    {
        item()
        {
            GameResult(viewModel)

        }
        item()
        {
            RoundBar(viewModel)
        }
        item()
        {
            PlayerInfo(KTColors.Red,true,viewModel)
        }
        item()
        {
            PlayerInfo(KTColors.Blue,false,viewModel)
        }
    }
}

@Composable
fun PlayerInfo(color : Color,
               firstPlayer : Boolean,
               viewModel: ScoreViewModel)
{
    Column(modifier = Modifier.padding(top = 5.dp).background(Color.LightGray))
    {
        Box()
        {
            TeamSelection(color,firstPlayer,viewModel)
        }
        Box()
        {
            ScorePoints(color,firstPlayer,viewModel)
        }

    }

}

@Composable
fun RoundBar(viewModel: ScoreViewModel)
{

    Column(
        modifier = Modifier.fillMaxWidth().height(100.dp).border(2.dp, KTColors.Orange, RectangleShape)
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
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(1)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(1) }
            )
            {
                    Text("1", style = TextStyle(color = viewModel.GetTextRoundColor(1), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(2)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(2) }
            )
            {
                Text("2",style = TextStyle(color = viewModel.GetTextRoundColor(2), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(3)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(3) }
            )
            {
                Text("3",style = TextStyle(color = viewModel.GetTextRoundColor(3), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(0.75f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(4)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(4) }
            )
            {
                Text("4",style = TextStyle(color = viewModel.GetTextRoundColor(4), fontSize = 16.sp))
            }
            Button(
                modifier = Modifier.weight(1.0f).fillMaxWidth().fillMaxHeight().padding(vertical = 25.dp, horizontal = 5.dp).border(2.dp, KTColors.Orange),
                colors = ButtonDefaults.buttonColors(containerColor = viewModel.GetBackgroundRoundColor(5)),
                shape = RectangleShape,
                onClick = { viewModel.ChangeRound(5) }
            )
            {
                Text("End",style = TextStyle(color = viewModel.GetTextRoundColor(5), fontSize = 16.sp))
            }
            //Spacer(modifier = Modifier.weight(1.0f).fillMaxWidth())
        }
    }
}

@Composable
fun GameResult(viewModel : ScoreViewModel)
{
    Box(
        modifier = Modifier.fillMaxWidth().padding(5.dp),
        contentAlignment = Alignment.Center
    )
    {
        Text("${viewModel.GetAllPoints(true)}:${viewModel.GetAllPoints(false)}",style = TextStyle(fontSize = 48.sp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamSelection(
    color : Color,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel
)
{
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().background(color).padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        ExposedDropdownMenuBox(
            modifier = Modifier.fillMaxWidth().background(color),
            expanded = isExpanded,
            onExpandedChange = {
                isExpanded = !isExpanded
            }
        )
        {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                value = viewModel.GetTeam(firstPlayer).name,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    unfocusedContainerColor = color,
                    focusedContainerColor = color
                ),
                textStyle = TextStyle(fontSize = 20.sp)
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            )
            {
                KillTeams.teamList.forEachIndexed()
                {   index, team ->
                    DropdownMenuItem(
                        text = { Text(team.name) },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        onClick = {
                            if (firstPlayer) {
                                viewModel.RedPlayer.selectedTeam = team
                            } else {
                                viewModel.BluePlayer.selectedTeam = team
                            }
                            isExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ScorePoints(
    color : Color,
    firstPlayer : Boolean,
    viewModel: ScoreViewModel)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
    )
    {
        //Description
        Row()
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("Ops",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(color).weight(0.2f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(color).weight(0.2f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(color).weight(0.2f).fillMaxWidth().padding(5.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
        }
        //Crit Ops
        Row(
            modifier = Modifier.height(75.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Crit",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,0) },
                        contentPadding = PaddingValues(0.dp)
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,0))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,0) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,1) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,1))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,1) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }

                    }
                }

            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,2) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,2))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,2) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,3) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,3))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,3) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
               // Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,4) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,4))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,4) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchCritPoints(firstPlayer,5) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.CRITOP,firstPlayer,5))
                        )
                        {
                            if(viewModel.GetPoint(PointType.CRITOP,firstPlayer,5) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
                }
            }
        }
        //Tac Ops
        Row(
            modifier = Modifier.height(75.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Tac",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,0) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,0))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,0) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(2)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,1) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,1))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,1) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(2)
                                )
                            }
                        }
                    }
                }

            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,2) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,2))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,2) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(3)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,3) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,3))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,3) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(3)
                                )
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                // Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,4) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,4))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,4) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
                    Button(
                        modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(viewModel.GetAlphaByRound(4)), RectangleShape),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues(0.dp),
                        onClick = { viewModel.SwitchTacPoints(firstPlayer,5) }
                    )
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.TACOP,firstPlayer,5))
                        )
                        {
                            if(viewModel.GetPoint(PointType.TACOP,firstPlayer,5) == 1)
                            {
                                Image(
                                    modifier = Modifier.fillMaxSize().padding(5.dp),
                                    contentDescription = "Skull Point",
                                    painter = painterResource(id = R.drawable.skull),
                                    alpha = viewModel.GetAlphaByRound(4)
                                )
                            }
                        }
                    }
                }
            }
        }
        //Kill Ops
        Row(
            modifier = Modifier.height(75.dp).drawBehind
            {
                val strokeWidth = 10f
                val x = size.width - strokeWidth

                //top line
                drawLine(
                    color = color,
                    start = Offset(0f, 0f), //(0,0) at top-left point of the box
                    end = Offset(x, 0f), //top-right point of the box
                    strokeWidth = strokeWidth
                )
            }
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.1f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Kill",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP2",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,0))
                        )
                    }
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,1))
                        )
                    }
                }

            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                //Text("TP3",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,2))
                        )
                    }
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,3))
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.background(Color.LightGray).weight(0.2f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                // Text("TP4",color = Color.White, style = TextStyle(fontSize = 16.sp))
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,4))
                        )
                    }
                    Box(modifier = Modifier.weight(1.0f).fillMaxWidth().padding(2.dp).border(2.dp, color.copy(alpha = 0.5f), RectangleShape).background(Color.Transparent))
                    {
                        Box(
                            modifier = Modifier.height(50.dp).fillMaxWidth().background(viewModel.GetButtonPointColor(PointType.KILLOP,firstPlayer,5))
                        )

                    }
                }
            }
        }
    }
}

