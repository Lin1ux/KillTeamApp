package com.example.killteam.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.killteam.Objects.TeamInfo
import com.example.killteam.RemoveKeyWord
import com.example.killteam.Screen
import com.example.killteam.firebase.DatabaseViewModel
import com.example.killteam.firebase.EqSummary
import com.example.killteam.firebase.OperatorSummary
import com.example.killteam.firebase.PrimarySummary
import com.example.killteam.firebase.TacOpSummary
import com.example.killteam.firebase.TeamSummary
import com.example.killteam.firebase.UserData
import com.example.killteam.ui.theme.KTColors
import kotlin.math.ceil

@Composable
fun FactionData(navController : NavController,team : TeamInfo,dbViewModel : DatabaseViewModel)
{
    var RedTeamOnly by remember { mutableStateOf(true) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        item()
        {
            Button(
                modifier = Modifier.fillMaxWidth(0.4f),
                onClick = { RedTeamOnly = !RedTeamOnly },
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = KTColors.Orange
                )
            )
            {
                if(RedTeamOnly)
                {
                    Text("All Teams")
                }
                else
                {
                    Text("Only Red Team")
                }
            }
        }
        item()
        {
            OverallTeamInfo(dbViewModel,team,RedTeamOnly)
        }
        item()
        {
            favoriteTeamInfo(dbViewModel,team,RedTeamOnly)
        }
        item()
        {
            TeamGamesInfo(dbViewModel,team,RedTeamOnly)
        }
        item()
        {
            TeamPrimaryInfo(dbViewModel,team,RedTeamOnly)
        }
        item()
        {
            TeamTacOpInfo(dbViewModel,team,RedTeamOnly)
        }
        item()
        {
            TeamEqInfo(dbViewModel,team,RedTeamOnly)
        }
        item()
        {
            TeamOperatorsInfo(dbViewModel,team,RedTeamOnly)
        }
    }
}

