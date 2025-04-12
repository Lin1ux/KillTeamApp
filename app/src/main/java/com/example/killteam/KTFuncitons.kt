package com.example.killteam
import androidx.compose.ui.graphics.Color
import com.example.killteam.ui.theme.KTColors
import kotlin.math.ceil

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
//Return bonus points(All Points from Op/2 rounded up)
fun countBonus(pointList : MutableList<Int>) : Int
{
    return ceil(pointList.sum().toFloat()/2.0f).toInt()
}
//Return color depended on ploy type
fun ployToColor(ploy : PloyType) : Color
{
    when(ploy)
    {
        PloyType.STRATEGY -> return KTColors.Strategy
        PloyType.FIREFIGHT -> return KTColors.Firefight
    }
}

