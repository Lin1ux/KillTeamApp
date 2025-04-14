package com.example.killteam

sealed class Screen(val route: String)
{
    object ScoreScreen : Screen("score_screen")
    object FractionScreen : Screen("fraction_screen/{RedPlayer}")
    object UnitScreen : Screen("unit_screen/{RedPlayer}")
    object UnitPreview : Screen("preview_screen/{RedPlayer}/{index}")

    fun FractionRoute(RedPlayer : Boolean) = "fraction_screen/$RedPlayer"

    fun UnitRoute(RedPlayer : Boolean) = "unit_screen/$RedPlayer"

    fun UnitPreviewRoute(RedPlayer: Boolean, index: Int): String
    {
        return "preview_screen/$RedPlayer/$index"
    }
}