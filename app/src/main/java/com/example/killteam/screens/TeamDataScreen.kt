package com.example.killteam.screens

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.Objects.KillTeams.teamList
import com.example.killteam.Screen
import com.example.killteam.firebase.DatabaseViewModel
import com.example.killteam.ui.theme.KTColors

@Composable
fun TeamDataScreen(navController : NavController,dbViewModel: DatabaseViewModel)
{
    if (dbViewModel.data != null)
    {
        LazyColumn()
        {
            item()
            {
                TeamIcons(
                    onClick = { team ->
                        navController.navigate(Screen.FractionDataScreen.FractionDataRoute(team))
                    })
            }
        }
    }
}
@Composable
fun TeamRulesScreen(navController: NavController)
{
    LazyColumn()
    {
        item()
        {
            TeamIcons(
                onClick = { team ->
                    navController.navigate(Screen.FractionRulesScreen.FractionRulesRoute(team))
                })
        }
    }
}

@Composable
fun TeamIcons(onClick: (teamName : String) -> Unit)
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        teamList.chunked(2).forEach { pair ->
            Row(modifier = Modifier.padding(5.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                pair.forEach { team ->
                    Box(modifier = Modifier.padding(5.dp).weight(1f).fillMaxWidth().height(200.dp),
                        contentAlignment = Alignment.Center)
                    {
                        Column(modifier = Modifier.fillMaxSize().clickable{ onClick(team.name) })
                        {
                            Icon(
                                modifier = Modifier.padding(5.dp).weight(0.7f).fillMaxSize(),
                                painter = painterResource(team.icon),
                                contentDescription = team.name,
                                tint = KTColors.Orange,
                            )
                            Text(text = team.name,
                                modifier = Modifier.weight(0.3f).fillMaxWidth(),
                                style = TextStyle(fontSize = 25.sp),
                                textAlign = TextAlign.Center)
                        }
                    }
                }
                if (pair.size < 2)
                {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}