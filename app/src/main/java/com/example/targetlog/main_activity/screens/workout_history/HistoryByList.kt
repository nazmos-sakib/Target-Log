package com.example.targetlog.main_activity.screens.workout_history

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.commons.getDateTimeParts
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.data.db.room.Session
import com.example.targetlog.main_activity.screens.common_components.DateBoxButton
import com.example.targetlog.training_activity.common_component.ShareDeleteDropdownMenu
import com.example.targetlog.ui.theme.DarkGreen833
import java.util.Date


/*@Preview
@Composable
fun HistoryByListPreview() {
    HistoryByList(sessionId = 0L)
}*/

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryByList(
    item: Session,
    modifier: Modifier
) {

    //Log.d("TAG", "HistoryByList: $index")
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
            .padding(0.dp, 12.dp)
    ) {
        /*if (index % 2 == 0)
            //WorkoutHistoryCard1( modifier = Modifier.animateItemPlacement(), )
        else{}*/

        WorkoutHistoryCard2(
            modifier = modifier,
            speed = item.speed,
            hand = item.hand,
            timestamp = item.timestamp,
        )
    }


}


@Preview
@Composable
fun WorkoutHistoryCard1Preview() {
    WorkoutHistoryCard1(
        speed = "34",
        hand = "Right",
        timestamp = Date()
    )
}

@Composable
fun WorkoutHistoryCard1(
    modifier: Modifier = Modifier,
    speed: String?,
    hand: String?,
    timestamp: Date,
    onSizeChange: (DpSize) -> Unit = {}
) {
    val density = LocalDensity.current
    val timeDetails = getDateTimeParts(timestamp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                density.run {
                    onSizeChange(
                        DpSize(
                            it.size.width.toDp(),
                            it.size.height.toDp()
                        )
                    )
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DateBoxButton(
                date = timeDetails["date"] ?: "00",
                month = timeDetails["month"] ?: "00"
            )
            Column(
                modifier = Modifier.padding(15.dp, 10.dp),
            ) {
                timeDetails["time"]?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center, color = Color.White,
                        fontSize = 16.sp,
                    )
                }
                if (hand != null) {
                    Text(
                        text = hand,
                        textAlign = TextAlign.Center, color = Color.White,
                        fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "24",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 23.sp, fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Shots",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 16.sp
                )
            }
            Column(
                modifier = Modifier.padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "82",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 23.sp, fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Avg",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 16.sp
                )
            }
            Column(
                modifier = Modifier.padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "91",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 23.sp, fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Top",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 16.sp
                )
            }
            ShareDeleteDropdownMenu()
        }

    }
}


@Preview
@Composable
fun WorkoutHistoryCard2Preview() {
    WorkoutHistoryCard2(
        speed = "34",
        hand = "Right",
        timestamp = Date()
    )
}

@Composable
fun WorkoutHistoryCard2(
    modifier: Modifier = Modifier,
    speed: String?,
    hand: String?,
    timestamp: Date,
    onSizeChange: (DpSize) -> Unit = {}
) {
    val density = LocalDensity.current
    val timeDetails = getDateTimeParts(timestamp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                //Log.d("TAG", "WorkoutHistoryCard2: ")
                density.run {
                    //Log.d("TAG", "WorkoutHistoryCard2: ${it.size.toString()}")
                    onSizeChange(
                        DpSize(
                            it.size.width.toDp(),
                            it.size.height.toDp()
                        )
                    )
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DateBoxButton(
                date = timeDetails["date"] ?: "00",
                month = timeDetails["month"] ?: "00"
            )
            Column(
                modifier = Modifier.padding(15.dp, 10.dp),
            ) {
                timeDetails["time"]?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center, color = Color.White,
                        fontSize = 16.sp,
                    )
                }
                hand?.let {
                    Text(
                        text = hand,
                        textAlign = TextAlign.Center, color = Color.White,
                        fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.padding(10.dp, 10.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                speed?.let {
                    Text(
                        text = it,
                        textAlign = TextAlign.Center, color = Color.White,
                        fontSize = 23.sp, fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = "mps",
                    textAlign = TextAlign.Center, color = Color.White,
                    fontSize = 16.sp
                )
            }
            ShareDeleteDropdownMenu()
        }

    }
}
