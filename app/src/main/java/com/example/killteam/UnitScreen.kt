package com.example.killteam

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun UnitScreen(viewModel: ScoreViewModel,firstPlayer : Boolean)
{
    LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp))
    {
        item()
        {

        }
    }
}