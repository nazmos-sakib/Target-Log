package com.example.targetlog.main_activity.screens.sign_in

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.model.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService
):AppViewModel() {
    var email by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    var warningText by mutableStateOf("")
        private set

    var showWarningText by mutableStateOf(false)
        private set

    var showProgressBar by mutableStateOf(false)
        private set

    fun updateEmail(input: String) {
        email = input
    }

    fun updatePassword(input: String) {
        password = input
    }

    fun updateWarningText(input: String) {
        warningText = input
    }
    fun toggleShowWarningText( ) {
        showWarningText = !showWarningText
    }
    fun updateShowWarningText(input: Boolean ) {
        showWarningText = input
    }
    fun updateShowProgressBar(input: Boolean ) {
        showProgressBar = input
    }

    fun onLoginButtonClick(onClickNavigate: (String) -> Unit = { }){
        //email validating
        if (email.isEmpty()){
            updateWarningText("email needs to be filled")
            updateShowWarningText(true)
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            updateWarningText("email format is not correct")
            updateShowWarningText(true)
        } else{
            updateWarningText("")
            updateShowWarningText(false)
        }



        //password validating
        if (password.isEmpty()){
            updateWarningText("password must be provided")
            updateShowWarningText(true)
        } else{
            updateWarningText("")
            updateShowWarningText(false)
        }

        if (!showWarningText){
            updateShowProgressBar(true)
            runInCoroutineBlock {
                accountService.signInWithEmail(email,password)
                Log.d("SignUp->", "onNextButtonClick: inside coroutine")
                //onClickNavigate(SPLASH_SCREEN)
            }.invokeOnCompletion { exception ->
                if (exception != null) {
                    // The coroutine was cancelled or failed
                    updateWarningText(exception.message.toString())
                    updateShowWarningText(true)
                    updateShowProgressBar(false)
                } else {
                    // Coroutine completed successfully, no exception
                    onClickNavigate(SPLASH_SCREEN)
                    Log.d("SignUp->", "Coroutine completed successfully")
                }
            }
            Log.d("SignUp->", "onNextButtonClick: outside coroutine")
        }
    }


}