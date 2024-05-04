package com.example.fit5046_a3.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class DrawerMenu(val icon: ImageVector, val title: String, val
route: String)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.Home, "Home", Routes.Home.value),
    DrawerMenu(Icons.Filled.Person, "Profile", Routes.Profile.value),
    DrawerMenu(Icons.Filled.Info, "About", Routes.About.value)
)
