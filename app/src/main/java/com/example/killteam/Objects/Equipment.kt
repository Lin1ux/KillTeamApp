package com.example.killteam.Objects

import com.example.killteam.Objects.PlagueMarinesEQ.PlagueBells
import com.example.killteam.Objects.PlagueMarinesEQ.PlagueGrenades
import com.example.killteam.Objects.PlagueMarinesEQ.PlagueRounds
import com.example.killteam.Objects.PlagueMarinesEQ.PoisonVents
import kotlin.collections.listOf

object UniversalEquipment
{
    val AmmoCache = Equipment(
        name = "1x Ammo Cache",
        description = "Before the battle, you can set up one of your Ammo Cache markers wholly within your territory. Friendly operatives can perform the following mission action during the battle.",
        actions = listOf(Actions.AmmoResupply)
    )

    val CommsDevice = Equipment(
        name = "1x Comms Device",
        description = "Before the battle, you can set up one of your Comms Device markers wholly within your territory. While a friendly operative controls this marker, add 3\" to the distance requirements of its **SUPPORT** rules that refer to friendly operatives (e.g., \"select a friendly operative within 6\" would be \"within 9\" instead\"). Note you cannot benefit from your opponent’s Comms Device markers."
    )

    val Mines = Equipment(
        name = "1x Mines",
        description = "Before the battle, you can set up up to one of your Mines markers wholly within your territory and more than 2\" from other markers, access points and accesible terrain. The first time that marker is within an operative’s control range, remove that marker and inflict D3+3 damage on that operative."
    )

    val RazorWire = Equipment(
        name = "1x Razor Wire",
        description = "Razor wire is Exposed and Obstructing terrain. Before the battle, you can set it up wholly within your territory, on the killzone floor and more than 2\" from all other equipment terrain features, access points and accesible terrain.\n" +
                "\n **Obstructing:** Whenever an operative would cross over this terrain feature within 1\" of it, treat the distance as an additional 1\"."
    )

    val LightBarricades = Equipment(
        name = "2x Light Barricades",
        description = "Light barricades are Light terrain, except the feet, which are Insignificant and Exposed. Before the battle, you can set up any number of them wholly within your territory, on the killzone floor and more than 2\" from other equipment terrain features, acess points and accesible terrain."
    )

    val HeavyBarricade = Equipment(
        name = "1x Heavy Barricade",
        description = "A heavy barricade is Heavy terrain. Before the battle, you can set it up wholly within 4\" of your drop zone, on the killzone floor and more than 2\" from other equipment terrain features, access points and accesible terrain."
    )

    val Ladders = Equipment(
        name = "2x Ladders",
        description = "Ladders are Exposed terrain. Before the battle, you can set up any number of them as follows:\n" +
                "\n • Wholly within your territory.\n" +
                "\n • Upright against terrain that has a height of at least 2\".\n" +
                "\n • More than 2\" from other equipment terrain features.\n" +
                "\n • More than 1\" from doors and access points.\n" +
                "\nAdditionally, an operative can either move thorugh ladders as if they aren't there (but cannot finish on them), or climb them. Once per action, whenever an operative is climbing this terrain feature, treat the vertical distance as 1\". Note that if an operative then continues climbing another terrain feature during that action (including another ladder), that distance is determined as normal."
    )

    val PortableBarricade = Equipment(
        name = "1x Portable Barricade",
        description = "A portable barricade is Light, Protective and Portable terrain. Before the battle, you can set it up wholly within your territory, on the killzone floor and more than 2\" from all other equipment terrain features, acess points and accesible terrain.\n" +
                "\n **Protective:** While an operative is in Cover behind this terrain feature, improve its Save stat by 1 (to a maximum of 2+).\n" +
                "\n **Portable:** This terrain feature only provides cover while an operative is connected to it and if the shield is intervening (ignore its feet). Operatives connected to the inside of it can perform the following action during the battle.",
        actions = listOf(Actions.MoveWithBarricade)
    )

    val UtilityGrenades = Equipment(
        name = "Utility Grenades",
        description = "When you select this equipment, select two utility grenades (2 smoke, 2 stun, or 1 smoke and 1 stun). Each selection is a unique action your operatives can perform, but your kill team can only use that weapon a total number of times during the battle equal to your selection.",
        actions = listOf(Actions.SmokeGrenade, Actions.StunGrenade)
    )

