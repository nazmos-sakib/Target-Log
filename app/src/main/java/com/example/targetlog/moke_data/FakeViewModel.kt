package com.example.targetlog.moke_data

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.ViewModel
import com.example.targetlog.training_activity.shooting.data.ShootingSessionUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


open class FakeViewModel(
    private val _isConnected:  Boolean  = false
)  : ViewModel()  {

    private val _state = MutableStateFlow(
        ShootingSessionUiState(isBluetoothConnected = _isConnected)
    )
    val state :StateFlow<ShootingSessionUiState>
        get()  = _state

    @OptIn(ExperimentalMaterial3Api::class)
    fun updateBottomSheetScaffold(
        scaffoldState: BottomSheetScaffoldState
    ){
        _state.update {
            it.copy(
                //scaffoldState = scaffoldState
            )
        }
    }

}
