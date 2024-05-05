package com.example.fit5046_a3.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(drawerState: DrawerState) {
    Scaffold(
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor =
                    MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "Login") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }
                },
            )
        }
    ) { paddingValues ->
        // padding of the scaffold is enforced to be used
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "About Screen",
                    style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.size(30.dp))
                Text("This app was created as part of FIT5046 exercise ",
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
