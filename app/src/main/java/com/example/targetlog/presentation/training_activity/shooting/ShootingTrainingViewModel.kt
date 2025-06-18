package com.example.targetlog.presentation.training_activity.shooting

import android.util.Log
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.viewModelScope
import com.example.targetlog.commons.getCombinedDateTimeAsLong
import com.example.targetlog.data.UploadManager
import com.example.targetlog.db.firebase.repository.AccountService
import com.example.targetlog.db.firebase.repository.FireStoreService
import com.example.targetlog.db.room.repository.SessionRepository
import com.example.targetlog.domain.BluetoothController
import com.example.targetlog.domain.ConnectionResult
import com.example.targetlog.presentation.main_activity.screens.AppViewModel
import com.example.targetlog.presentation.training_activity.shooting.data.ShootingSessionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject


@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class ShootingTrainingViewModel @Inject constructor(
    private val bluetoothController: BluetoothController,
    private val sessionRepository: SessionRepository,
    private val accountService: AccountService,
    private val fireStoreService: FireStoreService,
) : AppViewModel() {
    private val TAG: String = "ShootingTrainingViewModel->"

    val isConnected: StateFlow<Boolean> = bluetoothController.isConnected


    private val _state = MutableStateFlow(ShootingSessionUiState())
    val state: StateFlow<ShootingSessionUiState>
        get() = _state

    /*
    val state = combine(
        _state,
        bluetoothController.isConnected,
        bluetoothController.errors,
    ){state, isConnected, err,   ->
        state.copy(
            isBluetoothConnected = isConnected,
            errMessage = err,
            messages = if(isConnected)  state.messages else emptyList()
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),_state.value)
*/
    init {
        //init observers active bluetooth connection the ESP
        bluetoothController.isConnected.onEach { isConnected ->
            _state.update { state ->
                state.copy(
                    isBluetoothConnected = isConnected,
                )
            }
        }.launchIn(viewModelScope)

        //error
        bluetoothController.errors.onEach { error ->
            _state.update { it.copy(errMessage = error) }
        }.launchIn(viewModelScope)

        //continuously observe for session start/fetch data from ESP
        //first time call startAWorkoutSession() that init sessionID
        //if session stared and it is not on pause then reactive JOB that-
        //fetch data from ESP else close the JOB
        viewModelScope.launch {
            state
                .map { it.isSessionStarted && !it.isTrainingOnPause }
                .distinctUntilChanged()
                .collect { canStart ->
                    if (canStart) {
                        state.value.sessionId?.let {
                            reActiveSessionDataCollectionJob()
                        }?: run{
                            startAWorkoutSession()
                        }
                    } else {
                        closeActiveSessionDataCollectionJob()
                    }
                }
        }
    }

    private var workoutSessionJob: Job? = null

    private fun startAWorkoutSession() {
        _state.update {
            it.copy(
                sessionId = getCombinedDateTimeAsLong(Date())
            )
        }
        Log.d(TAG, "startAWorkoutSession: Session-ID->${state.value.sessionId}")
        workoutSessionJob =
            bluetoothController.startAWorkoutSession().listen() //call the extension function
    }

    private fun reActiveSessionDataCollectionJob() {
        Log.d(TAG, "reActiveSessionDataCollectionJob: Session-ID->${state.value.sessionId}")
        workoutSessionJob =
            bluetoothController.startAWorkoutSession().listen() //call the extension function

    }
    fun closeActiveSessionDataCollectionJob() {
        workoutSessionJob?.cancel()
    }

    private fun endWorkoutSession() {
        Log.d(TAG, "endWorkoutSession: triggered")
        _state.update {
            it.copy(
                messages = emptyList(),
                lastMessage = "00",
                sessionId = null
            )
        }
        workoutSessionJob?.cancel()
    }

    private fun Flow<ConnectionResult>.listen(): Job {
        return onEach { result ->
            when (result) {
                is ConnectionResult.ConnectionEstablished -> {
                }

                is ConnectionResult.TransferSucceeded -> {
                    //update DataBase
                    _state.value.sessionId?.let { id->
                        //insert in Room DB
                        sessionRepository.insert(
                            trainingHand = _state.value.trainingHand,
                            sessionID = id,
                            message = result.message,
                            onFinishCallBack = {roomSession->
                                //insert in firebase-firestore sessionList Table
                                UploadManager.uploadData {  //viewModelScope.launch { // now the upload process will alive even when viewModel is changed
                                    fireStoreService.uploadSessionToFirebase(
                                        session = roomSession,
                                        currentUserId = accountService.currentUserId,
                                        onSuccess = { Log.d(TAG, "listen: firestore upload successful ${roomSession.entryUuid}")},
                                        onError = { Log.e(TAG, "listen: firestore upload error: $it")}
                                    )
                                }
                            }
                        )
                    }
                    //update UI
                    _state.update {
                        it.copy(
                            messages = it.messages + result.message,
                            lastMessage = result.message.message
                        )
                    }
                    Log.d(TAG, "listen: data received-" + result.message.message)
                    Log.d(TAG, "listen: total message size: ${state.value.messages.size}" )
                }

                is ConnectionResult.Error -> {

                }
            }
        }.catch { throwable ->
            bluetoothController.closeConnection()
            Log.d(TAG, "workout session error:" + throwable.message)
        }.launchIn(viewModelScope)
    }

    fun updateBottomSheetScaffold(
        scaffoldState: BottomSheetScaffoldState
    ) {
        _state.update {
            it.copy(
                scaffoldState = scaffoldState
            )
        }
    }

    fun startSession() {
        Log.d(TAG, "startSession: started")
        _state.update { it.copy(isSessionStarted = true) }
    }

    fun endSession() {
        _state.update { it.copy(isSessionStarted = false) }
    }

    fun startTraining() {
        _state.update { it.copy(isTrainingOnPause = false) }
    }

    fun pauseTraining() {
        _state.update { it.copy(isTrainingOnPause = true) }
    }

    fun updateTrainingHand(value: String) {
        _state.update { state ->
            state.copy(
                trainingHand = value
            )
        }
    }
    override fun onCleared() {
        super.onCleared()
        // Decide whether to cancel here or let it continue
        // uploadScope.cancel() // if you want to cancel
    }
}