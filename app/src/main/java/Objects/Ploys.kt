package Objects

//Death Korps
object DeathKorpsPloys
{
    val SiegeWarfare = Ploy(
        name = "Siege Warfare",
        description = "The ranged weapons of friendly ##DEATH KORPS## operatives gain the xxSaturatexx and xxAccurate 1xx weapon rules.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val TakeCover = Ploy(
        name = "Take Cover",
        description = "Whenever an enemy operative shoots a friendly ##DEATH KORPS## operative, if you can retain any cover saves, improve that friendly operative’s Save stat by 1 for that shot.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ClearTheLine = Ploy(
        name = "Clear The Line",
        description = "When a friendly ##DEATH KORPS## operative is fighting within your territory, contesting an objective marker, or retaliating, its melee weapons gain the xxAccurate 1xx weapon rule.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Regroup = Ploy(
        name = "Regroup",
        description = "**SUPPORT:** Select one friendly **DEATH KORPS** operative that’s more than 3” from enemy operatives. Each other friendly ##DEATH KORPS## operative within 5” of that operative and not within control range of enemy operatives can immediately perform a free **Dash** action in an order of your choice, but each that does so must end that move closer to that operative. \n" +
                "\nNote that a Comms Device from universal equipment only affects the second distance of this rule.\n" +
                "\nYou cannot use this ploy and the Chronometer faction equipment **STRATEGIC GAMBIT** during the same turning point.\n",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val InspirationalLeadership = Ploy(
        name = "Inspirational Leadership",
        description = "**SUPPORT:** Use this firefight ploy during a friendly ##DEATH KORPS## **WATCHMASTER** or **CONFIDANT** operative’s activation, before or after it performs an action. It issues a **GUARDSMAN ORDER.**",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CombinedArms = Ploy(
        name = "Combined Arms",
        description = "Use this firefight ploy after rolling your attack dice for a friendly ##DEATH KORPS## operative. If it’s shooting an enemy operative that’s been shot by another friendly ##DEATH KORPS## operative during this Turning Point, you can **re-roll** any of your attack dice.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val InLifeShame = Ploy(
        name = "In Life, Shame",
        description = "Use this firefight ploy when a friendly ##DEATH KORPS## operative is activated and given an **Engage** order. It receives every **GUARDSMAN ORDER.** This takes precedence over the normal rule that operatives cannot benefit from more than one **GUARDSMAN ORDER** at once.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val InDeathAtonement = Ploy(
        name = "In Death Atonement",
        description = "Use this firefight ploy when a ready friendly ##DEATH KORPS## operative is incapacitated. If it isn’t within control range of enemy operatives, that operative can immediately perform one **free action** before being removed from the killzone. \n\nNote that that friendly operative is injured for the duration of that action.",
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
        description = "Whenever a friendly ##KASRKIN## operative is shooting with a hot-shot weapon against a target that’s not in cover, that weapon has the xxPiercing Crits 1xx weapon rule, or xxPiercing 1xx instead if it’s a hot-shot volley gun.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val EngagefromCover = Ploy(
        name = "Engage from Cover",
        description = "Whenever an operative is shooting a friendly ##KASRKIN## operative that’s in cover, you can **re-roll** one of your defence dice.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ClearanceSweep = Ploy(
        name = "Clearance Sweep",
        description = "Place your Clearance Sweep marker in the killzone. Whenever a friendly ##KASRKIN## operative within 5\" horizontally of that marker is shooting an operative also within 5\" horizontally of that marker, that friendly operative’s weapons have the xxBalancedxx weapon rule. In the Ready step of the next Strategy phase, remove that marker.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Relocate = Ploy(
        name = "Relocate",
        description = "Select one friendly ##KASRKIN## operative that’s more than 3\" from every enemy operative. That operative, and each other friendly ##KASRKIN## operative that’s both within 3\" of that operative and more than 3\" from enemy operatives, can immediately perform a free **Dash** action in an order of your choice. You cannot use this ploy during the first turning point.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val SeizeTheInitiative = Ploy(
        name = "Seize the Initiative",
        description = "Use this firefight ploy at the end of the firefight phase (excluding the final Firefight phase). In the Initiative step of the next Strategy phase, when rolling off to decide initiative, you can do one of the following:\n" +
                "\n • **Re-roll** your dice.\n" +
                "\n • Force your opponent to **re-roll** their dice.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CoverRetreat = Ploy(
        name = "Cover Retreat",
        description = "Use this firefight ploy when a friendly ##KASRKIN## operative performs the **Fall Back** action while visible to and within 6\" of another ready friendly ##KASRKIN## operative that’s not within control range of enemy operatives. After that friendly operative has finished moving, but before that **Fall Back** action ends, that other friendly operative can immediately perform a free **Shoot** action.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val NeutriliseTarget = Ploy(
        name = "Neutralise Target",
        description = "Use this firefight ploy when a friendly ##KASRKIN## operative is shooting, after rolling your attack dice. If the target is **expended** and not in **cover,** you can **re-roll** any of your attack dice.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val GiveNoGround = Ploy(
        name = "Give No Ground",
        description = "Use this firefight ploy during a friendly ##KASRKIN## operative’s activation, or at the end of the Firefight phase. Select one of your mission markers or an objective marker. Until the end of that activation or until the start of the next turning point respectively, if the total APL of friendly ##KASRKIN## operatives that contest that marker is 2, and the total APL of enemy operatives that contest it is the same, friendly ##KASRKIN## operatives control that marker.",
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
        description = "Select one **COMBAT DOCTRINE** from the following:\n" +
                "\n • **Devastator Doctrine:** Shooting an operative more than 6\" from it.\n" +
                "\n • **Tactical Doctrine:** Shooting an operative within 6\" of it.\n" +
                "\n • **Assault Doctrine:** Fighting or retaliating.\n" +
                "\n" +
                "Whenever a friendly ##ANGEL OF DEATH## operative is x, its weapons have the xxBalancedxx weapon rule -X, where X is the **COMBAT DOCTRINE** you selected.\n",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val NoFear = Ploy(
        name = "And They Shall Know No Fear",
        description = "You can ignore any changes to the stats of friendly ##ANGEL OF DEATH## operatives from being injured (including their weapons’ stats).",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val AdaptiveTactics = Ploy(
        name = "Adaptive Tactics",
        description = "Change your secondary **CHAPTER TACTIC.** This ploy only lasts until the end of the turning point, at which point your original secondary **CHAPTER TACTIC** returns.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Indomitus = Ploy(
        name = "Indomitus",
        description = "Whenever an operative is shooting a friendly ##ANGEL OF DEATH## operative, if you roll two or more fails, you can discard one of them to retain another as a normal success instead.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val AdjustDoctrine = Ploy(
        name = "Adjust Doctrine",
        description = "Use this firefight ploy during a friendly ##ANGEL OF DEATH## operative’s activation, before or after it performs an action. If you’ve used the Combat Doctrine strategy ploy during this turning point, change the **COMBAT DOCTRINE** you selected.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val TranshumanPhysiology = Ploy(
        name = "Transhuman Physiology",
        description = "Use this firefight ploy when an operative is shooting a friendly ##ANGEL OF DEATH## operative, in the Roll Defence Dice step. You can retain one of your normal successes as a critical success instead.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ShockAssault = Ploy(
        name = "Shock Assault",
        description = "Use this firefight ploy when a friendly ##ANGEL OF DEATH## operative is performing the **Fight** action during an activation in which it performed the **Charge** action, at the start of the Resolve Attack Dice step. Until the end of that action:\n" +
                "\n • Its melee weapon has the xxShockxx weapon rule.\n" +
                "\n • The first time you strike during that sequence, inflict 1 additional damage (to a maximum of 7).",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val WrathOfVengeance = Ploy(
        name = "Wrath Of Vengeance",
        description = "Use this firefight ploy when a friendly ##ANGEL OF DEATH## operative is counteracting. It can perform an additional 1AP action for free during that **counteraction,** but both actions must be different.",
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
        description = "Up to D3 friendly ##CORSAIR VOIDSCARRED## operatives can immediately perform a free **Dash** action in an order of your choice. This turning point, each that does so cannot perform the **Dash** action during their activation. You cannot use this ploy during the first turning point.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val PiraticalProfiteers = Ploy(
        name = "Piratical Profiteers",
        description = "Whenever a friendly ##CORSAIR VOIDSCARRED## operative is shooting, fighting or retaliating, if it or the enemy operative in that sequence contests an objective marker or one of your mission markers, that friendly operative’s weapons have the xxBalancedxx weapon rule.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val MobileEngagement = Ploy(
        name = "Mobile Engagement",
        description = "Whenever an operative is shooting a friendly ##CORSAIR VOIDSCARRED## operative that performed an action in which it moved during this turning point, you can **re-roll** one of your defence dice.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Outcasts = Ploy(
        name = "Outcasts",
        description = "Whenever a friendly ##CORSAIR VOIDSCARRED## operative is more than 5\" from other friendly operatives, its weapons have the xxPunishingxx weapon rule.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val OpportunisticFighters = Ploy(
        name = "Opportunistic Fighters",
        description = "Use this firefight ploy when an enemy operative performs the **Fall Back** action. Before it moves, inflict 2D3 damage on that operative for each friendly ##CORSAIR VOIDSCARRED## operative within its control range.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val LightFingers = Ploy(
        name = "Light Fingers",
        description = "Use this firefight ploy during a friendly ##CORSAIR VOIDSCARRED## operative’s activation. Until the end of that activation, having an enemy operative within its control range doesn’t prevent that friendly operative from performing the **Pick Up Marker** or **Mission** actions.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CapriciousFlight = Ploy(
        name = "Capricious Flight",
        description = "Use this firefight ploy during a friendly ##CORSAIR VOIDSCARRED## operative’s activation. During that activation, that operative can perform the **Fall Back** action for 1 less AP.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ContemptuousAdventurer = Ploy(
        name = "Contemptuous Adventurer",
        description = "Use this firefight ploy when the first friendly ##CORSAIR VOIDSCARRED## operative is activated during the turning point if it’s more than 5\" from other friendly operatives. The first time that operative performs either the **Shoot** or **Fight** action during that activation, it has the xxRelentlessxx weapon rule. Note: this ploy cannot come into effect more than once per activation (you cannot use it during both the **Shoot** and **Fight** action in the same activation).",
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
        description = "Subtract 2\" from the Move stat of an enemy operative and worsen the Hit stat of its weapons by 1 (this isn’t cumulative with being injured) whenever any of the following are true:\n" +
                "\n • It’s within control range of friendly ##PLAGUE MARINE## operatives.\n" +
                "\n • It has one of your Poison tokens and is visible to (or vice versa) and within 3\" of friendly ##PLAGUE MARINE## operatives.\n" +
                "\n • It’s visible to (or vice versa) and within 3\" of a friendly ##PLAGUE MARINE## **ICON BEARER** operative.\n" +
                "\n",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val CloudOfFlies = Ploy(
        name = "Cloud Of Flies",
        description = "Place one of your Cloud of Flies markers in the killzone. Whenever an operative is shooting a friendly ##PLAGUE MARINE## operative that’s more than 3\" from it, if that friendly operative is wholly within 3\" of that marker, that friendly operative is **obscured.** In the Ready step of the next Strategy phase, remove that marker.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val LumberingDeath = Ploy(
        name = "Lumbering Death",
        description = "Whenever a friendly ##PLAGUE MARINE## operative is shooting or fighting during an activation in which it hasn’t moved more than 3\", or whenever it’s retaliating, its weapons have the xxCeaselessxx weapon rule.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val Nurglings = Ploy(
        name = "Nurglings",
        description = "Select one enemy operative within 3\" of a friendly ##PLAGUE MARINE## operative, or one enemy operative that has one of your Poison tokens and is within 7\" of a friendly ##PLAGUE MARINE## operative. Until the end of the selected operative’s next activation, subtract 1 from its APL stat.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val CurseofRot = Ploy(
        name = "Curse of Rot",
        description = "Use this firefight ploy when a friendly ##PLAGUE MARINE## operative is fighting against or shooting against an enemy operative within 3\" of it (or within 7\" of it if that enemy operative has one of your Poison tokens), after your opponent rolls their attack or defence dice. For each result of 3 they roll, inflict 1 damage on that enemy operative, that result is treated as a fail and they cannot re-roll it.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val PoisonousDemise = Ploy(
        name = "Poisonous Demise",
        description = "Use this firefight ploy when a friendly ##PLAGUE MARINE## operative is incapacitated, before it’s removed from the killzone. Each enemy operative visible to and within 3\" of that operative gains one of your Poison tokens (if they don’t already have one); for each of those enemy operatives that already has one of your Poison tokens (including if they gained one during this action), inflict 1 damage on them instead.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val SickeningResilience = Ploy(
        name = "Sickening Resilience",
        description = "Use this firefight ploy when an attack dice inflicts damage on a friendly ##PLAGUE MARINE## operative. Until the end of the activation or counteraction, for the purposes of the **Disgustingly Resilient** rule for that operative, always subtract 1 from the damage inflicted (to a minimum of 2) — you don’t need to roll.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val VirulentPoison = Ploy(
        name = "Virulent Poison",
        description = "Use this firefight ploy during a friendly ##PLAGUE MARINE## operative’s activation or counteraction, before or after it performs an action. Select one of the following:\n" +
                "\n • One enemy operative within 3\" of that operative gains one of your Poison tokens (if it doesn’t already have one).\n" +
                "\n • Roll 2D6: if the result is 7+, one enemy operative within 7\" of that operative gains one of your Poison tokens (if it doesn’t already have one).\n" +
                "\n",
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
        description = "Whenever an enemy operative is shooting against, fighting against or retaliating against a friendly ##HUNTER CLADE## **VANGUARD** operative, if that enemy operative is under the effects of the **Rad-Saturation** rule (see VANGUARD operatives), subtract 1 from the Normal Dmg stat of its weapons (to a minimum of 3).",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ScoutingProtocol = Ploy(
        name = "Scouting Protocol",
        description = "Each friendly ##HUNTER CLADE## **RANGER** operative that has a **Conceal** order and is more than 6\" from enemy operatives can immediately perform a free **Dash** action in an order of your choice. You cannot use this ploy during the first turning point.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val NeurostaticInterference = Ploy(
        name = "Neurostatic Interference",
        description = "Whenever an enemy operative within 6\" of a friendly ##HUNTER CLADE## **INFILTRATOR** operative is shooting, fighting or retaliating, your opponent cannot **re-roll** their attack dice.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val AccelerantAgents = Ploy(
        name = "Accelerant Agents",
        description = "During each friendly ##HUNTER CLADE## **RUSTSTALKER** operative’s activation, it can perform two **Fight** actions, and one of them can be free.",
        type = PloyType.STRATEGY,
        cost = "1CP"
    )

    val ControlEdict = Ploy(
        name = "Control Edict",
        description = "Use this firefight ploy when it’s your turn to activate a friendly operative. Select one friendly ##HUNTER CLADE## **LEADER** operative and one other ready friendly ##HUNTER CLADE## operative visible to and within 3\" of that **LEADER** operative; activate one of them as normal. When that first friendly operative is expended, you can activate the other friendly operative before your opponent activates. Whenever you use this ploy, you cannot select more than one **SICARIAN** ##HUNTER CLADE## operative.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val OmnissiahsImperative = Ploy(
        name = "Omnissiah's Imperative",
        description = "Use this firefight ploy during a friendly ##HUNTER CLADE## operative’s activation. Alternatively, use it when an enemy operative is shooting a friendly ##HUNTER CLADE## operative, at the end of the Roll Attack Dice step. Inflict D3+1 damage on that friendly operative. Until the Ready step of the next Strategy phase, that friendly operative has an additional rule determined by its current **DOCTRINA IMPERATIVE** as follows:\n" +
                "\n • **Protector:** This operative’s ranged weapons have the xxSeverexx weapon rule.\n" +
                "\n • **Conqueror:** Whenever this operative is fighting, after resolving your first attack dice during that sequence, you can immediately resolve another (before your opponent).\n" +
                "\n • **Bulwark:** Improve this operative’s Save stat by 1. In addition, whenever an operative is shooting this operative, you can collect and roll an additional defence dice. If you use this ploy during a **Shoot** action, this operative’s Save stat is changed immediately (this takes precedence over the core rules).\n" +
                "\n • **Aggressor:** You can ignore the first vertical distance of 2\" this operative moves during one climb up.\n" +
                "\n • **Neutral:** None.\n" +
                "\n" +
                "Note that you can use this ploy after rolling attack or defence dice for this operative, or before or after retaining or re-rolling those dice.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ScrapcodeOverload = Ploy(
        name = "Scrapcode Overload",
        description = "Use this firefight ploy when a friendly ##HUNTER CLADE## **INFILTRATOR** operative is activated. Alternatively, use this firefight ploy when a friendly ##HUNTER CLADE## **INFILTRATOR** operative, or an enemy operative within 3\" of that friendly operative, would perform the **Pick Up Marker** or a **Mission** action (excluding Operate Hatch). Until the start of that friendly operative’s next activation, whenever determining control of a marker, treat the total APL stat of enemy operatives that contest it as 1 lower if at least one of those enemy operatives is within 3\" of that friendly operative. " +
                "\n\nNote this isn’t a change to the APL stat, so any changes are cumulative with this, and this can change control of a marker before performing the action.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val CommandOverride = Ploy(
        name = "Command Override",
        description = "Use this firefight ploy when you activate a friendly ##HUNTER CLADE## operative. Select a **DOCTRINA IMPERATIVE** for that operative to have instead of its current one (if any) until the Ready step of the next Strategy phase.",
        type = PloyType.FIREFIGHT,
        cost = "1CP"
    )

    val ployList = listOf(DebilitatingIrradiation,ScoutingProtocol,NeurostaticInterference,AccelerantAgents,ControlEdict,OmnissiahsImperative,ScrapcodeOverload,CommandOverride)
}
