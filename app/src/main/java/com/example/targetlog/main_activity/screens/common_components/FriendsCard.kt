package com.example.targetlog.main_activity.screens.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.targetlog.R
import com.example.targetlog.commons.FRIENDS_PROFILE_SCREEN
import com.example.targetlog.model.User
import com.example.targetlog.model.service.ContactDTO

@Preview
@Composable
fun FriendsCard(
    user:User = User(),
    onClickNavigateToProfile:(String)->Unit={ _ -> },
){
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onClickNavigateToProfile(FRIENDS_PROFILE_SCREEN) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(
            id = R.drawable.profile_dp),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = user.displayName,
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }
}


//@Preview
@Composable
fun FriendsCardWithIcon(
    user: User,
    rId: Int,
    onIconClick:(User)->Unit={},
    onClickNavigateToProfile:(String)->Unit={ _ -> }
){
    Row(
        Modifier.fillMaxWidth( ),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(.8f)
        ) {
            FriendsCard(
                user,
                onClickNavigateToProfile = onClickNavigateToProfile
            )
        }

        //Spacer(modifier = Modifier.width(20.dp))
        Icon(
            painter = painterResource(id = rId),
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.clickable {
                onIconClick(user)
            }
        )
    }
}

@Preview
@Composable
fun FriendsCardWithIconPreview(){
    Row(
        Modifier.fillMaxWidth( ),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(.8f)
        ) {
            FriendsCard()
        }

        //Spacer(modifier = Modifier.width(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.add_user),
            contentDescription = "",
            tint = Color.White)
    }
}


@Composable
fun ContactInvitationCard(
    contact:ContactDTO,
    onClick:()->Unit={   }
){
    Row(
        Modifier.fillMaxWidth( ),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(.8f)
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { onClick()  },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(
                    id = R.drawable.manage_subscription
                ),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {

                    Text(text = contact.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                    Text(text = contact.pnNumber,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        //Spacer(modifier = Modifier.width(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.invite_friend),
            contentDescription = "",
            tint = Color.White)
    }
}


@Preview
@Composable
fun ContactInvitationCardPreview(){
    Row(
        Modifier.fillMaxWidth( ),
        horizontalArrangement  = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(.8f)
        ) {

            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { /*onClickNavigateToProfile(FRIENDS_PROFILE_SCREEN)*/ },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(painter = painterResource(
                    id = R.drawable.manage_subscription
                ),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Column {

                    Text(text = "displayName",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                    Text(text = "12334524532",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic
                    )
                }
            }
        }

        //Spacer(modifier = Modifier.width(20.dp))
        Icon(
            painter = painterResource(id = R.drawable.invite_friend),
            contentDescription = "",
            tint = Color.White)
    }
}