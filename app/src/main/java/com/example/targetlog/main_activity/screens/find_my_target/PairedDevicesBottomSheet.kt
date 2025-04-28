package com.example.targetlog.main_activity.screens.find_my_target

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.domain.BluetoothDeviceDomain

@Composable
fun PairedDevicesBottomSheet(
    pairedDeviceDomain: List<BluetoothDeviceDomain>,
    onDeviceClick: (BluetoothDeviceDomain)-> Unit = {},
    ) {

    LazyColumn(modifier = Modifier) {
        item {
            Text(text = "Paired Devices", fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        }
        items(pairedDeviceDomain){device->
            Text(
                text = device.name.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDeviceClick(device)  }
                    .padding(16.dp)
            )
        }

    }

}

@Preview
@Composable
fun PairedDevicesBottomSheetPreview() {
    PairedDevicesBottomSheet(
        pairedDeviceDomain = listOf(
            BluetoothDeviceDomain(
                name = "Esp1",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp2",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp3",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp4",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp5",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp6",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp7",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp8",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            ),
            BluetoothDeviceDomain(
                name = "Esp9",
                address = "23:SD:3F",
                type = 2,
                bondState = 0
            )
        )
    )
}