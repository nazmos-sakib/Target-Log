package com.example.targetlog.main_activity.screens.bottom_nav_profile

import androidx.lifecycle.viewModelScope
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.model.User
import com.example.targetlog.model.service.AccountService
import com.example.targetlog.model.service.FireStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
): AppViewModel() {

    private val _user = MutableStateFlow<User?>(null)  // A mutable state flow to hold user data
    val user: StateFlow<User?> get() = _user  // Expose the user as an immutable StateFlow

    init {
        observeCurrentUser()
    }

    private fun observeCurrentUser() {
        runInCoroutineBlock {
            accountService.currentUser.collect { user ->
                _user.value = user
            }
        }.invokeOnCompletion {

        }
    }

    fun onLogOutButtonClick(onClickNavigate: (String) -> Unit = { }) {

        runInCoroutineBlock {
            accountService.signOut()
            onClickNavigate(SPLASH_SCREEN)
        }
    }

}