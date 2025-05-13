package com.example.killteam

sealed class Screen(val route: String)
{
    object ScoreScreen : Screen("score_screen")
    object FractionScreen : Screen("fraction_screen/{RedPlayer}")
    object UnitScreen : Screen("unit_screen/{RedPlayer}")
    object UnitPreview : Screen("preview_screen/{RedPlayer}/{index}")
    object UnitAttack : Screen("attack_screen/{RedPlayer}/{index}/{weaponIndex}")

    fun ScoreScreenRoute() = "score_screen"

    fun FractionRoute(RedPlayer : Boolean) = "fraction_screen/$RedPlayer"

    fun UnitRoute(RedPlayer : Boolean) = "unit_screen/$RedPlayer"

    fun UnitPreviewRoute(RedPlayer: Boolean, index: Int): String
    {
        return "preview_screen/$RedPlayer/$index"
    }

    fun UnitAttackRoute(RedPlayer: Boolean, index: Int, weaponIndex: Int): String
    {
        return "attack_screen/$RedPlayer/$index/$weaponIndex"
    }
}