    val ExplosiveGrenades = Equipment(
        name = "Explosive Grenades",
        description = "When you select this equipment, select two explosive grenades (2 frag, 2 krak, or 1 frag and 1 krak). Each selection is a unique weapon your operatives can use, but your kill team can only use that weapon a total number of times during the battle equal to your selection.",
        weapons = listOf(Weapons.FragGranade, Weapons.KrakGranade)
    )

    val equipmentList = listOf(AmmoCache,CommsDevice,Mines,RazorWire,LightBarricades,HeavyBarricade,Ladders,PortableBarricade,UtilityGrenades,ExplosiveGrenades)
}

object DeathKorpsEQ
{
    val HandAxe = Equipment(
        name = "Hand Axe",
        description = "Friendly ##DEATH KORPS## operatives have the following melee weapon: ",
        weapons = listOf(Weapons.DKHandAxe)
    )

    val Chronometer = Equipment(
        name = "Chronometer",
        description = "**STRATEGIC GAMBIT** once per battle, in the first or second turning point. Each friendly ##DEATH KORPS## operative wholly within your territory can immediately perform a free Dash action in an order of your choice but must end that move closer to an opponent’s drop zone or killzone edge.\n" +
                "\nYou cannot use this **STRATEGIC GAMBIT** and the Regroup strategy ploy during the same turning point."
    )

    val CommBeads = Equipment(
        name = "Comm Beads",
        description = "Whenever a friendly ##DEATH KORPS## **WATCHMASTER** or **CONFIDANT** operative issues a **GUARDSMAN ORDER,** you can use this rule. If you do, instead of each friendly ##DEATH KORPS## operative within 6” receiving the order, you can select one friendly ##DEATH KORPS## operative to receive that order"
    )

    val GasBombardment = Equipment(
        name = "Gas Bombardment",
        description = "**STRATEGIC GAMBIT** once per battle. Place your Gas marker in the killzone; it cannot be placed underneath Vantage terrain. Whenever an operative is within 3” of that marker, subtract 1 from its APL stat. In the Ready step of the next Strategy phase, remove that marker. Note that an operative’s APL stat is only changed while it’s within 3\" of that marker. If it moves more than 3\" from that marker, its APL stat is no longer changed by this rule."
    )

    val equipmentList = listOf(HandAxe,Chronometer,CommBeads,GasBombardment) + UniversalEquipment.equipmentList
}

object KasrkinEQ
{
    val Foregrip = Equipment(
        name = "Foregrip",
        description = "Whenever a friendly ##KASRKIN## operative is shooting an operative within 3\" of it, ranged weapons on its datacard (excluding weapons that include ‘pistol’ in their name, e.g. hot-shot laspistol, all profiles of a plasma pistol, etc.) have the xxAccurate 1xx weapon rule."
    )

    val LongRangeScope = Equipment(
        name = "Long-Range Scope",
        description = "Whenever a friendly ##KASRKIN## operative is shooting an operative more than 6\" from it, that friendly operative’s hot-shot weapons have the xxSaturatexx weapon rule."
    )

    val RelicsOfCadia = Equipment(
        name = "Relics of Cadia",
        description = "Once per turning point, when a friendly ##KASRKIN## operative is shooting, if you roll two or more fails, you can discard one of them to retain another as a normal success instead. "
    )

    val CombatDaggers = Equipment(
        name = "Combat Daggers",
        description = "Friendly ##KASRKIN## operatives have the following melee weapon:",
        weapons = listOf(Weapons.KSCombatDaggers)
    )

    val equipmentList = listOf(Foregrip,LongRangeScope,RelicsOfCadia,CombatDaggers) + UniversalEquipment.equipmentList
}

object AngelsOfDeathEQ
{
    val PuritySeals = Equipment(
        name = "Purity Seals",
        description = "Once per turning point, when a friendly ##ANGEL OF DEATH## operative is shooting, fighting or retaliating, if you roll two or more fails, you can discard one of them to retain another as a normal success instead."
    )

    val TiltingShields = Equipment(
        name = "Tilting Shields",
        description = "Once per turning point, when a friendly ##ANGEL OF DEATH## operative is fighting or retaliating, after your opponent rolls their attack dice, you can use this rule. If you do, your opponent cannot retain attack dice results of less than 6 as critical successes during that sequence (e.g., as a result of the Lethal, Rending, or Severe weapon rules)."
    )

