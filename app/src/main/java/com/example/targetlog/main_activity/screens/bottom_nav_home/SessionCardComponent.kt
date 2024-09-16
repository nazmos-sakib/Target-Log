package com.example.targetlog.main_activity.screens.bottom_nav_home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.targetlog.R
import com.example.targetlog.commons.RoundedPolygonShape
import com.example.targetlog.main_activity.screens.common_components.HexagonThumbsUP


//@Preview
@Composable
fun SessionCard (
    profileImage: Int,
    username: String,
    sessionDescription: String,
    timeSpent: String,
    timeAgo: String
){
    val hexagon = remember {
        RoundedPolygon(
            6,
            rounding = CornerRounding(0.2f)
        )
    }
    val clip = remember(hexagon) {
        RoundedPolygonShape(polygon = hexagon)
    }
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_1),
                    contentDescription = "follower profile pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Transparent, CircleShape)
                )

                Column(modifier = Modifier.fillMaxWidth(.5f)) {
                    Text(text = "User1 completed a shooting session in",
                        color= Color.White)
                    Text(text = "00:23:45",
                        color= Color.White)
                }

                Column(horizontalAlignment = Alignment.End) {

                    Text(text = "158",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        color= Color.White)
                    Text(text = "Shots",
                        textAlign = TextAlign.Right,
                        color= Color.White)
                }

                Box(modifier = Modifier
                    .rotate(90f)
                    .padding(0.dp)) {
                    Box(
                        modifier = Modifier
                            .padding(0.dp)
                            .border(2.dp, Color.Gray,)
                            .size(50.dp)
                            .padding(0.dp)
                            .rotate(270f),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_thumb_up_24),
                            contentDescription = "follower profile pic",
                            tint = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )  {

                Box(
                    modifier = Modifier
                        .background(color = Color.Blue, RoundedCornerShape(4.dp))
                ) {
                    Text(text = "SESSION",
                        color= Color.White,
                        modifier = Modifier.padding(8.dp,3.dp))
                }


                Text(text = "2 days ago",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.Bold,
                    color= Color.Gray,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }

    }
}


@Preview
@Composable
fun SessionCardPreview (){

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_1),
                    contentDescription = "follower profile pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Transparent, CircleShape)
                )

                Column(modifier = Modifier.fillMaxWidth(.5f)) {
                    Text(text = "User1 completed a shooting session in",
                        color= Color.White)
                    Text(text = "00:23:45",
                        color= Color.White)
                }

                Column(horizontalAlignment = Alignment.End) {

                    Text(text = "158",
                        fontSize = 40.sp,
                        textAlign = TextAlign.Right,
                        fontWeight = FontWeight.Bold,
                        color= Color.White)
                    Text(text = "Shots",
                        textAlign = TextAlign.Right,
                        color= Color.White)
                }

                 HexagonThumbsUP()
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )  {

                Box(
                    modifier = Modifier
                        .background(color = Color.Blue,RoundedCornerShape(4.dp))
                ) {
                    Text(text = "SESSION",
                        color= Color.White,
                        modifier = Modifier.padding(8.dp,3.dp))
                }


                Text(text = "2 days ago",
                    textAlign = TextAlign.Right,
                    fontWeight = FontWeight.Bold,
                    color= Color.Gray,
                    modifier = Modifier.padding(0.dp)
                )
            }
        }

    }
}
