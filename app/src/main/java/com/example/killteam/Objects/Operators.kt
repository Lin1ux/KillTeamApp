package com.example.killteam.Objects

object DeathKorpsOperators
{
    val Watchmaster = Operator(
        name = "Death Korps Watchmaster",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 8,
        weapons = listOf(Weapons.DKPlasmaPistolStandard,Weapons.DKPlasmaPistolCharged, Weapons.DKPowerSword),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        leader = true,
        additionalRules = " **Adaptive Orders:** If this operative doesn’t issue a **GUARDSMAN ORDER** as a **STRATEGIC GAMBIT,** you can use the **Inspirational Leadership** firefight ploy for 0CP during this operative’s activation. "
    )

    val Sniper = Operator(
        name = "Death Korps Sniper",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLongLasConcealed,Weapons.DKLongLasMobile, Weapons.DKLongLasStationary, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        additionalRules = " ***Concealed Position:** This operative can only use this weapon the first time it’s performing the **Shoot** action during the battle. "
    )

    val GunnerGranadeLuncher = Operator(
        name = "Death Korps Gunner (Grenade Luncher)",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKGranadeLuncherFrag,Weapons.DKGranadeLuncherKrak, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
    )

    val GunnerPlasma = Operator(
        name = "Death Korps Gunner (Plasma gun)",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKPlasmaGunStandard,Weapons.DKPlasmaGunCharged, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
    )

    val GunnerMelta = Operator(
        name = "Death Korps Gunner (Meltagun)",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKMeltaGun, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,

    )

    val Sapper = Operator(
        name = "Death Korps Sapper",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun,Weapons.DKRemoteMine, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        additionalRules = " **Mine Layer:** This operative is carrying your Mine marker. It can perform the **Pick Up Marker** action on that marker, and whenever it performs the **Place Marker** action on that marker, it can immediately perform a free **Dash** action.\n\n " +
                "***Detonate:** Don’t select a valid target. Instead, shoot against each operative within 2” of your Mine marker, unless Heavy terrain is wholly intervening between that operative and that marker. Each of those operatives cannot be in cover or obscured. Roll each sequence separately in an order of your choice. This weapon cannot be selected if your Mine marker isn’t in the killzone. At the end of the action, remove your Mine marker from the killzone. "
    )

    val Zealot = Operator(
        name = "Death Korps Zealot",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        additionalRules = " **The Emperor Protects:** Whenever an operative is shooting this operative, you can **re-roll** any of your defence dice. \n\n " +
                "**Uplifting Primer: SUPPORT.** Whenever a friendly ##DEATH KORPS## operative is within 3” of this operative, that friendly operative’s weapons have the xxSeverexx weapon rule. "
    )

    val Medic = Operator(
        name = "Death Korps Medic",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        additionalRules = " **Medic:** The first time during each turning point that another friendly ##DEATH KORPS## operative would incapacitated while visible to and within 3\" of this operative, you can use this rule, providing neither this nor that operative is within control Range of an enemy operative. If you do, that friendly operative isn’t incapacitated and has 1 wound remaining and cannot be incapacitated for the remainder of the action. After that action, that friendly operative can then immediately perform a free **Dash** action, but must end that move within this operative’s control Range . Subtract 1 from this and that operative’s APL stats until the end of their next activations respectively, and if this rule was used during that friendly operative’s activation, that activation ends. You cannot use this rule if this operative is incapacitated, or if it’s a **Shoot** action and this operative would be a primary or secondary target.",
        actions = listOf(Actions.DKMedikit)
    )

    val VoxOperator = Operator(
        name = "Death Korps Vox-operator",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        additionalRules = " **Relay Orders:** Once per turning point, when this operative receives a **GUARDSMAN ORDER,** if it’s not within control Range of enemy operatives, it can relay that order. Whenever an order is relayed, all friendly ##DEATH KORPS## operatives in the killzone receive that order, then subtract 1 from this operative’s APL stat until the end of its next activation. ",
        actions = listOf(Actions.DKSignal)
    )

    val Spotter = Operator(
        name = "Death Korps Spotter",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun,Weapons.DKMortarBarage, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        actions = listOf(Actions.DKSpot)
    )

    val Trooper = Operator(
        name = "Death Korps Trooper",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        specialist = false,
        additionalRules = " **Group Activation:** Whenever this operative is expended, you must then activate one other ready friendly ##DEATH KORPS## **TROOPER** operative (if able) before your opponent activates. When that other operative is expended, your opponent then activates as normal (in other words, you cannot activate more than two operatives in succession with this rule). "
    )

    val operatorList = listOf(Watchmaster,Sniper,GunnerGranadeLuncher,GunnerPlasma,GunnerMelta,Sapper,Zealot,Medic,VoxOperator,Spotter,Trooper)

}

