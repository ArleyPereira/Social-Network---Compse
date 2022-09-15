package com.example.socialnetwork.presenter.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.socialnetwork.R
import com.example.socialnetwork.navigation.routes.BottomBarRoutes

sealed class BottomNavItem(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
) {
    object Feed : BottomNavItem(
        route = BottomBarRoutes.Feed.route,
        title = R.string.bottom_bar_item_feed,
        selectedIcon = R.drawable.ic_home_line,
        unselectedIcon = R.drawable.ic_home_fill
    )

    object Search : BottomNavItem(
        route = BottomBarRoutes.Search.route,
        title = R.string.bottom_bar_item_search,
        selectedIcon = R.drawable.ic_search_line,
        unselectedIcon = R.drawable.ic_search_fill
    )

    object Friends : BottomNavItem(
        route = BottomBarRoutes.Friends.route,
        title = R.string.bottom_bar_item_friends,
        selectedIcon = R.drawable.ic_friends_line,
        unselectedIcon = R.drawable.ic_friends_fill
    )

    object Profile : BottomNavItem(
        route = BottomBarRoutes.Profile.route,
        title = R.string.bottom_bar_item_profile,
        selectedIcon = R.drawable.ic_user_settings_line,
        unselectedIcon = R.drawable.ic_user_settings_fill
    )

}
