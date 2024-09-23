package com.example.targetlog.model.service

import com.example.targetlog.model.User

interface FireStoreService {

    suspend fun savePerson(user: User)
    suspend fun retrieveUserByUserId( currentUserId: String):User?
    suspend fun getUsersFromFirestore(currentUserId: String,onResult: (List<User>) -> Unit)
    suspend fun addUserToFriendList(
        currentUserId: String,
        addedUserId:String,
        onSuccess: ( ) -> Unit,
        onError:(String)->Unit
    )

    suspend fun getPotentialFriendsList(
        currentUserId: String,
        onSuccess: (List<User>) -> Unit,
        onError:(String)->Unit
    )

    suspend fun getFriendList(
        currentUserId: String,
        onResult: (List<User>) -> Unit,
    )
}