object KasrkinSquadOperators
{
    val Seargent = Operator(
        name = "Kasrkin Seargent",
        APL = 3,
        move = 6,
        save = 4,
        wounds = 9,
        weapons = listOf(Weapons.KSPlasmaPistolStandard,Weapons.KSPlasmaPistolCharged, Weapons.KSChainsword),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        leader = true,
        actions = listOf(Actions.KSTacticalCommand)
    )

    val Medic = Operator(
        name = "Kasrkin Medic",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasgun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        additionalRules = " **Medic!** The first time per turning point that a friendly ##KASRKIN## operative would incapacitated while visible to and within 3\" of this operative, you can use this rule, providing neither this nor that operative is within control Range of an enemy operative. If you do, that friendly operative isn’t incapacitated and has 1 wound remaining and cannot be incapacitated for the remainder of the action. After that action, that friendly operative can then immediately perform a free **Dash** action, but must end that move within this operative’s control Range . Subtract 1 from this and that operative’s APL stats until the end of their next activations respectively, and if this rule was used during that friendly operative’s activation, that activation ends. You cannot use this rule if this operative is incapacitated, or if it’s a Shoot action and this operative would be a primary or secondary target.\n",
        actions = listOf(Actions.KSMedikit)
    )

    val DemoTrooper = Operator(
        name = "Kasrkin Demo-Trooper",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasPistol, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        additionalRules = " **Melta Mine:** This operative is carrying your Melta Mine marker. It can perform the **Pick Up Marker** action on that marker, and whenever it performs the **Place Marker** action on that marker, it can immediately perform a free **Dash** action. That marker cannot be placed within an enemy operative’s control Range (if this operative is incapacitated while carrying that marker and that marker cannot be placed, it’s removed with this operative). \n\n " +
                        "**Proximity Mine:** The first time your Melta Mine marker is within another operative’s control Range , remove that marker and inflict **2D6+3** damage on that operative; if it isn’t incapacitated, end its action (if any), even if that action’s effects aren’t fulfilled. If it cannot be placed, move it the minimum amount to do so. Note that this operative is ignored for these effects (i.e. it cannot set it off or take damage from that marker). \n\n " +
                        "**Blast Padding:** Whenever an operative is shooting this operative with a weapon that has the xxBlastxx or xxTorrentxx weapon rule (excluding weapons that have a sweeping profile), you can **re-roll** one of your defence dice. In addition, this operative isn’t affected by the xxx\" Devastating xxx weapon rule (i.e. Devastating with a distance) unless they are the target during that sequence. "
    )

    val GunnerGranadeLuncher = Operator(
        name = "Kasrkin Gunner (Grenade Luncher)",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSGranadeLuncherFrag,Weapons.KSGranadeLuncherKrak, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
    )

    val GunnerPlasma = Operator(
        name = "Kasrkin Gunner (Plasmagun)",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSPlasmaGunStandard,Weapons.KSPlasmaGunCharged, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
    )

    val GunnerMelta = Operator(
        name = "Kasrkin Gunner (Meltagun)",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSMeltaGun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
    )

    val ReconTrooper = Operator(
        name = "Kasrkin Recon-Trooper",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasgun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        actions = listOf(Actions.KSAuspexScan)
    )

    val Sharpshooter = Operator(
        name = "Kasrkin Sharpshooter",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotMarksmanRifleConcealed,Weapons.KSHotShotMarksmanRifleMobile,Weapons.KSHotShotMarksmanRifleStationary, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        additionalRules = " **Camo Cloak:** Whenever an operative is shooting this operative:\n" +
                "\n • Ignore the xxSaturatexx weapon rule.\n" +
                "\n • If you can retain any cover saves, you can retain one additional cover save, or you can retain one cover save as a critical success instead. This isn’t cumulative with improved cover saves from Vantage terrain.\n\n " +
                "***Concealed Position:** This operative can only use this weapon the first time it’s performing the Shoot action during the battle. "
    )

    val VoxTrooper = Operator(
        name = "Kasrkin Vox-Trooper",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasgun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        additionalRules = " **Reconnoitre Killzone:** The **Relocate** strategy ploy costs you 0CP if this operative is the selected friendly ##KASRKIN## operative.",
        actions = listOf(Actions.KSBattleComms)
    )

    val Trooper = Operator(
        name = "Kasrkin Trooper",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasgun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28,
        specialist = false,
        additionalRules = "\n" +
                " **Adaptive Equipment:** You can do each of the following once per turning point:\n" +
                "\n • One friendly ##KASRKIN## **TROOPER** operative can perform the **Smoke Grenade** action.\n" +
                "\n • One friendly ##KASRKIN## **TROOPER** operative can perform the **Stun Grenade** action.\n\n " +
                "The rules for these actions are found in universal equipment. Performing these actions using this rule doesn’t count towards their action limits (i.e. if you also select those grenades from equipment).\n"
    )

