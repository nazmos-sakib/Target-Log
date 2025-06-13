package com.example.targetlog.data.db.firebase

data class FriendList(
    val userId:String = "",
    val listOfFriends: List<String> = listOf()
)
