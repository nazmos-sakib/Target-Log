package com.example.targetlog.main_activity.screens.personal

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.GreenLight


@Preview
@Composable
fun ChangeDisplayNameScreen(
    onClickNavigate:(String)->Unit={ _ -> },
    onBackClickNavigate:()->Unit={},
    viewModel: PersonalScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "DISPLAY NAME",
                backNavigate = true,
                onBackClickNavigate = onBackClickNavigate,
                onBluetoothButtonClick = onClickNavigate
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, innerPadding.calculateTopPadding() + 30.dp, 30.dp, 0.dp)
        ) {
            Text(text = "Display Name", color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 15.dp),
                value = viewModel.username,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = DarkLight,
                    unfocusedContainerColor = DarkLight,
                    unfocusedIndicatorColor = DarkLight
                ),
                onValueChange = { username -> viewModel.updateUsername(username) },
            )

            //buttons
            Row(
                modifier  = Modifier.fillMaxWidth(),
            horizontalArrangement  = Arrangement.SpaceBetween,
            verticalAlignment  = Alignment.CenterVertically,
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(Color.Black, Color.White, Color.Black, Color.Black)
                ) {
                    Text(text = "Cancel")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .weight(1f)
                        .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
                ) {
                    Text(text = "Save")
                }
            }

        }
    }
}