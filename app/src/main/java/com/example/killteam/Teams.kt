package com.example.killteam


object KillTeams
{
    val DeathKorps = TeamInfo(
        name = "Death Korps",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = DeathKorpsPloys.ployList
    )

    val KasrkinSquad = TeamInfo(
        name = "Kasrkin Squad",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = KasrkinPloys.ployList
    )

    val AngelsOfDeath = TeamInfo(
        name = "Angels Of Death",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = AngelsOfDeathPloys.ployList
    )

    val VoidscaredCorsairs = TeamInfo(
        name = "Corsairs Voidscared",
        archetypes = listOf(TacOps.Recon,TacOps.Infiltration),
        ploys = VoidscaredCorsairsPloys.ployList
    )

    val PlagueMarines = TeamInfo(
        name = "Plague Marines",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = PlagueMarinesPloys.ployList
    )

    val HunterClade = TeamInfo(
        name = "Hunter Clade",
        archetypes = listOf(TacOps.Recon,TacOps.SeekDestroy),
        ploys = HunterCladePloys.ployList
    )

    val teamList = listOf(DeathKorps,KasrkinSquad,AngelsOfDeath,VoidscaredCorsairs,PlagueMarines,HunterClade)
}
