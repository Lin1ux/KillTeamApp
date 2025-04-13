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
        leader = true
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
    )

    val Trooper = Operator(
        name = "Death Korps Trooper",
        APL = 2,
        move = 6,
        save = 5,
        wounds = 7,
        weapons = listOf(Weapons.DKLasgun,Weapons.DKMortarBarage, Weapons.DKBayonet),
        keywords = listOf("##DEATH KORPS##"),
        size = 25,
        specialist = false
    )

    val operatorList = listOf(Watchmaster,Sniper,GunnerGranadeLuncher,GunnerPlasma,GunnerMelta,Sapper,Zealot,Medic,VoxOperator,Spotter,Trooper)

}