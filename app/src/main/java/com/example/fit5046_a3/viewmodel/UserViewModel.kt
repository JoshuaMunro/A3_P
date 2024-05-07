package com.example.fit5046_a3.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fit5046_a3.model.User
import com.example.fit5046_a3.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val cRepository: UserRepository
    init{
        cRepository = UserRepository(application)
    }
    val allUsers: LiveData<List<User>> = cRepository.allUsers.asLiveData()


    fun insertUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.insert(user)
    }

    fun updateUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.update(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        cRepository.delete(user)
    }

    fun getUserProfile(email: String): LiveData<User?> {
        return cRepository.getUserByEmail(email).asLiveData()
    }
}
