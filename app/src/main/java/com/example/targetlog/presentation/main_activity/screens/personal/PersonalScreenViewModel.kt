package com.example.targetlog.presentation.main_activity.screens.personal

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.targetlog.presentation.main_activity.screens.AppViewModel
import com.example.targetlog.data.db.firebase.User
import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PersonalScreenViewModel  @Inject constructor(
    @ApplicationContext private val context: Context,
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
): AppViewModel() {
    private val _user = mutableStateOf(User())
    val user: State<User> get() = _user  // Expose the list as a StateFlow


    var username by mutableStateOf(_user.value.displayName)
        private set

    var email by mutableStateOf(_user.value.email)
        private set

    var password by mutableStateOf("")
        private set

    init {
        getUser()
        //updateUsername(_user.value.displayName)
        //updateEmail(_user.value.email)
    }

    private fun getUser(){
        runInCoroutineBlock {
            fireStoreService.retrieveUserByUserId(accountService.currentUserId)?.let {
                _user.value = it
                updateUsername(it.displayName)
                updateEmail(it.email)
            }
        }.invokeOnCompletion { exception->
            runInCoroutineBlock {
                withContext(Dispatchers.Main){
                    Toast.makeText(context,exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
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