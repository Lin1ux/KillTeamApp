package Objects

import kotlin.String

object Actions
{
    //Death Korps
    val DKMedikit = Action(
        name = "MEDIKIT",
        cost = 1,
        description = "Select one friendly ##DEATH KORPS## operative within this operative’s control Range to regain 2D3 lost wounds. It cannot be an operative that the **Medic!** rule was used on during this turning point.",
    )

    val DKSignal = Action(
        name = "SIGNAL",
        cost = 1,
        description = "**SUPPORT.** Select one other friendly ##DEATH KORPS## operative visible to and within 6” of this operative. Until the end of that operative’s next activation, add 1 to its APL stat.",
    )

    val DKSpot = Action(
        name = "SPOT",
        cost = 1,
        description = "**SUPPORT:** Select one enemy operative visible to this operative. Once during this Turning Point, when a friendly ##DEATH KORPS## operative within 3” of this operative shoots that enemy operative, you can use this effect. If you do:\n" +
                "\n • That friendly operative’s ranged weapons gain the xxSeek Lightxx weapon rule." +
                "\n • That enemy operative cannot be xxobscured.xx",
    )

    //Kasrkin
    val KSTacticalCommand = Action(
        name = "TACTICAL COMMAND",
        cost = 0,
        description = "Select one friendly ##KASRKIN## operative. Select a **SKILL AT ARMS** for that operative to have (instead of any it currently has) until the Ready step of the next Strategy phase.\n" +
                "\nAlternatively, instead of resolving the above effect, if your Clearance Sweep marker is in the killzone, you can remove it and place it again. ",
    )

    val KSMedikit = Action(
        name = "MEDIKIT",
        cost = 1,
        description = "Select one friendly ##KASRKIN## operative within this operative’s control Range to regain 2D3 lost wounds. It cannot be an operative that the **Medic!** rule was used on during this turning point.",
    )

    val KSAuspexScan = Action(
        name = "AUSPEX SCAN",
        cost = 1,
        description = "Until the start of this operative’s next activation or until it’s incapacitated (whichever comes first), whenever a friendly ##KASRKIN## operative is shooting an enemy operative within 8\" of this operative, that enemy operative cannot be xxobscured.xx "
    )

    val KSBattleComms = Action (
        name = "BATTLE COMMS",
        cost = 1,
        description = "Select one other friendly ##KASRKIN## operative. Until the end of that operative’s next activation, add 1 to its APL stat (to a maximum of 3 after all APL stat changes have been totalled).",
        limitation = "This operative can perform this action twice during its activation, but cannot perform this action while within control Range of an enemy operative. "
    )
    //Angels of Death
    val ADSpot = Action(
        name = "SPOT",
        cost = 1,
        description = " Until the start of this operative’s next activation, whenever it’s shooting, enemy operatives cannot be xxobscured.xx"
    )
}