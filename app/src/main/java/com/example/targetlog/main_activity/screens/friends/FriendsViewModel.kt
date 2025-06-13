package com.example.targetlog.main_activity.screens.friends

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.data.db.firebase.User
import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FriendsViewModel   @Inject constructor(
    @ApplicationContext private val context: Context,
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
): AppViewModel() {
    private val _friendsList = MutableStateFlow<List<User>>(emptyList())  // Store the list of users
    val friendsList: StateFlow<List<User>> get() = _friendsList  // Expose the list as a StateFlow


    init {
        fetchFriends()
     }

    private fun fetchFriends() {
        runInCoroutineBlock {
            fireStoreService.getFriendList(
                currentUserId = accountService.currentUserId,
                onResult = {
                    Log.d("FriendsViewModel", "fetchFriends: numbers of Friends: ${it.size}")
                    _friendsList.value = it
                }
            )
        }.invokeOnCompletion { exception->
            runInCoroutineBlock {
                withContext(Dispatchers.Main){
                    Toast.makeText(context,exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
            if (exception != null) {
                Log.d("FriendsViewModel", "fetchFriends: exception: ${exception.message}")
            }else{
                Log.d("FriendsViewModel", "fetchFriends: exception: null exception")
            }
        }
    }


}