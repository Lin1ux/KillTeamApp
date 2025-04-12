package com.example.killteam

import androidx.compose.foundation.layout.Box

//Death Korps
object DeathKorpsPloys
{
    val SiegeWarfare = Ploy(
        name = "Siege Warfare",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val TakeCover = Ploy(
        name = "Take Cover",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ClearTheLine = Ploy(
        name = "Clear The Line",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Regroup = Ploy(
        name = "Regroup",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val InspirationalLeadership = Ploy(
        name = "Inspirational Leadership",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CombinedArms = Ploy(
        name = "Combined Arms",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val InLifeShame = Ploy(
        name = "In Life, Shame",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val InDeathAtonement = Ploy(
        name = "In Death Atonement",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )


    val ployList = listOf(SiegeWarfare,TakeCover,ClearTheLine,Regroup,InspirationalLeadership,CombinedArms,InLifeShame,InDeathAtonement)
}

//Kasrkin
object KasrkinPloys
{
    val EliminationPattern = Ploy(
        name = "Elimination Pattern",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val EngagefromCover = Ploy(
        name = "Engage from Cover",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ClearanceSweep = Ploy(
        name = "Clearance Sweep",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Relocate = Ploy(
        name = "Relocate",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val SeizeTheInitiative = Ploy(
        name = "Seize the Initiative",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CoverRetreat = Ploy(
        name = "Cover Retreat",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val NeutriliseTarget = Ploy(
        name = "Neutralise Target",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val GiveNoGround = Ploy(
        name = "Give No Ground",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ployList = listOf(EliminationPattern,EngagefromCover,ClearanceSweep,Relocate,SeizeTheInitiative,CoverRetreat,NeutriliseTarget,GiveNoGround)
}

//Angels Of Death
object AngelsOfDeathPloys
{
    val CombatDoctrine = Ploy(
        name = "Combat Doctrine",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val NoFear = Ploy(
        name = "And They Shall Know No Fear",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val AdaptiveTactics = Ploy(
        name = "Adaptive Tactics",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Indomitus = Ploy(
        name = "Indomitus",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val AdjustDoctrine = Ploy(
        name = "Adjust Doctrine",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val TranshumanPhysiology = Ploy(
        name = "Transhuman Physiology",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ShockAssault = Ploy(
        name = "Shock Assault",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val WrathOfVengeance = Ploy(
        name = "Wrath Of Vengeance",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ployList = listOf(CombatDoctrine,NoFear,AdaptiveTactics,Indomitus,AdjustDoctrine,TranshumanPhysiology,ShockAssault,WrathOfVengeance)
}

//Voidscared Corsairs
object VoidscaredCorsairsPloys
{
    val Plunderers = Ploy(
        name = "Plunderers",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val PiraticalProfiteers = Ploy(
        name = "Piratical Profiteers",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val MobileEngagement = Ploy(
        name = "Mobile Engagement",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Outcasts = Ploy(
        name = "Outcasts",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val OpportunisticFighters = Ploy(
        name = "Opportunistic Fighters",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val LightFingers = Ploy(
        name = "Light Fingers",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CapriciousFlight = Ploy(
        name = "Capricious Flight",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ContemptuousAdventurer = Ploy(
        name = "Contemptuous Adventurer",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

        val ployList = listOf(Plunderers,PiraticalProfiteers,MobileEngagement,Outcasts,OpportunisticFighters,LightFingers,CapriciousFlight,ContemptuousAdventurer)
}

//Plague Marines
object PlagueMarinesPloys
{
    val Contagion = Ploy(
        name = "Contagion",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val CloudOfFlies = Ploy(
        name = "Cloud Of Flies",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val LumberingDeath = Ploy(
        name = "Lumbering Death",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Nurglings = Ploy(
        name = "Nurglings",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val CurseofRot = Ploy(
        name = "Curse of Rot",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val PoisonousDemise = Ploy(
        name = "Poisonous Demise",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val SickeningResilience = Ploy(
        name = "Sickening Resilience",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val VirulentPoison = Ploy(
        name = "Virulent Poison",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ployList = listOf(Contagion,CloudOfFlies,LumberingDeath,Nurglings,CurseofRot,PoisonousDemise,SickeningResilience,VirulentPoison)
}

//Hunter Clade (ADMech)
object HunterCladePloys
{
    val DebilitatingIrradiation = Ploy(
        name = "Debilitating Irradiation",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ScoutingProtocol = Ploy(
        name = "Scouting Protocol",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val NeurostaticInterference = Ploy(
        name = "Neurostatic Interference",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val AccelerantAgents = Ploy(
        name = "Accelerant Agents",
        description = "",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ControlEdict = Ploy(
        name = "Control Edict",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val OmnissiahsImperative = Ploy(
        name = "Omnissiah's Imperative",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ScrapcodeOverload = Ploy(
        name = "Scrapcode Overload",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CommandOverride = Ploy(
        name = "Command Override",
        description = "",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ployList = listOf(DebilitatingIrradiation,ScoutingProtocol,NeurostaticInterference,AccelerantAgents,ControlEdict,OmnissiahsImperative,ScrapcodeOverload,CommandOverride)
}
