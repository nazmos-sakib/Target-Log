package com.example.targetlog.main_activity.screens.workout_history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.main_activity.screens.common_components.TopBar
import com.example.targetlog.ui.theme.GreenBackground103
import com.example.targetlog.ui.theme.GreenLight
import com.example.targetlog.ui.theme.Purple40

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Workout_History(
    onClickGotoBluetoothScreen: (String) -> Unit = { _ -> },
    onBackClickNavigate: () -> Unit = { },
) {
    Scaffold(
        topBar = {
            TopBar(
                title = "SHOOTING FREESTYLE",
                onBluetoothButtonClick = onClickGotoBluetoothScreen,
                backNavigate  = true,
                onBackClickNavigate = onBackClickNavigate,
            )
        }
    ) { innerPadding ->


        var selectedTabIndex by remember {
            mutableIntStateOf(1)
        }
        val pagerState = rememberPagerState(
            pageCount = { 2 }
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp, innerPadding.calculateTopPadding() + 20.dp, 20.dp, 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {

                Text(
                    text = "WORKOUT COMPLETE",
                    modifier = Modifier.fillMaxWidth(.8f),
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 36.sp, fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    lineHeight = 40.sp
                )
                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "62",
                            modifier = Modifier,
                            textAlign = TextAlign.Center, color = Purple40,
                            fontSize = 36.sp, fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 40.sp
                        )
                        Text(
                            text = "Wall Ball Reps",
                            modifier = Modifier,
                            textAlign = TextAlign.Center, color = Color.White,
                            fontSize = 14.sp, fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 40.sp
                        )
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "2,576",
                            modifier = Modifier,
                            textAlign = TextAlign.Center, color = Purple40,
                            fontSize = 36.sp, fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 40.sp
                        )
                        Text(
                            text = "Lifetime Total",
                            modifier = Modifier,
                            textAlign = TextAlign.Center, color = Color.White,
                            fontSize = 14.sp, fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic,
                            lineHeight = 40.sp
                        )
                    }
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Bottom)
                            .padding(10.dp)
                            .weight(.3f),
                        painter = painterResource(id = R.drawable.share),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                //tab
                LaunchedEffect(selectedTabIndex) {
                    pagerState.animateScrollToPage(selectedTabIndex)
                }
                LaunchedEffect(pagerState.currentPage) {
                    selectedTabIndex = pagerState.currentPage
                }

                val unSelectedButtonColor = ButtonColors(
                    containerColor = GreenBackground103,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                )

                val selectedButtonColor = ButtonColors(
                    containerColor = GreenLight,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .background(GreenBackground103, ButtonDefaults.shape),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Button(
                        onClick = { selectedTabIndex = 1 },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = when (selectedTabIndex) {
                            0 -> unSelectedButtonColor
                            else -> selectedButtonColor
                        }
                    ) {
                        Text(
                            text = "Calender", textAlign = TextAlign.Center,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold,
                        )
                    }
                    Button(
                        onClick = { selectedTabIndex = 0 },
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        colors = when (selectedTabIndex) {
                            1 -> unSelectedButtonColor
                            else -> selectedButtonColor
                        }
                    ) {
                        Text(
                            text = "List", textAlign = TextAlign.Center,
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    }
                }





                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxSize(),
                    userScrollEnabled = false
                ) { page ->
                    when (page) {
                        1 -> {
                            HistoryByCalender(Modifier.fillMaxSize())
                        }

                        0 -> {
                            HistoryByList()
                        }
                    }

                }
            }

        }
    }
}
