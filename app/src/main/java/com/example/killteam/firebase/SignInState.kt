package com.example.killteam.firebase

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)
