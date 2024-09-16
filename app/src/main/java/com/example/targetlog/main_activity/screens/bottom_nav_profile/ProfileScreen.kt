package com.example.targetlog.main_activity.screens.bottom_nav_profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.targetlog.commons.PERSONAL_SCREEN
import com.example.targetlog.R
import com.example.targetlog.commons.ANALYTICS_GRAPH_SCREEN
import com.example.targetlog.commons.FRIENDS_SCREEN
import com.example.targetlog.main_activity.screens.common_components.ProfileBanner
import com.example.targetlog.main_activity.screens.common_components.SettingsCard
import com.example.targetlog.main_activity.screens.common_components.StatusCard
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.ui.theme.GreenLight
import com.example.targetlog.ui.theme.Purple97
import com.example.targetlog.ui.theme.Yellow900

@Preview
@Composable
fun ProfileScreen(
    onClickNavigate:(String)->Unit={ _ -> }
){

    Scaffold(
        topBar= { TopBar(title = "PROFILE",onBluetoothButtonClick = onClickNavigate) }
    ) { innerPadding->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp,innerPadding.calculateTopPadding(),0.dp,0.dp)
                .padding(30.dp,30.dp,30.dp,0.dp)
        ) {
            item(){

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

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.profile_2, text = "Personal", route = PERSONAL_SCREEN, onClickNavigate = onClickNavigate)

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.people, text = "Friends", route = FRIENDS_SCREEN, onClickNavigate = onClickNavigate)

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.achievements, text = "Achievements")

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.analytics_2, text = "Shooting Analytics",route = ANALYTICS_GRAPH_SCREEN, onClickNavigate = onClickNavigate)

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.working_history, text = "Workout History")

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.baseline_search_24, text = "Find My Board")

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.app_settings, text = "App Settings")

                Spacer(modifier = Modifier.height(15.dp))
                SettingsCard(resourcesId = R.drawable.equipment_settings, text = "Equipment/Device Settings")
                Spacer(modifier = Modifier.height(15.dp))
            }
        }

    }
}

