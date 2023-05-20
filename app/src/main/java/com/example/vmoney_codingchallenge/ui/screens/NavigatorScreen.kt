package com.example.vmoney_codingchallenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vmoney_codingchallenge.domain.navigation.BottomBarScreen
import com.example.vmoney_codingchallenge.domain.navigation.MainNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigatorScreen(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        MainNavGraph(navController = navController)
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {

    val screens = listOf(
        BottomBarScreen.People,
        BottomBarScreen.Rooms
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar {
        screens.forEach { screen ->
            val selected = screen.route == currentDestination

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(screen.route) },
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = "${screen.title} Icon")
                }
            )
        }
    }

}


