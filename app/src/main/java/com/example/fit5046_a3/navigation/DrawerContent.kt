package com.example.fit5046_a3.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale

@Composable
fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //header part
        Box(
            Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.LightGray)
        ) {
            Row {
                Image(
                    modifier = Modifier.size(150.dp),
                    imageVector = Icons.Filled.AccountCircle,
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
                Column() {
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { /*TODO*/},
                        modifier = Modifier.fillMaxWidth(0.9F)
                    ) {
                        Text("Google Login")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = {},
                        modifier = Modifier.fillMaxWidth(0.9F)) {
                        Text("Sign Up")
                    }

                }
            }

        }
        Spacer(modifier = Modifier.height(12.dp))
        menus.forEach {
            NavigationDrawerItem(
                label = { Text(text = it.title) },
                icon = { Icon(imageVector = it.icon,
                    contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
            Divider(color = Color.Gray)
        }
    }
}
