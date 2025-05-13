package com.example.killteam.Objects

object Actions
{
    //Universal
    val AmmoResupply = Action(
        name = "AMMO RESUPPLY",
        cost = 0,
        description = "One of your Ammo Cache markers the active operative controls is used this turning point.\n" +
                "Until the start of the next turning point, whenever this operative is shooting with a weapon from its datacard, you can re-roll one of your attack dice.",
        limitation = "An operative cannot perform this action while within control range of an enemy operative, if that marker isn’t yours, or if that marker has been used this turning point."
    )

    val MoveWithBarricade = Action(
        name = "MOVE WITH BARRICADE",
        description = "The same as the **Reposition** action, except the active operative can move no more than its Move stat minus 2\" and cannot climb, drop, jump or use any kill team's rules that remove it and set it back up again (e.g. HEARTHKYN SALVAGER FLY, MANDRAKE SHADOW PASSAGE)." +
                "Before this operative moves, remove the portable barricade it is connected to. After the operative moves, set up the portable barricade so that it is once again, but the portable barricade cannot be set up within 2\" of other equipment terrain features, access points or Accessible terrain. If this is not possible, the portable barricade is not set up again.",
        limitation = "This action is treated as a **Reposition** action. An operative cannot perform the Move with Barricade action in the same turning point in which it performed the **Fall Back** or **Charge** actions."
    )

    val SmokeGrenade = Action(
        name = "SMOKE GRENADE",
        description = "Place one of your smoke grenade markers within 6\" of this operative. It must be visible to this operative or set on vantage terrain or a terrain feature visible to this operative. The marker creates a 1\" horizontally and vertically unlimited smoke area from it (but not below it).\n" +
                "While an operative is wholly within a smoke area, it is considered xxobscuredxx to operatives more than 2\" away from it and vice versa. Additionally, when an operative shoots at an enemy operative that is wholly within a smoke area, the xxPiercingxx rule is ignored unless both operatives are within 2\" of each other.\n" +
                "In the Ready Step of the next Strategy Phase, roll a **D3.** Remove that smoke grenade marker once a number of activations equal to the result of that **D3** is completed, or at the end of the turning point (whichever occurs first).",
        limitation = "An operative cannot perform this action while within the control range of an enemy operative, or if the total number of times your kill team can perform it has been reached."
    )

    val StunGrenade = Action(
        name = "STUN GRENADE",
        description = "Select an enemy operative within 6\" of this operative and visible to it. That operative and each other operative within 1\" of it makes a stun check. To make a stun check, roll a D6: if the result is 3+, subtract 1 from its APL stat until the end of its next activation.",
        limitation = "An operative cannot perform this action while within control range of an enemy operative, or if you have reached the total number of times your kill team can perform it."
    )

    //Death Korps
    val DKMedikit = Action(
        name = "MEDIKIT",
        description = "Select one friendly ##DEATH KORPS## operative within this operative’s control Range to regain **2D3** lost wounds. It cannot be an operative that the **Medic!** rule was used on during this turning point.",
    )

    val DKSignal = Action(
        name = "SIGNAL",
        description = "**SUPPORT.** Select one other friendly ##DEATH KORPS## operative visible to and within 6” of this operative. Until the end of that operative’s next activation, add 1 to its APL stat.",
    )

    val DKSpot = Action(
        name = "SPOT",
        description = "**SUPPORT:** Select one enemy operative visible to this operative. Once during this Turning Point, when a friendly ##DEATH KORPS## operative within 3” of this operative shoots that enemy operative, you can use this effect. If you do:\n" +
                "\n • That friendly operative’s ranged weapons gain the xxSeek Lightxx weapon rule." +
                "\n • That enemy operative cannot be xxobscured.xx",
    )

    //Kasrkin
    val KSTacticalCommand = Action(
        name = "TACTICAL COMMAND",
        cost = 0,
        description = "Select one friendly ##KASRKIN## operative, then select one **SKILL AT ARMS** for that operative to have until the Ready step of the next Strategy phase. This can be in addition to any **SKILL AT ARMS** it already has, but they cannot be the same.\n" +
                "\nAlternatively, instead of resolving the above effect, if your Clearance Sweep marker is in the killzone, you can remove it and place it again. ",
    )

    val KSMedikit = Action(
        name = "MEDIKIT",
        description = "Select one friendly ##KASRKIN## operative within this operative’s control Range to regain **2D3** lost wounds. It cannot be an operative that the **Medic!** rule was used on during this turning point.",
    )

    val KSAuspexScan = Action(
        name = "AUSPEX SCAN",
        description = "Until the start of this operative’s next activation or until it’s incapacitated (whichever comes first), whenever an enemy operative is within 8\" of this operative, that enemy operative is being scanned. Whenever a friendly ##KASRKIN## operative is shooting an enemy operative that’s being scanned, that enemy operative cannot be xxobscured.xx "
    )

    val KSBattleComms = Action (
        name = "BATTLE COMMS",
        description = "Select one other friendly ##KASRKIN## operative. Until the end of that operative’s next activation, add 1 to its APL stat (to a maximum of 3 after all APL stat changes have been totalled).",
        limitation = "This operative can perform this action twice during its activation, but cannot perform this action while within control Range of an enemy operative. "
    )
    //Angels of Death
    val ADSpot = Action(
        name = "SPOT",
        description = " Until the start of this operative’s next activation, whenever it’s shooting, enemy operatives cannot be xxobscured.xx"
    )

    //Voidscared Corsairs
    val VCPistolBarrage = Action (
        name = "PISTOL BARRAGE",
        description = "Perform two free **Shoot** actions with this operative (this takes precedence over action restrictions). You must select its fusion pistol for one action and its shuriken pistol for the other (in any order).",
    )

