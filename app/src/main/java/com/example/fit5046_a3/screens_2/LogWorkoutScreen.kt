package com.example.fit5046_a3.screens_2

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fit5046_a3.model.Workout
import com.example.fit5046_a3.viewmodel.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun LogWorkoutScreen(userId: Long) {
    // Access the WorkoutViewModel
    val workoutViewModel: WorkoutViewModel = viewModel()

    // Sample workout types
    val workoutTypes = listOf(
        "Basketball", "Tennis", "Swimming", "Running",
        "Cycling", "Hiking", "Fitness training", "Boxing"
    )

    // States to hold UI input data
    var isExpanded by remember { mutableStateOf(false) }
    var selectedWorkoutType by remember { mutableStateOf(workoutTypes[0]) }
    var selectedDate by remember { mutableStateOf(Calendar.getInstance().timeInMillis) }
    var selectedStartTime by remember { mutableStateOf(Time(0, 0)) }
    var selectedEndTime by remember { mutableStateOf(Time(0, 0)) }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePickerStart by remember { mutableStateOf(false) }
    var showTimePickerEnd by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }

    // Date and Time Picker States
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = selectedDate
    )
    val timePickerStateStart = rememberTimePickerState()
    val timePickerStateEnd = rememberTimePickerState()

    // Date formatter for display
    val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)

    // Workout form layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Workout Form",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 24.sp
        )

        // Fitness Type Dropdown
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
            modifier = Modifier.padding(top = 32.dp)
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .height(64.dp),
                readOnly = true,
                value = selectedWorkoutType,
                onValueChange = {},
                label = { Text("Please select workout type", fontSize = 16.sp) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                }
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                workoutTypes.forEach { workoutType ->
                    DropdownMenuItem(
                        text = { Text(workoutType, fontSize = 20.sp) },
                        onClick = {
                            selectedWorkoutType = workoutType
                            isExpanded = false
                        }
                    )
                }
            }
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Date Picker Button
        Button(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pick Workout Date",
                fontSize = 19.sp)
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp),
            fontSize = 20.sp,
            text = "Workout Date: ${dateFormatter.format(Date(selectedDate))}",
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // Time Picker Buttons
        Button(
            onClick = { showTimePickerStart = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pick Start Time",
                fontSize = 19.sp)
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 20.sp,
            text = "Start Time: ${DateFormat.timeToString(selectedStartTime)}"
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        Button(
            onClick = { showTimePickerEnd = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pick End Time",
                fontSize = 19.sp)
        }
        Text(
            modifier = Modifier.padding(top = 16.dp),
            fontSize = 20.sp,
            text = "End Time: ${DateFormat.timeToString(selectedEndTime)}"
        )

        Spacer(modifier = Modifier.weight(1f))

        // Submit Button
        Button(
            onClick = {
                // Create a new Workout object
                val workout = Workout(
                    userId = userId,
                    workoutType = selectedWorkoutType,
                    workoutDate = dateFormatter.format(Date(selectedDate)),
                    startTime = DateFormat.timeToString(selectedStartTime),
                    endTime = DateFormat.timeToString(selectedEndTime)
                )

                Log.d("Workout", "Attempting to insert workout: $workout")
                // Insert the workout using the ViewModel
                workoutViewModel.insertWorkout(workout)

                // Show confirmation dialog
                showConfirmationDialog = true
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Submit Workout",
                fontSize = 19.sp)
        }

        // DatePicker and TimePicker dialogs
        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showDatePicker = false
                            selectedDate = datePickerState.selectedDateMillis ?: Calendar.getInstance().timeInMillis
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }

        if (showTimePickerStart) {
            TimePickerDialog(
                onDismissRequest = { showTimePickerStart = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedStartTime = Time(timePickerStateStart.hour, timePickerStateStart.minute)
                            showTimePickerStart = false
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showTimePickerStart = false }) { Text("Cancel") }
                }
            ) {
                TimePicker(state = timePickerStateStart)
            }
        }

        if (showTimePickerEnd) {
            TimePickerDialog(
                onDismissRequest = { showTimePickerEnd = false },
                confirmButton = {
                    TextButton(
                        onClick = {
                            selectedEndTime = Time(timePickerStateEnd.hour, timePickerStateEnd.minute)
                            showTimePickerEnd = false
                        }
                    ) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showTimePickerEnd = false }) { Text("Cancel") }
                }
            ) {
                TimePicker(state = timePickerStateEnd)
            }
        }

        // Confirmation dialog
        if (showConfirmationDialog) {
            Dialog(
                onDismissRequest = { showConfirmationDialog = false }
            ) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Workout Submitted!",
                            fontSize = 22.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        TextButton(
                            onClick = { showConfirmationDialog = false }
                        ) {
                            Text("OK")
                        }
                    }
                }
            }
        }
    }
}



