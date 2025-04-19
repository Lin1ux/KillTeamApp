package com.example.killteam.Objects


object KillTeams
{
    val DeathKorps = TeamInfo(
        name = "Death Korps",
        archetypes = listOf(TacOps.Security, TacOps.SeekDestroy),
        ploys = DeathKorpsPloys.ployList,
        equipment = DeathKorpsEQ.equipmentList,
        operators = DeathKorpsOperators.operatorList,
        nameKeyword = "Death Korps",
        minOperators = 10,  //10
        maxOperator = 14,
    )

    val KasrkinSquad = TeamInfo(
        name = "Kasrkin Squad",
        archetypes = listOf(TacOps.Security, TacOps.SeekDestroy),
        ploys = KasrkinPloys.ployList,
        equipment = KasrkinEQ.equipmentList,
        operators = KasrkinSquadOperators.operatorList,
        nameKeyword = "Kasrkin",
        minOperators = 10,
        maxOperator = 10
    )

    val AngelsOfDeath = TeamInfo(
        name = "Angels Of Death",
        archetypes = listOf(TacOps.Security, TacOps.SeekDestroy),
        ploys = AngelsOfDeathPloys.ployList,
        equipment = AngelsOfDeathEQ.equipmentList,
        operators = AngelsOfDeathOperators.operatorList,
        nameKeyword = "Intercessor",
        minOperators = 6,
        maxOperator = 6
    )

    val VoidscaredCorsairs = TeamInfo(
        name = "Corsairs Voidscared",
        archetypes = listOf(TacOps.Recon, TacOps.Infiltration),
        ploys = VoidscaredCorsairsPloys.ployList,
        equipment = VoidscaredCorsairsEQ.equipmentList,
        operators = VoidscaredCorsairsOperator.operatorList,
        nameKeyword = "",
        minOperators = 9,
        maxOperator = 9
    )

    val PlagueMarines = TeamInfo(
        name = "Plague Marines",
        archetypes = listOf(TacOps.Security, TacOps.SeekDestroy),
        ploys = PlagueMarinesPloys.ployList,
        equipment = PlagueMarinesEQ.equipmentList,
        operators = PlagueMarinesOperators.operatorList,
        nameKeyword = "Plague Marine",
        minOperators = 6,
        maxOperator = 6
    )

    val HunterClade = TeamInfo(
        name = "Hunter Clade",
        archetypes = listOf(TacOps.Recon, TacOps.SeekDestroy),
        ploys = HunterCladePloys.ployList,
        equipment = HunterCladeEQ.equipmentList,
        operators = HunterCladeOperators.operatorList,
        minOperators = 10,
        maxOperator = 10
    )

    val teamList = listOf(AngelsOfDeath,DeathKorps,HunterClade,KasrkinSquad,PlagueMarines,VoidscaredCorsairs)
}
