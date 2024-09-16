package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.targetlog.commons.BLUETOOTH_SCREEN
import com.example.targetlog.R
import com.example.targetlog.commons.FIND_MY_TARGET_SCREEN
import com.example.targetlog.ui.theme.TopBarBlue


//@Preview
@Composable
fun TopBar(
    title:String,
    backNavigate:Boolean?=null,
    onBackClickNavigate:( )->Unit={ },
    onBluetoothButtonClick:(String )->Unit={ _ -> }
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBlue)
            .padding(PaddingValues(15.dp, 0.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            backNavigate?.let {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_24),
                    contentDescription = "bluetooth",
                    tint = Color.White,
                    modifier = Modifier.clickable { onBackClickNavigate() }
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

            Text(
                text =  title,
                color= Color.White
            )
        }



        Button(
            onClick = {
                //onBluetoothButtonClick(BLUETOOTH_SCREEN)
                onBluetoothButtonClick(FIND_MY_TARGET_SCREEN)
                      },
            shape = RoundedCornerShape(4.dp),
            colors =  ButtonColors(Color.Black, Color.White, Color.Black, Color.Black),
            contentPadding = PaddingValues(5.dp, 1.dp)
        ) {
            Row(
                modifier = Modifier
            ) {
                Text(text = "R1",fontWeight= FontWeight.Bold)
                Icon(
                    painter = painterResource(id = R.drawable.baseline_bluetooth_24),
                    contentDescription = "bluetooth")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_24),
                    contentDescription = "bluetooth")
            }
        }
    }
}

@Preview
@Composable
fun TopBarPreview(
    title:String="Top Bar",
    backNavigate:Boolean?=null,
    onBackNavigate:( )->Unit={ },
    onBluetoothButtonClick:(String )->Unit={ _ -> }
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBlue)
            .padding(PaddingValues(15.dp, 0.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            backNavigate?.let {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow_24),
                    contentDescription = "bluetooth",
                    tint = Color.White,
                    modifier = Modifier.clickable {  }
                )
                Spacer(modifier = Modifier.width(10.dp))
            }

            Text(
                text =  title,
                color= Color.White
            )
        }


        Button(
            onClick = { onBluetoothButtonClick(BLUETOOTH_SCREEN) },
            shape = RoundedCornerShape(4.dp),
            colors =  ButtonColors(Color.Black, Color.White, Color.Black, Color.Black),
            contentPadding = PaddingValues(5.dp, 1.dp)
        ) {
            Row(
                modifier = Modifier
            ) {
                Text(text = "R1",fontWeight= FontWeight.Bold)
                Icon(
                    painter = painterResource(id = R.drawable.baseline_bluetooth_24),
                    contentDescription = "bluetooth")
                Icon(
                    painter = painterResource(id = R.drawable.baseline_check_24),
                    contentDescription = "bluetooth")
            }
        }
    }
}
