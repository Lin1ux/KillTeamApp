package com.example.killteam.Objects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.killteam.ListDescription
import com.example.killteam.ListDescriptionChapterTactics
import com.example.killteam.ListDescriptionWithPrimary
import com.example.killteam.PassiveDescription
import com.example.killteam.ScoreViewModel

class Passive(name : String = "name",
              description : String = ""
) : TeamRule(name, description)
{
    @Composable
    override fun Draw(viewModel: ScoreViewModel,firstPlayer : Boolean)
    {
        PassiveDescription(this)
    }

    override fun deepCopy(): TeamRule {
        return Passive(name = this.name, description = this.description)}
}

class SelectionRuleList(
    name : String = "name",
    description : String = "",
    ruleList: List<TeamRule>
) : TeamRule(name, description)
{
    val selectionList : List<TeamRule> = ruleList
    var selectedIndex : MutableState<Int> = mutableStateOf(-1)

    @Composable
    override fun Draw(viewModel: ScoreViewModel,firstPlayer : Boolean)
    {
        ListDescription(viewModel,firstPlayer,this)
    }

    override fun deepCopy(): TeamRule {
        return SelectionRuleList(
            name = this.name,
            description = this.description,
            ruleList = this.selectionList.map { it.deepCopy() }
        ).apply {
            this.selectedIndex.value = this@SelectionRuleList.selectedIndex.value
        }
    }

    fun SelectIndex(value : Int)
    {
        selectedIndex.value = value
    }
}

class SelectionRuleListWithPrimary(
    name : String = "name",
    description : String = "",
    ruleList: List<TeamRule>
) : TeamRule(name, description)
{
    val selectionList : List<TeamRule> = ruleList
    var selectedIndex : MutableState<Int> = mutableStateOf(-1)
    var primaryIndex : MutableState<Int> = mutableStateOf(-1)

    @Composable
    override fun Draw(viewModel: ScoreViewModel,firstPlayer : Boolean)
    {
        ListDescriptionWithPrimary(viewModel,firstPlayer,this)
    }

    override fun deepCopy(): TeamRule {
        return SelectionRuleListWithPrimary(
            name = this.name,
            description = this.description,
            ruleList = this.selectionList.map { it.deepCopy() }
        ).apply {
            this.selectedIndex.value = this@SelectionRuleListWithPrimary.selectedIndex.value
            this.primaryIndex.value = this@SelectionRuleListWithPrimary.primaryIndex.value
        }
    }

    fun SelectIndex(value : Int)
    {
        selectedIndex.value = value
    }

    fun SelectPrimaryIndex(value : Int)
    {
        primaryIndex.value = value
    }
}

class SelectionRuleListChapterTactics(
    name : String = "name",
    description : String = "",
    ruleList: List<TeamRule>
) : TeamRule(name, description)
{
    val selectionList : List<TeamRule> = ruleList
    var selectedIndex : MutableState<Int> = mutableStateOf(-1)
    var primaryIndex : MutableState<Int> = mutableStateOf(-1)

    @Composable
    override fun Draw(viewModel: ScoreViewModel,firstPlayer : Boolean)
    {
        ListDescriptionChapterTactics(viewModel,firstPlayer,this)
    }

    override fun deepCopy(): TeamRule {
        return SelectionRuleListChapterTactics(
            name = this.name,
            description = this.description,
            ruleList = this.selectionList.map { it.deepCopy() }
        ).apply {
            this.selectedIndex.value = this@SelectionRuleListChapterTactics.selectedIndex.value
            this.primaryIndex.value = this@SelectionRuleListChapterTactics.primaryIndex.value
        }
    }

    fun SelectIndex(value : Int)
    {
        if(value != primaryIndex.value)
        {
            selectedIndex.value = value
        }
    }

    fun SelectPrimaryIndex(value : Int)
    {
        primaryIndex.value = value
    }
}

object AngelsOfDeathRules
{
    val Aggressive = Passive(
        name = "Aggressive",
        description = "This operative's melee weapons have the xxRendingxx weapon rule.",
    )

    val Dueller = Passive(
        name = "Dueller",
        description = "Once per sequence, whenever this operative is fighting or retaliating:\n" +
                "\n • One of your normal successes can block one unresolved critical success (unless the enemy operative's weapon has the xxBrutalxx weapon rule).\n" +
                "\n • One of your critical successes can block two unresolved normal successes (instead of one critical success).",
    )

    val Resolute = Passive(
        name = "Resolute",
        description = "You can ignore any changes to this operative's APL stat.",
    )

