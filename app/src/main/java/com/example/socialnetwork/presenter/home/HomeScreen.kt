package com.example.socialnetwork.presenter.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialnetwork.navigation.graphs.BottomBarNavGraph
import com.example.socialnetwork.navigation.routes.BottomBarRoutes
import com.example.socialnetwork.presenter.bottombar.BottomNavItem
import com.example.socialnetwork.ui.theme.ColorPrimaryLight
import com.example.socialnetwork.ui.theme.ColorSecondaryDark

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomBarNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavItem.Feed,
        BottomNavItem.Search,
        BottomNavItem.Friends,
        BottomNavItem.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = isBottomMenu(currentDestination = currentDestination),
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it })
    ) {
        BottomNavigation(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
            backgroundColor = ColorPrimaryLight,
            elevation = 5.dp,
            contentColor = ColorSecondaryDark
        ) {
            screens.forEach {
                AddItem(
                    item = it,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(
    item: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

    BottomNavigationItem(
//        label = {
//            Text(text = stringResource(id = item.title))
//        },
        icon = {
            Icon(
                painter = painterResource(id = if (selected) item.unselectedIcon else item.selectedIcon),
                contentDescription = stringResource(id = item.title)
            )
        },
        selected = selected,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(item.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

private fun isBottomMenu(currentDestination: NavDestination?): Boolean {
    return when (currentDestination?.route) {
        BottomBarRoutes.Feed.route,
        BottomBarRoutes.Search.route,
        BottomBarRoutes.Friends.route,
        BottomBarRoutes.Profile.route -> {
            true
        }
        else -> false
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun HomeScreenPreview() {
    val screens = listOf(
        BottomNavItem.Feed,
        BottomNavItem.Search,
        BottomNavItem.Friends,
        BottomNavItem.Profile,
    )
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
        screens.forEach {
            AddItem(
                item = it,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}