//@RequiresApi(Build.VERSION_CODES.O)
//@Preview(showBackground = true)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DateTimePickerComponent() {
//    //TODO add date picker state
////    val datePickerState = rememberDatePickerState()
////    var showDatePicker by remember { mutableStateOf(false) }
//
//    val calendar = Calendar.getInstance()
//    calendar.set(2024, 0, 1) // month (0) is January
//    val datePickerState = rememberDatePickerState(
//        initialSelectedDateMillis = Instant.now().toEpochMilli()
//    )
//    var showDatePicker by remember {
//        mutableStateOf(false)
//    }
//    var selectedDate by remember {
//        mutableStateOf(calendar.timeInMillis)
//    }
//
//    //TODO add time picker state
//    val timePickerState = rememberTimePickerState()
//    var showTimePicker1 by remember { mutableStateOf(false) }
//    var showTimePicker2 by remember { mutableStateOf(false) }
////    var selectedStartTime by remember { mutableStateOf<Time?>(null) }
//    var selectedStartTime by remember { mutableStateOf<Time?>(null) }
//    var selectedEndTime by remember { mutableStateOf<Time?>(null) }
//
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//    ) {
//
//        Text(text = "Select workout date", modifier = Modifier.padding(bottom = 16.dp))
//
//        //date picker
//        Button(
//            onClick = {
//                showDatePicker = true //changing the visibility state
//            },
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            Text(text = "Date Picker")
//        }
//
//        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
//        Text(
//            text = "Date of workout: ${formatter.format(Date(selectedDate))}"
//        )
//
//        Divider(modifier = Modifier.padding(vertical = 16.dp))
//
//        Text(text = "Select workout start time", modifier = Modifier.padding(bottom = 16.dp))
//
//
//        //time picker
//        Button(
//            onClick = {
//                showTimePicker1 = true //changing the visibility state
//            },
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            Text(text = "Time Picker")
//        }
//        // To-Do
//        Text(
//            text = selectedStartTime?.let { "Selected Time: ${DateFormat.timeToString(it)}" }
//                ?: "No Time Selected"
//        )
//
//        Divider(modifier = Modifier.padding(vertical = 16.dp))
//
//        Text(text = "Select workout end time", modifier = Modifier.padding(bottom = 16.dp))
//
//
//        //time picker
//        Button(
//            onClick = {
//                showTimePicker2 = true //changing the visibility state
//            },
//            modifier = Modifier.fillMaxWidth(),
//        ) {
//            Text(text = "Time Picker")
//        }
//        // To-Do
//        Text(
//            text = selectedEndTime?.let { "Selected Time: ${DateFormat.timeToString(it)}" }
//                ?: "No Time Selected",
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        Spacer(modifier = Modifier.weight(1f)) // Spacer to push content to top
//
//        // Button to submit workout log
//        Button(
//            onClick = {
//                // Implement logic to submit workout log
//
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text("Submit")
//        }
//
//    }
//
//    //TODO show date picker when state is true
//    // date picker component
//    if (showDatePicker) {
//        DatePickerDialog(
//            onDismissRequest = { /*TODO*/ },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        showDatePicker = false
//                        selectedDate = datePickerState.selectedDateMillis!!
//                    }
//                ) { Text("OK") }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = {
//                        showDatePicker = false
//                    }
//                ) { Text("Cancel") }
//            }
//        )
//        {
//            DatePicker(state = datePickerState)
//        }
//    }
//
//
//    //TODO show time picker when state is true
//    // time picker component
//    if (showTimePicker1) {
//        TimePickerDialog(
//            onDismissRequest = { /*TODO*/ },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        selectedStartTime = Time(timePickerState.hour, timePickerState.minute)
//                        showTimePicker1 = false
//                    }
//                ) { Text("OK") }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = {
//                        showTimePicker1 = false
//                    }
//                ) { Text("Cancel") }
//            }
//        )
//        {
//            TimePicker(state = timePickerState)
//        }
//    }
//
//    if (showTimePicker2) {
//        TimePickerDialog(
//            onDismissRequest = { /*TODO*/ },
//            confirmButton = {
//                TextButton(
//                    onClick = {
//                        selectedEndTime = Time(timePickerState.hour, timePickerState.minute)
//                        showTimePicker2 = false
//                    }
//                ) { Text("OK") }
//            },
//            dismissButton = {
//                TextButton(
//                    onClick = {
//                        showTimePicker2 = false
//                    }
//                ) { Text("Cancel") }
//            }
//        )
//        {
//            TimePicker(state = timePickerState)
//        }
//    }
//}



@Composable
fun TimePickerDialog(
    title: String = "Select Time",
    onDismissRequest: () -> Unit,
    confirmButton: @Composable (() -> Unit),
    dismissButton: @Composable (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                ),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    dismissButton?.invoke()
                    confirmButton()
                }
            }
        }
    }
}

data class Time(val hour: Int, val minute: Int)

object DateFormat {
    fun timeToString(time: Time): String {
        val hourStr = if (time.hour < 10) "0${time.hour}" else time.hour.toString()
        val minuteStr = if (time.minute < 10) "0${time.minute}" else time.minute.toString()
        return "$hourStr:$minuteStr"
    }
}

