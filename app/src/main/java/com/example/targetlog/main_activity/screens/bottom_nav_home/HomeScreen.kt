package com.example.targetlog.main_activity.screens.bottom_nav_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.targetlog.R
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.main_activity.screens.common_components.StreakIndicator
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.ui.theme.DarkGreen833
import com.example.targetlog.ui.theme.DarkLight


@Preview
@Composable
fun HomeScreen(
    onClickGotoBluetoothScreen:(String)->Unit={ _ -> }
){

    Scaffold(
        topBar= { TopBar(title = "HOME",onBluetoothButtonClick = onClickGotoBluetoothScreen) },
        containerColor = Color(34, 48, 58, 255)
    ) { innerPadding->
        val configuration = LocalConfiguration.current




        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, innerPadding.calculateTopPadding(), 0.dp, 0.dp)
        ) {
            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(configuration.screenHeightDp.dp / 3),
                    contentAlignment = Alignment.Center
                ) {

                    Image(modifier = Modifier.fillMaxWidth( ),painter = painterResource(id = R.drawable.img_1),
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.Crop,
                        contentDescription = null)

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.weight(1f)) // Spacer will take up the remaining space

                        Banner()
                        Spacer(modifier = Modifier.weight(1f)) // Spacer will take up the remaining space

                        Box(
                            modifier = Modifier
                                //.align(Alignment.Bottom )
                                .background(Color.Transparent)
                                .padding(8.dp,20.dp)
                        ) {
                            StreakIndicator(19)
                        }
                    }

                }
            }



                items(10) {
                    Box(
                        modifier = Modifier
                            //.background(Color(31, 43, 54, 255), RoundedCornerShape(10.dp))
                            .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                            .padding(8.dp, 12.dp)
                    ) {
                        SessionCardPreview()
                    }
                }
            }


    }
}

