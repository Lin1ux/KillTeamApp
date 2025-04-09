package com.example.killteam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel()
{
    var round by mutableStateOf(1)

    var redTeamName by mutableStateOf(KillTeams.DeathKorps.name)
    var blueTeamName by mutableStateOf(KillTeams.DeathKorps.name)

    fun ChangeRound(newRound : Int)
    {
        if(newRound > 5 && newRound < 0 )
        {
            round = 1
            return
        }
        round = newRound
    }
}
