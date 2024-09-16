package com.example.targetlog.main_activity.screens.personal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonalScreenViewModel @Inject constructor():
    com.example.targetlog.main_activity.screens.AppViewModel() {
    var username by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    init {
        updateUsername("Gintama")
        updateEmail("Gintama@email.com")
    }

    fun updateUsername(input: String) {
        username = input
    }

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

}