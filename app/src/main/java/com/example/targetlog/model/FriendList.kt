package com.example.targetlog.model

data class FriendList(
    val userId:String = "",
    val listOfFriends: List<String> = listOf()
)
