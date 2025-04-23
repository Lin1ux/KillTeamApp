package com.example.killteam.Objects

object Weapons
{
    //Universal
    val FragGranade = Weapon(
        name = "Frag Grenade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Blast2, WeaponRules.Saturate)
    )

    val FragGranadeImproved = Weapon(
        name = "Frag Grenade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Blast2, WeaponRules.Saturate)
    )

    val KrakGranade = Weapon(
        name = "Krak Grenade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Piercieng1, WeaponRules.Saturate)
    )

    val KrakGranadeImproved = Weapon(
        name = "Krak Grenade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Piercieng1, WeaponRules.Saturate)
    )

    //Death Korps
    val DKLasgun = Weapon(
        name = "Lasgun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 2,
        CritDmg = 3,
        WeaponRulesList = listOf()
    )

    val DKBayonet = Weapon(
        name = "Bayonet",
        type = WeaponType.MELEE,
        Atk = 3,
        Hit = 4,
        Dmg = 2,
        CritDmg = 3,
        WeaponRulesList = listOf()
    )

    val DKHandAxe = Weapon(
        name = "Hand Axe",
        type = WeaponType.MELEE,
        Atk = 3,
        Hit = 4,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val DKPlasmaPistolStandard = Weapon(
        name = "Plasma Pistol (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1)
    )

    val DKPlasmaPistolCharged = Weapon(
        name = "Plasma Pistol (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1, WeaponRules.Hot,
            WeaponRules.Lethal5)
    )

