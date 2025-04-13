package Objects

object UniversalEquipment
{
    val AmmoCache = Equipment(
        name = "1x Ammo Cache",
        description = "Before the battle, you can set up one of your Ammo Cache markers wholly within your territory. Friendly operatives can perform the following mission action during the battle."
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
                "\n**Obstructing:** Whenever an operative would cross over this terrain feature within 1\" of it, treat the distance as an additional 1\"."
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
                "\n**Protective:** While an operative is in Cover behind this terrain feature, improve its Save stat by 1 (to a maximum of 2+).\n" +
                "\n**Portable:** This terrain feature only provides cover while an operative is connected to it and if the shield is intervening (ignore its feet). Operatives connected to the inside of it can perform the following action during the battle."
    )

    val UtilityGrenades = Equipment(
        name = "Utility Grenades",
        description = "When you select this equipment, select two utility grenades (2 smoke, 2 stun, or 1 smoke and 1 stun). Each selection is a unique action your operatives can perform, but your kill team can only use that weapon a total number of times during the battle equal to your selection."
    )

    val ExplosiveGrenades = Equipment(
        name = "Explosive Grenades",
        description = "When you select this equipment, select two explosive grenades (2 frag, 2 krak, or 1 frag and 1 krak). Each selection is a unique weapon your operatives can use, but your kill team can only use that weapon a total number of times during the battle equal to your selection."
    )

    val equipmentList = listOf(AmmoCache,CommsDevice,Mines,RazorWire,LightBarricades,HeavyBarricade,Ladders,PortableBarricade,UtilityGrenades,ExplosiveGrenades)
}

object DeathKorpsEQ
{
    val HandAxe = Equipment(
        name = "Hand Axe",
        description = "Friendly ##DEATH KORPS## operatives have the following melee weapon: "
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
    val aaaaaaa = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val bbbbb = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ccccccccccc = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ddddddd = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val equipmentList = UniversalEquipment.equipmentList//listOf()
}

object AngelsOfDeathEQ
{
    val aaaaaaa = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val bbbbb = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ccccccccccc = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ddddddd = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val equipmentList = UniversalEquipment.equipmentList//listOf()
}

object VoidscaredCorsairsEQ
{
    val aaaaaaa = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val bbbbb = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ccccccccccc = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ddddddd = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val equipmentList = UniversalEquipment.equipmentList//listOf()
}

object PlagueMarinesEQ
{
    val aaaaaaa = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val bbbbb = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ccccccccccc = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ddddddd = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val equipmentList = UniversalEquipment.equipmentList//listOf()
}

object HunterCladeEQ
{
    val aaaaaaa = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val bbbbb = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ccccccccccc = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val ddddddd = Equipment(
        name = "Explosive Grenades",
        description = ""
    )

    val equipmentList = UniversalEquipment.equipmentList//listOf()
}