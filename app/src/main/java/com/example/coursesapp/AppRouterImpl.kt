package com.example.coursesapp

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.example.presentation.core.navigation.AppRouter

internal class AppRouterImpl : AppRouter {

    private lateinit var navController: NavController

    fun setNavController(navController: NavController) {
        this.navController = navController
    }

    override fun navigateToLogin() {
        navController.navigate(
            resId = R.id.loginFragment,
            args = null,
            navOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        )
    }

    override fun navigateToHome() {
        navController.navigate(
            resId = R.id.mainFragment,
            args = null,
            navOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    inclusive = true
                }
            }
        )
    }
}