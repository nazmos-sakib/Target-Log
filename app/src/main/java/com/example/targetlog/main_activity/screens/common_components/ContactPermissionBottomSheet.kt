package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.ui.theme.GreenLight
import com.example.targetlog.ui.theme.TopBarBlue

@Preview
@Composable
fun ContactPermissionBottomSheet(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBlue, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement  = Arrangement.Center,
            horizontalAlignment  = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(40.dp))
            Icon(
                painter = painterResource(id = R.drawable.manage_subscription),
                contentDescription = "",
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "Please allow APP to access to your Contacts seamlessly find your friends",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth(.7f)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
            ) {
                Text(text = "Settings")
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(TopBarBlue, Color.White, Color.Black, Color.Black)
            ) {
                Text(text = "Not Now")
            }
            Spacer(modifier = Modifier.height(30.dp))

        }

    }
}

//@Preview
@Composable
fun CustomDialogBox(
    rId:Int,
    text:String,
    greenText:String,
    blueText:String,
    onPositiveButtonClick:()->Unit,
    onNegativeButtonClick:()->Unit
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBlue, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ){
        Column(
            verticalArrangement  = Arrangement.Center,
            horizontalAlignment  = Alignment.CenterHorizontally,
        ) {

            Spacer(modifier = Modifier.height(40.dp))
            Icon(
                painter = painterResource(id = rId),
                contentDescription = "",
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = text,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 14.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth(.7f)
            )
            Spacer(modifier = Modifier.height(20.dp))

            //positive button
            Button(
                onClick = onPositiveButtonClick,
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .border(4.dp, GreenLight, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(GreenLight, Color.Black, GreenLight, GreenLight)
            ) {
                Text(text = greenText)
            }

            Spacer(modifier = Modifier.height(20.dp))
            //negative button
            Button(
                onClick = onNegativeButtonClick,
                modifier = Modifier
                    .fillMaxWidth(.6f)
                    .border(1.dp, Color.White, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(TopBarBlue, Color.White, Color.Black, Color.Black)
            ) {
                Text(text = blueText)
            }
            Spacer(modifier = Modifier.height(30.dp))

        }

    }
}

@Preview
@Composable
fun CustomDialogBoxPreview(){
    CustomDialogBox(
        rId = R.drawable.timer,
        text = "Are you sure you'd like to end this session?",
        greenText = "End Session",
        blueText = "Continue Session",
        onPositiveButtonClick = {},
        onNegativeButtonClick = {}
    )
}