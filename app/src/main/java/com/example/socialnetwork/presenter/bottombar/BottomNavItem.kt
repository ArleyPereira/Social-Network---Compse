package com.example.socialnetwork.presenter.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Feed : BottomNavItem(
        route = "bottom_feed",
        title = "Feed",
        icon = Icons.Default.Home
    )

    object Search : BottomNavItem(
        route = "bottom_search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object Friends : BottomNavItem(
        route = "bottom_friends",
        title = "Amigos",
        icon = Icons.Default.Person
    )

    object Profile : BottomNavItem(
        route = "bottom_profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )

}
