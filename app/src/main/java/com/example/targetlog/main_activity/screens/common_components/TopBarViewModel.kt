package com.example.targetlog.main_activity.screens.common_components

import androidx.lifecycle.viewModelScope
import com.example.targetlog.domain.BluetoothController
import com.example.targetlog.main_activity.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TopBarViewModel @Inject constructor(
    private val bluetoothController: BluetoothController,
): AppViewModel() {
    private val _isConnectedState = MutableStateFlow(false)
    val isConnectedState: StateFlow<Boolean>
        get() =  _isConnectedState

    val isConnected: StateFlow<Boolean> = bluetoothController.isConnected


    init {
        //init observers
        bluetoothController.isConnected.onEach { isConnected->
            _isConnectedState.value = isConnected
        }.launchIn(viewModelScope)
    }

}