    val operatorList = listOf(Seargent,Medic,DemoTrooper,GunnerGranadeLuncher,GunnerPlasma,GunnerMelta,ReconTrooper,Sharpshooter,VoxTrooper,Trooper)

}

object AngelsOfDeathOperators
{
    val Captain = Operator(
        name = "Space Marine Captain",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 15,
        weapons = listOf(Weapons.ADPlasmaPistolStandard,Weapons.ADPlasmaPistolCharged, Weapons.ADPowerFist),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 40,
        leader = true,
        additionalRules = " **Heroic leader:** Once per turning point, you can use a firefight ploy for 0CP if this is the specified ##ANGEL OF DEATH## operative (excluding Command Re-roll), or the **Adjust Doctrine** firefight ploy for 0CP if this operative is in the killzone and not within control Range of enemy operatives. \n\n " +
                            "**Iron halo:** Once per battle, when an attack dice inflicts Normal Dmg on this operative, you can ignore that inflicted damage. "
    )
    val Grenadier = Operator(
        name = "Assault Intercessor Grenadier",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADHeavyBoltPistol,Weapons.FragGranadeImproved,Weapons.KrakGranadeImproved, Weapons.ADChainsword),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
        additionalRules = " **Grenadier:** This operative can use frag and krak grenades (see universal equipment). Doing so doesn’t count towards any Limited uses you have (i.e. if you also select those grenades from equipment for other operatives). Whenever it’s doing so, improve the Hit stat of that weapon by 1. \n" +
                        "Note weapons have statistics of grenade with improved 1+ hit stat"
    )

    val GunnerBoltRifle = Operator(
        name = "Intercessor Gunner (Bolt rifle)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADBoltRifle,Weapons.ADGranadeLuncherFrag,Weapons.ADGranadeLuncherKrak, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
    )

    val GunnerAutoBoltRifle = Operator(
        name = "Intercessor Gunner (Auto bolt rifle)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADAutoBoltRifle,Weapons.ADGranadeLuncherFrag,Weapons.ADGranadeLuncherKrak, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
    )

    val GunnerStalkerBoltRifle = Operator(
        name = "Intercessor Gunner (Stalker bolt rifle)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADStalkerBoltRifleMobile,Weapons.ADStalkerBoltRifleHeavy,Weapons.ADGranadeLuncherFrag,Weapons.ADGranadeLuncherKrak, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
    )

    val EliminatorSniper = Operator(
        name = "Eliminator Sniper",
        APL = 3,
        move = 7,
        save = 3,
        wounds = 12,
        weapons = listOf(Weapons.ADBoltPistol,Weapons.ADBoltSniperRifleExecutioner,Weapons.ADBoltSniperRifleHyperfrag,Weapons.ADBoltSniperRifleMortis,Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 40,
        additionalRules = " **Camo Cloak:** Whenever an operative is shooting this operative, ignore the xxSaturatexx weapon rule. This operative has the **CHAPTER TACTIC** Stealthy. If you selected that **CHAPTER TACTIC,** you can do both of its options (i.e. retain two cover saves – one normal and one critical success). ",
        actions = listOf(Actions.ADSpot)
    )

    val HeavyGunner = Operator(
        name = "Heavy Intercessor Gunner",
        APL = 3,
        move = 4,
        save = 3,
        wounds = 18,
        weapons = listOf(Weapons.ADHeavyBolterSweeping,Weapons.ADHeavyBolterFocused, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 40,
    )

    val AssaultWarrior = Operator(
        name = "Assault Intercessor Warrior",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADHeavyBoltPistol, Weapons.ADChainsword),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
        specialist = false
    )
    val WarriorBoltRifle = Operator(
        name = "Intercessor Warrior (Bolt rifle)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADBoltRifle, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
        specialist = false
    )
    val WarriorAutoRifle = Operator(
        name = "Intercessor Warrior (Auto Bolt rifle)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADAutoBoltRifle, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
        specialist = false
    )

    val WarriorStalkerRifle = Operator(
        name = "Intercessor Warrior (stalker bolt Rifle)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.ADStalkerBoltRifleMobile,Weapons.ADStalkerBoltRifleHeavy, Weapons.ADFists),
        keywords = listOf("##ANGELS OF DEATH##"),
        size = 32,
        specialist = false
    )

    val operatorList = listOf(Captain,Grenadier,GunnerAutoBoltRifle,GunnerBoltRifle,GunnerStalkerBoltRifle,EliminatorSniper,HeavyGunner,AssaultWarrior,WarriorAutoRifle,WarriorBoltRifle,WarriorStalkerRifle)

}

