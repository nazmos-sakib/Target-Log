package com.example.targetlog.model

import com.google.firebase.Timestamp

data class User(
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val skillLevel: String = "",
    val profilePicUrl: String = "",
    val timeStamp: Timestamp? = null,
    val isAnonymous: Boolean = false
)