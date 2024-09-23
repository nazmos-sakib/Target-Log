package com.example.targetlog.main_activity.screens.friends

import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.widget.Toast
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

@HiltViewModel
class FriendsViewModel   @Inject constructor(
    @ApplicationContext private val context: Context,
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
): AppViewModel() {
    private val _friendsList = MutableStateFlow<List<User>>(emptyList())  // Store the list of users
    val friendsList: StateFlow<List<User>> get() = _friendsList  // Expose the list as a StateFlow

    //val contentResolver: ContentResolver = TODO()

    init {
        fetchFriends()
     }

    private fun fetchFriends() {
        runInCoroutineBlock {
            fireStoreService.getFriendList(
                currentUserId = accountService.currentUserId,
                onResult = {
                    _friendsList.value = it
                }
            )
        }.invokeOnCompletion { exception->
            runInCoroutineBlock {
                withContext(Dispatchers.Main){
                    Toast.makeText(context,exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /*private fun query(
        uri: Uri, // the provider Uri
        projection: Array<String>, // the list of the columns to project
        selection: String? = null,  // the select clause (the WHERE)
        selectionArgs: Array<String>? = null, // the selection arguments (the WHERE arguments to replace)
        sort: String? = null // Definition of sort, offset & limit
    ): Cursor? {
        //return contentResolver.query(uri, projection, selection, selectionArgs, sort)
    }*/
}