    val ChapterReliquaries = Equipment(
        name = "Chapter Reliquaries",
        description = "You can use the **Wrath of Vengeance** firefight ploy for OCP if the specified friendly operative has an **Engage** order."
    )

    val Auspex = Equipment(
        name = "Auspex",
        description = "Once per turning point, when a friendly ##ANGEL OF DEATH## operative performs the **Shoot** action and you’re selecting a valid target, you can use this rule. If you do, until the end of the activation/counteraction, enemy operatives within 8\" of that friendly operative cannot be **obscured.**"
    )

    val equipmentList = listOf(PuritySeals,TiltingShields,ChapterReliquaries,Auspex) + UniversalEquipment.equipmentList
}

object VoidscaredCorsairsEQ
{
    val DiuturnalMantle = Equipment(
        name = "Diuturnal Mantle",
        description = "Whenever an operative is shooting a friendly ##CORSAIR VOIDSCARRED## operative, if the ranged weapon in that sequence has the **Blast** or **Torrent** weapon rule, you can re-roll one of your defense dice. In addition, friendly ##CORSAIR VOIDSCARRED## operatives aren’t affected by the **X\" Devastating X** weapon rule (i.e. Devastating with a distance) unless they are the target during that sequence."
    )

    val RunesOfGuidance = Equipment(
        name = "Runes of Guidance",
        description = "Once per turning point, when a friendly **VOIDSCARRED WAY SEEKER** or **VOIDSCARRED SOUL WEAVER** operative is performing a **PSYCHIC** action (excluding Warp Fold), you can use this rule. If you do, until the end of that action, add 3\" to its distance requirement. \n\nNote this has no effect on **PSYCHIC** weapons (e.g., the Devastating distance requirement of lightning strike)."
    )

    val Mistfield = Equipment(
        name = "Mistfield",
        description = "Once per turning point, when an operative is shooting a friendly ##CORSAIR VOIDSCARRED## operative more than 3\" away, during the Roll Defense Dice step, worsen the x of the **Piercing** weapon rule by 1 (if any) until the end of that sequence. This may cause **Piercing 1** to be ignored."
    )

    val StarCharts = Equipment(
        name = "Star Charts",
        description = "**STRATEGIC GAMBIT:** Roll one **D3:** If the result is higher than the number of the current turning point, you gain 1CP and cannot use this **STRATEGIC GAMBIT** for the rest of the battle."
    )

    val equipmentList = listOf(DiuturnalMantle,RunesOfGuidance,Mistfield,StarCharts) + UniversalEquipment.equipmentList
}

object PlagueMarinesEQ
{
    val PoisonVents = Equipment(
        name = "Poison Vents",
        description = "Whenever an enemy operative that has one of your Poison tokens is activated within 3\" of a friendly ##PLAGUE MARINE## operative, inflict **D3** damage on it (instead of the normal 1)."
    )

    val PlagueRounds = Equipment(
        name = "Plague Rounds",
        description = "Friendly ##PLAGUE MARINE## operatives’ boltguns and bolt pistols have the **Poison** and **Severe** weapon rules."
    )

    val PlagueBells = Equipment(
        name = "Plague Bells",
        description = "You can ignore any changes to the stats of friendly ##PLAGUE MARINE## operatives from being injured."
    )

    val PlagueGrenades = Equipment(
        name = "Plague Grenades",
        description = "Friendly ##PLAGUE MARINE## operatives have the following ranged weapon (you cannot select it for use more than twice during the battle): ",
        weapons = listOf(Weapons.PMBlightGranade)
    )

    val equipmentList = listOf(PoisonVents,PlagueRounds,PlagueBells,PlagueGrenades) + UniversalEquipment.equipmentList
}

object HunterCladeEQ
{
    val RadBombardment = Equipment(
        name = "Rad Bombardment",
        description = "Once per battle **STRATEGIC GAMBIT** in any turning point after the first. Select one objective marker or your opponent’s drop zone. Roll one D6 for each enemy operative within control range of that selected objective marker or within that drop zone, and subtract 1 from that enemy operative’s APL stat until the end of its next activation on a 4+; on a 6, also inflict **D3** damage on it (roll separately for each)."
    )

