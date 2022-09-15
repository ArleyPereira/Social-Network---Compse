package com.example.socialnetwork.navigation.routes

sealed class ProfileRoutes(val route: String) {
    object EditProfileRoutes : ProfileRoutes("edit_profile_screen")
}
