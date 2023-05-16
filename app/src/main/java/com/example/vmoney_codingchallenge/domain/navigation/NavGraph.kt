package com.example.vmoney_codingchallenge.domain.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vmoney_codingchallenge.ui.screens.NavigatorContent
import com.example.vmoney_codingchallenge.ui.screens.PeopleContent
import com.example.vmoney_codingchallenge.ui.screens.RoomsContent

@Composable
fun MainNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.People.route
    ){
        composable(BottomBarScreen.Navigator.route){
            NavigatorContent()
        }
        composable(BottomBarScreen.People.route){
            PeopleContent()
        }
        composable(BottomBarScreen.Rooms.route){
            RoomsContent()
        }
    }
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){

    object Navigator: BottomBarScreen(
        route = "navigator",
        title = "Navigator",
        icon = Icons.Default.Home
    )

    object People: BottomBarScreen(
        route = "people",
        title = "People",
        icon = Icons.Default.Person
    )

    object Rooms: BottomBarScreen(
        route = "rooms",
        title = "Rooms",
        icon = Icons.Default.Home
    )
}