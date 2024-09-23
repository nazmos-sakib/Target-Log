package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.RoundedPolygon
import com.example.targetlog.R
import com.example.targetlog.commons.RoundedPolygonShape
import com.example.targetlog.ui.theme.GreenLight

//@Preview
@Composable
fun ProfileBanner(
    name:String,
    email:String,
    isProfilePicEditable:Boolean = false,
    onProfilePictureChange:()->Unit = {}
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
        modifier = Modifier.fillMaxWidth(),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .weight(.3f)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_dp ),
                    contentDescription = "profile pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(clip)
                )
                if (isProfilePicEditable){
                    Box(
                        modifier  = Modifier.padding(10.dp)
                            .background(color = GreenLight, shape = RoundedCornerShape(10.dp))
                            .align(Alignment.BottomEnd)
                            .padding(3.dp),
                    ) {
                        Icon(painter = painterResource(id = R.drawable.edit), contentDescription = null, tint = Color.Black)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(.8f)
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = name,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                    Text(text = email,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    StreakIndicator(19)
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileBannerPreview(){
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
        modifier = Modifier.fillMaxWidth(),
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .weight(.3f)
                    .padding(0.dp, 7.dp, 0.dp, 0.dp)
                    .align(Alignment.CenterVertically),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile_dp ),
                    contentDescription = "profile pic",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(90.dp)
                        .clip(clip)
                )
                Box(
                    modifier  = Modifier.padding(10.dp)
                        .background(color = GreenLight, shape = RoundedCornerShape(10.dp))
                        .align(Alignment.BottomEnd)
                        .padding(3.dp),
                ) {
Icon(painter = painterResource(id = R.drawable.edit), contentDescription = null, tint = Color.Black)
                }
            }
            Box(
                modifier = Modifier
                    .weight(.8f)
                    .padding(0.dp, 0.dp, 0.dp, 0.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Column {
                    Text(text = "Jt Giles-Haris",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                    Text(text = "user@email.com",
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    StreakIndicator(19)
                }
            }
        }
    }
}