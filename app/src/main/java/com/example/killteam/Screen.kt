package com.example.killteam

sealed class Screen(val route: String)
{
    object ScoreScreen : Screen("score_screen")
    object FractionScreen : Screen("fraction_screen/{RedPlayer}")
    object UnitScreen : Screen("unit_screen/{RedPlayer}")
    object UnitSelectionScreen : Screen("unit_selection_screen/{RedPlayer}")

    fun FractionRoute(RedPlayer : Boolean) = "fraction_screen/$RedPlayer"

    fun UnitRoute(RedPlayer : Boolean) = "unit_screen/$RedPlayer"

    fun UnitSelectionRoute(RedPlayer : Boolean) = "unit_selection_screen/$RedPlayer"
}