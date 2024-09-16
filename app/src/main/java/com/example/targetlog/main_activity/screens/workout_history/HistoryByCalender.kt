package com.example.targetlog.main_activity.screens.workout_history

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.ui.theme.GreenLight

//@Preview
@Composable
fun HistoryByCalender(
    modifier: Modifier = Modifier
){
    var sizeInDp by remember { mutableStateOf(DpSize(100.dp,100.dp)) }
    val density = LocalDensity.current


    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            color = Color.White,
            text = "JANUARY", fontSize = 25.sp,
            textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            contentPadding = PaddingValues(
                top = 10.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 10.dp
            ),
            modifier = Modifier.fillMaxWidth()
                .height(sizeInDp.height*6),
            userScrollEnabled = false
        ) {
            val weeks = listOf("M", "T", "W", "T", "F", "S", "S",)
            val freiTag = listOf(2,3,6,7,8,12,14,17,18,19,22,25 )

            items(weeks) { s ->
                Text(
                    text = s,
                    Modifier
                        .padding(8.dp),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            items(30) { index->
                Box(modifier = Modifier
                    .onSizeChanged {
                        sizeInDp = density.run {
                            Log.d("TAG", "onSizeChanged: ${it.toString()}")
                            DpSize(
                                it.width.toDp(),
                                it.height.toDp()
                            )
                        }
                    }
                    .onGloballyPositioned {
                        sizeInDp = density.run {
                            DpSize(
                                it.size.width.toDp(),
                                it.size.height.toDp()
                            )
                        }
                    }
                ){
                    CheckBoxForCalender(index !in freiTag)
                }
            }
        }
    }

}

@Preview
@Composable
fun CheckBoxForCalender(
    isActive:Boolean = true
) {
    Box(
        modifier  = Modifier
            .padding(5.dp)
            .background( when(isActive){true->GreenLight;false->Color.Transparent} , RoundedCornerShape(6.dp))
            .border(1.dp,GreenLight, RoundedCornerShape(6.dp))
            .padding(5.dp),
        contentAlignment  = Alignment.Center,
    ) {
        Icon(modifier = Modifier.size(30.dp), imageVector =   Icons.Default.Check, contentDescription = null, tint = Color.Black)
    }
}

@Preview
@Composable
fun CheckBoxForCalenderNoWorkOutPreview(
) {
    Box(
        modifier  = Modifier
            .padding(5.dp)
            .border(1.dp,GreenLight, RoundedCornerShape(6.dp))
            .padding(5.dp),
        contentAlignment  = Alignment.Center,
    ) {
        Icon(modifier = Modifier.size(30.dp), imageVector =   Icons.Default.Check, contentDescription = null, tint = Color.Black)
    }
}