    val VCWarpFold = Action (
        name = "WARP FOLD",
        description = "**PSYCHIC:** Select two friendly ##CORSAIR VOIDSCARRED## operatives visible to and within 5\" of this operative. Remove them both from the killzone and set them back up in each other’s previous locations (in other words, swap their positions). If one of them performed the **Charge,** **Fall Back** or **Reposition** action during this turning point and the other is ready, the other cannot perform any of those actions in its activation during this turning point.",
    )

    val VCWardingShield = Action (
        name = "WARDING SHIELD",
        description = "**PSYCHIC:** Select one friendly ##CORSAIR VOIDSCARRED## operative visible to and within 6\" of this operative. Until the start of this operative’s next activation, until it’s incapacitated or until it performs this action again (whichever comes first), the first time an attack dice inflicts Normal Damage on that friendly operative, ignore that inflicted damage.",
    )

    val VCSoulChannel = Action (
        name = "SOUL CHANNEL",
        description = "**PSYCHIC:** Select one other friendly ##CORSAIR VOIDSCARRED## operative visible to and within 6\" of this operative. Until the end of that operative’s next activation, add 1 to its APL stat.",
    )

    val VCSoulHeal = Action (
        name = "SOUL HEAL",
        description = "**PSYCHIC:** Select one friendly ##CORSAIR VOIDSCARRED## operative visible to and within 6\" of this operative. That operative regains **2D3** lost wounds.",
    )

    //Plague Marines
    val PMPoisonousMiasma = Action (
        name = "POISONOUS MIASMA",
        description = "**PSYCHIC.** Select one enemy operative visible to and within 7\" of this operative, or one enemy operative that’s a valid target for this operative. That enemy operative gains one of your Poison tokens (if it doesn’t already have one). If it already has one, inflict 3 damage on that enemy operative instead. "
    )

    val PMPutrescentVitality = Action (
        name = "PUTRESCENT VITALITY",
        description = "**PSYCHIC.** Select one friendly operative visible to and within 3\" of this operative, then roll **2D6:** if the result is 7, the selected operative regains 7 lost wounds; otherwise, the selected operative regains lost wounds equal to the highest **D6.** ",
        limitation = "This operative cannot perform this action while within control Range of an enemy operative, or more than once per turning point."
    )

    val PMFlail = Action (
        name = "Flail",
        description = "Inflict **D3+2** damage on each other operative that’s both visible to and within 2\" of this operative. Roll separately for each: if it’s an enemy operative, if the **D3** result is a 3, that enemy operative also gains one of your Poison tokens (if it doesn’t already have one). ",
        limitation = " This operative cannot perform this action while it has a **Conceal** order, or during an activation in which it has performed more than one **Fight** action, and it cannot perform more than one **Fight** action during an activation in which it performs this action. "
    )

    //Hunter Clade (admech)

    val HCSignal = Action(
        name = "SIGNAL",
        description = "**SUPPORT.** Select one other friendly ##HUNTER CLADE## operative visible to and within 6” of this operative. Until the end of that operative’s next activation, add 1 to its APL stat.",
    )

    val HCSpot = Action(
        name = "SPOT",
        description = "**SUPPORT:** Select one enemy operative visible to this operative. Once during this Turning Point, when a friendly ##HUNTER CLADE## operative within 3” of this operative shoots that enemy operative, you can use this effect. If you do:\n" +
                "\n • That friendly operative’s ranged weapons gain the xxSeek Lightxx weapon rule." +
                "\n • That enemy operative cannot be xxobscured.xx",
    )

    //Legionary
    val LGrislyMark = Action(
        name = "GRISLY MARK",
        description = "Place your Grisly marker within this operative’s control Range .\n\n" +
                "\n • Whenever an enemy operative is within 3\" of your Grisly marker, your opponent must spend 1 additional AP for that enemy operative to perform the **Pick Up Marker** and mission actions.\n" +
                "\n • Whenever determining control of a marker, treat the total APL stat of enemy operatives that contest it as 1 lower if at least one of those enemy operatives is within 3\" of your Grisly marker. Note this isn’t a change to the APL stat, so any changes are cumulative with this.",
        limitation = "This operative can only perform this action once per battle, and cannot perform it while within control Range of an enemy operative.",
        cost = 2
        )
    //Nemesis Claw
    val NCPremonition = Action(
        name = "PREMONITION",
        description = "**PSYCHIC.** Spend 1 of your Prescience points to gain 1CP.",
        limitation = "This operative cannot perform this action while within control Range of an enemy operative, or more than once per turning point."
    )

    val NCPoisonObjective = Action(
        name = "POISON OBJECTIVE",
        description = "Select one objective marker this operative controls to gain one of your Terrorchem tokens. It cannot be an objective marker within control Range of an enemy operative, or one that already has one of your Terrorchem tokens. The first time that objective marker is within control Range of an enemy operative that doesn’t have one of your Terrorchem tokens, that operative gains that Terrorchem token, then inflict **2D3** damage on it (if it’s during an action, at the end of that action).",
    )

    val DisconcertingMimicry = Action(
        name = "Disconcerting Mimicry",
        description = "**PSYCHIC.** Select one enemy operative within 6” of this operative, then select one of the following for that enemy operative (you can only select each option once per battle): " +
                "\n • Until the end of its next activation, subtract 1 from its APL stat.\n" +
                "\n • Change its order.\n" +
                "\n • Perform a free **Dash** action with it (specify the location for your opponent to move it to)."
    )
}