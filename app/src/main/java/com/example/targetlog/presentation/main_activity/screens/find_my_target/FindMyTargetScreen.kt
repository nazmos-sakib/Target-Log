package com.example.targetlog.presentation.main_activity.screens.find_my_target

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.R
import com.example.targetlog.presentation.main_activity.screens.common_components.TopBar
import com.example.targetlog.presentation.ui.theme.GreenLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FindMyTargetScreen(
    viewModel: FindMyTargetViewModel = hiltViewModel(),
    onBackClickNavigate: () -> Unit = { },
) {

    val isConnected by viewModel.isConnected.collectAsState()
    val isConnectedState by viewModel.isConnectedState.collectAsState()

    val pairedDevices by viewModel.pairedDevices.collectAsState(initial = emptyList())

    val sheetState = rememberStandardBottomSheetState(
        initialValue = SheetValue.PartiallyExpanded,
        skipHiddenState = true
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val scope = rememberCoroutineScope()

    val images = listOf(
        R.drawable.bluetooth_signal_weak,
        R.drawable.bluetooth_signal_medium,
        R.drawable.bluetooth_signal_semi_strong,
        R.drawable.bluetooth_signal_strong
    )

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1000L) // 1 second delay
            currentIndex = (currentIndex + 1) % images.size
        }
    }


    Scaffold(
        topBar = {
            TopBar(
                title = "FIND MY BALL",
                onBluetoothButtonClick = {},
                backNavigate  = true,
                onBackClickNavigate = onBackClickNavigate,
            )
        },
        containerColor = Color.Black
    ) { innerPadding ->

        innerPadding
        //Bottom sheet
        BottomSheetScaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = bottomSheetScaffoldState,
            sheetPeekHeight = 20.dp,
            sheetContent = {
                PairedDevicesBottomSheet(
                    pairedDeviceDomain = pairedDevices
                ) { device ->
                    viewModel.connectToESP(device) {
                        scope.launch { sheetState.partialExpand() }
                    }
                }
            },
            sheetContainerColor = Color.Gray
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp, 0.dp, 20.dp, 0.dp),
                verticalArrangement  = Arrangement.Center,
                horizontalAlignment  = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "FIND MY BALL",
                    modifier = Modifier.fillMaxWidth(.8f),
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 36.sp, fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 40.sp
                )
                Text(
                    text = "R1",
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    textAlign = TextAlign.Center, color = GreenLight,
                    fontSize = 36.sp,
                    lineHeight = 40.sp
                )
                Image(modifier = Modifier.padding(5.dp),painter = painterResource(id = images[currentIndex]), contentDescription = null)
                Text(
                    text = when(isConnected){
                        true -> "Signal is strong"
                        false -> "Not Connected"
                    } ,
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .padding(10.dp),
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 36.sp,
                    lineHeight = 40.sp
                )


            }
        }


    }
}

@Preview
@Composable
fun FindMyTargetScreenPreview() {
    FindMyTargetScreen()
}

