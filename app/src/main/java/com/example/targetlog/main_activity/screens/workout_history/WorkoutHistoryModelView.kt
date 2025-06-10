package com.example.targetlog.main_activity.screens.workout_history

import android.util.Log
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewModelScope
import com.example.targetlog.data.db.SessionIdCount
import com.example.targetlog.data.db.Session
import com.example.targetlog.data.db.SessionGroup
import com.example.targetlog.db.repository.SessionRepository
import com.example.targetlog.main_activity.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class  WorkoutHistoryModelView  @Inject constructor(
    private val sessionRepository: SessionRepository
) : AppViewModel(){

    private val _sessionCategory = MutableStateFlow<List<SessionIdCount>>(emptyList())
    val sessionCategory: StateFlow<List<SessionIdCount>> = _sessionCategory

    // 2. Initialize sessionDetails as StateFlow if needed (optional)
    private val _sessionDetails = MutableStateFlow<List<Session>>(emptyList())
    val sessionDetails: StateFlow<List<Session>> = _sessionDetails.asStateFlow()

    private var currentOffset = 0
    private val pageSize = 20
    private var isLoading = false
    private var endReached = false

    private var currentSessionId: Long? = null
    private val loadMutex = Mutex()

    /*val  :MutableIntState
        get() = _totalWorkoutCount
*/
    // Backing property
    private val _totalWorkoutCount = MutableStateFlow(0)
    // Exposed to UI
    val totalWorkoutCount: StateFlow<Int> = _totalWorkoutCount.asStateFlow()

    val groupedSessions: StateFlow<List<SessionGroup>> = sessionDetails
        .map { groupSessionsByMonth(it) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    init {
        //updateSessionHistory()
        getTotalWorkOutCount()
    }

    fun initialize(sessionId: Long?) {
        Log.d("History-ViewModel", "initialize:sessionId $sessionId")
        if (currentSessionId != null) return // already initialized
        currentSessionId = sessionId
        loadNextPage()
        Log.d("WorkoutHistoryModelView", "initialize:size ${sessionDetails.value.size}")
    }

    private fun getTotalWorkOutCount(){
        viewModelScope.launch {
            sessionRepository.getTotalWorkoutCountFlow.collect {
                Log.d("WorkoutHistoryModelView", "updated TotalWorkOutCount: $it")
                _totalWorkoutCount.value = it
            }
        }
    }

    fun updateSessionDetailsBySessionId(sessionId:Long){
        Log.d("workoutViewModel->", "updateSessionDetailsBySessionId: session_ID$sessionId")
        viewModelScope.launch((Dispatchers.IO)) {
            try {
                _sessionDetails.value =
                    sessionRepository.getSessionDetailsBySessionId(sessionId)

            } catch (e: Exception) {
                println("Error-<: ${e.message}")
            }
        }

        Log.d("workoutViewModel->", "updateSessionDetailsBySessionId: "+sessionDetails.value.size)
    }

    /*fun loadNextPage() {
        if (isLoading || endReached) return

        isLoading = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newData = if (currentSessionId.isNotNull() || currentSessionId!! < 1) {
                    sessionRepository.getAllSessionsPaged(pageSize, currentOffset)
                } else {
                    sessionRepository.getSessionsBySessionIdPaged(currentSessionId!!, pageSize, currentOffset)
                }

                if (newData.isEmpty()) {
                    endReached = true
                } else {
                    currentOffset += newData.size
                    _sessionDetails.value += newData
                }
                Log.d("History-ViewModel", "loadNextPage:newData size: ${newData.size}")
            } catch (e: Exception) {
                println("Error loading page: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }*/

    fun loadNextPage() {
        viewModelScope.launch(Dispatchers.IO) {
            if (endReached) return@launch

            // Only one coroutine can enter this block at a time
            loadMutex.withLock {
                if (endReached) return@withLock

                try {
                    isLoading = true

                    val newData = if (currentSessionId == null || currentSessionId!! < 1) {
                        sessionRepository.getAllSessionsPaged(pageSize, currentOffset)
                    } else {
                        sessionRepository.getSessionsBySessionIdPaged(currentSessionId!!, pageSize, currentOffset)
                    }

                    if (newData.isEmpty()) {
                        endReached = true
                    } else {
                        currentOffset += newData.size
                        _sessionDetails.update { it + newData }
                    }

                    Log.d("History-ViewModel", "loadNextPage:newData size: ${newData.size}")
                } catch (e: Exception) {
                    println("Error loading page: ${e.message}")
                } finally {
                    isLoading = false
                }
            }
        }
    }

    private fun groupSessionsByMonth(sessions: List<Session>): List<SessionGroup> {
        val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        return sessions.groupBy { session ->
            formatter.format(session.timestamp)
        }.map { (monthYear, sessionList) ->
            SessionGroup(monthYear, sessionList)
        }
    }
}