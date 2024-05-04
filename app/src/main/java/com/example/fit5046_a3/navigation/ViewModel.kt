package com.example.fit5046_a3.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
class NavigationViewModel: ViewModel() {
    val name:MutableState<String> = mutableStateOf("")
    fun setName(newName: String) {
        name.value=newName
    }
}
