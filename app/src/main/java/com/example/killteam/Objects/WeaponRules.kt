package com.example.killteam.Objects

object WeaponRules
{

    val AccurateX = WeaponRule(
        name = "Accurate X",
        description = "You can retain up to x attack dice as normal successes without rolling them. If a weapon has more than one instance of Accurate x, you can treat it as one instance of Accurate 2 instead (this takes precedence over x rules above)."
    )

    val Balanced = WeaponRule(
        name = "Balanced",
        description = "You can re-roll one of your attack dice."
    )

    val BlastX = WeaponRule(
        name = "Blast X",
        description = "The target you select is the primary target. After shooting the primary target, shoot with this weapon against each secondary target in an order of your choice (roll each sequence separately). Secondary targets are other operatives visible to and within x of the primary target, e.g., Blast 2” (they are all valid targets, regardless of a Conceal order). Secondary targets are in cover and obscured if the primary target was."
    )

    val Blast1 = WeaponRule(
        name = "Blast 1",
        description = "The target you select is the primary target. After shooting the primary target, shoot with this weapon against each secondary target in an order of your choice (roll each sequence separately). Secondary targets are other operatives visible to and within 1” of the primary target (they are all valid targets, regardless of a Conceal order). Secondary targets are in cover and obscured if the primary target was."
    )

    val Blast2 = WeaponRule(
        name = "Blast 2",
        description = "The target you select is the primary target. After shooting the primary target, shoot with this weapon against each secondary target in an order of your choice (roll each sequence separately). Secondary targets are other operatives visible to and within 2” of the primary target (they are all valid targets, regardless of a Conceal order). Secondary targets are in cover and obscured if the primary target was."
    )

    val Brutal = WeaponRule(
        name = "Brutal",
        description = "Your opponent can only block with critical successes."
    )

    val Ceaseless = WeaponRule(
        name = "Ceaseless",
        description = "You can re-roll any of your attack dice results of one result (e.g., results of 2).",
    )

    val ConcealedPosition = WeaponRule(
        name = "Concealed Position*",
        description = "This operative can only use this weapon the first time it’s performing the Shoot action during the battle."
    )

    val Detonate = WeaponRule(
        name = "Detonate*",
        description = "Don’t select a valid target. Instead, shoot against each operative within 2” of your Mine marker, unless Heavy terrain is wholly intervening between that operative and that marker. Each of those operatives cannot be in cover or obscured. Roll each sequence separately in an order of your choice. This weapon cannot be selected if your Mine marker isn’t in the killzone. At the end of the action, remove your Mine marker from the killzone. "
    )

    val DevastatingX = WeaponRule(
        name = "Devastating X",
        description = "Each retained critical success immediately inflicts x damage on the operative this weapon is being used against, e.g., Devastating 3. If the rule starts with a distance (e.g., 1” Devastating x), inflict x damage on that operative and each other operative visible to and within that distance of it. Note that success isn’t discarded after doing so—it can still be resolved later in the sequence."
    )

    val Devastating11 = WeaponRule(
        name = "1\" Devastating 1",
        description = "Each retained critical success immediately inflicts 1 damage on the operative this weapon is being used against and each other operative visible to and within 1\" of it. Note that success isn’t discarded after doing so—it can still be resolved later in the sequence."
    )

    val Devastating2 = WeaponRule(
        name = "Devastating 2",
        description = "Each retained critical success immediately inflicts 2 damage on the operative this weapon is being used against. Note that success isn’t discarded after doing so—it can still be resolved later in the sequence."
    )

    val Devastating22 = WeaponRule(
        name = "2\" Devastating 2",
        description = "Each retained critical success immediately inflicts 2 damage on the operative this weapon is being used against and each other operative visible to and within 2\" of it. Note that success isn’t discarded after doing so—it can still be resolved later in the sequence."
    )

    val Devastating3 = WeaponRule(
        name = "Devastating 3",
        description = "Each retained critical success immediately inflicts 3 damage on the operative this weapon is being used against. Note that success isn’t discarded after doing so—it can still be resolved later in the sequence."
    )

