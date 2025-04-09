package com.example.killteam

object KillTeams
{
    val DeathKorps = TeamInfo(
        name = "Death Korps",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy)
    )

    val KasrkinSquad = TeamInfo(
        name = "Kasrkin Squad",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy)
    )

    val AngelsOfDeath = TeamInfo(
        name = "Angels Of Death",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy)
    )

    val VoidscaredCorsairs = TeamInfo(
        name = "Voidscared Corsairs",
        archetypes = listOf(TacOps.Recon,TacOps.Infiltration)
    )

    val PlagueMarines = TeamInfo(
        name = "Plague Marines",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy)
    )

    val HunterClade = TeamInfo(
        name = "Hunter Clade",
        archetypes = listOf(TacOps.Recon,TacOps.SeekDestroy)
    )

    val teamList = listOf(DeathKorps,KasrkinSquad,AngelsOfDeath,VoidscaredCorsairs,PlagueMarines,HunterClade)
}