object VoidscaredCorsairsOperator
{
    val Felarch = Operator(
        name = "Felarch",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 9,
        weapons = listOf(Weapons.VCNeuroDisruptor,Weapons.VCPowerWeapon),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        leader = true,
        additionalRules = " **Veteran Raider:** This operative can perform a 1AP action for free during their activation as a result of the **Aeldari Raiders** rule (instead of the **Dash** action). \n\n " +
                    "**One Step Ahead:** Once per battle, after an enemy operative performs an action during its activation, if this operative is ready, you can use this rule. If you do, roll one D6: if the result is higher than that enemy operative’s APL stat, you can interrupt that activation and immediately perform either a free **Shoot** or a free **Fight** action with this operative, but other enemy operatives cannot be selected as a valid target to fight against during that action (note that secondary targets from the xxBlastxx weapon rule can still be targeted). After you perform that action, subtract 1 from this operative’s APL stat until the end of its next activation. "
    )

    val GunnerBlaster = Operator(
        name = "Gunner (Blaster)",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCBlaster,Weapons.VCFists),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
    )

    val GunnerShredder = Operator(
        name = "Gunner (Shredder)",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCShredder,Weapons.VCFists),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
    )

    val HeavyGunnerWraithCannon = Operator(
        name = "Heavy Gunner (Wraithcannon)",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCWraithCannon,Weapons.VCFists),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
    )

    val StarstormDuelist = Operator(
        name = "Starstorm Duelist",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCFusionPistol,Weapons.VCShurikenPistol,Weapons.VCFists),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        additionalRules = " **Quick on the Trigger:** This operative can perform the **Shoot** action while within control Range of an enemy operative. If it does, when selecting a valid target, you can only select an enemy operative within this operative’s control Range , and can do so even if other friendly operatives are within that enemy operative’s control Range . ",
        actions = listOf(Actions.VCPistolBarrage)
    )

    val KurniteHunter = Operator(
        name = "Kurnite Hunter",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCFalchou,Weapons.VCShurikenPistol,Weapons.VCPowerWeapon),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        additionalRules = "**Faolchú’s Bond:** The first time during each turning point that this operative is retaliating, if it’s ready, in the Resolve Attack Dice step of that sequence, you resolve the first attack dice (i.e. defender instead of attacker). \n\n " +
        "**Erudite Hunter:** **STRATEGIC GAMBIT.** Select one enemy operative within 9\" of this operative. Once during this turning point, after that enemy operative performs an action in which it moves during its activation, you can interrupt that activation to use this rule. If you do, this operative can immediately perform either a free **Reposition** action (it cannot end that move further from that enemy operative), or a free **Charge** action (you can change its order to do so, and it must end that move within control Range of that enemy operative). "
    )

    val ShadeRunner = Operator(
        name = "Shade Runner",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCShurikenPistol,Weapons.VCThrowingBlades,Weapons.VCHekatariiBlades),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        additionalRules = " **Blink Pack:** Whenever this operative performs the **Charge,** **Fall Back** or **Reposition** action, it can warp jump. If it does, don’t move it. Instead, remove it from the killzone and set it back up wholly within 7\" of its original location, measuring the horizontal distance only (in Killzone: Gallowdark, this distance can be measured through walls). It must be set up in a location it can be placed, and unless it’s the **Charge** action, it cannot be set up within control Range of an enemy operative. This operative cannot warp jump during the same activation in which it performed the **Dash** action (or vice versa).  \n\n " +
                "**Slicing Attack:** Whenever this operative performs the **Reposition** action with a warp jump (see left), you can use this rule. If you do, after it moves, draw an imaginary line 1mm in diameter and up to 7\" long between it and its previous location. Note this doesn’t have to be a straight line. Inflict **1D3+2** damage on one enemy operative that line crosses. You cannot inflict damage on an enemy operative that was not visible to this operative at the start of that action. A 28mm round marker can be temporarily placed underneath this operative before it moves to help determine this. "
    )

    val Kurnati = Operator(
        name = "Kurnati",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCShurikenPistol,Weapons.VCDualPowerWeapons),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        additionalRules = " **Blademaster:** This operative can perform the **Dash** action during an activation in which it performed the **Charge** action, but can only use any remaining move distance it had from that **Charge** action (to a maximum of 3\").   \n\n " +
                "**Bladed Stance:** Whenever this operative is fighting or retaliating, you can resolve one of your successes before the normal order. If you do, that success must be used to block. "
    )

    val FateDealer = Operator(
        name = "Fate Dealer",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCRangerLongRifleMobile,Weapons.VCRangerLongRifleStationary,Weapons.VCShurikenPistol,Weapons.VCFists),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        additionalRules = " **Camo Cloak:** Whenever an operative is shooting this operative:\n" +
                "\n • Ignore the xxSaturatexx weapon rule.\n" +
                "\n • If you can retain any cover saves, you can retain one additional cover save, or you can retain one cover save as a critical success instead. This isn’t cumulative with improved cover saves from Vantage terrain."
    )

    val WaySeeker = Operator(
        name = "Way Seeker",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCFreezingGrasp,Weapons.VCLightningStrike, Weapons.VCShurikenPistol,Weapons.VCWitchStaff),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        actions = listOf(Actions.VCWarpFold,Actions.VCWardingShield)
    )

    val SoulWeaver = Operator(
        name = "Soul Weaver",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCShurikenPistol,Weapons.VCPowerWeapon),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        actions = listOf(Actions.VCSoulChannel,Actions.VCSoulHeal)
    )

    val Warrior = Operator(
        name = "Warrior",
        APL = 2,
        move = 7,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.VCShurikenPistol,Weapons.VCPowerWeapon),
        keywords = listOf("##Corsairs Voidscared"),
        size = 28,
        specialist = false,
        additionalRules = " **Prowling Raiders:** You can use the **Capricious Flight** and **Light Fingers firefight** ploys for OCP each if a friendly **WARRIOR** operative is the specified ##CORSAIR VOIDSCARRED## operative. "
    )

    val operatorList = listOf(Felarch,GunnerBlaster,GunnerShredder,HeavyGunnerWraithCannon,StarstormDuelist,KurniteHunter,ShadeRunner,Kurnati,FateDealer,WaySeeker,SoulWeaver,Warrior)
}