    val DKPowerSword = Weapon(
        name = "Power Sword",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Lethal5)
    )

    val DKLongLasConcealed = Weapon(
        name = " Long-las (Concealed)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3, WeaponRules.Heavy, WeaponRules.Silent,
            WeaponRules.ConcealedPosition)
    )

    val DKLongLasMobile = Weapon(
        name = " Long-las (Mobile)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val DKLongLasStationary = Weapon(
        name = " Long-las (Stationary)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3, WeaponRules.Heavy)
    )

    val DKGranadeLuncherFrag = Weapon(
        name = " Grenade launcher (Frag)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Blast2)
    )

    val DKGranadeLuncherKrak = Weapon(
        name = " Grenade launcher (Krak)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Piercieng1)
    )

    val DKPlasmaGunStandard = Weapon(
        name = "Plasma gun (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 4,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Piercieng1)
    )

    val DKPlasmaGunCharged = Weapon(
        name = "Plasma gun (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 5,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Piercieng1, WeaponRules.Hot,WeaponRules.Lethal5)
    )

    val DKMeltaGun = Weapon(
        name = " Melta gun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 6,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Devastating4,WeaponRules.Piercieng2)
    )

    val DKRemoteMine = Weapon(
        name = "Remote mine",
        type = WeaponType.RANGED,
        Atk = 2,
        Hit = 4,
        Dmg = 5,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.HeavyDash,WeaponRules.Limited1, WeaponRules.Piercieng1,
            WeaponRules.Silent, WeaponRules.Detonate)
    )

    val DKMortarBarage = Weapon(
        name = "Mortar barrage",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Blast2, WeaponRules.HeavyDash, WeaponRules.Silent)
    )

    //Kasrkin Squad
    val KSHotShotLasgun = Weapon(
        name = "Hot-shot Lasgun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val KSGunButt = Weapon(
        name = "Gun butt",
        type = WeaponType.MELEE,
        Atk = 3,
        Hit = 4,
        Dmg = 2,
        CritDmg = 3,
        WeaponRulesList = listOf()
    )

    val KSCombatDaggers = Weapon(
        name = "Combat Dagger",
        type = WeaponType.MELEE,
        Atk = 3,
        Hit = 4,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val KSPlasmaPistolStandard = Weapon(
        name = "Plasma Pistol (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1)
    )

    val KSPlasmaPistolCharged = Weapon(
        name = "Plasma Pistol (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1, WeaponRules.Hot,
            WeaponRules.Lethal5)
    )

    val KSChainsword = Weapon(
        name = "Chainsword",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf()
    )

    val KSHotShotLasPistol = Weapon(
        name = "Hot-shot laspistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range8)
    )

    val KSHotShotMarksmanRifleConcealed = Weapon(
        name = "Hot-shot marksman rifle (Concealed)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3, WeaponRules.Heavy, WeaponRules.Silent,
            WeaponRules.ConcealedPosition)
    )

    val KSHotShotMarksmanRifleMobile = Weapon(
        name = "Hot-shot marksman rifle (Mobile)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val KSHotShotMarksmanRifleStationary = Weapon(
        name = "Hot-shot marksman rifle (Stationary)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3, WeaponRules.Heavy)
    )

    val KSGranadeLuncherFrag = Weapon(
        name = " Grenade launcher (Frag)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Blast2)
    )

    val KSGranadeLuncherKrak = Weapon(
        name = " Grenade launcher (Krak)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Piercieng1)
    )

    val KSPlasmaGunStandard = Weapon(
        name = "Plasma gun (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Piercieng1)
    )

    val KSPlasmaGunCharged = Weapon(
        name = "Plasma gun (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 5,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Piercieng1, WeaponRules.Hot,WeaponRules.Lethal5)
    )

    val KSMeltaGun = Weapon(
        name = " Melta gun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 6,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Devastating4,WeaponRules.Piercieng2)
    )

    //Angels of Death
    val ADAutoBoltRifle = Weapon(
        name = "Auto bolt rifle",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Torrent1)
    )

    val ADBoltRifle = Weapon(
        name = "Bolt rifle",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.PiercingCrits1)
    )

    val ADStalkerBoltRifleMobile = Weapon(
        name = "Stalker bolt rifle (Mobile)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val ADStalkerBoltRifleHeavy = Weapon(
        name = "Stalker bolt rifle (Heavy)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.PiercingCrits1,WeaponRules.Lethal5,WeaponRules.HeavyDash)
    )

    val ADHeavyBoltPistol = Weapon(
        name = "Heavy bolt pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range8,WeaponRules.PiercingCrits1)
    )

    val ADBoltPistol = Weapon(
        name = "Bolt pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range8)
    )

    val ADFists = Weapon(
        name = "Fists",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val ADChainsword = Weapon(
        name = "Chainsword",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf()
    )

    val ADPlasmaPistolStandard = Weapon(
        name = "Plasma pistol (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1)
    )

    val ADPlasmaPistolCharged = Weapon(
        name = "Plasma pistol (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1, WeaponRules.Hot,
            WeaponRules.Lethal5)
    )

    val ADPowerFist = Weapon(
        name = "Power fist",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 5,
        CritDmg = 7,
        WeaponRulesList = listOf(WeaponRules.Brutal)
    )

    val ADGranadeLuncherFrag = Weapon(
        name = "Auxiliary grenade launcher (Frag)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Blast2)
    )

    val ADGranadeLuncherKrak = Weapon(
        name = "Auxiliary grenade launcher (Krak)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Piercieng1)
    )

    val ADBoltSniperRifleExecutioner = Weapon(
        name = "Bolt sniper rifle (Executioner)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.HeavyDash, WeaponRules.Saturate, WeaponRules.SeekLight,WeaponRules.Silent)
    )

    val ADBoltSniperRifleHyperfrag = Weapon(
        name = "Bolt sniper rifle (Hyperfrag)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Blast1,WeaponRules.HeavyDash,WeaponRules.Silent)
    )

    val ADBoltSniperRifleMortis = Weapon(
        name = "Bolt sniper rifle (Mortis)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3,WeaponRules.HeavyDash,WeaponRules.Piercieng1,WeaponRules.Silent)
    )

    val ADHeavyBolterSweeping = Weapon(
        name = "Heavy bolter (Sweeping)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.PiercingCrits1,WeaponRules.Torrent1)
    )

    val ADHeavyBolterFocused = Weapon(
        name = "Heavy bolter (Focused)",
        type = WeaponType.RANGED,
        Atk = 5,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.PiercingCrits1)
    )

    //voidscared Corsairs
    val VCShurikenPistol = Weapon(
        name = "Shuriken pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range8,WeaponRules.Rending)
    )

    val VCPowerWeapon = Weapon(
        name = "Power Sword",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Lethal5)
    )

    val VCFists = Weapon(
        name = "Fists",
        type = WeaponType.MELEE,
        Atk = 3,
        Hit = 3,
        Dmg = 2,
        CritDmg = 3,
        WeaponRulesList = listOf()
    )

    val VCNeuroDisruptor = Weapon(
        name = "Neuro disruptor",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8,WeaponRules.Piercieng1,WeaponRules.Stun)
    )

    val VCShredder = Weapon(
        name = "Shredder",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Rending,WeaponRules.Torrent2)
    )

    val VCBlaster = Weapon(
        name = "Blaster",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Piercieng2)
    )

    val VCWraithCannon = Weapon(
        name = "Wraithcannon",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 6,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating4,WeaponRules.HeavyDash,WeaponRules.Piercieng2)
    )

    val VCFusionPistol = Weapon(
        name = "Fusion pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 5,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Range3,WeaponRules.Devastating3,WeaponRules.Piercieng2)
    )

    val VCFalchou = Weapon(
        name = "Falchou",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 1,
        CritDmg = 2,
        WeaponRulesList = listOf(WeaponRules.Rending,WeaponRules.Saturate,WeaponRules.SeekLight,WeaponRules.Silent)
    )

    val VCThrowingBlades = Weapon(
        name = "Throwing blades",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6,WeaponRules.Silent)
    )

    val VCHekatariiBlades = Weapon(
        name = "Hekatarii blades",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Ceaseless,WeaponRules.Lethal5)
    )

    val VCDualPowerWeapons = Weapon(
        name = "Dual power weapons",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Ceaseless,WeaponRules.Lethal5)
    )

    val VCRangerLongRifleStationary = Weapon(
        name = "Ranger long rifle (Stationary)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3, WeaponRules.Heavy,WeaponRules.Silent)
    )

    val VCRangerLongRifleMobile = Weapon(
        name = "Ranger long rifle (Mobile)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val VCFreezingGrasp = Weapon(
        name = "Freezing grasp",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 1,
        CritDmg = 2,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Severe, WeaponRules.Silent,WeaponRules.Stun)
    )

    val VCLightningStrike = Weapon(
        name = "Lightning strike",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Devastating22)
    )

    val VCWitchStaff = Weapon(
        name = "Witch staff",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Shock)
    )

    //Plague Marines
    val PMPlasmaPistolStandard = Weapon(
        name = "Plasma pistol (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1)
    )

    val PMPlasmaPistolCharged = Weapon(
        name = "Plasma pistol (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1, WeaponRules.Hot,
            WeaponRules.Lethal5)
    )

    val PMBoltgun = Weapon(
        name = "Boltgun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val PMBoltgunToxic = Weapon(
        name = "Boltgun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Toxic)
    )

    val PMPlagueSword = Weapon(
        name = "Plague sword",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Severe,WeaponRules.Toxic,WeaponRules.Poison)
    )

    val PMEntropy = Weapon(
        name = "Entropy",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 7,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Range7, WeaponRules.Saturate,WeaponRules.Severe,WeaponRules.Poison)
    )

    val PMPlagueWind = Weapon(
        name = "Plague wind",
        type = WeaponType.RANGED,
        Atk = 6,
        Hit = 3,
        Dmg = 2,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Psychic, WeaponRules.Saturate,WeaponRules.Severe,WeaponRules.Torrent1,WeaponRules.Poison)
    )

    val PMCorruptedStuff = Weapon(
        name = "Corrupted Stuff",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Severe,WeaponRules.Shock,WeaponRules.Stun,WeaponRules.Poison)
    )

    val PMFlailOfCorruption = Weapon(
        name = "Flail of corruption",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Brutal,WeaponRules.Severe,WeaponRules.Shock,WeaponRules.Poison)
    )

    val PMPlagueSpewer = Weapon(
        name = "Plague spewer",
        type = WeaponType.RANGED,
        Atk = 5,
        Hit = 2,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Range7,WeaponRules.Saturate,WeaponRules.Severe,WeaponRules.Torrent2,WeaponRules.Poison)
    )

    val PMFists = Weapon(
        name = "Fists",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val PMBoltPistol = Weapon(
        name = "Bolt pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range8)
    )

    val PMPlagueKnife4 = Weapon(
        name = "Plague knife",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Severe,WeaponRules.Toxic)
    )

    val PMPlagueKnife5 = Weapon(
        name = "Plague knife",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Severe,WeaponRules.Toxic)
    )

    val PMBlightGranade = Weapon(
        name = "Blight grenade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Blast2, WeaponRules.Saturate,
            WeaponRules.Severe,WeaponRules.Poison)
    )

    val PMBlightGranadeImproved = Weapon(
        name = "Blight grenade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Blast2, WeaponRules.Saturate,
            WeaponRules.Severe,WeaponRules.Poison)
    )

    //Hunter Clade (admech)

    val HCGalvanicRifle = Weapon(
        name = "Galvanic rifle",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.HeavyReposition, WeaponRules.PiercingCrits1)
    )

    val HCGunButt = Weapon(
        name = "Gun Butt",
        type = WeaponType.MELEE,
        Atk = 3,
        Hit = 4,
        Dmg = 2,
        CritDmg = 3,
        WeaponRulesList = listOf()
    )

    val HCArcPistol = Weapon(
        name = "Arc pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1,WeaponRules.Stun)
    )

    val HCArcMaul = Weapon(
        name = "Arc maul",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 4,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Shock)
    )

    val HCArcRifle = Weapon(
        name = "Arc rifle",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Piercieng1,WeaponRules.Stun)
    )

    val HCPlasmaCaliverStandard = Weapon(
        name = "Plasma caliver (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Piercieng1)
    )

    val HCPlasmaCaliverCharged = Weapon(
        name = "Plasma caliver (Supercharged)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 5,
        CritDmg = 6,
        WeaponRulesList = listOf(WeaponRules.Hot,WeaponRules.Lethal5,WeaponRules.Piercieng1)
    )

    val HCTransuanicArquebuseMobile = Weapon(
        name = "Transuranic arquebus (Mobile)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating2,WeaponRules.HeavyDash,WeaponRules.Piercieng1)
    )

    val HCTransuanicArquebuseStationary = Weapon(
        name = "Transuranic arquebus (Stationary)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 2,
        Dmg = 4,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Devastating3,WeaponRules.Heavy,WeaponRules.Piercieng1,WeaponRules.Severe)
    )

    val LPlasmaPistolStandard = Weapon(
        name = "Plasma pistol (Standard)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1)
    )

    val LPlasmaPistolCharged = Weapon(
        name = "Plasma pistol (Supercharge)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range8, WeaponRules.Piercieng1, WeaponRules.Hot,
            WeaponRules.Lethal5)
    )

    val LPowerFist = Weapon(
        name = "Power fist",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 4,
        Dmg = 5,
        CritDmg = 7,
        WeaponRulesList = listOf(WeaponRules.Brutal)
    )

    val LDaemonBlade = Weapon(
        name = "Daemon blade",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 4,
        CritDmg = 7,
        WeaponRulesList = listOf(WeaponRules.Lethal5)
    )

    val LMeltaGun = Weapon(
        name = " Melta gun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 6,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Devastating4,WeaponRules.Piercieng2)
    )

    val LMissileLuncherFrag = Weapon(
        name = "Missile launcher (Frag)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Blast2, WeaponRules.HeavyReposition)
    )

    val LMissileLuncherKrak = Weapon(
        name = "Missile launcher (Krak)",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 5,
        CritDmg = 7,
        WeaponRulesList = listOf(WeaponRules.Piercieng1, WeaponRules.HeavyReposition)
    )

    val LBoltPistol = Weapon(
        name = "Bolt pistol",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range8)
    )

    val LDaemonicClaw = Weapon(
        name = "Daemonic claw",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Rending)
    )

    val LDoubleHandedChainAxe = Weapon(
        name = "Double-handed chain axe",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 4,
        Dmg = 5,
        CritDmg = 7,
        WeaponRulesList = listOf(WeaponRules.Brutal)
    )

    val LFlensingBlades = Weapon(
        name = "Flensing blades",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Lethal5)
    )

    val LBoltgun = Weapon(
        name = "Boltgun",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val LChainsword = Weapon(
        name = "Chainsword",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf()
    )

    val LFists = Weapon(
        name = "Fists",
        type = WeaponType.MELEE,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val LMaleficBlades = Weapon(
        name = "MaleficBlades",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf()
    )

    val LFireBlast = Weapon(
        name = "Fire Blast",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 3,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Blast2, WeaponRules.Devastating11,
            WeaponRules.Saturate)
    )

    val LLifeSiphon = Weapon(
        name = "Life Siphon",
        type = WeaponType.RANGED,
        Atk = 5,
        Hit = 3,
        Dmg = 3,
        CritDmg = 3,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Saturate, WeaponRules.LifeSyphon)
    )

    val LFellDagger = Weapon(
        name = "Fell dagger",
        type = WeaponType.MELEE,
        Atk = 5,
        Hit = 3,
        Dmg = 3,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Psychic,WeaponRules.Rending,WeaponRules.LifeSyphon)
    )
}