package com.example.targetlog.presentation.main_activity.screens.bottom_nav_home

import android.util.Log
import com.example.targetlog.commons.toSessionIdCountList
import com.example.targetlog.data.db.firebase.User
import com.example.targetlog.data.db.firebase.repository.FireStoreServiceImp
import com.example.targetlog.data.db.room.SessionIdCount
import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import com.example.targetlog.presentation.main_activity.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class NavHomeViewModel @Inject constructor(
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService
) : AppViewModel() {
    private val TAG = "NavHomeViewModel"

    private val _friendsTimeline = MutableStateFlow<Map<User, List<SessionIdCount>>>(emptyMap())
    val friendsTimeline: StateFlow<Map<User, List<SessionIdCount>>> = _friendsTimeline.asStateFlow()

    init {
        runInCoroutineBlock {
            fireStoreService.friendsTimeLine(
                accountService.currentUserId
            ).collect { users ->
                Log.d(TAG, "init: friends: ${users.size}")
                users.forEach { user ->
                    fireStoreService
                        .syncSessionsWithFirestore(
                            userId = user.id,
                            onSuccess = { sessions ->
                                Log.d(TAG, "init: for user: ${user.displayName} - ${sessions.size} session found")
                                updateFriendsTimeline(user, sessions.toSessionIdCountList())
                            },
                            onError = { e ->
                                Log.e(TAG, "init: $e")
                            }
                        )
                }

            }
        }
    }

    private fun updateFriendsTimeline(user: User, sessionIdCounts: List<SessionIdCount>) {
        _friendsTimeline.update { currentMap ->
            currentMap.toMutableMap().apply {
                this[user] = sessionIdCounts
            }
        }
    }
}