object PlagueMarinesOperators{

    val Champion = Operator(
        name = "Plague Marine Champion",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 15,
        weapons = listOf(Weapons.PMPlasmaPistolStandard,Weapons.PMPlasmaPistolCharged,Weapons.PMPlagueSword),
        keywords = listOf("##Plague Marine##"),
        size = 32,
        leader = true,
        additionalRules = " **Grandfather’s Blessing:** Whenever an enemy operative that has one of your Poison tokens loses one or more wounds within 7\" of this operative, this operative regains up to an equal number of lost wounds (to a maximum of 3 lost wounds per turning point, and only if this operative isn’t incapacitated).  \n\n " +
                "***Toxic:** Whenever this operative is using this weapon against an enemy operative that has one of your Poison tokens, add 1 to both Dmg stats of this weapon. "
    )

    val MalignantPlaguecaster = Operator(
        name = "Malignant Plaguecaster",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.PMEntropy,Weapons.PMPlagueWind,Weapons.PMCorruptedStuff),
        keywords = listOf("##Plague Marine##"),
        size = 32,
        actions = listOf(Actions.PMPoisonousMiasma,Actions.PMPutrescentVitality)
    )

    val Bombardier = Operator(
        name = "Plague Marine Bombardier",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.PMBoltgun,Weapons.PMBlightGranadeImproved,Weapons.KrakGranadeImproved,Weapons.PMFists),
        keywords = listOf("##Plague Marine##"),
        size = 32,
        additionalRules = " **Grenadier:** This operative can use blight and krak grenades (see faction and universal equipment). Doing so doesn’t count towards any Limited uses you have (i.e. if you also select those grenades from equipment for other operatives). Whenever it’s doing so, improve the Hit stat of that weapon by 1 and blight grenades have the Toxic weapon rule (see below). \n" +
                "Note weapons have statistics of grenade with improved 1+ hit stat"
    )

    val Fighter = Operator(
        name = "Plague Marine Fighter",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.PMFlailOfCorruption),
        keywords = listOf("##Plague Marine##"),
        size = 32,
        actions = listOf(Actions.PMFlail)
    )

    val HeavyGunner = Operator(
        name = "Plague Marine Heavy Gunner",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.PMPlagueSpewer,Weapons.PMFists),
        keywords = listOf("##Plague Marine##"),
        size = 32
    )

    val IconBearer = Operator(
        name = "Plague Marine Icon Bearer",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.PMBoltPistol,Weapons.PMPlagueKnife5),
        keywords = listOf("##Plague Marine##"),
        size = 32,
        additionalRules = " **Icon Bearer:** Whenever determining control of a marker, treat this operative’s APL stat as 1 higher. Note this isn’t a change to its APL stat, so any changes are cumulative with this. \n\n " +
                    "**Icon of Contagion:** Whenever this operative is within your opponent’s territory, the **Contagion** strategy ploy costs you 0CP. "
    )

    val Warrior = Operator(
        name = "Plague Marine Warrior",
        APL = 3,
        move = 5,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.PMBoltgunToxic,Weapons.PMPlagueKnife4),
        keywords = listOf("##Plague Marine##"),
        size = 32,
        additionalRules = " **Repulsive Fortitude:** Whenever an operative is shooting this operative, defence dice results of 5+ are critical successes. \n\n " +
                "***Toxic:** Whenever this operative is using this weapon against an enemy operative that has one of your Poison tokens, add 1 to both Dmg stats of this weapon. "
    )

    val operatorList = listOf(Champion,MalignantPlaguecaster,Bombardier,Fighter,HeavyGunner,IconBearer,Warrior)

}

object HunterCladeOperators{

