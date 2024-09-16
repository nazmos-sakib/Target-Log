package com.example.targetlog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgeDefaults
import androidx.compose.material3.BadgeDefaults.containerColor
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.targetlog.commons.BOTTOM_NAV_HOME_SCREEN
import com.example.targetlog.commons.BOTTOM_NAV_PROFILE_SCREEN
import com.example.targetlog.commons.BOTTOM_NAV_TRAINING_SCREEN
import com.example.targetlog.commons.getTopLineShape
import com.example.targetlog.data_classes.BottomMenuContent
import com.example.targetlog.ui.theme.GreenLight


@Composable
fun BottomNavMenu(
    items:List<BottomMenuContent>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomMenuContent) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(
        modifier = Modifier.padding(0.dp)
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
            .border(1.dp,Color.LightGray, getTopLineShape(1.dp)),
        containerColor = Color.Transparent,
        contentColor = Color.Black
    ){
        items.forEach {item->
            val selected = item.route == backStackEntry.value?.destination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { onItemClick(item) },
                colors = NavigationBarItemColors(
                    selectedIconColor = GreenLight,
                    selectedTextColor = Color.White,
                    selectedIndicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Black,
                    disabledIconColor = Color.Black,
                    disabledTextColor = Color.Black,
                ),
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(top = 15.dp),
                    ) {
                        if(item.badgeCount > 0){
                            BadgedBox(
                                badge = {
                                    Badge(
                                        modifier = Modifier,
                                        containerColor  = BadgeDefaults.containerColor,
                                        contentColor = contentColorFor(containerColor),
                                        content = {
                                            Text(text = item.badgeCount.toString())
                                        })
                                }
                            ) {
                                Icon(
                                    imageVector = item.iconId,
                                    contentDescription = item.title,
                                    modifier = Modifier
                                        .size(20.dp)
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.iconId,
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                        if(selected){
                            Text(
                                text = item.title,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }

                    }
                }
            )
        }

    }
}


@Composable
fun getBottomNavItems():List<BottomMenuContent> = listOf(
    BottomMenuContent(
        title="Home",
        route = BOTTOM_NAV_HOME_SCREEN,
        iconId = ImageVector.vectorResource(id = R.drawable.home_2)
    ),
    BottomMenuContent(
        title="Training",
        route = BOTTOM_NAV_TRAINING_SCREEN,
        iconId = ImageVector.vectorResource(id = R.drawable.raps),
        //badgeCount = 13
    ),

    BottomMenuContent(
        title="Profile",
        route = BOTTOM_NAV_PROFILE_SCREEN,
        iconId = ImageVector.vectorResource(id = R.drawable.profile_2))
)