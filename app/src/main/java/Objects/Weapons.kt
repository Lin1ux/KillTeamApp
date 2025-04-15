package Objects

import kotlin.String

object Weapons
{
    //Universal
    val FragGranade = Weapon(
        name = "Frag Granade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Blast2, WeaponRules.Saturate)
    )

    val FragGranadeImproved = Weapon(
        name = "Frag Granade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 3,
        Dmg = 2,
        CritDmg = 4,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Blast2, WeaponRules.Saturate)
    )

    val KrakGranade = Weapon(
        name = "Krak Granade",
        type = WeaponType.RANGED,
        Atk = 4,
        Hit = 4,
        Dmg = 4,
        CritDmg = 5,
        WeaponRulesList = listOf(WeaponRules.Range6, WeaponRules.Piercieng1, WeaponRules.Saturate)
    )

    val KrakGranadeImproved = Weapon(
        name = "Krak Granade",
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
}