package com.example.killteam.screens

import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.killteam.Screen
import com.example.killteam.firebase.DatabaseViewModel
import com.example.killteam.firebase.SignInState
import com.example.killteam.firebase.TeamSummary
import com.example.killteam.firebase.UserData
import com.example.killteam.ui.theme.KTColors
import kotlin.math.ceil

@Composable
fun SignInScreen(
    state: SignInState,
    onSignInClick: () -> Unit
)
{
    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError)
    {
        state.signInError?.let { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize().padding(15.dp),
        contentAlignment = Alignment.Center
    )
    {
        Button(onClick = onSignInClick,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = KTColors.Orange))
        {
            Text("Sign in")
        }
    }
}

@Composable
fun ProfileScreen(
    userData: UserData?,
    dbViewModel: DatabaseViewModel,
    navController: NavController,
    onSignOutClick: () -> Unit
)
{
    val context = LocalContext.current

    LazyColumn()
    {
        item()
        {
            OverallInfo(dbViewModel,userData,onSignOutClick)
        }
        item()
        {
            favoriteInfo(dbViewModel)
        }
        item()
        {
            TeamSummary(dbViewModel,true)
        }
        item()
        {
            TeamSummary(dbViewModel,false)
        }
        item()
        {
            TeamInfo(navController,dbViewModel)
        }
    }
}

@Composable
fun OverallInfo(dbViewModel: DatabaseViewModel,userData : UserData?,onSignOutClick: () -> Unit)
{
    Column(modifier = Modifier.fillMaxWidth().height(275.dp).padding(5.dp))
    {
        Row(modifier = Modifier.padding(5.dp))
        {
            Column(
                modifier = Modifier.weight(0.5f).fillMaxHeight().padding(5.dp)
                    .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                if (userData?.profilePictureUrl != null) {
                    AsyncImage(
                        modifier = Modifier.size(150.dp).padding(5.dp).clip(RoundedCornerShape(5.dp)),
                        model = userData.profilePictureUrl,
                        contentDescription = "Profile picture",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                if (userData?.username != null) {
                    Text(
                        text = userData.username,
                        style = TextStyle(fontSize = 20.sp),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
                Button(
                    onClick = onSignOutClick,
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = KTColors.Orange
                    )
                )
                {
                    Text("Sign Out")
                }
            }
            if (dbViewModel.data != null) {
                Column(
                    modifier = Modifier.weight(0.5f).padding(5.dp)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp)),
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Text(
                        text = "Overall",
                        modifier = Modifier.fillMaxWidth()
                            .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                            .background(KTColors.Blue),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Text(
                        text = "Game Played: ${dbViewModel.GetNumberOfGames()}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Victories: ${dbViewModel.GetNumberOfVictories()}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Defeats: ${dbViewModel.GetNumberOfDefeat()}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Draws: ${dbViewModel.GetNumberOfDraws()}",
                        modifier = Modifier.padding(start = 5.dp, bottom = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Average Score",
                        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
                            .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                            .background(KTColors.Blue),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    Text(
                        text = "Score: ${"%.2f".format(dbViewModel.GetAvarageScore())}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "CritOp: ${"%.2f".format(dbViewModel.GetAvarageCritOp())}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "TacOp: ${"%.2f".format(dbViewModel.GetAvarageTacOp())}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "KillOp: ${"%.2f".format(dbViewModel.GetAvarageKillOp())}",
                        modifier = Modifier.padding(start = 5.dp, bottom = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                }
            }
        }
    }
}

@Composable
fun favoriteInfo(dbViewModel: DatabaseViewModel)
{
    if (dbViewModel.data != null)
    {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
        )
        {
            Text(
                text = "Favorite",
                modifier = Modifier.fillMaxWidth()
                    .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                    .background(KTColors.Blue),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Text(
                text = "Favorite Team: ${dbViewModel.GetFavoriteTeam()}",
                modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                color = KTColors.Equipment
            )
            Text(
                text = "Favorite TacOp: ${dbViewModel.GetFavoriteTacOp()}",
                modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                color = KTColors.Equipment
            )
            Text(
                text = "Favorite Primary: ${dbViewModel.GetFavoritePrimaryOp()}",
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp).fillMaxWidth(),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                color = KTColors.Equipment
            )
        }
    }
}

@Composable
fun TeamSummary(dbViewModel: DatabaseViewModel,teamRed : Boolean = true)
{
    if (dbViewModel.data != null)
    {
        Column(
            modifier = Modifier.fillMaxWidth().
            padding(horizontal = 10.dp, vertical = 5.dp).
            border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
        )
        {
            Text(
                text = if(teamRed) {"Your Teams Summary"} else {"Opponent's Teams Summary"},
                modifier = Modifier.fillMaxWidth()
                    .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                    .background(KTColors.Blue),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Row(modifier = Modifier.fillMaxWidth().background(KTColors.Blue))
            {
                Text(
                    text = "Name",
                    modifier = Modifier.weight(0.4f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Games",
                    modifier = Modifier.weight(0.15f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Win Rate",
                    modifier = Modifier.weight(0.17f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "C|T|K",
                    modifier = Modifier.weight(0.28f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

            }
            dbViewModel.GetAllTeams(teamRed).forEach { team ->
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Blue))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    val teamInfo : TeamSummary = dbViewModel.GetTeamInfo(team,teamRed)
                    Text(
                        text = team,
                        modifier = Modifier.weight(0.4f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "${teamInfo.games}",
                        modifier = Modifier.weight(0.15f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${ceil(teamInfo.winRate*100).toInt()}%",
                        modifier = Modifier.weight(0.17f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${"%.1f".format(teamInfo.critOp)} | ${"%.1f".format(teamInfo.tacOp)} | ${"%.1f".format(teamInfo.killOp)}",
                        modifier = Modifier.weight(0.28f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun TeamInfo(navController : NavController,dbViewModel: DatabaseViewModel)
{
    if (dbViewModel.data != null)
    {
        Box(modifier = Modifier.fillMaxWidth().padding(5.dp),
            contentAlignment = Alignment.Center)
        {
            Button(
                onClick = { navController.navigate(Screen.TeamDataScreen.TeamScreenRoute()) },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = KTColors.Orange
                )
            )
            {
                Text("Team statistics")
            }
        }
    }
}