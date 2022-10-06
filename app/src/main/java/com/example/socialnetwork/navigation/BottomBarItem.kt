package com.example.socialnetwork.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.socialnetwork.R
import com.example.socialnetwork.presenter.destinations.*

enum class BottomBarItem(
    val direction: DirectionDestination,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Feed(FeedScreenDestination, Icons.Default.List, R.string.bottom_bar_item_feed),
    Search(SearchScreenDestination, Icons.Default.Search, R.string.bottom_bar_item_search),
    Friends(FriendsScreenDestination, Icons.Default.Person, R.string.bottom_bar_item_friends),
    Profile(ProfileScreenDestination, Icons.Default.Settings, R.string.bottom_bar_item_profile)
}