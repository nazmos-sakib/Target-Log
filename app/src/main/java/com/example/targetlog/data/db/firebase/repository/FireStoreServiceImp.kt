package com.example.targetlog.data.db.firebase.repository

import android.util.Log
import com.example.targetlog.data.db.firebase.FirestoreSession
import com.example.targetlog.data.db.firebase.FirestoreSessionList
import com.example.targetlog.data.db.firebase.FriendList
import com.example.targetlog.data.db.firebase.User
import com.example.targetlog.data.db.room.Session
import com.example.targetlog.db.firebase.repository.FireStoreService
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class FireStoreServiceImp @Inject constructor() : FireStoreService {
    private val TAG = "FireStoreServiceImp"
    private val firebaseDb: FirebaseFirestore = Firebase.firestore
    private val friendsListTable: CollectionReference = firebaseDb.collection("friendsList")
    private val userTable: CollectionReference = firebaseDb.collection("users")
    private val sessionsTable: CollectionReference = firebaseDb.collection("sessions")


    override suspend fun savePerson(user: User) {
        userTable.add(user).await()
    }

    override suspend fun retrieveUserByUserId(currentUserId: String): User? {
        val querySnapShot = userTable.get().await()
        for (document in querySnapShot.documents) {
            val person = document.toObject(User::class.java)
            person?.let {
                if (it.id == currentUserId) {
                    Log.d("TAG", "retrieveUserByUserId: ${it.toString()}")
                    return person
                }
            }
        }
        return null
    }

    override suspend fun getUsersFromFirestore(
        currentUserId: String,
        onResult: (List<User>) -> Unit
    ) {
        filterFromFirestore(
            currentUserId = currentUserId,
            onResult = onResult
        )
    }

    //get all the user except current login user
    private fun filterFromFirestore(currentUserId: String, onResult: (List<User>) -> Unit) {
        userTable
            .whereNotEqualTo("id", currentUserId)
            .get()
            .addOnSuccessListener { result ->
                val users = result.documents.mapNotNull { document ->
                    val user =
                        document.toObject(User::class.java)  // Convert document to User object
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
        addedUserId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        friendsListTable
            .whereEqualTo("userId", currentUserId)
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
        onError: (String) -> Unit
    ) {
        friendsListTable
            .whereEqualTo("userId", currentUserId)
            .get()
            .addOnSuccessListener { result ->
                val friendsList = result.documents.mapNotNull { document ->
                    document.toObject(FriendList::class.java)  // Convert document to User object
                }

                if (friendsList.isEmpty()) {
                    filterFromFirestore(
                        currentUserId = currentUserId,
                        onResult = {
                            onSuccess(it)
                        }
                    )
                } else {
                    userTable
                        .whereNotIn("id", friendsList[0].listOfFriends + currentUserId)
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

    override suspend fun getFriendList(
        currentUserId: String,
        onResult: (List<User>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        friendsListTable
            .whereEqualTo("userId", currentUserId)
            .get()
            .addOnSuccessListener { result ->
                val friendsList = result.documents.mapNotNull { document ->
                    document.toObject(FriendList::class.java)  // Convert document to FriendList class object
                }
                if (friendsList.isNotEmpty()) {
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
                            onFailure(exception)
                            //onResult(emptyList())
                        }
                }else{
                    onFailure(Exception("No friends found"))
                }
            }
            .addOnFailureListener {
                onFailure(it)
                //onResult(emptyList())
            }
    }

    override suspend fun uploadSessionToFirebase(
        session: Session,
        currentUserId: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val sessionData = FirestoreSession(
            entryUuid = session.entryUuid, // include this if needed
            sessionId = session.sessionId,
            speed = session.speed,
            hand = session.hand,
            timestamp = session.timestamp.time
        )

        val documentRef = sessionsTable.document(currentUserId)

        documentRef.get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    // Append to existing array
                    documentRef.update("listOfSession", FieldValue.arrayUnion(sessionData))
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onError(it.message ?: "Failed to update session list") }
                } else {
                    // Create new document
                    val newData = mapOf(
                        "userId" to currentUserId,
                        "listOfSession" to listOf(sessionData)
                    )
                    documentRef.set(newData)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { onError(it.message ?: "Failed to create session document") }
                }
            }
            .addOnFailureListener { onError(it.message ?: "Error accessing session document") }
    }


    override suspend fun uploadSessionsToFirebase(sessions: List<Session>, userId: String) {
        val firestore = Firebase.firestore

        val firestoreSessions = sessions.map {
            FirestoreSession(
                entryUuid = it.entryUuid,
                sessionId = it.sessionId,
                speed = it.speed,
                hand = it.hand,
                timestamp = it.timestamp.time
            )
        }

        val sessionListDocument = FirestoreSessionList(
            userId = userId,
            listOfSession = firestoreSessions
        )

        firestore.collection("sessionsList")
            .document(userId)
            .set(sessionListDocument) // overwrites the entire document
    }

    override suspend fun syncSessionsWithFirestore(
        userId: String,
        onSuccess: (sessions: List<Session>) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val snapshot = sessionsTable.document(userId).get().await()
            if (!snapshot.exists()) {
                onError("No session data found for user.")
                return
            }

            val sessionList = snapshot.toObject(FirestoreSessionList::class.java)
                ?.listOfSession.orEmpty()

            val sessions = sessionList.map { firestoreSession ->
                Session(
                    entryUuid = firestoreSession.entryUuid,
                    sessionId = firestoreSession.sessionId,
                    speed = firestoreSession.speed,
                    hand = firestoreSession.hand,
                    timestamp = Date(firestoreSession.timestamp)
                )
            }
            Log.i(TAG, "syncSessionsWithFirestore: session size ${sessions.size}")
            onSuccess(sessions)
        } catch (e: Exception) {
            Log.e("Sync", "Sync error: ${e.message}", e)
            onError(e.message ?: "Sync failed")
        }
    }



}