    val Stealthy = Passive(
        name = "Stealthy",
        description = "Whenever an operative is shooting this operative, if you retain any cover saves, you can retain one additional cover save or retain one cover save as a critical success instead. This isn’t cumulative with improved cover saves from Vantage terrain. ",
    )
    val Mobile = Passive(
        name = "Mobile",
        description = "This operative can perform the **Fall Back** action for 1 less AP. It can also perform the **Charge** action while within control range of an enemy operative and leave its control range.",
    )

    val Hardy = Passive(
        name = "Hardy",
        description = "Whenever an operative is shooting this operative, defence dice results of 5+ are critical successes.",
    )
    val Sharpshooter = Passive(
        name = "Sharpshooter",
        description = "Whenever this operative is shooting during an activation in which it hasn’t performed **Charge, Fall Back or Reposition,** its bolt weapons gain the xxSeverexx weapon rule.",
    )

    val SiegeSpecialist = Passive(
        name = "Siege Specialist",
        description = "This operative's ranged weapons have the xxSaturatexx weapon rule.",
    )

    val ChapterTactics = SelectionRuleListChapterTactics(
        name = "Chapter Tactics",
        description = "When selecting your kill team, select a primary and secondary **CHAPTER TACTIC** for friendly ##ANGEL OF DEATH## operatives to gain for the battle. Multiple instances of the same **CHAPTER TACTIC** are not cumulative.\n\n" +
                "Designer’s Note: If you’re playing a series of games, i.e. a campaign or tournament, you must select the same primary and secondary **CHAPTER TACTIC** for every battle (you can still change the secondary with the Adaptive Tactics strategy ploy).",
        ruleList = listOf(Aggressive,Dueller,Resolute,Stealthy,Mobile,Hardy,Sharpshooter,SiegeSpecialist)
        )

    val Astartes = Passive(
        name = "Astartes",
        description = "During each friendly ##ANGEL OF DEATH## operative’s activation, it can perform either two **Shoot** actions or two **Fight** actions. If it’s two **Shoot** actions, a bolt weapon must be selected for at least one of them, and if it’s a bolt sniper rifle or heavy bolter, 1 additional AP must be spent for the second action if both actions are using that weapon.\n\n" +
                "Each friendly ##ANGEL OF DEATH## operative can counteract regardless of its order.",
    )

    val teamRulesList = listOf<TeamRule>(ChapterTactics,Astartes)
}

object DeathKorpsRules
{
    val MoveMoveMove = Passive(
        name = "Move! Move! Move!",
        description = "Whenever an operative that has received this order performs a **Reposition** action, add 1\" to its Movement stat.",
    )

    val TakeAim = Passive(
        name = "Take Aim!",
        description = "The ranged weapons of operatives that have received this order (excluding mortar barrage and remote detonator) have the xxCeaselessxx weapon rule.",
    )

    val DigIn = Passive(
        name = "Dig In!",
        description = "Whenever an enemy operative is shooting a friendly operative that has received this order, if you retain any cover saves, you can **re-roll** any of your defence dice results of one result (e.g. results of 2).",
    )

    val FixBayonets = Passive(
        name = "Fix Bayonets!",
        description = "Melee weapons of operatives that have received this order have the xxCeaselessxx weapon rule.",
    )

    val GuardsmanOrders = SelectionRuleList(
        name = "Guardsmen Orders",
        description = "**STRATEGIC GAMBIT** and **SUPPORT.** A friendly **WATCHMASTER** ##DEATH KORPS## operative can issue a **GUARDSMAN ORDER.** Whenever it does so, select one **GUARDSMAN ORDER** for all friendly ##DEATH KORPS## operatives within 6\" of it to receive.\n\n" +
                "Whenever a friendly operative receives a **GUARDSMAN ORDER,** apply its rules until the end of the turning point. Operatives cannot benefit from more than one **GUARDSMAN ORDER** at a time; they only benefit from the most recent order they received during the turning point.",
        ruleList = listOf(MoveMoveMove,TakeAim,DigIn,FixBayonets)
    )

    val teamRulesList = listOf<TeamRule>(GuardsmanOrders)
}

object HunterCladeRules
{
    val ProtectorImperative = Passive(
        name = "Protector Imperative",
        description =   " **Optimisation:** Friendly ##HUNTER CLADE## operatives' ranged weapons have the xxCeaselessxx weapon rule.\n\n" +
                        " **Deprecation:** Worsen the Hit stat of friendly ##HUNTER CLADE## operatives' melee weapons by 1. This isn’t cumulative with being injured.\n",
    )

    val ConquerorImperative = Passive(
        name = "Conqueror Imperative",
        description =   " **Optimisation:** Friendly ##HUNTER CLADE## operatives' melee weapons have the xxCeaselessxx weapon rule.\n\n" +
                        " **Deprecation:** Worsen the Hit stat of friendly ##HUNTER CLADE## operatives' ranged weapons by 1. This isn’t cumulative with being injured.",
    )

