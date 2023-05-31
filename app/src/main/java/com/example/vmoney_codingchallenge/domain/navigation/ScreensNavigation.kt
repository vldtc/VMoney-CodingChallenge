package com.example.vmoney_codingchallenge.domain.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vmoney_codingchallenge.ui.screens.people.PeopleScreen
import com.example.vmoney_codingchallenge.ui.screens.rooms.RoomsScreen
import com.example.vmoney_codingchallenge.ui.screens.people.details.PeopleDetailsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = ScreensNavigation.People.route
    ){
        composable(ScreensNavigation.People.route){
            PeopleScreen(
                onClick = {peopleID ->
                    navController.navigate("${ScreensNavigation.PeopleDetails.route}/$peopleID")
                }
            )
        }
        composable(ScreensNavigation.Rooms.route){
            RoomsScreen()
        }
        composable("${ScreensNavigation.PeopleDetails.route}/{peopleID}"){ backStackEntry ->
            PeopleDetailsScreen(
                backStackEntry.arguments?.getString("peopleID")!!.toInt(),
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

sealed class ScreensNavigation(
    val route: String,
    var title: String,
    val icon: ImageVector
){
    object People: ScreensNavigation(
        route = "people",
        title = "People",
        icon = Icons.Default.Person
    )

    object Rooms: ScreensNavigation(
        route = "rooms",
        title = "Rooms",
        icon = Icons.Default.Home
    )

    object PeopleDetails: ScreensNavigation(
        route = "people_details",
        title = "",
        icon = Icons.Default.Person
    )
}