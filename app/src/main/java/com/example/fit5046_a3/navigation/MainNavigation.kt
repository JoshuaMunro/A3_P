package com.example.fit5046_a3.navigation


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fit5046_a3.screens.About
import com.example.fit5046_a3.screens.Counter
import com.example.fit5046_a3.screens.Home
import com.example.fit5046_a3.screens.LogWorkout
import com.example.fit5046_a3.screens.Login
import com.example.fit5046_a3.screens.Profile
import com.example.fit5046_a3.screens.SignUp
import com.example.fit5046_a3.screens.Statistics
import com.example.fit5046_a3.screens.ViewWorkouts
import com.example.fit5046_a3.screens.WorkoutMap
import com.example.fit5046_a3.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun MainNavigation (navViewModel: NavigationViewModel, userViewModel: UserViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue =
    DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(navController = navController,
            startDestination = Routes.Home.value,
            modifier = Modifier.padding(8.dp)
        ) {
            composable(Routes.Home.value) {
                Home(drawerState, navViewModel)
            }
            composable(Routes.Profile.value) {
                Profile(drawerState, navViewModel, userViewModel)
            }
            composable(Routes.About.value) {
                About(drawerState)
            }
            composable(Routes.ViewWorkouts.value) {
                ViewWorkouts(drawerState)
            }
            composable(Routes.LogWorkout.value) {
                LogWorkout(drawerState)
            }
            composable(Routes.WorkoutMap.value) {
                WorkoutMap(drawerState)
            }
            composable(Routes.Counter.value) {
                Counter(drawerState)
            }
            composable(Routes.Statistics.value) {
                Statistics(drawerState)
            }
            composable(Routes.SignUp.value) {
                SignUp(drawerState)
            }
            composable(Routes.Login.value) {
                Login(drawerState)
            }
        }
    }
}