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
//Return list of one type ploys
fun GetOneTypePloys(Ploys : List<Ploy>,type : PloyType) : List<Ploy>
{
    var list : MutableList<Ploy> = mutableListOf()
    Ploys.forEach { ploy ->
        if(ploy.type == type)
        {
            list.add(ploy)
        }
    }
    return list
}

//Return list of one type ploys
fun GetOneTypePloysSelection(Ploys : List<ploySelection>,type : PloyType) : List<ploySelection>
{
    var list : MutableList<ploySelection> = mutableListOf()
    Ploys.forEach { selectedPloy ->
        if(selectedPloy.ploy.type == type)
        {
            list.add(selectedPloy)
        }
    }
    return list
}

//Return alpha depended on state of ploySelection
fun GetAlphaFromPloySelecion(PloySelection : ploySelection) : Float
{
    when(PloySelection.ploy.type)
    {
        PloyType.STRATEGY -> {
            if (PloySelection.selected)
            {
                return 1.0f
            }
            return 0.5f
        }
        PloyType.FIREFIGHT -> {
            if (PloySelection.selected)
            {
                return 0.5f
            }
            return 1.0f
        }
    }
}

