package com.example.killteam.firebase

import com.google.firebase.database.PropertyName

data class GameInfoList(
    @PropertyName("username") val username: String = "",
    @PropertyName("games") var games: List<GameInfo> = emptyList()
)

data class GameInfo(
    @PropertyName("redPlayer") val redPlayer: PlayerInfo = PlayerInfo(),
    @PropertyName("bluePlayer") val bluePlayer: PlayerInfo = PlayerInfo(),
)

data class PlayerInfo(
    @PropertyName("teamName") val teamName: String = "",
    @PropertyName("tacOp") val tacOp: String = "",
    @PropertyName("primaryOp") val primaryOp: String = "",
    @PropertyName("cp") val cp: Int = 0,
    @PropertyName("killPoints") val killPoints: List<Int> = emptyList(),
    @PropertyName("critPoints") val critPoints: List<Int> = emptyList(),
    @PropertyName("tacPoints") val tacPoints: List<Int> = emptyList(),
    @PropertyName("equipment") val equipment: List<String> = emptyList(),
    @PropertyName("units") val units: List<UnitInfo> = emptyList(),
    @PropertyName("score") val score: Int = 0,
)

data class UnitInfo(
    @PropertyName("name") val name: String = "",
    @PropertyName("currentWounds") val currentWounds: Int = 0,
    @PropertyName("wounds") val wounds: Int = 0,
)