    val RangerAlpha  = Operator(
        name = "Ranger Alpha",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.HCArcPistol,Weapons.HCArcMaul),
        keywords = listOf("##Hunter Clade##"),
        size = 25,
        leader = true,
        additionalRules = " **Canticle of Elimination:** Whenever a friendly ##HUNTER CLADE## **RANGER** operative is within 3\" of this operative, that friendly operative's ranged weapons have the xxPunishingxx weapon rule. \n\n " +
            "**Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. \n\n " +
            "**Control Protocol:** You can use the **Command Override** firefight ploy for 0CP if the specified friendly ##HUNTER CLADE## operative is visible to this operative."
    )

    val RangerArcRifle  = Operator(
        name = "Ranger Gunner (Arc Rifle)",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 7,
        weapons = listOf(Weapons.HCArcRifle,Weapons.HCGunButt),
        keywords = listOf("##Hunter Clade##"),
        size = 25,
        additionalRules = " **Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. "
    )

    val RangerPlasmaCaliver  = Operator(
        name = "Ranger Gunner (Plasma Caliver)",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 7,
        weapons = listOf(Weapons.HCPlasmaCaliverStandard,Weapons.HCPlasmaCaliverCharged,Weapons.HCGunButt),
        keywords = listOf("##Hunter Clade##"),
        size = 25,
        additionalRules = " **Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. "
    )

    val RangerArquebues  = Operator(
        name = "Ranger Gunner (Arquebues)",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 7,
        weapons = listOf(Weapons.HCTransuanicArquebuseMobile,Weapons.HCTransuanicArquebuseStationary,Weapons.HCGunButt),
        keywords = listOf("##Hunter Clade##"),
        size = 60,
        additionalRules = " **Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. "
    )

    val RangerDiktat = Operator(
        name = "Ranger Diktat",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 7,
        weapons = listOf(Weapons.HCGalvanicRifle,Weapons.HCGunButt),
        keywords = listOf("##Hunter Clade##"),
        size = 25,
        additionalRules = " **Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. ",
        actions = listOf(Actions.HCSignal)
    )

    val RangerSurveyor = Operator(
        name = "Ranger Surveyor",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 7,
        weapons = listOf(Weapons.HCGalvanicRifle,Weapons.HCGunButt),
        keywords = listOf("##Hunter Clade##"),
        size = 25,
        additionalRules = " **Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. ",
        actions = listOf(Actions.HCSpot)
    )

    val Ranger = Operator(
        name = "Ranger",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 7,
        weapons = listOf(Weapons.HCGalvanicRifle,Weapons.HCGunButt),
        keywords = listOf("##Hunter Clade##, Skitari"),
        size = 25,
        additionalRules = " **Targeting Protocol:** Whenever this operative is shooting, if it hasn’t moved during the activation, or if it’s a counteraction, ranged weapons on its datacard have the xxLethal 5+xx weapon rule. Note this operative isn’t restricted from moving after shooting. ",
        specialist = false
    )

    val operatorList = listOf(RangerAlpha,RangerDiktat,RangerArcRifle,RangerPlasmaCaliver,RangerArquebues,RangerSurveyor,Ranger)
}

object LegionairesOperators
{
    val Champion = Operator(
        name = "Champion",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 15,
        weapons = listOf(Weapons.LPlasmaPistolStandard,Weapons.LPlasmaPistolCharged,Weapons.LPowerFist),
        keywords = listOf("##Legionary##"),
        size = 32,
        leader = true,
        additionalRules = "**In the Eyes of the Gods:** Once during each of this operative’s activations, if it incapacitates an enemy operative, add 1 to its APL stat until the end of that activation. "
    )

    val Chosen = Operator(
        name = "Chosen",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 15,
        weapons = listOf(Weapons.LPlasmaPistolStandard,Weapons.LPlasmaPistolCharged,Weapons.LDaemonBlade),
        keywords = listOf("##Legionary##"),
        size = 32,
        leader = true,
        additionalRules = " **Daemonic Aura:** Whenever an enemy operative performs the **Fall Back** action while within control Range of this operative, you can use this rule. If you do, roll one **D6:** on a 3+, that enemy operative cannot perform that action during that activation or counteraction (the AP spent on it is refunded). \n\n" +
            " **Soul Gorge:** After this operative fights or retaliates, if it isn’t incapacitated, but it incapacitated an enemy operative during that sequence, it regains **D3+1** lost wounds. "
    )

    val GunnerMelta = Operator(
        name = "Gunner (Meltagun)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LMeltaGun,Weapons.LFists),
        keywords = listOf("##Legionary##"),
        size = 32,
    )

    val HeavyGunnerMissileLuncher = Operator(
        name = "Heavy Gunner (Missile luncher)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LMissileLuncherFrag,Weapons.LMissileLuncherKrak,Weapons.LFists),
        keywords = listOf("##Legionary##"),
        size = 32,
    )

