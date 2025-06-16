package com.example.targetlog.presentation.main_activity.screens.bottom_nav_profile

import android.util.Log
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.presentation.main_activity.screens.AppViewModel
import com.example.targetlog.data.db.firebase.User
import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import com.example.targetlog.db.room.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
    private val sessionRepository: SessionRepository
): AppViewModel() {
    private val TAG = "ProfileViewModel"
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
            val signOutResult = runCatching { accountService.signOut() }

            if (signOutResult.isSuccess) { // ✅ Signed out successfully
                val deleteResult = runCatching { sessionRepository.deleteAllData() }
                if (!deleteResult.isSuccess) {
                    // ❌ Signed out, but failed to clear DB (rare but possible)
                    Log.e(TAG, "SignOut: Failed to clear local data: ${deleteResult.exceptionOrNull()?.message}")
                }
            } else {
                // ❌ Sign-out failed — don't delete DB
                Log.e(TAG, "SignOut: Failed to sign out: ${signOutResult.exceptionOrNull()?.message}")
            }
        }.invokeOnCompletion { throwable ->
            if (throwable == null) {
                // Success
                Log.d(TAG, "SignOut: succeed")
                onClickNavigate(SPLASH_SCREEN)
            } else {
                // Handle error
                Log.e(TAG, "SignOut: Failed: ${throwable.message}")
            }
        }
    }

}