package com.example.killteam

sealed class Screen(val route: String)
{
    object ScoreScreen : Screen("score_screen")
    object DiceScreen : Screen("dice_screen")
    object LoginScreen : Screen("login_screen")
    object WeaponeRuleScreen : Screen("weapon_rule_screen")
    object TacopScreen : Screen("tacop_screen")
    object ProfileScreen : Screen("profile_screen")
    object HistoryListScreen : Screen("history_list_screen")
    object FractionScreen : Screen("fraction_screen/{RedPlayer}")
    object UnitScreen : Screen("unit_screen/{RedPlayer}")
    object GamePreviewScreen : Screen("game_preview_screen/{index}")
    object UnitPreviewScreen : Screen("preview_screen/{RedPlayer}/{index}")
    object UnitAttack : Screen("attack_screen/{RedPlayer}/{index}/{weaponIndex}")

    fun ScoreScreenRoute() = "score_screen"

    fun DiceRoute() = "dice_screen"

    fun LoginScreenRoute() = "login_screen"

    fun ProfileScreenRoute() = "profile_screen"

    fun WeaponRuleRoute() = "weapon_rule_screen"

    fun TacopScreenRoute() = "tacop_screen"

    fun HistoryListScreenRoute() = "history_list_screen"

    fun FractionRoute(RedPlayer : Boolean) = "fraction_screen/$RedPlayer"

    fun UnitRoute(RedPlayer : Boolean) = "unit_screen/$RedPlayer"

    fun GamePreviewRoute(index: Int): String
    {
        return "game_preview_screen/$index"
    }

    fun UnitPreviewRoute(RedPlayer: Boolean, index: Int): String
    {
        return "preview_screen/$RedPlayer/$index"
    }

    fun UnitAttackRoute(RedPlayer: Boolean, index: Int, weaponIndex: Int): String
    {
        return "attack_screen/$RedPlayer/$index/$weaponIndex"
    }
}