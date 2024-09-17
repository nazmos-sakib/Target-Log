package com.example.targetlog.main_activity.screens.sign_up

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.targetlog.commons.SPLASH_SCREEN
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.model.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel  @Inject constructor(
    private val accountService: AccountService
): AppViewModel() {
    var email by mutableStateOf("")
        private set
    var username by mutableStateOf("")
        private set
    var phoneNumber by mutableStateOf("")
        private set
    var dateOfBirth by mutableStateOf("")
        private set
    var skillLevel by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var retypePassword by mutableStateOf("")
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
    fun updateUsername(input: String) {
        username = input
    }
    fun updatePhoneNumber(input: String) {
        phoneNumber = input
    }
    fun updateDateOfBirth(input: String) {
        dateOfBirth = input
    }
    fun updateSkillLevel(input: String) {
        skillLevel = input
    }
    fun updatePassword(input: String) {
        password = input
    }
    fun updateRetypePassword(input: String) {
        retypePassword = input
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

    @OptIn(InternalCoroutinesApi::class)
    fun onNextButtonClick(onClickNavigate: (String) -> Unit = { }){
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

        //username validating
        if (username.isEmpty()){
            updateWarningText("username needs to be filled")
            updateShowWarningText(true)
        }else{
            warningText = " "
            updateShowWarningText(false)
        }

        //password validating
        if (password.isEmpty() and retypePassword.isEmpty()){
            updateWarningText("password must be provided")
            updateShowWarningText(true)
        } else if (password.length<8) {
            updateWarningText("password must be minimum 8 character")
            updateShowWarningText(true)
        } else if (password != retypePassword) {
            updateWarningText("Password does not match")
            updateShowWarningText(true)
        }else{
            updateWarningText(" ")
            updateShowWarningText(false)
        }

        if (!showWarningText){
            updateShowProgressBar(true)
            runInCoroutineBlock {
                accountService.createAccountWithEmailPassword(email,password)
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