@Composable
fun OverallTeamInfo(dbViewModel: DatabaseViewModel,team : TeamInfo, RedTeamOnly : Boolean = true)
{
    Column(modifier = Modifier.fillMaxWidth().height(275.dp))
    {
        Row(modifier = Modifier.padding(5.dp))
        {
            Column(
                modifier = Modifier.weight(0.5f).height(275.dp).padding(5.dp)
                    .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Icon(
                    modifier = Modifier.size(150.dp).padding(5.dp).weight(0.7f),
                    painter = painterResource(team.icon),
                    contentDescription = team.name,
                    tint = KTColors.Orange,
                )
                Spacer(modifier = Modifier.weight(0.05f))
                Text(
                    modifier = Modifier.weight(0.25f),
                    text = team.name,
                    style = TextStyle(fontSize = 20.sp),
                    textAlign = TextAlign.Center
                )
            }
            if (dbViewModel.data != null) {
                Column(
                    modifier = Modifier.weight(0.5f).height(275.dp).padding(5.dp)
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
                        text = "Games Played: ${dbViewModel.GetNumberOfGames(team,RedTeamOnly)}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Victories: ${dbViewModel.GetNumberOfVictories(team,RedTeamOnly)}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Defeats: ${dbViewModel.GetNumberOfDefeat(team,RedTeamOnly)}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "Draws: ${dbViewModel.GetNumberOfDraws(team,RedTeamOnly)}",
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
                        text = "Score: ${"%.2f".format(dbViewModel.GetAvarageScore(team,RedTeamOnly))}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "CritOp: ${"%.2f".format(dbViewModel.GetAvarageCritOp(team,RedTeamOnly))}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "TacOp: ${"%.2f".format(dbViewModel.GetAvarageTacOp(team,RedTeamOnly))}",
                        modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                        style = TextStyle(fontSize = 18.sp),
                        textAlign = TextAlign.Start,
                        color = KTColors.Equipment
                    )
                    Text(
                        text = "KillOp: ${"%.2f".format(dbViewModel.GetAvarageKillOp(team,RedTeamOnly))}",
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
fun favoriteTeamInfo(dbViewModel: DatabaseViewModel,team : TeamInfo, RedTeamOnly : Boolean = true)
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
                text = "Favorite TacOp: ${dbViewModel.GetFavoriteTacOp(team,RedTeamOnly)}",
                modifier = Modifier.padding(start = 5.dp).fillMaxWidth(),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                color = KTColors.Equipment
            )
            Text(
                text = "Favorite Primary: ${dbViewModel.GetFavoritePrimaryOp(team,RedTeamOnly)}",
                modifier = Modifier.padding(start = 5.dp, bottom = 5.dp).fillMaxWidth(),
                style = TextStyle(fontSize = 18.sp),
                textAlign = TextAlign.Start,
                color = KTColors.Equipment
            )
        }
    }
}

@Composable
fun TeamGamesInfo(dbViewModel: DatabaseViewModel,team : TeamInfo,RedTeamOnly: Boolean = true)
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
                text = "Games Against",
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
            dbViewModel.GetAllTeamsAgainst(team,RedTeamOnly).forEach { teamAgainst ->
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Blue))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    val teamInfo : TeamSummary = dbViewModel.GetTeamPlayedAgainstInfo(team,teamAgainst,RedTeamOnly)
                    Text(
                        text = teamAgainst,
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
fun TeamEqInfo(dbViewModel: DatabaseViewModel,team : TeamInfo,RedTeamOnly: Boolean = true)
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
                text = "Equipment",
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
                    modifier = Modifier.weight(0.3f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Win Rate",
                    modifier = Modifier.weight(0.3f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            dbViewModel.GetEqTeams(team,RedTeamOnly).forEach { eq ->
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Blue))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    val eqInfo : EqSummary = dbViewModel.GetEqInfo(team,eq,RedTeamOnly)
                    Text(
                        text = eq,
                        modifier = Modifier.weight(0.4f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "${eqInfo.games}",
                        modifier = Modifier.weight(0.3f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${ceil(eqInfo.winRate*100).toInt()}%",
                        modifier = Modifier.weight(0.3f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun TeamTacOpInfo(dbViewModel: DatabaseViewModel,team : TeamInfo,RedTeamOnly: Boolean = true)
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
                text = "Tacops",
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
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Win Rate",
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Avg Score",
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            dbViewModel.GetTacopTeams(team,RedTeamOnly).forEach { tacop ->
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Blue))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    val tacOpInfo : TacOpSummary = dbViewModel.GetTacopInfo(team,tacop,RedTeamOnly)
                    Text(
                        text = tacop,
                        modifier = Modifier.weight(0.4f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "${tacOpInfo.games}",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${ceil(tacOpInfo.winRate*100).toInt()}%",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${"%.1f".format(tacOpInfo.score)}",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun TeamPrimaryInfo(dbViewModel: DatabaseViewModel,team : TeamInfo,RedTeamOnly: Boolean = true)
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
                text = "Primary Ops",
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
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Win Rate",
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Avg Score",
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            dbViewModel.GetPrimaryOp(team,RedTeamOnly).forEach { primaryOp ->
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Blue))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    val eqInfo : PrimarySummary = dbViewModel.GetPrimaryOpInfo(team,primaryOp,RedTeamOnly)
                    Text(
                        text = primaryOp,
                        modifier = Modifier.weight(0.4f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "${eqInfo.games}",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${ceil(eqInfo.winRate*100).toInt()}%",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${"%.1f".format(eqInfo.score)}",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun TeamOperatorsInfo(dbViewModel: DatabaseViewModel,team : TeamInfo,RedTeamOnly: Boolean = true)
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
                text = "Operators",
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
                    modifier = Modifier.weight(0.6f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Games",
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
                Text(
                    text = "Win Rate",
                    modifier = Modifier.weight(0.2f)
                        .border(2.dp, KTColors.Blue, RoundedCornerShape(5.dp))
                        .background(KTColors.Blue),
                    style = TextStyle(fontSize = 14.sp),
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
            dbViewModel.GetOperatorsTeams(team,RedTeamOnly).forEach { unit ->
                Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).background(KTColors.Blue))
                Row(modifier = Modifier.fillMaxWidth().padding(5.dp))
                {
                    val operatorsInfo : OperatorSummary = dbViewModel.GetOperatorsInfo(team,unit,RedTeamOnly)
                    Text(
                        text = "${unit.name.RemoveKeyWord(team.name)} " +
                                if(unit.amount > 1) "(x${unit.amount})" else "",
                        modifier = Modifier.weight(0.6f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Start,
                    )
                    Text(
                        text = "${operatorsInfo.games}",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                    Text(
                        text = "${ceil(operatorsInfo.winRate*100).toInt()}%",
                        modifier = Modifier.weight(0.2f),
                        style = TextStyle(fontSize = 14.sp),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}