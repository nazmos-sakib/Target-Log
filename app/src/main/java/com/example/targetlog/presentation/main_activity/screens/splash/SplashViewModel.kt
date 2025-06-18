package com.example.targetlog.presentation.main_activity.screens.splash


import android.util.Log
import com.example.targetlog.commons.BOTTOM_NAV_HOME_SCREEN
import com.example.targetlog.commons.SIGN_IN_SCREEN
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.presentation.main_activity.screens.AppViewModel
import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import com.example.targetlog.db.room.repository.SessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
    private val sessionRepository: SessionRepository
) :  AppViewModel() {
    private val TAG = "SplashViewModel"
    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) {
            runInCoroutineBlock {
                fireStoreService
                    .syncSessionsWithFirestore(
                        userId = accountService.currentUserId,
                        onSuccess = {
                            runInCoroutineBlock {
                                sessionRepository.insertAll(it)
                            }.invokeOnCompletion { e->
                                if (e==null){
                                    openAndPopUp(BOTTOM_NAV_HOME_SCREEN, SPLASH_SCREEN)
                                } else {
                                    Log.e(TAG, "onAppStart: ${e.message}" )
                                    openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
                                }
                            }
                        },
                        onError = { e ->
                            Log.e(TAG, "onAppStart: $e" )
                            openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
                        }
                    )
            }

        }
        else openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }

    /*
            private fun createAnonymousAccount(openAndPopUp: (String, String) -> Unit) {
                launchCatching {
                    accountService.createAnonymousAccount()
                    openAndPopUp(NOTES_LIST_SCREEN, SPLASH_SCREEN)
                }
            }

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
         openAndPopUp(BOTTOM_NAV_HOME_SCREEN, SPLASH_SCREEN)
         //openAndPopUp(SIGN_UP_SCREEN, SPLASH_SCREEN)
         //openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }

     */
}