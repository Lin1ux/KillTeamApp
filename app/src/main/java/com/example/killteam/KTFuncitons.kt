package com.example.killteam
import com.example.killteam.Objects.Mission
import com.example.killteam.Objects.Ploy
import com.example.killteam.Objects.PloyType
import com.example.killteam.Objects.TeamInfo
import com.example.killteam.Objects.WeaponRule
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
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
fun GetOneTypePloys(Ploys : List<Ploy>, type : PloyType) : List<Ploy>
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
            return 0.25f
        }
        PloyType.FIREFIGHT -> {
            if (PloySelection.selected)
            {
                return 0.25f
            }
            return 1.0f
        }
    }
}
//Format text
fun FormatTextWithMarkers(text: String): AnnotatedString {
    val parts = text.split(" ")

    return buildAnnotatedString {
        var isBold = false
        var isOrange = false
        var isWeapon = false

        parts.forEach { part ->
            if (part.length < 2)    //If word is to small it's added immediately
            {
                append(part+" ")
            }
            else
            {
                //Turning on formating
                if (part.get(0) == '*' && part.get(1) == '*') {
                    isBold = true
                }
                if (part.get(0) == '#' && part.get(1) == '#') {
                    isOrange = true
                }
                if (part.get(0) == 'x' && part.get(1) == 'x') {
                    isWeapon = true
                }
                //Append parts with removed markers
                if(isBold)
                {
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) { append(part.replace("**", "")+" ") }
                }
                else if(isOrange)
                {
                    withStyle(SpanStyle(color = KTColors.Orange,fontWeight = FontWeight.Bold)) { append(part.replace("##", "")+" ") }
                }
                else if(isWeapon)
                {
                    withStyle(SpanStyle(color = KTColors.Weapon)) { append(part.replace("xx", "")+" ") }
                }
                else
                {
                    append(part+" ")
                }
                //Turning off if markers are on the end
                if (part.get(part.length-1) == '*' && part.get(part.length-2) == '*') {
                    isBold = false
                }
                if (part.get(part.length-1) == '#' && part.get(part.length-2) == '#') {
                    isOrange = false
                }
                if (part.get(part.length-1) == 'x' && part.get(part.length-2) == 'x') {
                    isWeapon = false
                }
            }
        }
    }
}
//Give color for given type
fun GetOrderColor(order : Order) : Color
{
    when(order)
    {
        Order.CONCEAL -> { return KTColors.Conceal }
        Order.ENGAGE ->  { return KTColors.Engage }
    }
}
//Return icon of order
fun GetOrderIcon(troop : selectedOperators) : Int
{
    when(troop.order.value)
    {
        Order.CONCEAL -> { return R.drawable.conceal }
        Order.ENGAGE ->  { return R.drawable.engage }
    }
}
//Give alpha value dependend on given value
fun GetReadyAlpha(ready : Boolean) : Float
{
    if(ready)
    {
        return 1.0f
    }
    return 0.25f
}
//return color for unit
fun GetOrderColor(operator: selectedOperators,modifyAlpha : Boolean = true,checkWounds : Boolean = false) : Color
{
    if(checkWounds && operator.currentWounds.value == 0)
    {
        return KTColors.Incapacitated
    }
    var alpha : Float = 1.0f
    if(!operator.ready.value && modifyAlpha)
    {
        alpha = 0.25f
    }

    when(operator.order.value)
    {
        Order.CONCEAL -> { return KTColors.Conceal.copy(alpha = alpha) }
        Order.ENGAGE ->  { return KTColors.Engage.copy(alpha = alpha) }
    }
}

//Remove keyword from name
fun String.RemoveKeyWord(viewModel: ScoreViewModel,firstPlayer : Boolean) :
        String = this.replace(viewModel.GetPlayer(firstPlayer).GetTeam().nameKeyword,"")

//Get List of Weapon rules and build string with them
fun ConvertWeaponRulesToString(rules : List<WeaponRule>) : String
{
    if(rules.isEmpty())
    {
        return "-"
    }
    var strRules : String = ""
    rules.forEachIndexed { index, rule ->
        if(index != 0)
        {
            strRules+= ", "
        }
        strRules += rule.name
    }
    return strRules
}
//Return if operator is injured (have less than half wounds)
fun IsInjured(operator: selectedOperators) : Boolean
{
    if(operator.currentWounds.value < ceil(operator.operator.wounds.toFloat()/2) )    // 7/2 = 3.5   8/2 = 4
    {
        return true
    }
    return false
}
//Return color dependend on operators wounds
fun SelectColorByInjuring(normalColor : Color, injuredColor : Color , operator: selectedOperators) : Color
{
    if(IsInjured(operator))
    {
        return injuredColor
    }
    return normalColor
}
//Return color dependend on operators given value
fun SelectColorByInjuring(normalColor : Color, injuredColor : Color , injured : Boolean) : Color
{
    if(injured)
    {
        return injuredColor
    }
    return normalColor
}
//Return worsen Move stat (move - 2)
fun InjureWorsenMove(operator: selectedOperators) : Int
{
    if(IsInjured(operator))
    {
        return operator.operator.move - 2
    }
    return operator.operator.move
}
//Worsen Hit Stat (if true)
fun Int.WorsenHitStat(worsen : Boolean) : Int = if(worsen) this + 1 else this

