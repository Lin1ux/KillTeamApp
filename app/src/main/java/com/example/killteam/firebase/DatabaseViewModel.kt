package com.example.killteam.firebase

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.killteam.Objects.TeamInfo
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.collections.forEach

class DatabaseViewModel(): ViewModel()
{

    var data by mutableStateOf<GameInfoList?>(null)
        private set

    val db = FirebaseFirestore.getInstance()

    fun IsdataEmpty() : Boolean
    {
        if(data == null)
        {
            return true
        }
        if (data?.games?.size ?: 0 == 0)
        {
            return true
        }
        return false
    }

    fun SaveNewGame(gameData: GameInfo, user : UserData?)
    {
        if(user == null)
        {
            return;
        }
        viewModelScope.launch {
            try
            {
                val document = db.collection("users").document(user.userId).get().await()

                val currentList = document.toObject(GameInfoList::class.java)?.games ?: emptyList()
                val updatedData = GameInfoList(
                    username = user.username ?: "Player",
                    games = currentList + gameData
                )

                db.collection("users").document(user.userId).set(updatedData).addOnSuccessListener {
                    Log.d("Firestore", "User data successfully written!")
                }.addOnFailureListener { e ->
                    Log.w("Firestore", "Error writing document", e)
                }

            } catch (e: Exception) {
                Log.w("Firestore", "Saving Data Failed (loading upToDate data)", e)
                throw e
            }
        }
    }

    fun DeleteGame(index : Int,user : UserData?)
    {
        Log.d("Firestore", "Deleting game ${index} ${data?.games[index]?.redPlayer?.teamName} vs ${data?.games[index]?.bluePlayer?.teamName}")

        var newList : List<GameInfo> = emptyList()

        data?.games?.forEachIndexed { i, gameInfo ->
            if (i != index)
            {
                newList += gameInfo
            }
        }
        if(user == null)
        {
            return;
        }
        viewModelScope.launch {
            try
            {
                //val document = db.collection("users").document(user.userId).get().await()

                //val currentList = document.toObject(GameInfoList::class.java)?.games ?: emptyList()
                val updatedData = GameInfoList(
                    username = user.username ?: "Player",
                    games = newList
                )

                db.collection("users").document(user.userId).set(updatedData).addOnSuccessListener {
                    Log.d("Firestore", "User data successfully written!")
                }.addOnFailureListener { e ->
                    Log.w("Firestore", "Error writing document", e)
                }

            } catch (e: Exception) {
                Log.w("Firestore", "Saving Data Failed (loading upToDate data)", e)
                throw e
            }
        }
    }

    fun getUserData(user: UserData?)
    {
        if(user == null)
        {
            return;
        }
        db.collection("users").document(user.userId)
            .get()
            .addOnSuccessListener { document ->
                // Użyj viewModelScope aby zapewnić główny wątek
                viewModelScope.launch {
                    data = document.toObject(GameInfoList::class.java)
                    Log.d("Firestore", "Data loaded: ${data?.username}")
                }
            }
            .addOnFailureListener { e ->
                viewModelScope.launch {
                    data = null
                    Log.w("Firestore", "Load error", e)
                }
            }
    }

