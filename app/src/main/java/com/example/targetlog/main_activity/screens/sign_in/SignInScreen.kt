package com.example.targetlog.main_activity.screens.sign_in

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.R
import com.example.targetlog.commons.SIGN_UP_SCREEN
import com.example.targetlog.main_activity.screens.common_components.GreenButton
import com.example.targetlog.ui.theme.DarkLight

@Preview
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onClickNavigate: (String) -> Unit = { },
) {
    Scaffold(
        modifier = Modifier ,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "LOGIN",
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
                .padding(20.dp, innerPadding.calculateTopPadding()+20.dp, 20.dp, 20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {

            //email
            CustomFrame(
                text = "Email Address", textFieldValue = viewModel.email,
                modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            ) { email->
                viewModel.updateEmail(email)
            }
            Spacer(modifier = Modifier.height(20.dp))

            //password
            var showPassword by remember {
                mutableStateOf(false)
            }
            Text(text = "Password", color = Color.White)
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 15.dp),
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

            Spacer(modifier = Modifier.height(20.dp))

            //warnings
            AnimatedVisibility(visible = viewModel.showWarningText) {
                Text(text = viewModel.warningText, modifier = Modifier)
            }

            Spacer(modifier = Modifier.height(20.dp))

            //submit section
            var isSubmitButtonEnable by remember {
                mutableStateOf(true)
            }

            LaunchedEffect(key1 =   viewModel.showProgressBar) {
                //isSubmitButtonEnable = viewModel.showProgressBar
            }
            Box(
                modifier = Modifier.fillMaxWidth( )
                .wrapContentSize(align = Alignment.Center)
                .fillMaxWidth(0.65f),
                contentAlignment = Alignment.Center
            ){
                GreenButton(
                    onButtonClick = {
                        viewModel.onLoginButtonClick(onClickNavigate)
                    },
                    //enabled = isSubmitButtonEnable
                ){
                    Text(text = "Login")
                }
                if (viewModel.showProgressBar){
                    CircularProgressIndicator(
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }//submit button end

            Spacer(modifier = Modifier.height(20.dp))


            Text(text = "Forget Password?", color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Create New Account", color = Color.White,
                modifier = Modifier.fillMaxWidth().clickable {
                    onClickNavigate(SIGN_UP_SCREEN)
                },
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Composable
fun CustomFrame(
    modifier: Modifier = Modifier,
    text:String,
    textFieldValue:String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit
){
    Text(text = text, color = Color.White, fontSize = 18.sp, modifier = Modifier.padding(0.dp,10.dp))
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
        onValueChange = {  onValueChange(it) },
        keyboardOptions = keyboardOptions
    )
}


