package com.example.killteam.firebase

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    fun GetNumberOfGames() : Int
    {
        return data?.games?.size ?: 0
    }
    //Get  number of victories
    fun GetNumberOfVictories() : Int
    {
        var victories = 0
        data?.games?.forEach { game ->

            if(game.redPlayer.score > game.bluePlayer.score)
            {
                victories++
            }
        }
        return victories
    }

    //Get number of defeats
    fun GetNumberOfDefeat() : Int
    {
        var defeats = 0
        data?.games?.forEach { game ->

            if(game.redPlayer.score < game.bluePlayer.score)
            {
                defeats++
            }
        }
        return defeats
    }

    //Get number of victories
    fun GetNumberOfDraws() : Int
    {
        var draws = 0
        data?.games?.forEach { game ->

            if(game.redPlayer.score == game.bluePlayer.score)
            {
                draws++
            }
        }
        return draws
    }

    fun GetAvarageScore() : Float
    {
        var sum = 0.0f
        data?.games?.forEach { game ->

            sum += game.redPlayer.score
        }
        return sum/GetNumberOfGames()
    }

    fun GetAvarageCritOp() : Float
    {
        var sum = 0.0f
        data?.games?.forEach { game ->

            sum += game.redPlayer.critPoints.sum()
        }
        return sum/GetNumberOfGames()
    }

    fun GetAvarageTacOp() : Float
    {
        var sum = 0.0f
        data?.games?.forEach { game ->

            sum += game.redPlayer.tacPoints.sum()
        }
        return sum/GetNumberOfGames()
    }

    fun GetAvarageKillOp() : Float
    {
        var sum = 0.0f
        data?.games?.forEach { game ->

            sum += game.redPlayer.killPoints.sum()
        }
        return sum/GetNumberOfGames()
    }

    fun GetFavoriteTeam() : String
    {
        val teams = mutableMapOf<String, Int>()
        data?.games?.forEach { game ->
            teams[game.redPlayer.teamName] = teams.getOrDefault(game.redPlayer.teamName, 0) + 1
        }
        return teams.maxByOrNull { it.value }?.key ?: ""
    }

    fun GetFavoriteTacOp() : String
    {
        val teams = mutableMapOf<String, Int>()
        data?.games?.forEach { game ->
            if(game.redPlayer.tacOp != "Unknown")
            {
                teams[game.redPlayer.tacOp] = teams.getOrDefault(game.redPlayer.tacOp, 0) + 1
            }

        }
        return teams.maxByOrNull { it.value }?.key ?: ""
    }

    fun GetFavoritePrimaryOp() : String
    {
        val teams = mutableMapOf<String, Int>()
        data?.games?.forEach { game ->
            if(game.redPlayer.primaryOp != "UNKNOWN")
            {
                teams[game.redPlayer.primaryOp] = teams.getOrDefault(game.redPlayer.primaryOp, 0) + 1
            }

        }
        return teams.maxByOrNull { it.value }?.key ?: ""
    }

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
