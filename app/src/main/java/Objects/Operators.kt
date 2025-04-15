package Objects

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
        additionalRules = "**Adaptive Orders:** If this operative doesn’t issue a **GUARDSMAN ORDER** as a **STRATEGIC GAMBIT,** you can use the **Inspirational Leadership** firefight ploy for 0CP during this operative’s activation. "
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
        additionalRules = "***Concealed Position:** This operative can only use this weapon the first time it’s performing the **Shoot** action during the battle. "
    )

    val GunnerGranadeLuncher = Operator(
        name = "Death Korps Gunner (Granade Luncher)",
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
        weapons = listOf(Weapons.DKPlasmaGunStandard,Weapons.DKPlasmaPistolCharged, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
    )

    val GunnerMelta = Operator(
        name = "Death Korps Gunner (Melta gun)",
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
        additionalRules = "**Mine Layer:** This operative is carrying your Mine marker. It can perform the **Pick Up Marker** action on that marker, and whenever it performs the **Place Marker** action on that marker, it can immediately perform a free **Dash** action.\n\n " +
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
        additionalRules = "**The Emperor Protects:** Whenever an operative is shooting this operative, you can **re-roll** any of your defence dice. \n\n " +
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
        additionalRules = "**Medic:** The first time during each turning point that another friendly ##DEATH KORPS## operative would incapacitated while visible to and within 3\" of this operative, you can use this rule, providing neither this nor that operative is within control Range of an enemy operative. If you do, that friendly operative isn’t incapacitated and has 1 wound remaining and cannot be incapacitated for the remainder of the action. After that action, that friendly operative can then immediately perform a free **Dash** action, but must end that move within this operative’s control Range . Subtract 1 from this and that operative’s APL stats until the end of their next activations respectively, and if this rule was used during that friendly operative’s activation, that activation ends. You cannot use this rule if this operative is incapacitated, or if it’s a **Shoot** action and this operative would be a primary or secondary target.",
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
        additionalRules = "**Relay Orders:** Once per turning point, when this operative receives a **GUARDSMAN ORDER,** if it’s not within control Range of enemy operatives, it can relay that order. Whenever an order is relayed, all friendly ##DEATH KORPS## operatives in the killzone receive that order, then subtract 1 from this operative’s APL stat until the end of its next activation. ",
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
        additionalRules = "**Group Activation:** Whenever this operative is expended, you must then activate one other ready friendly ##DEATH KORPS## **TROOPER** operative (if able) before your opponent activates. When that other operative is expended, your opponent then activates as normal (in other words, you cannot activate more than two operatives in succession with this rule). "
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
        leader = true
    )

    val Medic = Operator(
        name = "Kasrkin Medic",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasgun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
    )

    val DemoTrooper = Operator(
        name = "Kasrkin Demo-Trooper",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasPistol, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
    )

    val GunnerGranadeLuncher = Operator(
        name = "Kasrkin Gunner (Granade Luncher)",
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
        size = 28
    )

    val Sharpshooter = Operator(
        name = "Kasrkin Sharpshooter",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotMarksmanRifleConcealed,Weapons.KSHotShotMarksmanRifleMobile,Weapons.KSHotShotMarksmanRifleStationary, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
    )

    val VoxTrooper = Operator(
        name = "Kasrkin Vox-Trooper",
        APL = 2,
        move = 6,
        save = 4,
        wounds = 8,
        weapons = listOf(Weapons.KSHotShotLasgun, Weapons.KSGunButt),
        keywords = listOf("##KASRKIN##"),
        size = 28
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
        specialist = false
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
        leader = true
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
        size = 40
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