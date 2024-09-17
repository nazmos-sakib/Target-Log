package com.example.targetlog.model

data class User (
    val id: String = "",
    val email: String = "",
    val provider: String = "",
    val displayName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val skillLevel: String = "",
    val profilePicUrl: String = "",
    val timeStrap: String = "",
    val isAnonymous: Boolean = true
)