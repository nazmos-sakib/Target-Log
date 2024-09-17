package com.example.targetlog.main_activity.screens.bottom_nav_profile

import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel  @Inject constructor(
    private val accountService: AccountService
): AppViewModel() {

    fun onLogOutButtonClick(onClickNavigate: (String) -> Unit = { }) {

        runInCoroutineBlock {
            accountService.signOut()
            onClickNavigate(SPLASH_SCREEN)
        }
    }

}