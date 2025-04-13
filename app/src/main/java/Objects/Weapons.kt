package Objects

import kotlin.String

object Weapons
{
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
        name = "Plasma Pistol (Supercharge)",
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

}