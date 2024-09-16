package com.example.targetlog.main_activity.screens.sign_up

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.main_activity.screens.common_components.GreenButton
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.GreenDark
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun SignUpScreen(
    onBackClickNavigate: () -> Unit = { },
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_24),
                    contentDescription = "bluetooth",
                    tint = Color.White,
                    modifier = Modifier.clickable { onBackClickNavigate() }
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Email or Username",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, innerPadding.calculateTopPadding() + 20.dp, 20.dp, 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            //email
            CustomFrame(
                text = "Email Address",
                textFieldValue = "",
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { email ->

            }
            Spacer(modifier = Modifier.height(10.dp))

            //FUll Name
            CustomFrame(
                text = "Full Name",
                textFieldValue = "",
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { fullName ->

            }
            Spacer(modifier = Modifier.height(10.dp))

            //Phone Number
            CustomFrame(
                text = "Phone Number",
                textFieldValue = "",
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { phoneNumber ->

            }
            Spacer(modifier = Modifier.height(10.dp))

            //DateOfBirth
            CustomFrame(
                text = "Date Of Birth",
                textFieldValue = "",
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { dateOfBirth ->

            }
            Spacer(modifier = Modifier.height(10.dp))


            //Skill Level
            CustomFrame(
                text = "Skill Level",
                textFieldValue = "",
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { skillLevel ->

            }
            Spacer(modifier = Modifier.height(20.dp))


            //password
            Text(text = "Create Password", color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 25.dp),
                value = "",
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = DarkLight,
                    unfocusedContainerColor = DarkLight,
                    unfocusedIndicatorColor = DarkLight
                ),
                onValueChange = { password -> },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { },
                        painter = painterResource(id = R.drawable.password_eye_24),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )

            //retype password
            Text(text = "Retype Password", color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),
                value = "",
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = DarkLight,
                    unfocusedContainerColor = DarkLight,
                    unfocusedIndicatorColor = DarkLight
                ),
                onValueChange = { password -> },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { },
                        painter = painterResource(id = R.drawable.password_eye_24),
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            )

            var isUserAgreed by remember {
                mutableStateOf(false)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Checkbox(
                    modifier = Modifier,
                    checked = isUserAgreed,
                    onCheckedChange = { isUserAgreed = it },
                    colors = CheckboxColors(
                        checkedCheckmarkColor = Color.Black,
                        uncheckedCheckmarkColor = Color.Transparent,
                        checkedBoxColor = GreenLight,
                        uncheckedBoxColor = DarkLight,
                        disabledCheckedBoxColor = Color.Transparent,
                        disabledUncheckedBoxColor = Color.Transparent,
                        disabledIndeterminateBoxColor = Color.Transparent,
                        checkedBorderColor = DarkLight,
                        uncheckedBorderColor = DarkLight,
                        disabledBorderColor = DarkLight,
                        disabledUncheckedBorderColor = DarkLight,
                        disabledIndeterminateBorderColor = DarkLight
                    )
                )
                Text(text = "I certify that I am at lest 13 years of age", color = Color.White)
            }
            Spacer(modifier = Modifier.height(20.dp))
            GreenButton(
                onButtonClick = {},
                enabled = isUserAgreed
            ) {
                Text(text = "Next")
            }
        }
    }
}

@Composable
fun CustomFrame(
    modifier: Modifier = Modifier,
    text: String,
    textFieldValue: String,
    onValueChange: (String) -> Unit
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier.padding(0.dp, 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 5.dp),
        value = textFieldValue,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors().copy(
            focusedContainerColor = DarkLight,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = DarkLight,
            unfocusedContainerColor = DarkLight,
            unfocusedIndicatorColor = DarkLight
        ),
        onValueChange = { onValueChange(it) },
    )
}


