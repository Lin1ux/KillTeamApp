package com.example.killteam

import android.util.Log
import androidx.compose.foundation.layout.Box
import com.example.killteam.Objects.KillTeams
import com.example.killteam.Objects.PointType
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Casino
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.killteam.R
import com.example.killteam.ScoreViewModel
import com.example.killteam.Screen
import com.example.killteam.getMissions
import com.example.killteam.ui.theme.KTColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class,ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController : NavController,
           title: String,
           RightButton : Boolean = true,
           onMenuClick: () -> Unit = {})
{
    val scope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier.height(100.dp),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = KTColors.Infiltration,
            titleContentColor = Color.White
        ),
        title = {
            Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center)
            {
                Text(title, textAlign = TextAlign.Center)
            }
        },
        navigationIcon = {
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = onMenuClick )
            {
                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu",tint = Color.White)
            }
        },
        actions = {
            var clicked : Boolean = false
            //val interactionSource = remember { MutableInteractionSource() }

            IconButton(
                modifier = Modifier.fillMaxHeight(),
                enabled = RightButton,
                onClick = {
                                if (!clicked)
                                {
                                    navController.popBackStack()
                                    clicked = true
                                }
                          }
            )
            {
                if(RightButton)
                {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Menu",tint = Color.White)
                }
            }
        }
    )
}

@Composable
fun NavigationMenu(
    navController: NavController,
    items : List<MenuItem>
)
{

    val actions: List<() -> Unit> = listOf(
        { navController.navigate(Screen.LoginScreen.LoginScreenRoute()) },
        { Log.d("Akcja","Akcja 2") },
        { Log.d("Akcja","Akcja 3") },
        { navController.navigate(Screen.DiceScreen.DiceRoute()) },
        { navController.navigate(Screen.HistoryListScreen.HistoryListScreenRoute()) },
        { navController.navigate(Screen.ScoreScreen.ScoreScreenRoute()) },
    )

    Row(modifier = Modifier.fillMaxWidth())
    {
        Column(modifier = Modifier.weight(0.55f).fillMaxHeight())
        {
            Spacer(modifier = Modifier.height(55.dp))
            LazyColumn(modifier = Modifier.clip(RoundedCornerShape(5.dp)).background(KTColors.Background))
            {
                item()
                {
                    Box(modifier = Modifier.fillMaxWidth().height(80.dp).background(KTColors.Background),
                        contentAlignment = Alignment.Center)
                    {
                        Text("Kill Team\n Companion",style = TextStyle(fontSize = 24.sp), textAlign = TextAlign.Center)
                    }
                }
                itemsIndexed(items) { index,item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(KTColors.Background)
                            .clickable() { actions[index]() }
                            .border(2.dp, KTColors.Orange, RoundedCornerShape(5.dp))
                    )
                    {
                        Icon(modifier = Modifier.padding(5.dp),imageVector = item.icon, contentDescription = item.contentDescription)
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(item.title,style = TextStyle(fontSize = 18.sp),modifier = Modifier.padding(5.dp))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(0.45f))
    }
}

data class MenuItem(
    val id : Int,
    val title : String,
    val contentDescription : String,
    val icon : ImageVector = Icons.AutoMirrored.Filled.ArrowBack
)

fun getMenuItem() : List<MenuItem>
{
    return listOf(
        MenuItem(1,"Login","1"),
        MenuItem(2,"Rule Set",""),
        MenuItem(3,"Weapon Rules",""),
        MenuItem(4,"Dice Roller","",Icons.Default.Casino),
        MenuItem(5,"Games","",Icons.Default.Casino),
        MenuItem(6,"Score Screen","",Icons.Outlined.Star)
    )
}