    fun getDataByIndex(index: Int) : GameInfo
    {
        return data?.games[index] ?: GameInfo()
    }
    //Get number of games
    fun GetNumberOfGames(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Int
    {
        if(team == null)
        {
            return data?.games?.size ?: 0
        }
        var games = 0
        data?.games.orEmpty().forEach { game ->
            if(game.redPlayer.teamName == team.name)
            {
                games += 1
            }
            if(!RedTeamOnly && game.bluePlayer.teamName == team.name)
            {
                games += 1
            }
        }
        return games
    }
    //Get  number of victories
    fun GetNumberOfVictories(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Int
    {
        var victories = 0
        if(team == null)
        {
            data?.games?.forEach { game ->

                if(game.redPlayer.score > game.bluePlayer.score)
                {
                    victories++
                }
            }
        }
        else
        {
            data?.games?.forEach { game ->

                if(game.redPlayer.score > game.bluePlayer.score
                    && game.redPlayer.teamName == team.name)
                {
                    victories++
                }
                if( !RedTeamOnly
                    && game.redPlayer.score < game.bluePlayer.score
                    && game.bluePlayer.teamName == team.name)
                {
                    victories++
                }
            }
        }
        return victories
    }

    //Get number of defeats
    fun GetNumberOfDefeat(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Int
    {
        var defeats = 0
        if(team == null)
        {
            data?.games?.forEach { game ->

                if(game.redPlayer.score < game.bluePlayer.score)
                {
                    defeats++
                }
            }
        }
        else
        {
            data?.games?.forEach { game ->
                if(game.redPlayer.score < game.bluePlayer.score
                    && game.redPlayer.teamName == team.name)
                {
                    defeats++
                }
                if( !RedTeamOnly
                    && game.redPlayer.score > game.bluePlayer.score
                    && game.bluePlayer.teamName == team.name)
                {
                    defeats++
                }
            }
        }
        return defeats
    }

    //Get number of victories
    fun GetNumberOfDraws(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Int
    {
        var draws = 0
        if(team == null)
        {
            data?.games?.forEach { game ->

                if(game.redPlayer.score == game.bluePlayer.score)
                {
                    draws++
                }
            }
        }
        else
        {
            data?.games?.forEach { game ->
                if(game.redPlayer.score == game.bluePlayer.score
                    && game.redPlayer.teamName == team.name)
                {
                    draws++
                }
                if( !RedTeamOnly
                    && game.redPlayer.score == game.bluePlayer.score
                    && game.bluePlayer.teamName == team.name)
                {
                    draws++
                }
            }
        }
        return draws
    }

    fun GetAvarageScore(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Float
    {
        var sum = 0.0f
        if(team == null)
        {
            data?.games?.forEach { game ->

                sum += game.redPlayer.score
            }
            return sum/GetNumberOfGames()
        }
        else
        {
            var games = 0
            data?.games?.forEach { game ->
                if (game.redPlayer.teamName == team.name) {
                    games += 1
                    sum += game.redPlayer.score
                }
                if (!RedTeamOnly && game.bluePlayer.teamName == team.name) {
                    games += 1
                    sum += game.bluePlayer.score
                }
            }
            return sum/games
        }
    }

    fun GetAvarageCritOp(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Float
    {
        var sum = 0.0f
        if(team == null)
        {
            data?.games?.forEach { game ->

                sum += game.redPlayer.critPoints.sum()
            }
            return sum/GetNumberOfGames()
        }
        else
        {
            var games = 0
            data?.games?.forEach { game ->
                if (game.redPlayer.teamName == team.name) {
                    games += 1
                    sum += game.redPlayer.critPoints.sum()
                }
                if (!RedTeamOnly && game.bluePlayer.teamName == team.name) {
                    games += 1
                    sum += game.bluePlayer.critPoints.sum()
                }
            }
            return sum/games
        }
    }

    fun GetAvarageTacOp(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Float
    {
        var sum = 0.0f
        if(team == null)
        {
            data?.games?.forEach { game ->

                sum += game.redPlayer.tacPoints.sum()
            }
            return sum / GetNumberOfGames()
        }
        else
        {
            var games = 0
            data?.games?.forEach { game ->
                if (game.redPlayer.teamName == team.name) {
                    games += 1
                    sum += game.redPlayer.tacPoints.sum()
                }
                if (!RedTeamOnly && game.bluePlayer.teamName == team.name) {
                    games += 1
                    sum += game.bluePlayer.tacPoints.sum()
                }
            }
            return sum/games
        }
    }

    fun GetAvarageKillOp(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : Float
    {
        var sum = 0.0f
        if (team == null)
        {
            data?.games?.forEach { game ->

                sum += game.redPlayer.killPoints.sum()
            }
            return sum / GetNumberOfGames()
        }
        else
        {
            var games = 0
            data?.games?.forEach { game ->
                if (game.redPlayer.teamName == team.name) {
                    games += 1
                    sum += game.redPlayer.killPoints.sum()
                }
                if (!RedTeamOnly && game.bluePlayer.teamName == team.name) {
                    games += 1
                    sum += game.bluePlayer.killPoints.sum()
                }
            }
            return sum/games
        }
    }

    fun GetFavoriteTeam() : String
    {
        val teams = mutableMapOf<String, Int>()
        data?.games?.forEach { game ->
            teams[game.redPlayer.teamName] = teams.getOrDefault(game.redPlayer.teamName, 0) + 1
        }
        return teams.maxByOrNull { it.value }?.key ?: ""
    }
    //Get favorite tacOp
    fun GetFavoriteTacOp(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : String {
        val teams = mutableMapOf<String, Int>()
        if (team == null)
        {
            data?.games?.forEach { game ->
                if (game.redPlayer.tacOp != "Unknown") {
                    teams[game.redPlayer.tacOp] = teams.getOrDefault(game.redPlayer.tacOp, 0) + 1
                }
            }
        }
        else
        {
            data?.games?.forEach { game ->
                if (game.redPlayer.tacOp != "Unknown" && game.redPlayer.teamName == team.name) {
                    teams[game.redPlayer.tacOp] = teams.getOrDefault(game.redPlayer.tacOp, 0) + 1
                }
                if (!RedTeamOnly && game.bluePlayer.tacOp != "Unknown" && game.bluePlayer.teamName == team.name)
                {
                    teams[game.bluePlayer.tacOp] = teams.getOrDefault(game.bluePlayer.tacOp, 0) + 1
                }
            }
        }
        return teams.maxByOrNull { it.value }?.key ?: ""
    }
    //Get Favorite Primary Op
    fun GetFavoritePrimaryOp(team: TeamInfo? = null, RedTeamOnly: Boolean = false) : String
    {
        val teams = mutableMapOf<String, Int>()
        if(team == null)
        {
            data?.games?.forEach { game ->
                if (game.redPlayer.primaryOp != "UNKNOWN") {
                    teams[game.redPlayer.primaryOp] =
                        teams.getOrDefault(game.redPlayer.primaryOp, 0) + 1
                }
            }
        }
        else
        {
            data?.games?.forEach { game ->
                if (game.redPlayer.primaryOp != "UNKNOWN" && game.redPlayer.teamName == team.name) {
                    teams[game.redPlayer.primaryOp] = teams.getOrDefault(game.redPlayer.primaryOp, 0) + 1
                }
                if (!RedTeamOnly && game.bluePlayer.tacOp != "Unknown" && game.bluePlayer.teamName == team.name) {
                    teams[game.bluePlayer.primaryOp] = teams.getOrDefault(game.bluePlayer.primaryOp, 0) + 1
                }
            }
        }
        return teams.maxByOrNull { it.value }?.key ?: ""
    }
    //Get all teams
    fun GetAllTeams(redTeam : Boolean) : List<String>
    {
        var teams = mutableListOf<String>()


        data?.games?.forEach { team ->
            if(!(team.redPlayer.teamName in teams) && redTeam)
            {
                teams.add(team.redPlayer.teamName)
            }
            if(!(team.bluePlayer.teamName in teams) && !redTeam)
            {
                teams.add(team.bluePlayer.teamName)
            }
        }
        return teams.sorted()
    }
    //Get all teams which given team played
    fun GetAllTeamsAgainst(playedTeam: TeamInfo, RedTeamOnly: Boolean = true) : List<String>
    {
        var teams = mutableListOf<String>()

        data?.games?.forEach { team ->
            if( playedTeam.name == team.redPlayer.teamName
                && !(team.bluePlayer.teamName in teams))
            {
                    teams.add(team.bluePlayer.teamName)
            }

            if(     !RedTeamOnly
                &&  playedTeam.name == team.bluePlayer.teamName
                &&  !(team.redPlayer.teamName in teams))
            {
                teams.add(team.redPlayer.teamName)
            }
        }
        return teams.sorted()
    }

    //Get informations about team
    fun GetTeamInfo(teamName : String,redTeam : Boolean) : TeamSummary
    {
        var TeamSummary : TeamSummary = TeamSummary(name = teamName)
        if(redTeam)
        {
            //Get team values
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == teamName)
                {
                    TeamSummary.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        TeamSummary.winRate+=1
                    }
                    TeamSummary.score+=team.redPlayer.score
                    TeamSummary.critOp+=team.redPlayer.critPoints.sum()
                    TeamSummary.tacOp+=team.redPlayer.tacPoints.sum()
                    TeamSummary.killOp+=team.redPlayer.killPoints.sum()
                }
            }
            //Counting avarage
            TeamSummary.winRate/=TeamSummary.games
            TeamSummary.score/=TeamSummary.games
            TeamSummary.critOp/=TeamSummary.games
            TeamSummary.tacOp/=TeamSummary.games
            TeamSummary.killOp/=TeamSummary.games
        }
        else
        {
            data?.games?.forEach { team ->
                if(team.bluePlayer.teamName == teamName)
                {
                    TeamSummary.games+=1
                    if(team.bluePlayer.score > team.redPlayer.score)
                    {
                        TeamSummary.winRate+=1
                    }
                    TeamSummary.score+=team.bluePlayer.score
                    TeamSummary.critOp+=team.bluePlayer.critPoints.sum()
                    TeamSummary.tacOp+=team.bluePlayer.tacPoints.sum()
                    TeamSummary.killOp+=team.bluePlayer.killPoints.sum()
                }
            }
            //Counting avarage
            TeamSummary.winRate/=TeamSummary.games
            TeamSummary.score/=TeamSummary.games
            TeamSummary.critOp/=TeamSummary.games
            TeamSummary.tacOp/=TeamSummary.games
            TeamSummary.killOp/=TeamSummary.games
        }
        return TeamSummary
    }
    //Get informations about team
    fun GetTeamPlayedAgainstInfo(playedTeam : TeamInfo,teamName : String,RedTeamOnly : Boolean) : TeamSummary
    {
        var TeamSummary : TeamSummary = TeamSummary(name = teamName)
        if(RedTeamOnly)
        {
            //Get team values
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == playedTeam.name && team.bluePlayer.teamName == teamName)
                {
                    TeamSummary.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        TeamSummary.winRate+=1
                    }
                    TeamSummary.score+=team.redPlayer.score
                    TeamSummary.critOp+=team.redPlayer.critPoints.sum()
                    TeamSummary.tacOp+=team.redPlayer.tacPoints.sum()
                    TeamSummary.killOp+=team.redPlayer.killPoints.sum()
                }
            }
            //Counting avarage
            TeamSummary.winRate/=TeamSummary.games
            TeamSummary.score/=TeamSummary.games
            TeamSummary.critOp/=TeamSummary.games
            TeamSummary.tacOp/=TeamSummary.games
            TeamSummary.killOp/=TeamSummary.games
        }
        else
        {
            data?.games?.forEach { team ->
                //Red Team
                if(team.redPlayer.teamName == playedTeam.name && team.bluePlayer.teamName == teamName)
                {
                    TeamSummary.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        TeamSummary.winRate+=1
                    }
                    TeamSummary.score+=team.redPlayer.score
                    TeamSummary.critOp+=team.redPlayer.critPoints.sum()
                    TeamSummary.tacOp+=team.redPlayer.tacPoints.sum()
                    TeamSummary.killOp+=team.redPlayer.killPoints.sum()
                }
                //Blue Team
                if(team.bluePlayer.teamName == playedTeam.name && team.redPlayer.teamName == teamName)
                {
                    TeamSummary.games+=1
                    if(team.bluePlayer.score > team.redPlayer.score)
                    {
                        TeamSummary.winRate+=1
                    }
                    TeamSummary.score+=team.bluePlayer.score
                    TeamSummary.critOp+=team.bluePlayer.critPoints.sum()
                    TeamSummary.tacOp+=team.bluePlayer.tacPoints.sum()
                    TeamSummary.killOp+=team.bluePlayer.killPoints.sum()
                }
            }
            //Counting avarage
            TeamSummary.winRate/=TeamSummary.games
            TeamSummary.score/=TeamSummary.games
            TeamSummary.critOp/=TeamSummary.games
            TeamSummary.tacOp/=TeamSummary.games
            TeamSummary.killOp/=TeamSummary.games
        }
        return TeamSummary
    }
    //Get all equipment which team played
    fun GetEqTeams(playedTeam: TeamInfo, RedTeamOnly: Boolean = true) : List<String>
    {
        var eq = mutableListOf<String>()

        data?.games?.forEach { team ->
            //Red Team eq
            if(playedTeam.name == team.redPlayer.teamName)
            {
                team.redPlayer.equipment.forEach { equipment ->
                    if (!(equipment in eq))
                    {
                        eq.add(equipment)
                    }
                }
            }
            //Blue Team eq
            if(!RedTeamOnly && playedTeam.name == team.bluePlayer.teamName)
            {
                team.bluePlayer.equipment.forEach { equipment ->
                    if (!(equipment in eq))
                    {
                        eq.add(equipment)
                    }
                }
            }
        }
        return eq.sorted()
    }

    fun GetEqInfo(playedTeam : TeamInfo,equipment : String,RedTeamOnly : Boolean) : EqSummary
    {
        var eq : EqSummary = EqSummary(name = equipment)

        if(RedTeamOnly)
        {
            //Get team values
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == playedTeam.name && team.redPlayer.equipment.contains(equipment))
                {
                    eq.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        eq.winRate+=1
                    }
                }
            }
        }
        else
        {
            //Get team values
            //Red Team
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == playedTeam.name && team.redPlayer.equipment.contains(equipment))
                {
                    eq.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        eq.winRate+=1
                    }
                }
                if(team.bluePlayer.teamName == playedTeam.name && team.bluePlayer.equipment.contains(equipment))
                {
                    eq.games+=1
                    if(team.redPlayer.score < team.bluePlayer.score)
                    {
                        eq.winRate+=1
                    }
                }
            }
        }
        //Counting avarage
        eq.winRate/=eq.games
        return eq
    }

    fun GetTacopTeams(playedTeam: TeamInfo, RedTeamOnly: Boolean = true) : List<String>
    {
        var tacops = mutableListOf<String>()

        data?.games?.forEach { team ->
            //Red Team tacop
            if(playedTeam.name == team.redPlayer.teamName)
            {
                if (!(team.redPlayer.tacOp in tacops))
                {
                    tacops.add(team.redPlayer.tacOp)
                }
            }
            //Blue Team tacop
            if(!RedTeamOnly && playedTeam.name == team.bluePlayer.teamName)
            {
                if (!(team.bluePlayer.tacOp in tacops))
                {
                    tacops.add(team.bluePlayer.tacOp)
                }
            }
        }
        return tacops.sorted()
    }

    fun GetTacopInfo(playedTeam : TeamInfo,tacoop : String,RedTeamOnly : Boolean) : TacOpSummary
    {
        var tacop : TacOpSummary = TacOpSummary(name = tacoop)

        if(RedTeamOnly)
        {
            //Get team values
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == playedTeam.name && team.redPlayer.tacOp == tacoop)
                {
                    tacop.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        tacop.winRate+=1
                    }
                    tacop.score += team.redPlayer.tacPoints.sum() - team.redPlayer.tacPoints[6]
                }
            }
        }
        else
        {
            //Get team values

            data?.games?.forEach { team ->
                //Red Team
                if(team.redPlayer.teamName == playedTeam.name && team.redPlayer.tacOp == tacoop)
                {
                    tacop.games+=1
                    if(team.redPlayer.score > team.bluePlayer.score)
                    {
                        tacop.winRate+=1
                    }
                    tacop.score += team.redPlayer.tacPoints.sum() - team.redPlayer.tacPoints[6]
                }
                //Blue team
                if(team.bluePlayer.teamName == playedTeam.name && team.bluePlayer.tacOp == tacoop)
                {
                    tacop.games+=1
                    if(team.redPlayer.score < team.bluePlayer.score)
                    {
                        tacop.winRate+=1
                    }
                    tacop.score += team.bluePlayer.tacPoints.sum() - team.bluePlayer.tacPoints[6]
                }
            }
        }
        //Counting avarage
        tacop.winRate/=tacop.games
        tacop.score/=tacop.games
        return tacop
    }

    fun GetPrimaryOp(playedTeam: TeamInfo, RedTeamOnly: Boolean = true) : List<String>
    {
        var primaryOps = mutableListOf<String>()

        data?.games?.forEach { primary ->
            //Red Team tacop
            if(playedTeam.name == primary.redPlayer.teamName)
            {
                if (!(primary.redPlayer.primaryOp in primaryOps))
                {
                    primaryOps.add(primary.redPlayer.primaryOp)
                }
            }
            //Blue Team tacop
            if(!RedTeamOnly && playedTeam.name == primary.bluePlayer.teamName)
            {
                if (!(primary.bluePlayer.primaryOp in primaryOps))
                {
                    primaryOps.add(primary.bluePlayer.primaryOp)
                }
            }
        }
        return primaryOps.sorted()
    }

    fun GetPrimaryOpInfo(playedTeam : TeamInfo,primaryOp : String,RedTeamOnly : Boolean) : PrimarySummary
    {
        var primaryOps : PrimarySummary = PrimarySummary(name = primaryOp)

        if(RedTeamOnly)
        {
            //Get team values
            data?.games?.forEach { primary ->
                if(primary.redPlayer.teamName == playedTeam.name && primary.redPlayer.primaryOp == primaryOp)
                {
                    primaryOps.games+=1
                    if(primary.redPlayer.score > primary.bluePlayer.score)
                    {
                        primaryOps.winRate+=1
                    }
                    primaryOps.score += primary.redPlayer.critPoints[6]
                    primaryOps.score += primary.redPlayer.tacPoints[6]
                    primaryOps.score += primary.redPlayer.killPoints[6]
                }
            }
        }
        else
        {
            //Get team values

            data?.games?.forEach { primary ->
                //Red Team
                if(primary.redPlayer.teamName == playedTeam.name && primary.redPlayer.primaryOp == primaryOp)
                {
                    primaryOps.games+=1
                    if(primary.redPlayer.score > primary.bluePlayer.score)
                    {
                        primaryOps.winRate+=1
                    }
                    primaryOps.score += primary.redPlayer.critPoints[6]
                    primaryOps.score += primary.redPlayer.tacPoints[6]
                    primaryOps.score += primary.redPlayer.killPoints[6]
                }
                //Blue team
                if(primary.bluePlayer.teamName == playedTeam.name && primary.bluePlayer.primaryOp == primaryOp)
                {
                    primaryOps.games+=1
                    if(primary.redPlayer.score < primary.bluePlayer.score)
                    {
                        primaryOps.winRate+=1
                    }
                    primaryOps.score += primary.bluePlayer.critPoints[6]
                    primaryOps.score += primary.bluePlayer.tacPoints[6]
                    primaryOps.score += primary.bluePlayer.killPoints[6]
                }
            }
        }
        //Counting avarage
        primaryOps.winRate/=primaryOps.games
        primaryOps.score/=primaryOps.games
        return primaryOps
    }

    fun GetOperatorsTeams(playedTeam: TeamInfo, RedTeamOnly: Boolean = true) : List<OperatorInfo>
    {
        var operators = mutableListOf<OperatorInfo>()

        data?.games?.forEach { team ->
            //Red Team operators
            if(playedTeam.name == team.redPlayer.teamName)
            {
                team.redPlayer.units.forEach { unit ->
                    val amount = team.redPlayer.units.count{ it == unit }
                    if(!operators.contains(OperatorInfo(name = unit.name,amount = amount)))
                    {
                        operators.add(OperatorInfo(name = unit.name,amount = amount))
                    }
                }
            }
            //Blue Team eq
            if(!RedTeamOnly && playedTeam.name == team.bluePlayer.teamName)
            {
                team.bluePlayer.units.forEach { unit ->
                    val amount = team.bluePlayer.units.count{ it == unit }
                    if(!operators.contains(OperatorInfo(name = unit.name,amount = amount)))
                    {
                        operators.add(OperatorInfo(name = unit.name,amount = amount))
                    }
                }
            }
        }
        return operators
    }

    fun GetOperatorsInfo(playedTeam : TeamInfo,operator : OperatorInfo,RedTeamOnly : Boolean) : OperatorSummary
    {
        var operators : OperatorSummary = OperatorSummary(name = operator.name)

        if(RedTeamOnly)
        {
            //Get team values
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == playedTeam.name)
                {
                    for (unit in team.redPlayer.units)
                    {
                        if(unit.name == operator.name && operator.amount == team.redPlayer.units.count{ it == unit })
                        {
                            operators.games+=1
                            if(team.redPlayer.score > team.bluePlayer.score)
                            {
                                operators.winRate+=1
                            }
                            break
                        }

                    }
                }
            }
        }
        else
        {
            //Get team values
            //Red Team
            data?.games?.forEach { team ->
                if(team.redPlayer.teamName == playedTeam.name)
                {
                    for (unit in team.redPlayer.units)
                    {
                        if(unit.name == operator.name && operator.amount == team.redPlayer.units.count{ it == unit })
                        {
                            operators.games+=1
                            if(team.redPlayer.score > team.bluePlayer.score)
                            {
                                operators.winRate+=1
                            }
                            break
                        }
                    }
                }
                if(team.bluePlayer.teamName == playedTeam.name)
                {
                    for (unit in team.bluePlayer.units)
                    {
                        if(unit.name == operator.name && operator.amount == team.bluePlayer.units.count{ it == unit })
                        {
                            operators.games+=1
                            if(team.redPlayer.score < team.bluePlayer.score)
                            {
                                operators.winRate+=1
                            }
                            break
                        }
                    }
                }
            }
        }
        //Counting avarage
        operators.winRate/=operators.games
        return operators
    }
}

data class TeamSummary(
    var name: String = "",
    var games: Int = 0,
    var winRate: Float = 0.0f,
    var score : Float = 0.0f,
    var critOp : Float = 0.0f,
    var tacOp : Float = 0.0f,
    var killOp : Float = 0.0f
)

data class EqSummary(
    var name: String = "",
    var games: Int = 0,
    var winRate: Float = 0.0f,
)

data class TacOpSummary(
    var name: String = "",
    var games: Int = 0,
    var winRate: Float = 0.0f,
    var score: Float = 0.0f,
)

data class PrimarySummary(
    var name: String = "",
    var games: Int = 0,
    var winRate: Float = 0.0f,
    var score: Float = 0.0f,
)

data class OperatorInfo(
    var name: String = "",
    var amount: Int = 0,
)

data class OperatorSummary(
    var name: String = "",
    var games: Int = 0,
    var winRate: Float = 0.0f,
)