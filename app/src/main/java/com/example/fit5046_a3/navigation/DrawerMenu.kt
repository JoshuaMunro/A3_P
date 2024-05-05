package com.example.fit5046_a3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenu(val icon: ImageVector, val title: String, val
route: String)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, "Home", Routes.Home.value),
    DrawerMenu(Icons.Filled.Person, "Profile", Routes.Profile.value),
    DrawerMenu(Icons.Filled.Info, "About", Routes.About.value),
    DrawerMenu(Icons.Filled.ThumbUp, "View Workouts", Routes.ViewWorkouts.value),
    DrawerMenu(Icons.Filled.Create,"Log Workout", Routes.LogWorkout.value),
    DrawerMenu(Icons.Filled.List,"See gyms near you", Routes.WorkoutMap.value),
    DrawerMenu(Icons.Filled.PlayArrow,"Count your steps", Routes.Counter.value),
    DrawerMenu(Icons.Filled.Star,"Statistics", Routes.Statistics.value),

)
