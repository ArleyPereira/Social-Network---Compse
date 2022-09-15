package com.example.socialnetwork.presenter.home

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.socialnetwork.navigation.BottomBar
import com.example.socialnetwork.presenter.NavGraphs
import com.example.socialnetwork.presenter.bottombar.BottomNavItem
import com.example.socialnetwork.presenter.destinations.FeedScreenDestination
import com.ramcosta.composedestinations.DestinationsNavHost

@ExperimentalMaterial3Api
@Composable
fun HomeScreen() {

    val navController = rememberNavController()

    val userLogged = false
    val startRoute = if(userLogged) FeedScreenDestination else NavGraphs.root.startRoute

    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        DestinationsNavHost(
            navGraph = NavGraphs.root,
            navController = navController,
            startRoute = startRoute
        )
    }
}

//@Composable
//fun BottomBar(navController: NavHostController) {
//    val screens = listOf(
//        BottomNavItem.Feed,
//        BottomNavItem.Search,
//        BottomNavItem.Friends,
//        BottomNavItem.Profile,
//    )
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//
//    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
//    if (bottomBarDestination) {
//        BottomNavigation {
//            screens.forEach { screen ->
//                AddItem(
//                    screen = screen,
//                    currentDestination = currentDestination,
//                    navController = navController
//                )
//            }
//        }
//    }
//}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}