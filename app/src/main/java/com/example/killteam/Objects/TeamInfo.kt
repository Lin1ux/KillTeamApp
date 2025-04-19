package com.example.killteam.Objects

import androidx.compose.runtime.Composable

data class TeamInfo(
    var name : String,
    var archetypes : List<Archetype>,
    var ploys : List<Ploy>,
    var equipment: List<Equipment>,
    var operators: List<Operator> = DeathKorpsOperators.operatorList,
    var nameKeyword : String = "", //Word which is shared in most of operators
    var minOperators : Int,
    var maxOperator: Int

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
    var description: String,
    var weapons : List<Weapon> = listOf<Weapon>(),
    var actions: List<Action> = listOf<Action>()
)

data class Operator(
    var name : String,
    var APL : Int,
    var move : Int,
    var save : Int,
    var wounds : Int,
    var weapons : List<Weapon>,
    var keywords : List<String>,
    var size : Int,
    var leader : Boolean = false,
    var specialist : Boolean = true,
    var additionalRules : String = "",
    var actions : List<Action> = emptyList()
)

data class Weapon(
    var name : String,
    var type : WeaponType,
    var Atk : Int,
    var Hit : Int,
    var Dmg : Int,
    var CritDmg : Int,
    var WeaponRulesList : List<WeaponRule>
)

data class WeaponRule(
    var name : String,
    var description: String
)

//Enum of weapon types
enum class WeaponType{
    RANGED,
    MELEE
}

data class AditionalRules(
    var name : String,
    var description: String
)

data class Action(
    var name : String,
    var cost : Int = 1,
    var description : String,
    var limitation : String = " This operative cannot perform this action while within control Range of an enemy operative. "
)

abstract class TeamRules(
    val name: String,
    val description: String = "",
    val team : TeamType

)
{
    @Composable
    open fun draw()
    {

    }
}

//Enum of weapon types
enum class TeamType{
    ANGELS_OF_DEATH,
    CORSAIRS_VOIDSCARED,
    DEATH_KORPS,
    HUNTER_CLADE,
    KASRKIN_SQUAD,
    PLAGUE_MARINE
}