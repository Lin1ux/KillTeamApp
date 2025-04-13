package com.example.killteam


object KillTeams
{
    val DeathKorps = TeamInfo(
        name = "Death Korps",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = DeathKorpsPloys.ployList,
        equipment = DeathKorpsEQ.equipmentList
    )

    val KasrkinSquad = TeamInfo(
        name = "Kasrkin Squad",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = KasrkinPloys.ployList,
        equipment = KasrkinEQ.equipmentList
    )

    val AngelsOfDeath = TeamInfo(
        name = "Angels Of Death",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = AngelsOfDeathPloys.ployList,
        equipment = AngelsOfDeathEQ.equipmentList
    )

    val VoidscaredCorsairs = TeamInfo(
        name = "Corsairs Voidscared",
        archetypes = listOf(TacOps.Recon,TacOps.Infiltration),
        ploys = VoidscaredCorsairsPloys.ployList,
        equipment = VoidscaredCorsairsEQ.equipmentList
    )

    val PlagueMarines = TeamInfo(
        name = "Plague Marines",
        archetypes = listOf(TacOps.Security,TacOps.SeekDestroy),
        ploys = PlagueMarinesPloys.ployList,
        equipment = PlagueMarinesEQ.equipmentList
    )

    val HunterClade = TeamInfo(
        name = "Hunter Clade",
        archetypes = listOf(TacOps.Recon,TacOps.SeekDestroy),
        ploys = HunterCladePloys.ployList,
        equipment = HunterCladeEQ.equipmentList
    )

    val teamList = listOf(DeathKorps,KasrkinSquad,AngelsOfDeath,VoidscaredCorsairs,PlagueMarines,HunterClade)
}
