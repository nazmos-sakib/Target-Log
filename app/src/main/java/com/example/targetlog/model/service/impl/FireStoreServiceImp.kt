package com.example.targetlog.model.service.impl

import com.example.targetlog.model.FriendList
import com.example.targetlog.model.User
import com.example.targetlog.model.service.FireStoreService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
 import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import okhttp3.internal.wait
import javax.inject.Inject

class FireStoreServiceImp @Inject constructor():FireStoreService {
    private val db:FirebaseFirestore = Firebase.firestore
    private val friendsListTable: CollectionReference = db.collection("friendsList")
    private val userTable:CollectionReference = db.collection("users")


    override suspend fun savePerson(user: User) {
        userTable.add(user).await()
    }

    override suspend fun retrieveUserByUserId(currentUserId: String ): User? {
        val querySnapShot = userTable.get().await()
        for (document in querySnapShot.documents){
            val person = document.toObject (User::class.java)
            person?.let {
                if (it.id == currentUserId){
                    return person
                }
            }
        }
        return null
    }
    override suspend fun getUsersFromFirestore(currentUserId: String,onResult: (List<User>) -> Unit) {
        filterFromFirestore(
            currentUserId = currentUserId,
            onResult =  onResult
        )
    }

    //get all the user except current login user
    private fun filterFromFirestore(currentUserId: String,onResult: (List<User>) -> Unit) {
         userTable
             .whereNotEqualTo("id",currentUserId)
             .get()
            .addOnSuccessListener { result ->
                val users = result.documents.mapNotNull { document ->
                    val user = document.toObject(User::class.java)  // Convert document to User object
                    /*if (user != null && user.id != currentUserId) {  // Filter out current user
                        user
                    } else {
                        null
                    }*/
                    user
                }
                onResult(users)
            }
            .addOnFailureListener { _ ->
                // Handle the error (for example, log it)
                onResult(emptyList())
            }
    }

    override suspend fun addUserToFriendList(
        currentUserId: String,
        addedUserId:String,
        onSuccess: ( ) -> Unit,
        onError:(String)->Unit
    ) {
         friendsListTable
            .whereEqualTo("userId",currentUserId)
            .get()
             .addOnSuccessListener { querySnapshot ->
                 if (!querySnapshot.isEmpty) {
                     // Document exists, get the first matching document
                     val document = querySnapshot.documents[0]
                     val documentRef = document.reference

                     // Update the "listOfFriend" field by adding the addedUserId
                     documentRef.update("listOfFriends", FieldValue.arrayUnion(addedUserId))
                         .addOnSuccessListener {
                             onSuccess() // Call the success callback
                         }
                         .addOnFailureListener { exception ->
                             onError(exception.message ?: "Failed to add friend")
                         }
                 } else {
                     // No document exists, create a new one
                     val newFriendData = hashMapOf(
                         "userId" to currentUserId,
                         "listOfFriends" to listOf(addedUserId)  // Initialize the list with addedUserId
                     )

                     // Add a new document with the currentUserId and listOfFriend
                     friendsListTable.add(newFriendData)
                         .addOnSuccessListener {
                             onSuccess() // Call the success callback
                         }
                         .addOnFailureListener { exception ->
                             onError(exception.message ?: "Failed to create friend list")
                         }
                 }
             }
             .addOnFailureListener { exception ->
                 onError(exception.message ?: "Error fetching friend list")
             }
    }


    override suspend fun getPotentialFriendsList(
        currentUserId: String,
        onSuccess: (List<User>) -> Unit,
        onError:(String)->Unit
    ) {
         friendsListTable
             .whereEqualTo("userId",currentUserId)
             .get()
            .addOnSuccessListener { result ->
                val friendsList = result.documents.mapNotNull { document ->
                     document.toObject(FriendList::class.java)  // Convert document to User object
                }

                if (friendsList.isEmpty()){
                    filterFromFirestore(
                        currentUserId = currentUserId,
                        onResult = {
                            onSuccess(it)
                        }
                    )
                } else {
                    userTable
                        .whereNotIn("id", friendsList[0].listOfFriends+currentUserId)
                        .get()
                        .addOnSuccessListener { u ->
                            val users = u.documents.mapNotNull { document ->
                                document.toObject(User::class.java)  // Convert document to User object
                            }
                            onSuccess(users)
                        }
                        .addOnFailureListener { exception ->
                            // Handle the error (for example, log it)
                            onSuccess(emptyList())
                            exception.message?.let { onError(it) }
                        }
                }

             }
            .addOnFailureListener { exception ->
                // Handle the error (for example, log it)
                onSuccess(emptyList())
                exception.message?.let { onError(it) }
            }
    }

    override suspend fun getFriendList(currentUserId: String, onResult: (List<User>) -> Unit) {
        friendsListTable
            .whereEqualTo("userId",currentUserId)
            .get()
            .addOnSuccessListener { result ->
                val friendsList = result.documents.mapNotNull { document ->
                    document.toObject(FriendList::class.java)  // Convert document to User object
                }
                if (friendsList.isNotEmpty()){
                    userTable
                        .whereIn("id", friendsList[0].listOfFriends)
                        .get()
                        .addOnSuccessListener { u ->
                            val users = u.documents.mapNotNull { document ->
                                document.toObject(User::class.java)  // Convert document to User object
                            }
                            onResult(users)
                        }
                        .addOnFailureListener { exception ->
                            // Handle the error (for example, log it)
                            onResult(emptyList())
                         }
                 }
            }
            .addOnFailureListener{
                onResult(emptyList())
            }
    }


}