    val Devastating4 = WeaponRule(
        name = "Devastating 4",
        description = "Each retained critical success immediately inflicts 4 damage on the operative this weapon is being used against. Note that success isn’t discarded after doing so—it can still be resolved later in the sequence."
    )

    val Heavy = WeaponRule(
        name = "Heavy",
        description = "An operative cannot use this weapon in an activation or counteraction in which it moved, and it cannot move in an activation or counteraction in which it used this weapon. This weapon rule has no effect on preventing the Guard action."
    )

    val HeavyDash = WeaponRule(
        name = "Heavy (Dash only)",
        description = "An operative cannot use this weapon in an activation or counteraction in which it moved (Dash is exception), and it cannot move (Dash is exception) in an activation or counteraction in which it used this weapon. Only Dash move is allowed. This weapon rule has no effect on preventing the Guard action."
    )

    val HeavyReposition = WeaponRule(
        name = "Heavy (Reposition only)",
        description = "An operative cannot use this weapon in an activation or counteraction in which it moved (Reposition is exception), and it cannot move (Reposition is exception) in an activation or counteraction in which it used this weapon. Only Reposition move is allowed. This weapon rule has no effect on preventing the Guard action."
    )

    val Hot = WeaponRule(
        name = "Hot",
        description = "After an operative uses this weapon, roll one D6. If the result is less than the weapon’s Hit stat, inflict damage on that operative equal to the result multiplied by two. If it’s used multiple times in one action (e.g., Blast), still only roll one D6."
    )

    val LethalX = WeaponRule(
        name = "Lethal X",
        description = "Your successes equal to or greater than x are critical successes, e.g., Lethal 5+."
    )

    val Lethal5 = WeaponRule(
        name = "Lethal 5+",
        description = "Your successes equal to or greater than 5 are critical successes"
    )

    val LifeSyphon = WeaponRule(
        name = "Life Syphon*",
        description = "When you select this weapon, you can use this rule. If you do, at the start of the Resolve Attack Dice step, you can select one friendly LEGIONARY operative visible to and within 6\" of this operative. For each attack dice you resolve during that step that inflicts damage, that friendly operative regains 1 lost wound, or D3 lost wounds if it was a critical success. You cannot use this weapon rule more than once per turning point."
    )

    val LimitedX = WeaponRule(
        name = "Limited X",
        description = "After an operative uses this weapon a number of times in the battle equal to x, they no longer have it. If it’s used multiple times in one action (e.g., Blast), treat this as one use."
    )

    val Limited1 = WeaponRule(
        name = "Limited 1",
        description = "After an operative uses this weapon a number of times in the battle equal to 1, they no longer have it. If it’s used multiple times in one action (e.g., Blast), treat this as one use."
    )

    val PiercingX = WeaponRule(
        name = "Piercing X",
        description = "The defender collects x less defence dice, e.g., Piercing 1. If the rule is Piercing Crits x, this only comes into effect if you retain any critical successes."
    )

    val Piercieng1 = WeaponRule(
        name = "Piercing 1",
        description = "The defender collects 1 less defence dice."
    )

    val Piercieng2 = WeaponRule(
        name = "Piercing 2",
        description = "The defender collects 2 less defence dice."
    )

    val PiercingCrits1 = WeaponRule(
        name = "Piercing Crits 1",
        description = "The defender collects 1 less defence dice if you retain any critical successes."
    )

    val Poison = WeaponRule(
        name = "Poison*",
        description = "In the Resolve Attack Dice step, if you inflict damage with any successes, the operative this weapon is being used against (excluding friendly PLAGUE MARINE operatives) gains one of your Poison tokens (if it doesn’t already have one). Whenever an operative that has one of your Poison tokens is activated, inflict 1 damage on it."
    )

    val Psychic = WeaponRule(
        name = "PSYCHIC",
        description = "Keyword"
    )

    val Punishing = WeaponRule(
        name = "Punishing",
        description = "If you retain any critical successes, you can retain one of your fails as a normal success instead of discarding it."
    )

    val RangeX = WeaponRule(
        name = "Range X",
        description = "Only operatives within x of the active operative can be valid targets, e.g., Range 9”",
    )

    val Range3 = WeaponRule(
        name = "Range 3",
        description = "Only operatives within 3\" of the active operative can be valid targets",
    )

