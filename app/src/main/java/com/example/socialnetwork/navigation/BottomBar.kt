package com.example.socialnetwork.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.example.socialnetwork.presenter.destinations.FriendsScreenDestination
import com.example.socialnetwork.presenter.destinations.ProfileScreenDestination
import com.example.socialnetwork.presenter.destinations.SearchScreenDestination
import com.example.socialnetwork.ui.theme.ColorPrimaryLight
import com.example.socialnetwork.ui.theme.ColorSecondaryDark

@ExperimentalMaterial3Api
@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = isBottomMenu(currentDestination = currentDestination),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation(
            modifier = Modifier
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)
                )
                .clip(shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp)),
            backgroundColor = Color.White,
            contentColor = ColorSecondaryDark
        ) {
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
        selected = currentDestination?.route == item.direction.route,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(item.direction.route) {
                launchSingleTop = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                restoreState = true
            }
        }
    )
}

private fun isBottomMenu(currentDestination: NavDestination?): Boolean {
    return when (currentDestination?.route) {
        FeedScreenDestination.route,
        SearchScreenDestination.route,
        FriendsScreenDestination.route,
        ProfileScreenDestination.route -> true
        else -> false
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun BottomBarPreview() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
        backgroundColor = ColorPrimaryLight,
        elevation = 5.dp,
        contentColor = ColorSecondaryDark
    ) {
        BottomBarItem.values().forEach {
            AddItem(
                item = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}