    val RefractorField = Equipment(
        name = "Refractor Field",
        description = "Once per turning point, when an operative is shooting a friendly ##HUNTER CLADE## operative, at the start of the Roll Defence Dice step, you can use this rule. If you do, worsen the x of the xxPiercingxx weapon rule by 1 (if any) until the end of that sequence. Note that xxPiercing 1xx would therefore be ignored."
    )

    val ExtremisMindLink = Equipment(
        name = "Extremis Mind-Link",
        description = "Once per battle, you can use the **Control Edict** firefight ploy for 0CP, but instead of activating the selected friendly operatives in succession, activate them at the same time. Complete their activations action by action in any order."
    )

    val RedundancySystems = Equipment(
        name = "Redundancy Systems",
        description = "Once per turning point, when a friendly ##HUNTER CLADE## operative is activated, if it’s not within control range of enemy operatives, you can use this rule. If you do, that friendly operative regains up to **D3+2** lost wounds."
    )

    val equipmentList = listOf(RadBombardment,RefractorField,ExtremisMindLink,RedundancySystems) + UniversalEquipment.equipmentList
}

object LegionairesEQ
{
    val WardedArmour = Equipment(
        name = "Warded Armour",
        description = "**Strategic Gambit.** Select one friendly ##LEGIONARY## operative. Until the Ready step of the next Strategy phase, change that operative’s Save stat to 2+."
    )

    val TaintedRounds = Equipment(
        name = "Tainted Rounds",
        description = "Once per turning point, when a friendly ##LEGIONARY## operative is performing the **Shoot** action and you select a bolt pistol or boltgun, you can use this rule.\n\n" +
                    "If you do, until the end of that action, that weapon has the xxRendingxx weapon rule."
    )

    val ChaosTalismans = Equipment(
        name = "Chaos Talismans",
        description = "**Strategic Gambit.** Select one Marks of Chaos keyword. Once during each of their activations, when a friendly ##LEGIONARY## operative that has that keyword is shooting, fighting or retaliating, if you roll two or more fails, you can inflict **D3** damage on that friendly operative to discard one of them and retain the other as a normal success instead.\n\n" +
                "Note that if it’s the **Shoot** action and that damage incapacitates that friendly operative, the action doesn’t end (continue the sequence with your successful attack dice)."
    )

    val MaleficBlades = Equipment(
        name = "Malefic Blades",
        description = "Friendly ##LEGIONARY## operatives have the following melee weapon for the battle:",
        weapons = listOf(Weapons.LMaleficBlades)
    )

    val equipmentList = listOf(WardedArmour,TaintedRounds,ChaosTalismans,MaleficBlades) + UniversalEquipment.equipmentList
}

object NemesisClawEQ
{
    val FlayedSkin = Equipment(
        name = "Flayed Skin",
        description = "**Flayed Skin:** Whenever an enemy operative is shooting against, fighting against or retaliating against a friendly ##NEMESIS CLAW## operative within 2\" of it, your opponent cannot **re-roll** their attack dice results of 1."
    )

    val ChainSnare = Equipment(
        name = "Chain Snare",
        description = "Whenever an enemy operative would perform the **Fall Back** action while within control range of a friendly ##NEMESIS CLAW## operative, if no other enemy operatives are within that friendly operative’s control range, you can use this rule. If you do, roll two **D6,** or one **D6** if that enemy operative has a higher Wounds stat than that friendly operative. If any result is a 4+, that enemy operative cannot perform that action during that activation or counteraction (no AP are spent on it)."
    )

    val GrislyTrophy = Equipment(
        name = "Grisly Trophy",
        description = "**Grisly Trophy:** Once per battle, when a friendly ##NEMESIS CLAW## operative incapacitates an enemy operative within 2\" of it, it gains one of your Grisly Trophy tokens (if it doesn’t already have one). Whenever a friendly ##NEMESIS CLAW## operative that has one of your Grisly Trophy tokens is visible to and within 2\" of an enemy operative, subtract 1 from the Atk stat of that enemy operative’s weapons."
    )

    val CommsJammers = Equipment(
        name = "Comms Jammers",
        description = "**Comms Jammers:** Whenever an enemy operative is within 3\" of a friendly ##NEMESIS CLAW## operative, that enemy operative’s APL stat cannot be added to. Note that this doesn’t affect APL stats that have already been changed.",
        weapons = listOf(Weapons.LMaleficBlades)
    )

    val equipmentList = listOf(FlayedSkin,ChainSnare,GrislyTrophy,CommsJammers) + UniversalEquipment.equipmentList
}