    val Range6 = WeaponRule(
        name = "Range 6",
        description = "Only operatives within 6\" of the active operative can be valid targets",
    )

    val Range7 = WeaponRule(
        name = "Range 7",
        description = "Only operatives within 7\" of the active operative can be valid targets",
    )

    val Range8 = WeaponRule(
        name = "Range 8",
        description = "Only operatives within 8\" of the active operative can be valid targets"
    )

    val Relentless = WeaponRule(
        name = "Relentless",
        description = "You can re-roll any of your attack dice."
    )

    val Rending = WeaponRule(
        name = "Rending",
        description = "If you retain any critical successes, you can retain one of your normal successes as a critical success instead."
    )

    val Saturate = WeaponRule(
        name = "Saturate",
        description = "The defender cannot retain cover saves."
    )

    val SeekX = WeaponRule(
        name = "Seek",
        description = "When selecting a valid target, operatives cannot use terrain for cover. If the rule is Seek Light, operatives cannot use Light terrain for cover. Whilst this can allow such operatives to be targeted (assuming they’re visible), it doesn’t remove their cover save (if any)."
    )

    val Seek = WeaponRule(
        name = "Seek",
        description = "When selecting a valid target, operatives cannot use terrain for cover. Whilst this can allow such operatives to be targeted (assuming they’re visible), it doesn’t remove their cover save (if any)."
    )

    val SeekLight = WeaponRule(
        name = "Seek Light",
        description = "When selecting a valid target, operatives cannot use Light terrain for cover. Whilst this can allow such operatives to be targeted (assuming they’re visible), it doesn’t remove their cover save (if any)."
    )

    val Severe = WeaponRule(
        name = "Severe",
        description = "If you don’t retain any critical successes, you can change one of your normal successes to a critical success. Any rules that take effect as a result of retaining a critical success (e.g., Devastating, Piercing Crits, etc.) still do."
    )

    val Shock = WeaponRule(
        name = "Shock",
        description = "The first time you strike with a critical success in each sequence, also discard one of your opponent’s unresolved normal successes (or a critical success if there are none)."
    )

    val Silent = WeaponRule(
        name = "Silent",
        description = "An operative can perform the Shoot action with this weapon while it has a Conceal order."
    )

    val Stun = WeaponRule(
        name = "Stun",
        description = "If you retain any critical successes, subtract 1 from the APL stat of the operative this weapon is being used against until the end of its next activation."
    )

    val Terrorchem = WeaponRule(
        name = "Terrorchem*",
        description = "In the Resolve Attack Dice step, if you inflict damage with any critical successes, the operative this weapon is being used against gains one of your Terrorchem tokens (if it doesn’t already have one). "
    )

    val TorrentX = WeaponRule(
        name = "Torrent X",
        description = "Select a valid target as normal as the primary target, then select any number of other valid targets within x of the first valid target as secondary targets, e.g., Torrent 2”. Shoot with this weapon against all of them in an order of your choice (roll each sequence separately)."
    )

    val Torrent1 = WeaponRule(
        name = "Torrent 1",
        description = "Select a valid target as normal as the primary target, then select any number of other valid targets within 1\" of the first valid target as secondary targets. Shoot with this weapon against all of them in an order of your choice (roll each sequence separately)."
    )

    val Torrent2 = WeaponRule(
        name = "Torrent 2",
        description = "Select a valid target as normal as the primary target, then select any number of other valid targets within 2\" of the first valid target as secondary targets, e.g., Torrent 2”. Shoot with this weapon against all of them in an order of your choice (roll each sequence separately)."
    )

    val Toxic = WeaponRule(
        name = "Toxic*",
        description = "Whenever this operative is using this weapon against an enemy operative that has one of your Poison tokens, add 1 to both Dmg stats of this weapon "
    )

    val WeaponRuleList = listOf(AccurateX,Balanced,BlastX,Brutal,Ceaseless,DevastatingX,Heavy,Hot,LethalX,LimitedX,PiercingX,Punishing,RangeX,Relentless,Rending,Saturate,SeekX,Severe,Shock,Silent,Stun,TorrentX)
}