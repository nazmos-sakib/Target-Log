package com.example.targetlog.training_activity.shooting

import android.util.Log
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.example.targetlog.domain.BluetoothController
import com.example.targetlog.domain.BluetoothDeviceDomain
import com.example.targetlog.domain.BluetoothMessage
import com.example.targetlog.domain.ConnectionResult
import com.example.targetlog.main_activity.screens.AppViewModel
import com.example.targetlog.training_activity.shooting.data.ShootingSessionUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


@OptIn(ExperimentalMaterial3Api::class)
@HiltViewModel
class ShootingTrainingViewModel @Inject constructor(
    private val bluetoothController: BluetoothController,
): AppViewModel() {
    private val TAG:String = "ShootingTrainingViewModel->"

    val isConnected: StateFlow<Boolean> = bluetoothController.isConnected


    private val _state = MutableStateFlow(ShootingSessionUiState())
    val state : StateFlow<ShootingSessionUiState>
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
        bluetoothController.isConnected.onEach { isConnected->
            _state.update { state->
                state.copy(
                    isBluetoothConnected = isConnected,
                ) }
        }.launchIn(viewModelScope)

        //error
        bluetoothController.errors.onEach { error->
            _state.update {  it.copy(  errMessage = error ) }
        }.launchIn(viewModelScope)

        //continuously observe for session start/fetch data from ESP
        viewModelScope.launch {
            state
                .map { it.isSessionStarted && !it.isTrainingOnPause }
                .distinctUntilChanged()
                .collect { canStart ->
                    if (canStart) {
                        startAWorkoutSession()
                    } else {
                        endWorkoutSession()
                    }
                }
        }
    }
    private var workoutSessionJob : Job?  = null

    private fun startAWorkoutSession(){
        Log.d(TAG, "startAWorkoutSession: ")
        workoutSessionJob = bluetoothController.startAWorkoutSession().listen() //call the extension function
    }

    private fun endWorkoutSession() {
        Log.d(TAG, "endWorkoutSession: triggered")
        workoutSessionJob?.cancel()
    }

        private fun Flow<ConnectionResult>.listen(): Job {
        return onEach { result->
            when(result){
                is ConnectionResult.ConnectionEstablished ->{
                }

                is ConnectionResult.TransferSucceeded ->{
                    _state.update {
                        it.copy(
                            messages = it.messages +  result.message ,
                            lastMessage = result.message.message
                        )
                    }
                    Log.d(TAG, "listen: data received-"+result.message.message)
                }

                is ConnectionResult.Error -> {

                }
            }
        }.catch { throwable->
            bluetoothController.closeConnection()
            Log.d(TAG,"workout session error:"+throwable.message)
        }.launchIn(viewModelScope)
    }
    fun updateBottomSheetScaffold(
        scaffoldState: BottomSheetScaffoldState
    ){
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
}