package com.example.socialnetwork.navigation.graphs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.socialnetwork.navigation.routes.AuthRoutes
import com.example.socialnetwork.navigation.routes.GraphRoutes
import com.example.socialnetwork.presenter.auth.login.LoginScreen
import com.example.socialnetwork.presenter.auth.recover.RecoverAccountScreen
import com.example.socialnetwork.presenter.auth.register.RegisterScreen

/**
 * Fluxo de autenticação, registro e recuperação da conta
 * @author Arley Santana
 */
@ExperimentalMaterial3Api
fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = GraphRoutes.AuthGraph.route,
        startDestination = AuthRoutes.LoginAuthRoutes.route
    ) {
        composable(route = AuthRoutes.LoginAuthRoutes.route) {
            LoginScreen(
                onRegisterClick = { loginScreenToRegisterScreen(navController) },
                onRecoverClick = { loginScreenToRecoverScreen(navController) },
                onLoginInApp = {
                    navController.navigate(GraphRoutes.HomeGraph.route)
                    {
                        popUpTo(GraphRoutes.RootGraph.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = AuthRoutes.RegisterAuthRoutes.route) {
            RegisterScreen(onBackPressed = {
                onBackPressed(navController)
            })
        }

        composable(route = AuthRoutes.RecoverAccountAuthRoutes.route) {
            RecoverAccountScreen(onBackPressed = {
                onBackPressed(navController)
            })
        }
    }
}

/**
 * Realiza a navegação de retorno a tela anterior
 * @author Arley Santana
 */
private fun onBackPressed(navController: NavController) {
    navController.popBackStack()
}

/**
 * Realiza a navegação da tela de login para a tela de registro
 * @author Arley Santana
 */
private fun loginScreenToRegisterScreen(navController: NavController) {
    navController.navigate(AuthRoutes.RegisterAuthRoutes.route)
}

/**
 * Realiza a navegação da tela de login para a tela de recuperação da conta
 * @author Arley Santana
 */
private fun loginScreenToRecoverScreen(navController: NavController) {
    navController.navigate(AuthRoutes.RecoverAccountAuthRoutes.route)
}