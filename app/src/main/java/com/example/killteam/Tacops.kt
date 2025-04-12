package com.example.killteam

import androidx.compose.ui.graphics.Color
import com.example.killteam.KillTeams.AngelsOfDeath
import com.example.killteam.KillTeams.DeathKorps
import com.example.killteam.KillTeams.HunterClade
import com.example.killteam.KillTeams.KasrkinSquad
import com.example.killteam.KillTeams.PlagueMarines
import com.example.killteam.KillTeams.VoidscaredCorsairs
import com.example.killteam.ui.theme.KTColors
import kotlin.String

data class Archetype(
    var name : String,
    var color : Color,
    var missionList : List<Mission>
)

data class Mission(
    var name : String,
    var description : String,
    var color : Color
)

object TacOps
{
    //Unknown
    val Unknown = Mission(
        name = "Unknown",
        description = "",
        color = Color.Black
    )
    //Recon
    val ConfirmKill = Mission(
        name = "Confirm Kill",
        description = "",
        color = KTColors.Recon
    )
    val RecoverItems = Mission(
        name = "Confirm Kill",
        description = "",
        color = KTColors.Recon
    )
    val PlantBeacons = Mission(
        name = "Confirm Kill",
        description = "",
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
        description = "",
        color = KTColors.SeekDestroy
    )
    val StormObjectives = Mission(
        name = "Storm Objectives",
        description = "",
        color = KTColors.SeekDestroy
    )
    val Champion = Mission(
        name = "Champion",
        description = "",
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
        description = "",
        color = KTColors.Security
    )
    val TakeGround = Mission(
        name = "Take Ground",
        description = "",
        color = KTColors.Security
    )
    val SecureCenter = Mission(
        name = "Secure Center",
        description = "",
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
        description = "",
        color = KTColors.Infiltration
    )
    val Implant = Mission(
        name = "Implant",
        description = "",
        color = KTColors.Infiltration
    )
    val Wiretap = Mission(
        name = "Wiretap",
        description = "",
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