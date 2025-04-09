package com.example.killteam

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.killteam.ui.theme.KTColors

class ScoreViewModel : ViewModel()
{
    var round by mutableStateOf(1)                          //Current round of game

    //var redTeam by mutableStateOf(KillTeams.teamList[0])    //Team selected by red player
    //var blueTeam by mutableStateOf(KillTeams.teamList[0])   //Team selected by blue player

    //var selectedRedTacop by mutableStateOf(TacOps.Unknown)  //Selected tacoop by red player
    //var selectedBlueTacop by mutableStateOf(TacOps.Unknown) //Selected tacoop by blue player

    var RedPlayer by mutableStateOf(Player(
        selectedTeam = KillTeams.teamList[0],
        selectedTacop = TacOps.Unknown,
        showTacoop = false
        )
    )

    var BluePlayer by mutableStateOf(Player(
        selectedTeam = KillTeams.teamList[0],
        selectedTacop = TacOps.Unknown,
        showTacoop = false
        )
    )

    //Change round on selected value
    fun ChangeRound(newRound : Int)
    {
        if(newRound > 5 || newRound < 0)
        {
            round = 1
            return
        }
        round = newRound
    }
    //return background color which depends of round number
    fun GetBackgroundRoundColor(buttonNumber : Int) : Color
    {
        if(round == buttonNumber)
        {
            return KTColors.Orange
        }
        return Color.Transparent
    }
    //return text color which depends of round number
    fun GetTextRoundColor(buttonNumber : Int) : Color
    {
        if(round == buttonNumber)
        {
            return Color.White
        }
        return KTColors.Orange
    }
    //return Information about selected team
    fun GetTeam(isRedTeam : Boolean) : TeamInfo
    {
        if(isRedTeam)
        {
            return RedPlayer.selectedTeam
        }
        return BluePlayer.selectedTeam
    }
}

data class Player(
        var selectedTeam : TeamInfo,
        var selectedTacop : Mission,
        var showTacoop : Boolean
        )