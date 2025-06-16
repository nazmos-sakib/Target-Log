package com.example.targetlog.db.firebase.repository

import com.example.targetlog.data.db.firebase.User
import com.example.targetlog.data.db.room.Session

interface FireStoreService {

    suspend fun savePerson(user: User)
    suspend fun retrieveUserByUserId( currentUserId: String): User?
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
        onFailure: (Throwable) -> Unit
    )

    suspend fun uploadSessionToFirebase(
        session: Session,
        currentUserId: String,
        onSuccess: () -> Unit,
        onError: (String)->Unit
    )
    suspend fun uploadSessionsToFirebase(
        sessions: List<Session>,
        userId: String
    )
}