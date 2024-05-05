package com.example.fit5046_a3.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.launch
import androidx.compose.ui.unit.dp
import java.util.*

data class WorkoutRecords(val startTime: Calendar, val endTime: Calendar, val calories: Int)

val records = arrayOf(
    WorkoutRecords(createCalendar(2024, 3, 12, 8, 31), createCalendar(2024, 3, 12, 9, 42), 200),
    WorkoutRecords(createCalendar(2024, 3, 13, 7, 45), createCalendar(2024, 3, 13, 8, 55), 250),
    WorkoutRecords(createCalendar(2024, 3, 14, 9, 0), createCalendar(2024, 3, 14, 9, 30), 180),
    WorkoutRecords(createCalendar(2024, 3, 14, 9, 0), createCalendar(2024, 3, 14, 9, 30), 180),
    WorkoutRecords(createCalendar(2024, 3, 14, 9, 0), createCalendar(2024, 3, 14, 9, 30), 180),
    WorkoutRecords(createCalendar(2024, 3, 14, 9, 0), createCalendar(2024, 3, 14, 9, 30), 180),
    WorkoutRecords(createCalendar(2024, 3, 14, 9, 0), createCalendar(2024, 3, 14, 9, 30), 180),
    WorkoutRecords(createCalendar(2024, 3, 14, 9, 0), createCalendar(2024, 3, 14, 9, 30), 180)

)

fun createCalendar(year: Int, month: Int, day: Int, hour: Int, minute: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(year, month - 1, day, hour, minute)
    return calendar
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewWorkouts(drawerState: DrawerState) {
    Scaffold(
        topBar = {
            val coroutineScope = rememberCoroutineScope()
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(text = "About") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }
                },
            )
        }
    ) { paddingValues ->
        // padding of the scaffold is enforced to be used
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(

                text = "Past Workouts",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(records.size) { index ->
                    WorkoutRecordItem(record = records[index])
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun WorkoutRecordItem(record: WorkoutRecords) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("${record.startTime.get(Calendar.DAY_OF_MONTH)}/${record.startTime.get(Calendar.MONTH) + 1}/${record.startTime.get(Calendar.YEAR)}")
            Spacer(modifier = Modifier.size(10.dp))
            Text("${record.startTime.get(Calendar.HOUR_OF_DAY)}:${String.format("%02d", record.startTime.get(Calendar.MINUTE))} - ${record.endTime.get(Calendar.HOUR_OF_DAY)}:${String.format("%02d", record.endTime.get(Calendar.MINUTE))}")
            Spacer(modifier = Modifier.size(20.dp))
        }
        // Image
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_camera),
            contentDescription = "Picture",
            modifier = Modifier.size(80.dp) // Adjust size as needed
        )
        // Data Column
        Column {
            Text(
                text = "Duration: ${String.format("%02d", record.endTime.get(Calendar.MINUTE) - record.startTime.get(Calendar.MINUTE))} minutes",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Calories: ${record.calories}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}