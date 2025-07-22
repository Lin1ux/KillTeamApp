package com.example.killteam.Objects

import androidx.compose.ui.graphics.Color
import com.example.killteam.ui.theme.KTColors
import kotlin.String

data class Archetype(
    var name : String,
    var color : Color,
    var missionList : List<Mission>
)

data class Mission(
    var name : String,
    var victoryPoints : String,
    var reveal : String = "When you first score VP from this op.",
    var additionalRules : String = "",
    var actions: List<Action> = emptyList(),
    var color : Color
)

object TacOps
{
    //Unknown
    val Unknown = Mission(
        name = "Unknown",
        victoryPoints = "",
        reveal = "",
        color = Color.Black
    )
    //Recon
    val ConfirmKill = Mission(
        name = "Confirm Kill",
        victoryPoints = "At the end of each turning point after the first, if a friendly operative controls one of your Confirm Kill mission markers, that marker is not contested by enemy operatives and no enemy operatives are within that friendly operative’s control range, you can remove that mission marker to score 1VP, or 2VP if it was placed for an enemy operative with a Wounds stat of 12 or more.\n" +
                "\n" +
                "You can score a maximum of 2VP from this operation per turning point.",
        reveal = "The first time an enemy operative is incapacitated.",
        additionalRules = "Whenever an enemy operative is incapacitated, before it’s removed from the killzone, place one of your Confirm Kill mission markers within its control range.",
        color = KTColors.Recon
    )
    val RecoverItems = Mission(
        name = "Recover Items",
        victoryPoints = "At the end of the fourth turning point, for each of your Item mission markers that both the **Pick Up Marker** action has been performed upon and friendly operatives control, you score 2VP. Note that it’s not a requirement to be carrying those markers, but each of them must have been carried by friendly operatives at some point during the battle.",
        reveal = "At the start of the Set Up Operatives step, before equipment is set up.",
        additionalRules = "When revealed, your opponent places one of your Item mission markers on the centerline and one within 2” of your territory. You then place one more than 6” from your territory. In all cases, your Item mission markers must be more than 2” from other markers (including other Item mission markers). Your operatives can perform the **Pick Up Marker** action on your Item mission markers after the first turning point.",
        color = KTColors.Recon
    )
    val PlantBeacons = Mission(
        name = "Plant Beacons",
        victoryPoints = "Once per turning point after the first, whenever one of your Beacon mission markers is placed wholly within your territory, you score 1VP.\n" +
                "\n" +
                "Once per turning point after the first, whenever one of your Beacon mission markers is placed wholly within your opponent’s territory, you score 1VP.",
        reveal = "The first time a friendly operative performs the **Plant Beacon action.**",
        actions = listOf(Actions.PlantBeacon),
        color = KTColors.Recon
    )
    val Recon = Archetype(
        name = "Recon",
        color = KTColors.Recon,
        missionList = listOf(ConfirmKill,RecoverItems,PlantBeacons)
    )
    //Seek and Destroy
    val Overun = Mission(
        name = "Overun",
        victoryPoints = "Once per turning point after the first, if an enemy operative is incapacitated by a friendly operative, and that friendly operative is wholly within your opponent’s territory when it does so, you score 1VP.\n" +
                "\n" +
                "At the end of each turning point after the first, if the total APL stat of friendly operatives that both fulfilled the above condition that turning point (regardless of scoring you the VP) and are still wholly within your opponent’s territory is 3 or more, you score 1VP.",
        color = KTColors.SeekDestroy
    )
    val StormObjectives = Mission(
        name = "Storm Objectives",
        victoryPoints = "Once per turning point after the first, if an objective marker is stormed by friendly operatives this turning point, you score 1VP.\n" +
                "\n" +
                "At the end of each turning point after the first, if friendly operatives control an objective marker that was stormed by friendly operatives this turning point, you score 1VP.",
        additionalRules = "At the end of each friendly operative’s activation, if it controls an objective marker that enemy operatives controlled at the start of that activation or that’s wholly within your opponent’s territory, and that objective marker is not contested by enemy operatives, that objective marker is stormed by friendly operatives this turning point.",
        color = KTColors.SeekDestroy
    )
    val Champion = Mission(
        name = "Champion",
        victoryPoints = "In each turning point after the first, whenever your champion incapacitates an enemy operative you score 1VP, or 2VP if that enemy operative had a Wounds stat of 12 or more (in either case to a maximum of 2VP per turning point).",
        reveal = "When you select your first champion.",
        additionalRules = "As a **STRATEGIC GAMBIT** in each turning point after the first, you can select one friendly operative to be your champion for the turning point.",
        color = KTColors.SeekDestroy
    )
    val SeekDestroy = Archetype(
        name = "Seek and Destroy",
        color = KTColors.SeekDestroy,
        missionList = listOf(Overun,StormObjectives,Champion)
    )
    //Security
    val Contain = Mission(
        name = "Contain",
        victoryPoints = "At the end of each turning point after the first:\n" +
                "\n" +
                " • If there are no enemy operatives wholly within your territory, you score 1VP.\n" +
                " • If there are no enemy operatives wholly within 6\" of your drop zone, you score 1VP.\n",
        color = KTColors.Security
    )
    val TakeGround = Mission(
        name = "Take Ground",
        victoryPoints = "" +
                "At the end of each turning point after the first:\n" +
                "\n" +
                " • In Killzone: Volkus, if friendly operatives control any Stronghold terrain features within your opponent’s territory, you score 2VP; for each ruin (large or small) terrain feature within your opponent’s territory that friendly operatives control, you score 1VP.\n" +
                " • In Killzone: Gallowdark, for each access point that’s on the centerline or within your opponent’s territory that friendly operatives control, you score 1VP.\n" +
                " • In any other killzone, for each terrain feature with Heavy terrain within your opponent’s territory that friendly operatives control, you score 1VP.\n" +
                "\n" +
                "You can score a maximum of 2VP from this op per turning point.\n" +
                "\n" +
                "An operative contests a Stronghold terrain feature if it’s wholly within. An operative contests all other terrain features within their control range, or while underneath a terrain feature’s Vantage terrain.\n" +
                "\n" +
                "Friendly operatives control each terrain feature if the total APL of those contesting it is greater than that of enemy operatives.",
        color = KTColors.Security
    )
    val SecureCenter = Mission(
        name = "Secure Center",
        victoryPoints = "At the end of each turning point after the first:\n" +
                "\n" +
                " • If the total APL stat of friendly operatives within 3” of the centre of the killzone is greater than that of enemy operatives, you score 1VP.\n" +
                " • If the total APL stat of friendly operatives on the centerline but more than 3” from the centre of the killzone is greater than that of enemy operatives, you score 1VP.\n",
        color = KTColors.Security
    )
    val Security = Archetype(
        name = "Security",
        color = KTColors.Security,
        missionList = listOf(Contain,TakeGround,SecureCenter)
    )
    //Infiltration
    val Surveillance = Mission(
        name = "Surveillance",
        victoryPoints = "Once per turning point after the first, if a friendly operative performs the **Surveillance** action, you score 1VP.\n" +
                "\n" +
                "At the end of each turning point after the first, if a friendly operative that performed the **Surveillance** action during that turning point is in the killzone and has a Conceal order, you score 1VP.",
        reveal = "The first time a friendly operative performs the **Surveillance action.**",
        actions = listOf(Actions.Surveillance),
        color = KTColors.Infiltration
    )
    val Implant = Mission(
        name = "Implant",
        victoryPoints = "Once per turning point after the first, if you implant an enemy operative, you score 1VP.\n" +
                "\n" +
                "At the end of each turning point after the first, if any implanted enemy operatives are in the killzone, you score 1VP.",
        additionalRules = "Whenever a friendly operative is fighting, when you would resolve an attack dice, you can implant the enemy operative instead of striking or blocking (then discard that dice).\n" +
                "\n" +
                "Whenever a friendly operative is shooting an enemy operative within 6” of it, when you would resolve an attack dice, you can implant the enemy operative instead of inflicting damage with that dice.\n" +
                "\n" +
                "Each operative can only be implanted once, and cannot be implanted during the first turning point.",
        color = KTColors.Infiltration
    )
    val Wiretap = Mission(
        name = "Wiretap",
        victoryPoints = "Once per turning point after the first, whenever an enemy operative starts or ends an action within 2” of your Wiretap mission marker, you score 1VP.\n" +
                "\n" +
                "At the end of each turning point after the first, if any enemy operatives with an Engage order are within 2” of your Wiretap mission marker, you score 1VP.",
        reveal = "The first time a friendly operative performs the **Wiretap action.**",
        actions = listOf(Actions.Wiretap),
        color = KTColors.Infiltration
    )
    val Infiltration = Archetype(
        name = "Infiltration",
        color = KTColors.Infiltration,
        missionList = listOf(Surveillance,Implant,Wiretap)
    )

}

//Enum for selecting Ops
enum class PointType
{
    CRITOP,
    TACOP,
    KILLOP,
    UNKNOWN
}