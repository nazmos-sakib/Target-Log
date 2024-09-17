package com.example.targetlog.main_activity.screens.sign_up

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animate
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.R
import com.example.targetlog.main_activity.screens.common_components.GreenButton
import com.example.targetlog.ui.theme.DarkLight
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onBackClickNavigate: () -> Unit = { },
    onClickNavigate: (String) -> Unit = { },
) {
    Scaffold(
        modifier = Modifier.padding(bottom = 0.dp),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
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
                .padding(20.dp, innerPadding.calculateTopPadding() + 20.dp, 20.dp, 0.dp)
                .verticalScroll(rememberScrollState())
                .imePadding()
                //.padding(WindowInsets.ime.asPaddingValues())
            ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            //email
            CustomFrame(
                text = "Email Address",
                textFieldValue = viewModel.email,
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            ) { email ->
                viewModel.updateEmail(email)
            }
            Spacer(modifier = Modifier.height(10.dp))

            //FUll Name
            CustomFrame(
                text = "Full Name",
                textFieldValue = viewModel.username,
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { fullName ->
                viewModel.updateUsername(fullName)
            }
            Spacer(modifier = Modifier.height(10.dp))

            //Phone Number
            CustomFrame(
                text = "Phone Number",
                textFieldValue = viewModel.phoneNumber,
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { phoneNumber ->
                viewModel.updatePhoneNumber(phoneNumber)
            }
            Spacer(modifier = Modifier.height(10.dp))

            //DateOfBirth
            CustomFrame(
                text = "Date Of Birth",
                textFieldValue = viewModel.dateOfBirth,
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { dateOfBirth ->
                viewModel.updateDateOfBirth(dateOfBirth)
            }
            Spacer(modifier = Modifier.height(10.dp))


            //Skill Level
            CustomFrame(
                text = "Skill Level",
                textFieldValue = viewModel.skillLevel,
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min)
            ) { skillLevel ->
                viewModel.updateSkillLevel(skillLevel)
            }
            Spacer(modifier = Modifier.height(20.dp))


            //password
            var showPassword by remember {
                mutableStateOf(false)
            }
            Text(text = "Create Password", color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 25.dp),
                value = viewModel.password,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = DarkLight,
                    unfocusedContainerColor = DarkLight,
                    unfocusedIndicatorColor = DarkLight
                ),
                onValueChange = { password -> viewModel.updatePassword(password) },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { showPassword = !showPassword },
                        painter = painterResource(
                            id = when (showPassword) {
                                true -> R.drawable.password_show
                                false -> R.drawable.password_hide
                            }
                        ),
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                visualTransformation = when (showPassword) {
                    false -> PasswordVisualTransformation()
                    true -> VisualTransformation.None
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )

            //retype password
            var showRetypePassword by remember {
                mutableStateOf(false)
            }
            Text(text = "Retype Password", color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 20.dp),
                value = viewModel.retypePassword,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors().copy(
                    focusedContainerColor = DarkLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedIndicatorColor = DarkLight,
                    unfocusedContainerColor = DarkLight,
                    unfocusedIndicatorColor = DarkLight
                ),
                onValueChange = { password -> viewModel.updateRetypePassword(password) },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable { showRetypePassword = !showRetypePassword },
                        painter = painterResource(
                            id = when (showRetypePassword) {
                                true -> R.drawable.password_show
                                false -> R.drawable.password_hide
                            }
                        ),
                        contentDescription = "",
                        tint = Color.White
                    )
                },
                visualTransformation = when (showRetypePassword) {
                    false -> PasswordVisualTransformation()
                    true -> VisualTransformation.None
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
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

            //warnings
            AnimatedVisibility(visible = viewModel.showWarningText) {
                Text(text = viewModel.warningText, modifier = Modifier)
            }

            Spacer(modifier = Modifier.height(20.dp))

            //submit section
            var isSubmitButtonEnable by remember {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = isUserAgreed ) {
                isSubmitButtonEnable = isUserAgreed
            }
            LaunchedEffect(key1 =   viewModel.showProgressBar) {
                isSubmitButtonEnable = viewModel.showProgressBar
            }
            Box(
                modifier  = Modifier
                    .fillMaxWidth()
                    .height(intrinsicSize = IntrinsicSize.Min),
                contentAlignment  = Alignment.Center,
                propagateMinConstraints  = false,
            ){

                GreenButton(
                    onButtonClick = {
                        viewModel.onNextButtonClick(onClickNavigate)
                    },
                    enabled = isSubmitButtonEnable
                ) {
                    Text(text = "Next")
                }

                if (viewModel.showProgressBar){
                    CircularProgressIndicator(
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

        }
    }
}

@Composable
fun CustomFrame(
    modifier: Modifier = Modifier,
    text: String,
    textFieldValue: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
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
        keyboardOptions = keyboardOptions
    )
}


