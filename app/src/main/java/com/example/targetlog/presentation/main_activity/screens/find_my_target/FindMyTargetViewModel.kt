package com.example.targetlog.presentation.main_activity.screens.find_my_target

import androidx.lifecycle.viewModelScope
import com.example.targetlog.domain.BluetoothController
import com.example.targetlog.domain.BluetoothDeviceDomain
import com.example.targetlog.presentation.main_activity.screens.AppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FindMyTargetViewModel @Inject constructor(
    private val bluetoothController: BluetoothController,
): AppViewModel() {
    private val _isConnectedState = MutableStateFlow(false)
    val isConnectedState: StateFlow<Boolean>
        get() =  _isConnectedState

    val isConnected: StateFlow<Boolean> = bluetoothController.isConnected
    val pairedDevices: SharedFlow<List<BluetoothDeviceDomain>> = bluetoothController.pairedDevices

    init {
        //init observers
        bluetoothController.isConnected.onEach { isConnected->
            _isConnectedState.value = isConnected
        }.launchIn(viewModelScope)
    }

    fun connectToESP(device:BluetoothDeviceDomain,afterDeviceConnected: ()->Unit){
        bluetoothController.connectToESP(device){afterDeviceConnected()}//call the extension function
    }

}