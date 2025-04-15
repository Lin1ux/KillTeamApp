package Objects

import kotlin.Int


object KillTeams
{
    val DeathKorps = TeamInfo(
        name = "Death Korps",
        archetypes = listOf(TacOps.Security, TacOps.SeekDestroy),
        ploys = DeathKorpsPloys.ployList,
        equipment = DeathKorpsEQ.equipmentList,
        operators = DeathKorpsOperators.operatorList,
        nameKeyword = "Death Korps",
        minOperators = 2,  //10
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
        minOperators = 9,
        maxOperator = 9
    )

    val PlagueMarines = TeamInfo(
        name = "Plague Marines",
        archetypes = listOf(TacOps.Security, TacOps.SeekDestroy),
        ploys = PlagueMarinesPloys.ployList,
        equipment = PlagueMarinesEQ.equipmentList,
        minOperators = 6,
        maxOperator = 6
    )

    val HunterClade = TeamInfo(
        name = "Hunter Clade",
        archetypes = listOf(TacOps.Recon, TacOps.SeekDestroy),
        ploys = HunterCladePloys.ployList,
        equipment = HunterCladeEQ.equipmentList,
        minOperators = 10,
        maxOperator = 10
    )

    val teamList = listOf(DeathKorps,KasrkinSquad,AngelsOfDeath,VoidscaredCorsairs,PlagueMarines,HunterClade)
}
