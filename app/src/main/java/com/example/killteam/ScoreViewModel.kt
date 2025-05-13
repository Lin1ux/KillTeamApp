package com.example.killteam

import com.example.killteam.Objects.Equipment
import com.example.killteam.Objects.KillOpPoints.KillOpPointMatrix
import com.example.killteam.Objects.KillTeams
import com.example.killteam.Objects.Mission
import com.example.killteam.Objects.Operator
import com.example.killteam.Objects.Ploy
import com.example.killteam.Objects.PointType
import com.example.killteam.Objects.TacOps
import com.example.killteam.Objects.TeamInfo
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.compose.ui.graphics.Color
import com.example.killteam.Objects.SelectionRuleList
import com.example.killteam.Objects.SelectionRuleListChapterTactics
import com.example.killteam.Objects.SelectionRuleListWithPrimary
import com.example.killteam.Objects.TeamRule
import com.example.killteam.Objects.Weapon
import com.example.killteam.ui.theme.KTColors
import kotlin.Int

class ScoreViewModel : ViewModel()
{
    var round by mutableStateOf(1)                          //Current round of game
    var gameFinished by mutableStateOf(false)               //Is game finished

    var initiativeSet = mutableStateListOf(true,false,false,false)        //Is initiative set for rounds

    //Player State
    inner class PlayerState
    {
        var selectedTeam by mutableStateOf(KillTeams.teamList[0])       //Selected Kill Team
        var selectedTacop by mutableStateOf(TacOps.Unknown)             //Selected Tacop
        var primaryOp by mutableStateOf(PointType.UNKNOWN)              //Selected Primary OP

        val critPoints = mutableStateListOf(0,0,0,0,0,0,0)              //Points from CritOp
        val tacPoints = mutableStateListOf(0,0,0,0,0,0,0)               //Points from TacOp
        var killPoints = mutableStateListOf(0,0,0,0,0,0,0)              //Points from KillOp

        var commandPoints by mutableStateOf(3)                          //Amount of Command points (CP)

        var teamRulesData = mutableStateListOf<teamRulesState>()        //List of Team rules

        var ploysData = mutableStateListOf<ploySelection>()             //List of information about ploys
        var eqData = mutableStateListOf<eqSelection>()                  //List of selected equipment
        var troopsData = mutableStateListOf<selectedOperators>()        //List of selected operators

        var troopsSelected by mutableStateOf(false)                     //Are operators selected

        fun SetTeam(team : TeamInfo)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            teamRulesData.clear()
            ploysData.clear()
            eqData.clear()
            troopsData.clear()
            troopsSelected = false
            selectedTeam = team
            killPoints = mutableStateListOf(0,0,0,0,0,0,0)
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
        fun IncreaseCP(value : Int = 1)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            commandPoints += value
        }
        //Decrease Players CP
        fun DecreaseCP(value : Int = 1)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            commandPoints -= value
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
            primaryOp = type
        }
        //Return is Primary op selected
        fun IsPrimaryOpSelected() : Boolean
        {
            return primaryOp != PointType.UNKNOWN
        }
        //Give information about Selected Tacop
        fun GetPrimaryInfo() : String
        {
            if(primaryOp == PointType.UNKNOWN) //If player don't select primary op
            {
                return "Select Primary Op!"
            }
            if(!gameFinished)   //If game isn't finished primary op is hidden
            {
                return "Primary Op Selected"
            }
            //If game finishes tac op is revealed
            when(primaryOp)
            {
                PointType.CRITOP -> { return "Crit Op: "+critPoints.last().toString() }
                PointType.TACOP -> { return "Tac Op: "+tacPoints.last().toString() }
                PointType.KILLOP -> { return "Kill Op: "+killPoints.last().toString() }
                PointType.UNKNOWN -> { return "Primary Op: None"}
            }
        }

        //Return team rules of player
        fun GetTeamRulesData() : List<teamRulesState>
        {
            if(teamRulesData.isEmpty())
            {
                selectedTeam.teamRules.forEachIndexed { index, rule ->
                    teamRulesData.add(teamRulesState(rule.deepCopy(),index))
                    teamRulesData[index].rule.ruleIndex = index
                }
            }
            return teamRulesData
        }

        fun GetTeamRule(index : Int) : TeamRule
        {
            return teamRulesData[index].rule
        }
        //Set selected Index
        fun SetTeamRuleActiveIndex(value : Int,index : Int)
        {
            (teamRulesData[index].rule as? SelectionRuleList)?.SelectIndex(value)
            (teamRulesData[index].rule as? SelectionRuleListWithPrimary)?.SelectIndex(value)
            (teamRulesData[index].rule as? SelectionRuleListChapterTactics)?.SelectIndex(value)

        }
        //Set Primary Index
        fun SetTeamRulePrimaryIndex(value : Int,index : Int)
        {
            (teamRulesData[index].rule as? SelectionRuleListWithPrimary)?.SelectPrimaryIndex(value)
            (teamRulesData[index].rule as? SelectionRuleListChapterTactics)?.SelectPrimaryIndex(value)
        }
        //Reset Selected Index
        fun ResetSelection()
        {
            teamRulesData.forEachIndexed { index, rule ->
                (teamRulesData[index].rule as? SelectionRuleList)?.SelectIndex(-1)
                (teamRulesData[index].rule as? SelectionRuleListWithPrimary)?.SelectIndex(-1)
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
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            ploysData[Index].selected = !ploysData[Index].selected
        }
        //Switch Placement of Ploy
        fun SwitchEqPlacement(Index: Int)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
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
        //Confirm that team is selected
        fun selectTeam()
        {
            troopsSelected = true
        }
        //Return current wounds
        fun GetCurrentWounds(index: Int) : Int
        {
            return troopsData[index].currentWounds.value
        }
        //Set current operator wounds on 0
        fun KillOperator(index : Int)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            troopsData[index].currentWounds.value = 0
            CountKillBonus()
        }
        //Changes state of readiness
        fun SwitchOperatorReadiness(index : Int)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            troopsData[index].ready.value = !troopsData[index].ready.value
        }
        //Return is operator ready
        fun IsOperatorReady(index : Int) : Boolean
        {
            return troopsData[index].ready.value
        }
        //Switch order state
        fun SwitchOperatorOrder(index : Int)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            when(troopsData[index].order.value)
            {
                Order.CONCEAL -> troopsData[index].order.value = Order.ENGAGE
                Order.ENGAGE -> troopsData[index].order.value = Order.CONCEAL
            }
        }
        //Make all operators ready
        fun SetAllOperatorsReady()
        {
            troopsData.forEachIndexed { index,troop ->
                troopsData[index].ready.value = true
            }
        }
        //Increase amount of wounds
        fun IncreaseWound(value : Int,index : Int)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            troopsData[index].currentWounds.value += value
            if (troopsData[index].currentWounds.value > troopsData[index].operator.wounds)
            {
                troopsData[index].currentWounds.value = troopsData[index].operator.wounds
            }
            CountKillBonus()
        }
        //Deacrease operators Wounds
        fun DecreaseWound(value : Int,index : Int)
        {
            if(gameFinished) //block possibility to change value after finishing game
            {
                return
            }
            troopsData[index].currentWounds.value -= value
            if (troopsData[index].currentWounds.value < 0)
            {
                troopsData[index].currentWounds.value = 0
            }
            CountKillBonus()
        }
        //Get Weapon by index from operator's index and
        fun GetWeaponById(unitIndex : Int, weaponIndex : Int) : Weapon
        {
            return troopsData[unitIndex].operator.weapons[weaponIndex]
        }
    }
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
        if(newRound > 5 || newRound < 0)
        {
            round = 1
            return
        }
        round = newRound
    }
    //Set Initiative
    fun SetInitiative(isFirstPlayerInits : Boolean, initiativeRound : Int)
    {
        if(initiativeSet[initiativeRound])
        {
            return
        }
        //Saving information about changing initiative in this round
        initiativeSet[initiativeRound] = true
        //Resetting ploys
        BluePlayer.ploysData = mutableStateListOf<ploySelection>()
        RedPlayer.ploysData = mutableStateListOf<ploySelection>()
        //Setting all operatives to ready state
        RedPlayer.SetAllOperatorsReady()
        BluePlayer.SetAllOperatorsReady()
        //Resetting selected fraction rules
        RedPlayer.ResetSelection()
        BluePlayer.ResetSelection()
        if(isFirstPlayerInits)
        {
            RedPlayer.IncreaseCP(1)
            BluePlayer.IncreaseCP(2)
            return
        }
        RedPlayer.IncreaseCP(2)
        BluePlayer.IncreaseCP(1)
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
    //Counts kill points
    fun CountKillBonus()
    {
        //Get max amount of operator
        val redOperatorAmount : Int = RedPlayer.GetSelectedTroops().size
        val blueOperatorAmount : Int = BluePlayer.GetSelectedTroops().size

        var redDead : Int = 0
        var blueDead : Int = 0

        //Counting dead operators
        RedPlayer.GetSelectedTroops().forEach { troop ->
            if(troop.currentWounds.value <= 0)
            {
                redDead++
            }
        }
        BluePlayer.GetSelectedTroops().forEach { troop ->
            if(troop.currentWounds.value <= 0)
            {
                blueDead++
            }
        }
        //Getting points from kills
        var BluePoints = KillOpPointMatrix[redDead]?.get(redOperatorAmount) //Points depend on killed red operators
        var RedPoints = KillOpPointMatrix[blueDead]?.get(blueOperatorAmount) //Points depend on killed blue operators

        //if null it's change for 0
        if(BluePoints == null)
        {
            BluePoints = 0
        }
        if(RedPoints == null)
        {
            RedPoints = 0
        }
        //Setting red Points
        RedPlayer.killPoints.forEachIndexed { index,point ->
            if(index < RedPoints)
            {
                RedPlayer.killPoints[index] = 1
            }
            else
            {
                RedPlayer.killPoints[index] = 0
            }
        }
        //Setting blue Points
        BluePlayer.killPoints.forEachIndexed { index,point ->
            if(index < BluePoints)
            {
                BluePlayer.killPoints[index] = 1
            }
            else
            {
                BluePlayer.killPoints[index] = 0
            }
        }
        //Extra point for having more kill points
        if(BluePoints < RedPoints)
        {
            RedPlayer.killPoints[5] = 1
            BluePlayer.killPoints[5] = 0
        }
        else if(RedPoints < BluePoints)
        {
            RedPlayer.killPoints[5] = 0
            BluePlayer.killPoints[5] = 1
        }
        else    //Points are equal means no extra points
        {
            RedPlayer.killPoints[5] = 0
            BluePlayer.killPoints[5] = 0
        }
    }
    //Finishes game and counts bonus points
    fun FinishGame()
    {
        gameFinished = true
        //Counting Primary Ops points for Red Player
        when(RedPlayer.primaryOp)
        {
            PointType.CRITOP -> { RedPlayer.critPoints[RedPlayer.critPoints.size-1] = countBonus(RedPlayer.critPoints) }
            PointType.TACOP -> { RedPlayer.tacPoints[RedPlayer.tacPoints.size-1] = countBonus(RedPlayer.tacPoints) }
            PointType.KILLOP -> { RedPlayer.killPoints[RedPlayer.killPoints.size-1] = countBonus(RedPlayer.killPoints) }
            PointType.UNKNOWN -> { }
        }
        //Counting Primary Ops points for Blue Player
        when(BluePlayer.primaryOp)
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
    var ready : MutableState<Boolean> = mutableStateOf(true),
    var order : MutableState<Order> = mutableStateOf(Order.CONCEAL)
)

enum class Order
{
    CONCEAL,
    ENGAGE
}

data class teamRulesState(
    var rule : TeamRule,
    var index : Int
)


