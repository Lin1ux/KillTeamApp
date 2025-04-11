package com.example.killteam

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.killteam.ui.theme.KTColors
import kotlin.Int
import kotlin.collections.List

class ScoreViewModel : ViewModel()
{
    var round by mutableStateOf(1)                          //Current round of game

    //First Player - Red
    var RedPlayer by mutableStateOf(Player(
        allPoints = 0,
        selectedTeam = KillTeams.teamList[0],
        selectedTacop = TacOps.Unknown,
        showTacoop = false,
        critPoints = mutableListOf(0,0,0,0,0,0,0),
        tacPoints = mutableListOf(0,0,0,0,0,0,0),
        killPoints = mutableListOf(0,0,0,0,0,0,0)
        )
    )

    //Second Player - Blue
    var BluePlayer by mutableStateOf(Player(
        allPoints = 0,
        selectedTeam = KillTeams.teamList[0],
        selectedTacop = TacOps.Unknown,
        showTacoop = false,
        critPoints = mutableListOf(0,0,0,0,0,0,0),
        tacPoints = mutableListOf(0,0,0,0,0,0,0),
        killPoints = mutableListOf(0,0,0,0,0,0,0)
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
    //Return color which button point should have
    fun GetButtonPointColor(Type : PointType, isRedTeam: Boolean, Index : Int) : Color
    {
        //check if player is red
        if(isRedTeam)
        {
            //Select type of button
            when(Type)
            {
                PointType.CRITOP ->
                {
                    //point which can be changed in current round (for example if user make mistake)
                    if (RedPlayer.critPoints[Index] == 1 && Index.IndexToRound() == round)
                    {
                        return KTColors.Red
                    }
                    else if(RedPlayer.critPoints[Index] == 1)   //point but not changeable in current round
                    {
                        return KTColors.AlphaRed
                    }
                    else    //no point
                    {
                        return Color.Transparent
                    }
                }
                PointType.TACOP ->
                {
                    //point which can be changed in current round (for example if user make mistake)
                    if (RedPlayer.tacPoints[Index] == 1 && Index.IndexToRound() == round)
                    {
                        return KTColors.Red
                    }
                    else if(RedPlayer.tacPoints[Index] == 1) //point but not changeable in current round
                    {
                        return KTColors.AlphaRed
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
                PointType.KILLOP ->
                {
                    //point but not changeable by clicking
                    if (RedPlayer.killPoints[Index] == 1)
                    {
                        return KTColors.AlphaRed
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
            }
        }
        else    //Second Player - Blue
        {
            when(Type)
            {
                PointType.CRITOP ->
                {
                    //point which can be changed in current round (for example if user make mistake)
                    if (BluePlayer.critPoints[Index] == 1 && Index.IndexToRound() == round)
                    {
                        return KTColors.Blue
                    }
                    else if(BluePlayer.critPoints[Index] == 1) //point but not changeable in current round
                    {
                        return KTColors.AlphaBlue
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
                PointType.TACOP ->
                {
                    //point which can be changed in current round (for example if user make mistake)
                    if (BluePlayer.tacPoints[Index] == 1 && Index.IndexToRound() == round)
                    {
                        return KTColors.Blue
                    }
                    else if(BluePlayer.tacPoints[Index] == 1) //point but not changeable in current round
                    {
                        return KTColors.AlphaBlue
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
                PointType.KILLOP ->
                {
                    //point but not changeable by clicking
                    if (BluePlayer.killPoints[Index] == 1)
                    {
                        return KTColors.AlphaBlue
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
            }
        }
    }
    //return alpha depended on current round
    fun GetAlphaByRound(turningPointNumber : Int) : Float
    {
        if (round == turningPointNumber)
        {
            return 1.0f
        }
        else
        {
            return 0.5f
        }
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
    //Change point of Crit Ops point list
    fun SwitchCritPoints(isRedTeam : Boolean,index : Int)
    {
        //Don't make changes if it isn' right round
        if(index.IndexToRound() != round)
        {
            return
        }
        if(isRedTeam)
        {
            //Makes copy of critPoints so change would be seen by button
            RedPlayer = RedPlayer.copy(critPoints = RedPlayer.critPoints.toMutableList().apply
            {
                set(index, if (this[index] == 0) 1 else 0)  //change of point
            })
        }
        else
        {
            //Makes copy of critPoints so change would be seen by button
            BluePlayer = BluePlayer.copy(critPoints = BluePlayer.critPoints.toMutableList().apply
            {
                set(index, if (this[index] == 0) 1 else 0) //change of point
            })
        }
    }
    //Change point of Tac Ops point list
    fun SwitchTacPoints(isRedTeam : Boolean,index : Int)
    {
        //Don't make changes if it isn' right round
        if(index.IndexToRound() != round)
        {
            return
        }
        if(isRedTeam)
        {
            //Makes copy of critPoints so change would be seen by button
            RedPlayer = RedPlayer.copy(tacPoints = RedPlayer.tacPoints.toMutableList().apply
            {
                set(index, if (this[index] == 0) 1 else 0) //change of point
            })
        }
        else
        {
            //Makes copy of critPoints so change would be seen by button
            BluePlayer = BluePlayer.copy(tacPoints = BluePlayer.tacPoints.toMutableList().apply
            {
                set(index, if (this[index] == 0) 1 else 0) //change of point
            })
        }
    }
    //Return value of selected ops on given index
    fun GetPoint(Type : PointType, isRedTeam: Boolean, index : Int) : Int
    {
        if(isRedTeam)
        {
            when(Type)
            {
                PointType.CRITOP ->
                {
                    return RedPlayer.critPoints[index]
                }
                PointType.TACOP ->
                {
                    return RedPlayer.tacPoints[index]
                }
                PointType.KILLOP ->
                {
                    return RedPlayer.killPoints[index]
                }
            }
        }
        else
        {
            when(Type)
            {
                PointType.CRITOP ->
                {
                    return BluePlayer.critPoints[index]
                }
                PointType.TACOP ->
                {
                    return BluePlayer.tacPoints[index]
                }
                PointType.KILLOP ->
                {
                    return BluePlayer.killPoints[index]
                }
            }
        }
    }
    //Return sum of all points
    fun GetAllPoints(isRedTeam : Boolean) : Int
    {
        if(isRedTeam)
        {
            return RedPlayer.critPoints.sum() + RedPlayer.tacPoints.sum() + RedPlayer.killPoints.sum()
        }
        return return BluePlayer.critPoints.sum() + BluePlayer.tacPoints.sum() + BluePlayer.killPoints.sum()
    }
}

//Data of player
data class Player(
        //Points
    var allPoints : Int,
    var critPoints : MutableList<Int>,
    var tacPoints : MutableList<Int>,
    var killPoints : MutableList<Int>,
        //Team Info
    var selectedTeam : TeamInfo,
    var selectedTacop : Mission,
    var showTacoop : Boolean
        )

//Enum for selecting Ops
enum class PointType
{
    CRITOP,
    TACOP,
    KILLOP
}