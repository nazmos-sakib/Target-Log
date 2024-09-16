package com.example.targetlog.main_activity.screens.personal
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.commons.CHANGE_DISPLAY_NAME_SCREEN
import com.example.targetlog.commons.CHANGE_EMAIL_SCREEN
import com.example.targetlog.R
import com.example.targetlog.main_activity.screens.common_components.SettingsCard
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.ui.theme.DarkLight

@Preview
@Composable
fun PersonalScreen(
    onClickNavigate:(String)->Unit={ _ -> },
    onBackClickNavigate:()->Unit={},
    viewModel: PersonalScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "PERSONAL",
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
                readOnly = true,
                onValueChange = { username -> viewModel.updateUsername(username) },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { onClickNavigate(CHANGE_DISPLAY_NAME_SCREEN) },
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )

            val s1:String = "example@email.com"
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Email",color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 15.dp),
                value = s1,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = DarkLight,
                    unfocusedContainerColor = DarkLight,
                    unfocusedIndicatorColor = DarkLight
                ),
                readOnly = true,
                onValueChange = {   },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { onClickNavigate(CHANGE_EMAIL_SCREEN) },
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )

            //
            Spacer(modifier = Modifier.height(30.dp))
            SettingsCard(
                resourcesId = R.drawable.authentication_key,
                text = "Two-Factor Authentication"
            )

            Spacer(modifier = Modifier.height(15.dp))
            SettingsCard(resourcesId = R.drawable.manage_subscription, text = "Manage Subscription")

            Spacer(modifier = Modifier.height(15.dp))
            SettingsCard(resourcesId = R.drawable.delete_data, text = "Delete Data")

            Spacer(modifier = Modifier.height(15.dp))
            SettingsCard(resourcesId = R.drawable.delete_account, text = "Delete Account")

        }
    }
}