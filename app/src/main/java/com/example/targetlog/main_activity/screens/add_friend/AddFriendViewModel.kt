package com.example.targetlog.main_activity.screens.add_friend

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.model.User
import com.example.targetlog.model.service.AccountService
import com.example.targetlog.model.service.FireStoreService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

@HiltViewModel
class AddFriendViewModel  @Inject constructor(
    @ApplicationContext private val context:Context,
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
): AppViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())  // Store the list of users
    val users: StateFlow<List<User>> get() = _users  // Expose the list as a StateFlow

    private val _potentialFriends = MutableStateFlow<List<User>>(emptyList())  // Store the list of users
    val potentialFriends: StateFlow<List<User>> get() = _potentialFriends  // Expose the list as a StateFlow

    init {
        fetchUsers()
        fetchPotentialFriends()
    }

    private fun fetchUsers() {
        runInCoroutineBlock {
            fireStoreService.getUsersFromFirestore(
                currentUserId = accountService.currentUserId,
                onResult = {
                    _users.value = it
                }
            )
        }.invokeOnCompletion { exception->
            runInCoroutineBlock {
                withContext(Dispatchers.Main){
                    Toast.makeText(context,exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun fetchPotentialFriends() {
        runInCoroutineBlock {
            fireStoreService.getPotentialFriendsList(
                currentUserId = accountService.currentUserId,
                onSuccess = {
                    _potentialFriends.value = it
                },
                onError = { err->
                    runInCoroutineBlock {
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,err ,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }.invokeOnCompletion { exception->
            runInCoroutineBlock {
                withContext(Dispatchers.Main){
                    Toast.makeText(context,exception?.message,Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun addUserToFriendList(
        addedUserId:String,
    ){
        runInCoroutineBlock {
            fireStoreService.addUserToFriendList(
                currentUserId = accountService.currentUserId,
                addedUserId = addedUserId,
                onSuccess = {
                    fetchPotentialFriends()
                },
                onError = { err->
                    runInCoroutineBlock {
                        withContext(Dispatchers.Main){
                            Toast.makeText(context,err ,Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }

    fun checkPermissionForContact(
        permission:String
    ):Boolean = ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
}