    val BulwarkImperative = Passive(
        name = "Bulwark Imperative",
        description =   " **Optimisation:**  Normal Dmg of 3 or more inflicts 1 less damage on ##HUNTER CLADE## friendly operatives.\n\n" +
                        " **Deprecation:** Subtract 1\" from the Move stat of friendly ##HUNTER CLADE## operatives.",
    )

    val AggressorImperative = Passive(
        name = "Aggressor Imperative",
        description =   " **Optimisation:** Add 1\" to the Move stat of friendly ##HUNTER CLADE## operatives.\n\n" +
                        " **Deprecation:** Worsen the Save stat of friendly ##HUNTER CLADE## operatives by 1.",
    )

    val NeutralImperative = Passive(
        name = "Neutral Imperative",
        description =   " **Optimisation:** None.\n\n" +
                        " **Deprecation:** None.",
    )

    val DoctrinaImperatives = SelectionRuleListWithPrimary(
        name = "Doctrina Imperatives",
        description = "At the end of the Select Operatives step, select one **DOCTRINA IMPERATIVE** to be a Primary Mode for your kill team until the end of the battle (note that selecting a Primary Mode doesn’t automatically give you the effects of that **DOCTRINA IMPERATIVE** for the battle; you must still select it as a **STRATEGIC GAMBIT,** as below)\n\n " +
                "**STRATEGIC GAMBIT.** Select one **DOCTRINA IMPERATIVE** for friendly ##HUNTER CLADE## operatives to have until the Ready step of the next Strategy phase. Each **DOCTRINA IMPERATIVE** has both an Optimisation and a Deprecation rule. Both are in effect while your kill team has that **DOCTRINA IMPERATIVE.** Once per battle, when you select the **DOCTRINA IMPERATIVE** that’s your kill team’s Primary Mode, you can ignore its Deprecation rule.",
        ruleList = listOf(ProtectorImperative,ConquerorImperative,BulwarkImperative,AggressorImperative,NeutralImperative)
    )

    val teamRulesList = listOf<TeamRule>(DoctrinaImperatives)
}

object VoidscaredCorsairsRules
{
    val AeldariRaiders = Passive(
        name = "Aeldari Raiders",
        description = "Each friendly ##CORSAIR VOIDSCARRED## operative can perform a free **Dash** action during their activation.",
    )

    val Rifles = Passive(
        name = "Rifles",
        description = "Whenever a friendly ##CORSAIR VOIDSCARRED## operative is shooting with a shuriken rifle or ranger long rifle during an activation in which it hasn’t performed the **Charge, Fall Back** or **Reposition** action, that weapon has the xxAccurate 1xx weapon rule. Note that operative isn’t restricted from performing those actions after shooting.",
    )

    val teamRulesList = listOf<TeamRule>(AeldariRaiders,Rifles)
}

object KasrkinSquadRules
{
    val LightEmUp = Passive(
        name = "Light 'Em Up",
        description = "Whenever a friendly ##KASRKIN## operative is shooting, its ranged weapons have the xxSeverexx weapon rule if any of the following are true for the target:\n" +
                "• It’s ready.\n" +
                "• It’s not in cover.\n" +
                "• It’s being scanned (see **RECON-TROOPER).**"
    )

    val StrikeFast = Passive(
        name = "Strike Fast",
        description = "Whenever a friendly ##KASRKIN## operative is performing the **Reposition** action, add 1\" to its Move stat.",
    )

    val IceInYourVeins = Passive(
        name = "Ice In Your Veins",
        description = "Whenever a friendly ##KASRKIN## operative is fighting or retaliating, or an operative is shooting it, the first time an attack dice inflicts Normal Dmg of 3 or more on this operative during that sequence, that dice inflicts 1 less damage on it.",
    )

    val ForCadia = Passive(
        name = "For Cadia!",
        description = "Add 1 to the Atk stat of friendly ##KASRKIN## operatives’ melee weapons (to a maximum of 4). Whenever a friendly ##KASRKIN## operative is fighting, the first time you strike during that sequence, inflict 1 additional damage.",
    )

    val SkillAtArms = SelectionRuleList(
        name = "Skill At Arms",
        description = "**STRATEGIC GAMBIT.** Select a **SKILL AT ARMS** for friendly ##KASRKIN## operatives to have until the Ready step of the next Strategy phase.",
        ruleList = listOf(LightEmUp,StrikeFast,IceInYourVeins,ForCadia)
        )

    val teamRulesList = listOf<TeamRule>(SkillAtArms)
}

