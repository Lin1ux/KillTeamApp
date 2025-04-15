package Objects

import kotlin.String

object Actions
{
    //Death Korps
    val DKMedikit = Action(
        name = "MEDIKIT",
        cost = 1,
        description = "Select one friendly ##DEATH KORPS## operative within this operative’s control Range to regain 2D3 lost wounds. It cannot be an operative that the **Medic!** rule was used on during this turning point.",
        limitation = "This operative cannot perform this action while within control Range of an enemy operative."
    )

    val DKSignal = Action(
        name = "SIGNAL",
        cost = 1,
        description = "**SUPPORT.** Select one other friendly ##DEATH KORPS## operative visible to and within 6” of this operative. Until the end of that operative’s next activation, add 1 to its APL stat.",
        limitation = "This operative cannot perform this action while within control Range of an enemy operative."
    )

    val DKSpot = Action(
        name = "SPOT",
        cost = 1,
        description = "**SUPPORT:** Select one enemy operative visible to this operative. Once during this Turning Point, when a friendly ##DEATH KORPS## operative within 3” of this operative shoots that enemy operative, you can use this effect. If you do:\n" +
                "\n • That friendly operative’s ranged weapons gain the xxSeek Lightxx weapon rule." +
                "\n • That enemy operative cannot be xxobscured.xx",
        limitation = "This operative cannot perform this action while within control Range of an enemy operative."
    )
}