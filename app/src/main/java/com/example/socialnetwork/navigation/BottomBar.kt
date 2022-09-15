package com.example.socialnetwork.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.example.socialnetwork.presenter.destinations.FriendsScreenDestination
import com.example.socialnetwork.presenter.destinations.ProfileScreenDestination
import com.example.socialnetwork.presenter.destinations.SearchScreenDestination

@ExperimentalMaterial3Api
@Composable
fun BottomBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = isBottomMenu(currentDestination = currentDestination),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation {
            BottomBarItem.values().forEach {
                AddItem(
                    item = it,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun RowScope.AddItem(
    item: BottomBarItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = item.name)
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == item.direction.route } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = { navController.navigate(item.direction.route) }
    )
}

private fun isBottomMenu(currentDestination: NavDestination?): Boolean {
    return when (currentDestination?.route) {
        FeedScreenDestination.route,
        SearchScreenDestination.route,
        FriendsScreenDestination.route,
        ProfileScreenDestination.route -> {
            true
        }
        else -> false
    }
}