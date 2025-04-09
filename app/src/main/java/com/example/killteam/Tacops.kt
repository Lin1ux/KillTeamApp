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
    var description : String
)

object TacOps
{
    //Unknown
    val Unknown = Mission(
        name = "Unknown",
        description = ""
    )
    //Recon
    val ConfirmKill = Mission(
        name = "Confirm Kill",
        description = ""
    )
    val RecoverItems = Mission(
        name = "Confirm Kill",
        description = ""
    )
    val PlantBeacons = Mission(
        name = "Confirm Kill",
        description = ""
    )
    val Recon = Archetype(
        name = "Recon",
        color = KTColors.Recon,
        missionList = listOf(ConfirmKill,RecoverItems,PlantBeacons)
    )
    //Seek and Destroy
    val Overun = Mission(
        name = "Overun",
        description = ""
    )
    val StormObjectives = Mission(
        name = "Storm Objectives",
        description = ""
    )
    val Champion = Mission(
        name = "Champion",
        description = ""
    )
    val SeekDestroy = Archetype(
        name = "Seek and Destroy",
        color = KTColors.SeekDestroy,
        missionList = listOf(Overun,StormObjectives,Champion)
    )
    //Security
    val Contain = Mission(
        name = "Contain",
        description = ""
    )
    val TakeGround = Mission(
        name = "Take Ground",
        description = ""
    )
    val SecureCenter = Mission(
        name = "Secure Center",
        description = ""
    )
    val Security = Archetype(
        name = "Security",
        color = KTColors.Security,
        missionList = listOf(Contain,TakeGround,SecureCenter)
    )
    //Infiltration
    val Surveillance = Mission(
        name = "Surveillance",
        description = ""
    )
    val Implant = Mission(
        name = "Implant",
        description = ""
    )
    val Wiretap = Mission(
        name = "Wiretap",
        description = ""
    )
    val Infiltration = Archetype(
        name = "Infiltratino",
        color = KTColors.Infiltration,
        missionList = listOf(Surveillance,Implant,Wiretap)
    )

}