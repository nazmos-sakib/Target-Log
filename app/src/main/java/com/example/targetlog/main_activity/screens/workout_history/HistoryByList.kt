package com.example.targetlog.main_activity.screens.workout_history

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.targetlog.commons.getDateTimeParts
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.db.repository.SessionRepository
import com.example.targetlog.main_activity.screens.common_components.DateBoxButton
import com.example.targetlog.training_activity.common_component.ShareDeleteDropdownMenu
import com.example.targetlog.ui.theme.DarkGreen833
import com.google.api.ResourceDescriptor.History
import kotlinx.coroutines.flow.distinctUntilChanged
import java.util.Date
import javax.inject.Inject


@Preview
@Composable
fun HistoryByListPreview() {
    HistoryByList(sessionId = 0L)
}

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HistoryByList(
    sessionId: Long,
    viewModel: WorkoutHistoryModelView = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.initialize(sessionId)
    }
    val sessionDetails by viewModel.sessionDetails.collectAsState()
    val listState = rememberLazyListState()



    var sizeInDp by remember { mutableStateOf(DpSize(100.dp, 100.dp)) }
    val density = LocalDensity.current


    LazyColumn(
        state = listState,
        modifier = Modifier
            //.verticalScroll(scrollState)
            .height(sizeInDp.height * (sessionDetails.size+2))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        userScrollEnabled = false,
    ) {


        stickyHeader {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = "JULY", fontSize = 25.sp,
                textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
            )
        }

        /*            items(
                        items = listOf(20,202,2,2),
                        key = {

                    }){

                    }*/

        itemsIndexed(sessionDetails) { index, item ->
            //Log.d("TAG", "HistoryByList: $index")
            Box(
                modifier = Modifier
                    .onSizeChanged {
                        sizeInDp = density.run {
                            //Log.d("TAG", "onSizeChanged: ${it.toString()}")
                            DpSize(
                                it.width.toDp(),
                                it.height.toDp()
                            )
                        }
                    }
                    .onGloballyPositioned {
                        sizeInDp = density.run {
                            //Log.d("TAG", "onGloballyPositioned: ${it.size.toString()}")

                            DpSize(
                                it.size.width.toDp(),
                                it.size.height.toDp()
                            )
                        }
                    }
                    .background(Color.Transparent)
                    .border(1.dp, DarkGreen833, getTopLineShape(1.dp))
                    .padding(0.dp, 12.dp)
            ) {
                /*if (index % 2 == 0)
                    //WorkoutHistoryCard1( modifier = Modifier.animateItemPlacement(), )
                else{}*/

                WorkoutHistoryCard2(
                    modifier = Modifier.animateItemPlacement(),
                    speed = item.speed,
                    hand = item.hand,
                    timestamp = item.timestamp,
                )
            }
        }
    }

    // Trigger pagination
    /*LaunchedEffect(listState) {
        Log.d("History-screen", "HistoryByList:LaunchedEffect>listState: ${listState.layoutInfo.visibleItemsInfo.lastIndex}")
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex != null && lastVisibleItemIndex >= sessionDetails.size - 5) {
                    viewModel.loadNextPage()
                }
            }
    }*/

    LaunchedEffect(Unit) {
        Log.d("History-screen", "HistoryByList:LaunchedEffect>listState: ${listState.layoutInfo.visibleItemsInfo.lastIndex}")
        snapshotFlow { listState.firstVisibleItemIndex + listState.layoutInfo.visibleItemsInfo.size }
            .distinctUntilChanged()
            .collect { visibleItemCount ->
                val totalItemCount = listState.layoutInfo.totalItemsCount
                if (visibleItemCount >= totalItemCount - 5) {
                    Log.d("Paging", "Load next page triggered")
                    viewModel.loadNextPage()
                }
            }
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