object PlagueMarinesRules
{
    val Poison = Passive(
        name = "Poison",
        description = "Some weapons in this team’s rules have the xxPoisonxx weapon rule.\n\n" +
                " ***Poison:** In the Resolve Attack Dice step, if you inflict damage with any successes, the operative this weapon is being used against (excluding friendly ##PLAGUE MARINE## operatives) gains one of your Poison tokens (if it doesn’t already have one). Whenever an operative that has one of your Poison tokens is activated, inflict 1 damage on it.",
    )

    val DisgustinglyResilient = Passive(
        name = "Disgustingly Resilient",
        description = "Whenever an attack dice inflicts damage of 3 or more on a friendly ##PLAGUE MARINE## operative, roll one D6: on a 4+, subtract 1 from that inflicted damage.",
    )

    val Astartes = Passive(
        name = "Astartes",
        description = "During each friendly ##PLAGUE MARINE## operative’s activation, it can perform either two **Shoot** actions or two **Fight** actions. If it’s two **Shoot** actions, a bolt pistol, boltgun or xxPSYCHICxx weapon must be selected for at least one of them. You cannot select the same ranged xxPSYCHICxx weapon more than once per activation.\n\n" +
                "Each friendly ##PLAGUE MARINE## operative can counteract regardless of its order.",
    )

    val teamRulesList = listOf<TeamRule>(Poison,DisgustinglyResilient,Astartes)
}

object LegionairesRules
{
    val Khorne = Passive(
        name = "Khorne - Wrathful Onslaught",
        description = "This operative’s melee weapons have the xxSeverexx weapon rule.",
    )

    val Nurgle = Passive(
        name = "Nurgle - Disgusting Vigour",
        description = "Whenever Normal Dmg of 3 or more is inflicted on this operative, roll one **D6:** on a 5+, subtract 1 from that inflicted damage.",
    )

    val Slaanesh = Passive(
        name = "Slaanesh - Unnatural Agility",
        description = "Add 1” to this operative’s Movement Stat.",
    )

    val Tzeentch = Passive(
        name = "Tzeentch - Empyreal Guidance",
        description = "This operative’s shoot weapons have the xxSeverexx weapon rule.",
    )

    val Undivided = Passive(
        name = "Undivided - Vicious Reavers",
        description = "Whenever this operative is shooting against, fighting against or retaliating against an enemy operative within 6” of it, this operative’s weapons have the xxCeaselessxx weapon rule.",
    )

    val MarkOfChaos = SelectionRuleList(
        name = "Mark Of Chaos",
        description = "When selecting a ##LEGIONARY## operative for the battle, you must choose one of the following keywords for it to have for that battle: **KHORNE, NURGLE, SLAANESH, TZEENTCH, UNDIVIDED.** Each operative’s keyword can be different, but a **BALEFIRE ACOLYTE** operative cannot have the **KHORNE** keyword.\n\n" +
                "Friendly ##LEGIONARY## operatives have an additional rule determined by this keyword. In addition, ##LEGIONARY## ploys have additional benefits for operatives with the relevant keyword.\n\n" +
                "This operative gains one ability from those listed below. The ability it gains depends on its keyword.",
        ruleList = listOf(Khorne,Nurgle,Slaanesh,Tzeentch,Undivided)
    )

    val Astartes = Passive(
        name = "Astartes",
        description = "During each friendly ##LEGIONARY## operative’s activation, it can perform either two **Shoot** actions or two **Fight** actions. If it’s two **Shoot** actions, a bolt pistol, boltgun or tainted bolt pistol must be selected for at least one of them.\n\n" +
                "Each friendly ##LEGIONARY## operative can Counteract regardless of its order.\n",
    )

    val teamRulesList = listOf<TeamRule>(MarkOfChaos,Astartes)
}

object NemesisClawRules
{
    val InMidnightClad = Passive(
        name = "In Midnight Clad",
        description = "Whenever an enemy operative is shooting a friendly ##NEMESIS CLAW## operative, that friendly operative is **obscured** if both of the following are true:\n" +
                "\n • It’s more than 6\" from enemy operatives it’s visible to.\n" +
                "\n • It has Heavy terrain within its control range, or any part of its base is underneath Vantage terrain.",
    )

    val Astartes = Passive(
        name = "Astartes",
        description = "During each friendly ##NEMESIS CLAW## operative’s activation, it can perform either two **Shoot** actions or two **Fight** actions. If it’s two **Shoot** actions, a bolt pistol, boltgun or scoped bolt pistol must be selected for at least one of them.\n\n" +
                "Each friendly ##NEMESIS CLAW## operative can counteract regardless of its order.",
    )

    val teamRulesList = listOf<TeamRule>(InMidnightClad,Astartes)
}