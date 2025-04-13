package com.example.killteam

data class TeamInfo(
    var name : String,
    var archetypes : List<Archetype>,
    var ploys : List<Ploy>

)

data class Ploy(
    var name : String,
    var description : String,
    var type : PloyType,
    var cost : String
)

//Enum of ploy types
enum class PloyType{
    STRATEGY,
    FIREFIGHT
}

data class Equipment(
    var name : String,
    var description: String
)
