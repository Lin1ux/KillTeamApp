package com.example.killteam.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.ConfirmDialog
import com.example.killteam.GetColorByWounds
import com.example.killteam.GetNumberOfOperators
import com.example.killteam.GetOrderColor
import com.example.killteam.GetRemainedOperators
import com.example.killteam.InfoPopUp
import com.example.killteam.Objects.PointType
import com.example.killteam.Objects.teamIcons
import com.example.killteam.R
import com.example.killteam.RemoveKeyWord
import com.example.killteam.ScoreViewModel
import com.example.killteam.Screen
import com.example.killteam.firebase.DatabaseViewModel
import com.example.killteam.firebase.GameInfo
import com.example.killteam.firebase.GoogleAuthUIClient
import com.example.killteam.firebase.PlayerInfo
import com.example.killteam.firebase.UnitInfo
import com.example.killteam.ui.theme.KTColors
import com.google.android.gms.auth.api.identity.Identity

@Composable
fun GamePreviewScreen(navController: NavController, dbViewModel: DatabaseViewModel,index : Int)
{
    if(dbViewModel.IsdataEmpty())
    {
        navController.navigate(Screen.HistoryListScreen.HistoryListScreenRoute())
        return
    }
    LazyColumn(modifier = Modifier.fillMaxWidth().padding(5.dp))
    {
        item()
        {
            GamePreviewResult(dbViewModel,index)
        }
        item()
        {
            TeamPreviewScreen(dbViewModel.getDataByIndex(index).redPlayer, KTColors.Red)
        }
        item()
        {
            TeamPreviewScreen(dbViewModel.getDataByIndex(index).bluePlayer, KTColors.Blue)
        }
        item()
        {
            DeleteButton(navController,dbViewModel,index)
        }
    }
}

@Composable
fun GamePreviewResult(dbViewModel : DatabaseViewModel,index : Int)
{
    Row()
    {
        Icon(
            //modifier = Modifier.weight(0.2f),
            modifier = Modifier.padding(5.dp).weight(0.3f).height(75.dp),
            painter = painterResource(teamIcons.Icon[dbViewModel.getDataByIndex(index).redPlayer.teamName]?: R.drawable.angelsofdeath),
            contentDescription = "Team",
            tint = KTColors.Red,
        )
        Spacer(modifier = Modifier.weight(0.05f))
        Box(
            modifier = Modifier.fillMaxWidth().padding(5.dp).weight(0.3f),
            contentAlignment = Alignment.Center
        )
        {
            Text("${dbViewModel.getDataByIndex(index).redPlayer.score}:${dbViewModel.getDataByIndex(index).bluePlayer.score}",style = TextStyle(fontSize = 40.sp))
        }
        Spacer(modifier = Modifier.weight(0.05f))
        Icon(
            modifier = Modifier.padding(5.dp).weight(0.3f).height(75.dp),
            painter = painterResource(teamIcons.Icon[dbViewModel.getDataByIndex(index).bluePlayer.teamName]?: R.drawable.angelsofdeath),
            contentDescription = "Team",
            tint = KTColors.Blue,

            )
    }
}

