package com.example.targetlog.main_activity.screens.splash


import com.example.targetlog.commons.BOTTOM_NAV_HOME_SCREEN
import com.example.targetlog.commons.SIGN_IN_SCREEN
import com.example.targetlog.commons.SIGN_UP_SCREEN
import com.example.targetlog.commons.SPLASH_SCREEN
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    //private val accountService: AccountService
) : com.example.targetlog.main_activity.screens.AppViewModel() {
/*
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(HOME_SCREEN, SPLASH_SCREEN)
        else createAnonymousAccount(openAndPopUp)
    }

    private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.createAnonymousAccount()
            openAndPopUp(NOTES_LIST_SCREEN, SPLASH_SCREEN)
        }
    }*/

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
         openAndPopUp(BOTTOM_NAV_HOME_SCREEN, SPLASH_SCREEN)
         //openAndPopUp(SIGN_UP_SCREEN, SPLASH_SCREEN)
         //openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }
}