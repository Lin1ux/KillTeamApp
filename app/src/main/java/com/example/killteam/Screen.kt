package com.example.killteam

sealed class Screen(val route: String)
{
    object MainScreen : Screen("main_screen")
    object ScoreScreen : Screen("score_screen")

    fun withArgs(vararg args: String): String
    {
        return buildString()
        {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}