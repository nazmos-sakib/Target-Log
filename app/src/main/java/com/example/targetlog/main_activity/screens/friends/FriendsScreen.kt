package com.example.targetlog.main_activity.screens.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.commons.ADD_FRIENDS_SCREEN
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.main_activity.screens.personal.PersonalScreenViewModel
import com.example.targetlog.ui.theme.DarkGreen833
import com.example.targetlog.ui.theme.GreenLight


@Preview
@Composable
fun FriendsScreen(
    onClickNavigate:(String)->Unit={ _ -> },
    onBackClickNavigate:()->Unit={},
    viewModel: PersonalScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Friends",
                backNavigate = true,
                onBackClickNavigate = onBackClickNavigate,
                onBluetoothButtonClick = onClickNavigate
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, innerPadding.calculateTopPadding() + 30.dp, 30.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { onClickNavigate(ADD_FRIENDS_SCREEN) },
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
            ) {
                Text(text = "Add Friends")
            }
            Spacer(modifier = Modifier.height(30.dp))
            LazyColumn {
                items(10) {
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                            .padding(8.dp, 12.dp)
                    ) {
                        FriendsCard(
                            onClickNavigate = onClickNavigate
                        )
                    }
                }
            }
        }
    }
}