    val Anoited = Operator(
        name = "Anoited",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LDaemonicClaw),
        keywords = listOf("##Legionary##"),
        size = 32,
        additionalRules = "**Unleash Daemon:** Once per battle, when this operative is activated, you can use this rule. If you do, until the end of the battle:" +
                "\n • This operative cannot perform the **Pick Up Marker** or mission actions (excluding Operate Hatch). If it’s carrying a marker, it must immediately perform the **Place Marker** action for 0AP (this takes precedence over all other rules)." +
                "\n • Normal and Critical Dmg of 4 or more inflicts 1 less damage on this operative. If this operative has the **NURGLE** keyword, you cannot reduce the damage of an attack dice by more than 1. In other words, you cannot use both rules to reduce Normal Dmg of 4 or more by 2." +
                "\n • Its daemonic claw has the xxCeaselessxx and xxLethal 5+xx weapon rules."
    )

    val Butcher = Operator(
        name = "Butcher",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LDoubleHandedChainAxe),
        keywords = listOf("##Legionary##"),
        size = 32,
        additionalRules = "**Devastating Onslaught:** " +
                "\n • Whenever this operative is fighting or retaliating, enemy operatives cannot assist." +
                "\n • At the end of each enemy operative’s activation or counteraction, you can select an enemy operative within 2\" of this operative. This operative can perform a free **Charge** action (you can change its order to **Engage** to do so), but it cannot move more than 2\" and must end that move within control Range of that selected operative."
    )

    val Shrivetalon = Operator(
        name = "Shrivetalon",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LFlensingBlades),
        keywords = listOf("##Legionary##"),
        size = 32,
        additionalRules = "**Vicious Reflexes:** Whenever this operative is retaliating, you resolve the first attack dice (i.e. defender instead of attacker). \n\n" +
                        "**Horrifying Dismemberment:** Whenever this operative incapacitates an enemy operative while fighting or retaliating, select one other enemy operative visible to and within 3\" of either this operative or the incapacitated enemy operative. Subtract 1 from that enemy operative’s APL stat until the end of its next activation. ",
        actions = listOf(Actions.LGrislyMark)
    )

    val IconBearerChainsword = Operator(
        name = "Icon Bearer (Chainsword)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LChainsword),
        keywords = listOf("##Legionary##"),
        size = 32,
        additionalRules = "**Icon Bearer:** Whenever determining control of a marker, treat this operative’s APL stat as 1 higher. Note this isn’t a change to its APL stat, so any changes are cumulative with this.  \n\n" +
                "**Favoured of the Dark Gods:** In the Ready step of each Strategy phase, if this operative controls an objective marker that isn’t tainted, that objective marker is tainted for the battle and you gain 1CP. Note that if any operative (including enemy operatives) has tainted an objective marker, you cannot taint that objective marker. "
    )

    val IconBearerBoltgun = Operator(
        name = "Icon Bearer (Bolt gun)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltgun,Weapons.LFists),
        keywords = listOf("##Legionary##"),
        size = 32,
        additionalRules = "**Icon Bearer:** Whenever determining control of a marker, treat this operative’s APL stat as 1 higher. Note this isn’t a change to its APL stat, so any changes are cumulative with this.  \n\n" +
                "**Favoured of the Dark Gods:** In the Ready step of each Strategy phase, if this operative controls an objective marker that isn’t tainted, that objective marker is tainted for the battle and you gain 1CP. Note that if any operative (including enemy operatives) has tainted an objective marker, you cannot taint that objective marker. "
    )

    val BaleFireAcolyte = Operator(
        name = "Bale Fire Acolyte",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LFireBlast,Weapons.LLifeSiphon,Weapons.LFellDagger),
        keywords = listOf("##Legionary##"),
        size = 32,
        additionalRules = "***Siphon Life:** When you select this weapon, you can use this rule. If you do, at the start of the Resolve Attack Dice step, you can select one friendly ##LEGIONARY## operative visible to and within 6\" of this operative. For each attack dice you resolve during that step that inflicts damage, that friendly operative regains 1 lost wound, or **D3** lost wounds if it was a critical success. You cannot use this weapon rule more than once per turning point."
    )

    val WarriorChainsword = Operator(
        name = "Warrior (Chainsword)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltPistol,Weapons.LChainsword),
        keywords = listOf("##Legionary##"),
        size = 32,
        specialist = false,
        additionalRules = "**Infernal pact:** Once per battle, when a friendly ##LEGIONARY## **WARRIOR** operative is activated, you can use this rule. If you do, change that operative’s **Marks of Chaos** keyword. "
    )

    val WarriorBoltgun = Operator(
        name = "Warrior (Bolt gun)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.LBoltgun,Weapons.LFists),
        keywords = listOf("##Legionary##"),
        size = 32,
        specialist = false,
        additionalRules = "**Infernal pact:** Once per battle, when a friendly ##LEGIONARY## **WARRIOR** operative is activated, you can use this rule. If you do, change that operative’s **Marks of Chaos** keyword. "
    )


    val operatorList = listOf(Champion,Chosen,GunnerMelta,HeavyGunnerMissileLuncher,Anoited,Butcher,Shrivetalon,IconBearerChainsword,IconBearerBoltgun,BaleFireAcolyte,WarriorBoltgun,WarriorChainsword)
}

