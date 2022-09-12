package com.rino.educationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rino.educationapp.core.entity.BottomNavItem
import com.rino.educationapp.ui.base.BottomNavigationBar
import com.rino.educationapp.ui.base.Screen
import com.rino.educationapp.ui.screens.*
import com.rino.educationapp.ui.theme.EducationAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(mainViewModel)
        }
    }
}

@Composable
fun MyApp(mainViewModel: MainViewModel) {
    EducationAppTheme {
        val navController = rememberNavController()
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigationBar(
                    items = listOf(
                        BottomNavItem(
                            name = Screen.Home.name,
                            route = Screen.Home.route,
                            icon = painterResource(
                                id = R.drawable.ic_home
                            )
                        ),
                        BottomNavItem(
                            name = Screen.Classes.name,
                            route = Screen.Classes.route,
                            icon = painterResource(
                                id = R.drawable.ic_pending_actions
                            )
                        ),
                        BottomNavItem(
                            name = Screen.Homework.name,
                            route = Screen.Homework.route,
                            icon = painterResource(
                                id = R.drawable.ic_event_note
                            )
                        ),
                        BottomNavItem(
                            name = Screen.Favorites.name,
                            route = Screen.Favorites.route,
                            icon = painterResource(
                                id = R.drawable.ic_star_border
                            )
                        )
                    ),
                    navController = navController,
                    onItemClick = { item ->
                        navController.navigate(item.route)
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Navigation(navController, mainViewModel)
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { HomeScreen(mainViewModel) }
        composable(Screen.Classes.route) { ClassesScreen(mainViewModel) }
        composable(Screen.Homework.route) { HomeworkScreen() }
        composable(Screen.Favorites.route) { FavoritesScreen() }
    }
}