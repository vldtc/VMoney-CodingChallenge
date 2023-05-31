package com.example.vmoney_codingchallenge.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.NavigationBarDefaults.containerColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vmoney_codingchallenge.domain.navigation.ScreensNavigation
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
        ScreensNavigation.People,
        ScreensNavigation.Rooms
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    if (currentDestination in listOf(
            ScreensNavigation.People.route,
            ScreensNavigation.Rooms.route
        )
    ) {
        NavigationBar(
            modifier = Modifier
                .height(50.dp),
            containerColor = MaterialTheme.colorScheme.primary,
        ) {
            screens.forEach { screen ->
                val selected = screen.route == currentDestination

                NavigationBarItem(
                    selected = selected,
                    onClick = { navController.navigate(screen.route) },
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(imageVector = screen.icon, contentDescription = "${screen.title} Icon")
                            Text(text = screen.title.uppercase(), fontSize = 8.sp)
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                        indicatorColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                        unselectedTextColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        }
    }
}