object NemesiClawOperators{

    val Visionary = Operator(
        name = "Visionary",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 15,
        weapons = listOf(Weapons.NCPlasmaPistolStandard,Weapons.NCPlasmaPistolCharged,Weapons.NCNostramanChainsword),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
        leader = true,
        additionalRules = "**Prescience: PSYCHIC.** In the Ready step of each Strategy phase, you gain **D3** Prescience points. You can spend your Prescience points in the Firefight phase as follows (you cannot use each of the following rules more than once per turning point):\n\n" +
                "\n • **Foreboding: PSYCHIC.** Whenever it’s your turn to activate a friendly operative, you can spend 1 of your Prescience points to skip that activation.\n" +
                "\n • **Portent: PSYCHIC.** Whenever an attack dice inflicts Normal Dmg on this operative, you can spend 1 of your Prescience points to ignore that inflicted damage.\n\n" +
                "You cannot gain or spend your Prescience points if this operative is incapacitated.. ",
        actions = listOf(Actions.NCPremonition)

    )

    val Fearmonger = Operator(
        name = "Fearmonger",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCScopedBoltPistolShort,Weapons.NCScopedBoltPistolLong,Weapons.NCTerrochemVial,
            Weapons.NCTaintedBlade),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
        additionalRules = " ***Terrorchem:** In the Resolve Attack Dice step, if you inflict damage with any critical successes, the operative this weapon is being used against gains one of your Terrorchem tokens (if it doesn’t already have one). \n\n" +
                            " **Terrorchem Poison:** Whenever an operative that has one of your Terrorchem tokens is activated, inflict **D3** damage on it. ",
        actions = listOf(Actions.NCPoisonObjective)
    )

    val Screecher = Operator(
        name = "Screecher",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCLightningClaws),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
        additionalRules = "**Screecher:** Whenever an enemy operative within 3” of this operative is shooting, fighting, or retaliating, your opponent cannot **re-roll** their attack dice. \n\n" +
                " **Appetite for Cruelty:** Whenever this operative is fighting against a wounded enemy operative, this operative’s lightning claws have the xxLethal 4+xx weapon rule. "
    )

    val Skinthief = Operator(
        name = "Skinthief",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCBoltPistol,Weapons.NCNostramanChaingaive),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
        additionalRules = "**Flay Them Alive:** Once per turning point, when this operative incapacitates an enemy operative within its control Range , you can select one other enemy operative visible to and within 6” of either this operative or the incapacitated enemy operative. Until the start of the next turning point, that other enemy operative cannot control markers or perform the **Pick Up Marker** or mission actions.  \n\n" +
                " **Tyrant of the Skinning Pits:** Whenever this operative is fighting or retaliating, Normal and Critical Dmg of 3 or more inflicts 1 less damage on it.  "
    )

    val Ventrilokar = Operator(
        name = "Ventrilokar",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCBoltPistol,Weapons.NCChainsword),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
        additionalRules = " **Icon Bearer:** Whenever determining control of a marker, treat this operative’s APL stat as 1 higher. Note this isn’t a change to its APL stat, so any changes are cumulative with this. ",
        actions = listOf(Actions.DisconcertingMimicry)
    )

    val HeavyGunnerMissile = Operator(
        name = "Heavy Gunner (Missile luncher)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCMissileLuncherFrag,Weapons.NCMissileLuncherKrak,Weapons.NCFists),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
    )

    val GunnerMelta = Operator(
        name = "Gunner (Meltagun)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCMeltaGun,Weapons.NCFists),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
    )

    val GunnerPlasma = Operator(
        name = "Gunner (Plasma gun)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCPlasmaGunStandard,Weapons.NCPlasmaGunCharged,Weapons.NCFists),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
    )

    val GunnerFlamer = Operator(
        name = "Gunner (Flamer)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCFlamer,Weapons.NCFists),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
    )

    val WarriorBolter = Operator(
        name = "Warrior (Bolt gun)",
        APL = 3,
        move = 6,
        save = 3,
        wounds = 14,
        weapons = listOf(Weapons.NCBoltgun,Weapons.NCFists),
        keywords = listOf("##Nemesis Claw##"),
        size = 32,
        additionalRules = "**Cruel Tormenter:** Whenever this operative is shooting against, fighting against or retaliating against an injured enemy operative, or an enemy operative that has a Wounds stat of 7 or less, its weapons have the xxLethal 5+xx weapon rule. "
    )




    val operatorList = listOf(Visionary,Fearmonger,Screecher,Skinthief,Ventrilokar,HeavyGunnerMissile,GunnerMelta,GunnerPlasma,WarriorBolter)
}