package com.example.killteam

//Convert Point index to round when it can be scored
fun Int.IndexToRound() : Int = when(this)
{
    0,1 -> 2
    2,3 -> 3
    4,5 -> 4
    else -> -1
}
//Return List of avaliable missions for team
fun getMissions(Team : TeamInfo) : MutableList<Mission>
{
    var list : MutableList<Mission> = mutableListOf()
    //list
    Team.archetypes.forEach { archetype ->
        archetype.missionList.forEach { mission ->
            list.add(mission)
        }
    }
    return list
}

