package com.example.fit5046_a3.screens_2

import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.unit.dp
import com.example.fit5046_a3.model.User
import com.example.fit5046_a3.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale

//@Preview(showBackground = true)
@RequiresApi(0)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEntry(userViewModel: UserViewModel) {
    var emailadd by remember { mutableStateOf ("") }
    var password by remember { mutableStateOf ("") }
    var confirmPassword by remember { mutableStateOf ("") }
    var weight by remember { mutableStateOf ("") }
    var height by remember { mutableStateOf ("") }
    var uname by remember { mutableStateOf ("") }
    val gender = listOf("Male", "Female", "Confidential")
    var isExpanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(gender[0]) }
    val calendar = Calendar.getInstance()
    calendar.set(2024, 0, 1) // month (0) is January
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    var selectedDate by remember {
        mutableStateOf(calendar.timeInMillis)
    }
    Column(modifier = Modifier.padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top) {
        Text(text = "Let's get started!",
            style = MaterialTheme.typography.titleLarge)
        Text(
            text = "",
            modifier = Modifier.height(32.dp) // Setting the height of a blank line
        )
        OutlinedTextField(
            value = emailadd,
            onValueChange = { emailadd = it },
            label = { Text("Email address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        OutlinedTextField(
            value = uname,
            onValueChange = { uname = it },
            label = { Text("User name") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 18.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it },
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .focusProperties {
                        canFocus = false
                    }
                    .padding(bottom = 16.dp),
                readOnly = true,
                value = selectedGender,
                onValueChange = {},
                label = { Text("Gender") },
                //manages the arrow icon up and down
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
            )
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            )
            {
                gender.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedGender = selectionOption
                            isExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    showDatePicker = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                        //selectedDateMillis!! null safety because type declared as Long?
                        selectedDate = datePickerState.selectedDateMillis!!
                    }) {
                        Text(text = "OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDatePicker = false
                    }) {
                        Text(text = "Cancel")
                    }
                }
            ) //end of dialog
            { //still column scope
                DatePicker(
                    state = datePickerState
                )
            }
        }// end of if
        Button(
            onClick = {
                showDatePicker = true
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Pick Date of Birth")
        }
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
        Text(
            text = "Date of Birth: ${formatter.format(Date(selectedDate))}"
        )

        Text(
            text = "",
            modifier = Modifier.height(13.dp) // Setting the height of a blank line
        )
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Weight(optional)") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
//        OutlinedTextField(
//            value = height,
//            onValueChange = { height = it },
//            label = { Text("Height(optional)") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 12.dp)
//        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter // 将内容对齐到底部中心
        ) {
            Button(
                onClick = {
                    val user = User(
                        email = emailadd,
                        userName = uname,
                        password = password,
                        height = height.toDoubleOrNull() ?: 0.0,
                        weight = weight.toDoubleOrNull() ?: 0.0,
                        dateOfBirth = formatter.format(Date(selectedDate)),
                        gender = selectedGender
                    )
                    userViewModel.insertUser(user)

                },
                modifier = Modifier.align(Alignment.BottomCenter) // 将按钮垂直对齐到底部中心
            ) {
                Text(text = "Start your journey!")
            }
        }
    }


}