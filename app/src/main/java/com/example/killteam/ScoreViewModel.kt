package com.example.killteam

import Objects.Equipment
import Objects.KillTeams
import Objects.Mission
import Objects.Operator
import Objects.Ploy
import Objects.PointType
import Objects.TacOps
import Objects.TeamInfo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.killteam.ui.theme.KTColors
import kotlin.Int

class ScoreViewModel : ViewModel()
{
    var round by mutableStateOf(1)                          //Current round of game
    var currentRound by mutableStateOf(1)                   //used to reset Ploys
    var gameFinished by mutableStateOf(false)               //Is game finished

    //Player State
    inner class PlayerState
    {
        var selectedTeam by mutableStateOf(KillTeams.teamList[0])
        var selectedTacop by mutableStateOf(TacOps.Unknown)
        var primaryTacop by mutableStateOf(PointType.UNKNOWN)

        val critPoints = mutableStateListOf(0,0,0,0,0,0,0)
        val tacPoints = mutableStateListOf(0,0,0,0,0,0,0)
        val killPoints = mutableStateListOf(0,0,0,0,0,0,0)

        var commandPoints by mutableStateOf(2)

        var ploysData = mutableStateListOf<ploySelection>()
        var eqData = mutableStateListOf<eqSelection>()
        var troopsData = mutableStateListOf<selectedOperators>()

        var troopsSelected by mutableStateOf(false)

        fun SetTeam(team : TeamInfo)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            ploysData.clear()
            eqData.clear()
            troopsData.clear()
            troopsSelected = false
            selectedTeam = team
        }
        //return Information about selected team
        fun GetTeam() : TeamInfo
        {
            return selectedTeam
        }
        //Change point of Crit Ops point list
        fun SwitchCritPoints(index : Int)
        {
            //Don't make changes if it isn' right round
            if(index.IndexToRound() != round || gameFinished)
            {
                return
            }
            if (critPoints[index] == 0)
            {
                critPoints[index] = 1
                return
            }
            critPoints[index] = 0
        }
        //Change point of Tac Ops point list
        fun SwitchTacPoints(index : Int)
        {
            //Don't make changes if it isn' right round
            if(index.IndexToRound() != round)
            {
                return
            }
            if (tacPoints[index] == 0)
            {
                tacPoints[index] = 1
                return
            }
            tacPoints[index] = 0
        }
        //Return sum of all points
        fun GetAllPoints() : Int
        {
            return critPoints.sum() + tacPoints.sum() + killPoints.sum()
        }
        //Return Command points of player
        fun GetCP() : Int
        {
            return commandPoints
        }
        //Increases Players CP
        fun IncreaseCP()
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            commandPoints++
        }
        //Decrease Players CP
        fun DecreaseCP()
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            commandPoints--
            if(commandPoints < 0)   //prevent showin values less than 0
            {
                commandPoints = 0
            }
        }
        //Returns TacOps
        fun GetTacOp() : Mission
        {
            return selectedTacop
        }

        //Selects TacOps
        fun SetTacOp(TacOp : Mission)
        {
            if(gameFinished)    //block possibility to change value after finishing game
            {
                return
            }
            selectedTacop = TacOp
        }

        //Return value of selected ops on given index
        fun GetPoint(Type : PointType,index : Int) : Int
        {
            when(Type)
            {
                PointType.CRITOP ->
                {
                    return critPoints[index]
                }
                PointType.TACOP ->
                {
                    return tacPoints[index]
                }
                PointType.KILLOP ->
                {
                    return killPoints[index]
                }
                else -> { return 0}
            }
        }

        //Selects Primary Ops
        fun SetPrimaryOp(type : PointType)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            primaryTacop = type
        }
        //Return is Primary op selected
        fun IsPrimaryOpSelected() : Boolean
        {
            return primaryTacop != PointType.UNKNOWN
        }
        //Give information about Selected Tacop
        fun GetPrimaryInfo() : String
        {
            if(primaryTacop == PointType.UNKNOWN) //If player don't select primary op
            {
                return "Select Primary Op!"
            }
            if(!gameFinished)   //If game isn't finished primary op is hidden
            {
                return "Primary Op Selected"
            }
            //If game finishes tac op is revealed
            when(primaryTacop)
            {
                PointType.CRITOP -> { return "Crit Op: "+critPoints.last().toString() }
                PointType.TACOP -> { return "Tac Op: "+tacPoints.last().toString() }
                PointType.KILLOP -> { return "Kill Op: "+killPoints.last().toString() }
                PointType.UNKNOWN -> { return "Primary Op: None"}
            }
        }

        //Return ploy Selection of player
        fun GetPloysBySelection() : List<ploySelection>
        {
            //if list is null
            if(ploysData.isEmpty())
            {
                selectedTeam.ploys.forEachIndexed { index,ploy ->
                    ploysData.add(ploySelection(ploy,false,index))
                }
            }
            return ploysData
        }
        //Return equipment Selection of player
        fun GetEqBySelection() : List<eqSelection>
        {
            //if list is null
            if(eqData.isEmpty())
            {
                selectedTeam.equipment.forEachIndexed { index,eq ->
                    eqData.add(eqSelection(eq,false,index))
                }
            }
            return eqData
        }
        //Switch Activation of Ploy
        fun SwitchPloyActivation(Index: Int)
        {
            ploysData[Index].selected = !ploysData[Index].selected
        }
        //Switch Placement of Ploy
        fun SwitchEqPlacement(Index: Int)
        {
            eqData[Index] = eqData[Index].copy(selected = !eqData[Index].selected)
        }
        //Return is player finish making his squad
        fun IsTroopsSelected() : Boolean
        {
            return troopsSelected
        }
        //return selected troops
        fun GetSelectedTroops() : List<selectedOperators>
        {
            return troopsData
        }
        //return is operator was selected
        fun isOperatorSelected(operator: Operator) : Boolean
        {
            troopsData.forEach { troop ->
                if(troop.operator == operator)
                {
                    return true
                }
            }
            return false
        }
        //Add operator to selected troops
        fun AddTroop(operator: Operator)
        {
            if(troopsData.size < selectedTeam.maxOperator)
            {
                troopsData.add(selectedOperators(operator = operator, currentWounds = mutableStateOf(operator.wounds)))
            }

        }
        //Remove operator from selected troops
        fun RemoveTroop(operator: Operator)
        {
            troopsData.forEachIndexed { index, troop ->
                if(troop.operator == operator)
                {
                    troopsData.removeAt(index)
                    return
                }
            }
        }
        //Clear all selected troops
        fun ClearTroopSelection()
        {
            troopsData.clear()
        }
        //Give troop selected by Index
        fun GetTroopSelectionByIndex(index : Int) : selectedOperators
        {
            return troopsData[index]
        }
        //Give troop selected by Index
        fun GetTroopByIndex(index : Int) : Operator
        {
            return troopsData[index].operator
        }
        //Check if selected squad is legal (valid)
        fun ValidateTeam() : Boolean
        {
            var leaderAmount : Int = 0
            var operatorAmount : Int = 0
            troopsData.forEach { troop ->
                if(troop.operator.leader)
                {
                    leaderAmount += 1
                }
                operatorAmount += 1
            }
            if(leaderAmount != 1)
            {
                return false
            }
            if(operatorAmount < selectedTeam.minOperators)
            {
                return false
            }
            if(operatorAmount > selectedTeam.maxOperator)
            {
                return false
            }
            return true
        }
        fun selectTeam()
        {
            troopsSelected = true
        }
    }
    //ENND
    val RedPlayer = PlayerState()   //First Player - Red
    val BluePlayer = PlayerState()  //Blue Player - Blue

    //Return player
    fun GetPlayer(isRedTeam: Boolean) : PlayerState
    {
        if(isRedTeam)
        {
            return RedPlayer
        }
        return BluePlayer
    }
    //Change round on selected value
    fun ChangeRound(newRound : Int)
    {
        if(gameFinished) //block possibility to change value after finishing game
        {
            return
        }
        if(currentRound+1 == newRound)  //Reseting Ploys
        {
            currentRound += 1
            BluePlayer.ploysData = mutableStateListOf<ploySelection>()
            RedPlayer.ploysData = mutableStateListOf<ploySelection>()
        }
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
    //return alpha depended on current round
    fun GetAlphaByRound(turningPointNumber : Int) : Float
    {
        if (round == turningPointNumber || gameFinished)
        {
            return 1.0f
        }
        else
        {
            return 0.5f
        }
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
                    if (RedPlayer.critPoints[Index] == 1 && (Index.IndexToRound() == round || gameFinished))
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
                    if (RedPlayer.tacPoints[Index] == 1 && (Index.IndexToRound() == round || gameFinished))
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
                    //Finish game
                    if(RedPlayer.killPoints[Index] == 1 && gameFinished)
                    {
                        return KTColors.Red
                    }
                    else if (RedPlayer.killPoints[Index] == 1) //point but not changeable by clicking
                    {
                        return KTColors.AlphaRed
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
                else -> { return Color.Transparent }
            }
        }
        else    //Second Player - Blue
        {
            when(Type)
            {
                PointType.CRITOP ->
                {
                    //point which can be changed in current round (for example if user make mistake)
                    if (BluePlayer.critPoints[Index] == 1 && (Index.IndexToRound() == round || gameFinished))
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
                    if (BluePlayer.tacPoints[Index] == 1 && (Index.IndexToRound() == round || gameFinished))
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
                    //Finish game
                    if(BluePlayer.killPoints[Index] == 1 && gameFinished)
                    {
                        return KTColors.Blue
                    }
                    if (BluePlayer.killPoints[Index] == 1) //point but not changeable by clicking
                    {
                        return KTColors.AlphaBlue
                    }
                    else //no point
                    {
                        return Color.Transparent
                    }
                }
                else -> { return Color.Transparent}
            }
        }
    }

    //Returns TacOp color
    fun GetTacOpColor(isRedTeam: Boolean) : Color
    {
        if(isRedTeam)
        {
            if(RedPlayer.selectedTacop != TacOps.Unknown)
            {
                return RedPlayer.selectedTacop.color
            }
            return KTColors.Red
        }
        if(BluePlayer.selectedTacop != TacOps.Unknown)
        {
            return BluePlayer.selectedTacop.color
        }
        return KTColors.Blue
    }
    //Finishes game and counts bonus points
    fun FinishGame()
    {
        gameFinished = true
        //Counting Primary Ops points for Red Player
        when(RedPlayer.primaryTacop)
        {
            PointType.CRITOP -> { RedPlayer.critPoints[RedPlayer.critPoints.size-1] = countBonus(RedPlayer.critPoints) }
            PointType.TACOP -> { RedPlayer.tacPoints[RedPlayer.tacPoints.size-1] = countBonus(RedPlayer.tacPoints) }
            PointType.KILLOP -> { RedPlayer.killPoints[RedPlayer.killPoints.size-1] = countBonus(RedPlayer.killPoints) }
            PointType.UNKNOWN -> { }
        }
        //Counting Primary Ops points for Blue Player
        when(BluePlayer.primaryTacop)
        {
            PointType.CRITOP -> { BluePlayer.critPoints[BluePlayer.critPoints.size-1] = countBonus(BluePlayer.critPoints) }
            PointType.TACOP -> { BluePlayer.tacPoints[BluePlayer.tacPoints.size-1] = countBonus(BluePlayer.tacPoints) }
            PointType.KILLOP -> { BluePlayer.killPoints[BluePlayer.killPoints.size-1] = countBonus(BluePlayer.killPoints) }
            PointType.UNKNOWN -> { }
        }
    }
}

data class ploySelection(
    var ploy : Ploy,
    var selected : Boolean,
    var index : Int
        )

data class eqSelection(
    var eq : Equipment,
    var selected: Boolean,
    var index: Int
)

data class selectedOperators(
    var operator: Operator,
    var currentWounds : MutableState<Int>,
    var ready : Boolean = true,
    var order : Order = Order.CONCEAL
)

enum class Order
{
    CONCEAL,
    ENGAGE
}

