package com.example.targetlog.main_activity.screens.friends

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.main_activity.screens.common_components.DailyActivity
import com.example.targetlog.main_activity.screens.common_components.ProfileBanner
import com.example.targetlog.main_activity.screens.common_components.StatusCard
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.main_activity.screens.personal.PersonalScreenViewModel
import com.example.targetlog.ui.theme.DarkGreen833
import com.example.targetlog.ui.theme.GreenLight
import com.example.targetlog.ui.theme.Purple97
import com.example.targetlog.ui.theme.Yellow900


@Preview
@Composable
fun FriendProfileScreen(
    onClickNavigate:(String)->Unit={ _ -> },
    onBackClickNavigate:()->Unit={},
    viewModel: PersonalScreenViewModel = hiltViewModel()
){

    Scaffold(
        topBar= { TopBar(title = "FRIEND'S PROFILE", backNavigate = true,onBluetoothButtonClick = onClickNavigate, onBackClickNavigate = onBackClickNavigate) }
    ) { innerPadding->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, innerPadding.calculateTopPadding(), 0.dp, 0.dp)
                .padding(30.dp, 30.dp, 30.dp, 0.dp)
        ) {
            item{

                Box(
                    modifier = Modifier
                        .fillMaxWidth() ,
                    contentAlignment = Alignment.Center
                ) {
                    ProfileBanner()
                }

                Spacer(modifier = Modifier.height(30.dp))
                StatusCard(text = "Average Weekly Workouts", score = "99", scoreColor = Purple97)

                Spacer(modifier = Modifier.height(15.dp))
                StatusCard(text = "Fastest Shot(mph)", score = "99", scoreColor = Yellow900)

                Spacer(modifier = Modifier.height(15.dp))
                StatusCard(text = "Lifetime Shoots", score = "99,999,000", scoreColor = GreenLight)
            }
            item {
                Text(text = "JULY",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 30.dp)
                )
            }
            items(7){
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                        .padding(8.dp,12.dp)
                ) {
                    DailyActivity()
                }

            }
        }

    }
}