@Composable
fun TeamPreviewScreen(
    data : PlayerInfo,
    color : Color)
{
    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 5.dp)
    )
    {
        Box(modifier = Modifier.fillMaxWidth().background(color).padding(bottom = 5.dp),
            contentAlignment = Alignment.Center)
        {
            Text(
                modifier = Modifier.padding(5.dp),
                text = data.teamName,
                style = TextStyle(fontSize = 28.sp),
                color = Color.White)
        }

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
            modifier = Modifier.height(65.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.15f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Crit",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            for(i in 0..5)
            {
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.15f).padding(5.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(modifier = Modifier.background(if(data.critPoints[i] == 1) { color} else KTColors.Background).height(50.dp).fillMaxWidth().border(2.dp,color,RectangleShape))
                    {
                        if(data.critPoints[i] == 1)
                        {
                            Image(
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                contentDescription = "Skull Point",
                                painter = painterResource(id = R.drawable.skull),
                                alpha = 1.0f)
                        }

                    }

                }
            }
        }
        //Tac Ops
        Row(
            modifier = Modifier.height(65.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.15f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Tac",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            for(i in 0..5)
            {
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.15f).padding(5.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(modifier = Modifier.background(if(data.tacPoints[i] == 1) { color} else KTColors.Background).height(50.dp).fillMaxWidth().border(2.dp,color,RectangleShape))
                    {
                        if(data.tacPoints[i] == 1) {
                            Image(
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                contentDescription = "Skull Point",
                                painter = painterResource(id = R.drawable.skull),
                                alpha = 1.0f
                            )
                        }
                    }

                }
            }
        }
        //Kill Ops
        Row(
            modifier = Modifier.height(65.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.15f).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Kill",color = Color.White, style = TextStyle(fontSize = 16.sp))
            }
            for(i in 0..5)
            {
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.15f).padding(5.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(modifier = Modifier.background(if(data.killPoints[i] == 1) { color} else KTColors.Background).height(50.dp).fillMaxWidth().border(2.dp,color,RectangleShape))
                    {
                        if(data.killPoints[i] == 1) {
                            Image(
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                contentDescription = "Skull Point",
                                painter = painterResource(id = R.drawable.skull),
                                alpha = 1.0f
                            )
                        }
                    }

                }
            }
        }
        Row(
            modifier = Modifier.height(65.dp)
        )
        {
            Box(
                modifier = Modifier.background(color).weight(0.3f).padding(end = 10.dp).fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                Text("Primary: ${data.primaryOp}", color = Color.White, style = TextStyle(fontSize = 16.sp), textAlign = TextAlign.Center)
            }
            for(i in 0..2)
            {
                var primaryOp : Int = 0
                if(data.primaryOp == "CRITOP")
                {
                    primaryOp = data.critPoints[6]
                }
                else if(data.primaryOp == "TACOP")
                {
                    primaryOp = data.tacPoints[6]
                }
                else if(data.primaryOp == "KILLOP")
                {
                    primaryOp = data.killPoints[6]
                }
                Box(
                    modifier = Modifier.background(KTColors.Background).weight(0.15f).padding(5.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                )
                {
                    Box(modifier = Modifier.background(if(primaryOp-1 >= i) { color} else KTColors.Background).height(50.dp).fillMaxWidth().border(2.dp,color,RectangleShape))
                    {
                        if(primaryOp-1 >= i) {
                            Image(
                                modifier = Modifier.fillMaxSize().padding(5.dp),
                                contentDescription = "Skull Point",
                                painter = painterResource(id = R.drawable.skull),
                                alpha = 1.0f
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier.background(color).weight(0.3f).padding(5.dp).fillMaxHeight(),
                contentAlignment = Alignment.Center
            )
            {
                Text("CP: ${data.cp}", color = Color.White, style = TextStyle(fontSize = 16.sp),textAlign = TextAlign.Center)
            }
        }
        Row(modifier = Modifier.padding(vertical = 5.dp))
        {
            Box(modifier = Modifier.background(color).weight(0.48f).padding(5.dp).fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("TacOp:\n ${data.tacOp}", color = Color.White, style = TextStyle(fontSize = 20.sp),textAlign = TextAlign.Center)
            }
            Spacer(Modifier.weight(0.02f))
            Box(modifier = Modifier.background(color).weight(0.48f).padding(5.dp).fillMaxHeight(),
                contentAlignment = Alignment.Center)
            {
                Text("Operators:\n ${GetRemainedOperators(data)}/${GetNumberOfOperators(data)}", color = Color.White, style = TextStyle(fontSize = 20.sp),textAlign = TextAlign.Center)
            }
        }
        Spacer(modifier = Modifier.height(2.dp).background(color))
        //Equipment
        if(data.equipment.size != 0)
        {
            Column(modifier = Modifier.fillMaxWidth().border(2.dp,color,RectangleShape))
            {
                Box(modifier = Modifier.background(color).fillMaxWidth().padding(5.dp),
                    contentAlignment = Alignment.Center)
                {
                    Text("Equipment", color = Color.White, style = TextStyle(fontSize = 24.sp),textAlign = TextAlign.Center)
                }
                data.equipment.forEach { element ->
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(5.dp).border(2.dp, KTColors.Equipment,RectangleShape),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text ="${element}",
                            style = TextStyle(fontSize = 20.sp),
                            textAlign = TextAlign.Center,
                            color = KTColors.Equipment)
                    }
                }
            }
        }
        if(data.units.size != 0)
        {
            Column(modifier = Modifier.fillMaxWidth().border(2.dp,color,RectangleShape))
            {
                Box(modifier = Modifier.background(color).fillMaxWidth().padding(5.dp),
                    contentAlignment = Alignment.Center)
                {
                    Text("Operators", color = Color.White, style = TextStyle(fontSize = 24.sp),textAlign = TextAlign.Center)
                }
                data.units.forEach { unit ->
                    OperatorList(unit)
                }
            }
        }
    }
}
@Composable
fun OperatorList(unit : UnitInfo)
{
    Row()
    {
        //Operator's Name
        Box(modifier = Modifier.weight(0.8f).padding(5.dp).border(2.dp, GetColorByWounds(KTColors.Conceal,unit),RectangleShape))
        {
            Text(unit.name,
                style = TextStyle(fontSize = 18.sp),
                color = GetColorByWounds(Color.Black,unit),
                textAlign = TextAlign.Start,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(5.dp))
        }
        //Operators Wounds (HP)
        Box(modifier = Modifier.weight(0.2f).padding(5.dp).border(2.dp, GetColorByWounds(KTColors.Conceal,unit),RectangleShape),
            contentAlignment = Alignment.Center)
        {
            Text("${unit.currentWounds}/${unit.wounds}",
                style = TextStyle(fontSize = 18.sp),
                color = GetColorByWounds(Color.Black,unit),
                textAlign = TextAlign.Center,

                modifier = Modifier.padding(5.dp))
        }
    }
}

@Composable
fun DeleteButton(navController: NavController, dbViewModel: DatabaseViewModel,index: Int)
{
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center)
    {
        var showDialog by remember { mutableStateOf(false) }
        Button(modifier = Modifier.padding(5.dp),
            onClick = { showDialog = true },
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = KTColors.Orange)
        )
        {
            Text("Delete Game")
        }
        if(showDialog)
        {
            ConfirmDialog(
                title = "Delete Game",
                description = "Are you sure you want to delete this game?",
                confirmText = "Delete",
                onAccept = {
                    navController.popBackStack()
                    navController.popBackStack()
                    navController.navigate(Screen.HistoryListScreen.HistoryListScreenRoute())
                    val googleAuthUiClient by lazy {
                        GoogleAuthUIClient(context = context.applicationContext, oneTapClient = Identity.getSignInClient(context.applicationContext))
                    }
                    if(googleAuthUiClient.isUserSignedIn())
                    {
                        dbViewModel.DeleteGame(index,googleAuthUiClient.getSignedInUser())
                    }

                },
                onDismiss = { showDialog = false },
            )
        }
    }
}

