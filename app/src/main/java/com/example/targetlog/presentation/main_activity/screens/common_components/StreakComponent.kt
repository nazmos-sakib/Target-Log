package com.example.targetlog.presentation.main_activity.screens.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.targetlog.R
import com.example.targetlog.presentation.ui.theme.GreenLight


@Preview
@Composable
fun StreakIndicator(
    weeks: Int = 0,
    fontSize: TextUnit = 14.sp
) {

    Box(
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(100, 100, 100), RoundedCornerShape(9.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 15.dp)
                    .drawBehind {
                        val path = Path().apply {
                            moveTo(
                                x = size.width-2,
                                y = 0f
                            )
                            lineTo(
                                x = size.width+80,
                                y = 0f
                            )
                            lineTo(
                                x = size.width,
                                y = size.height
                            )
                            close()
                        }
                        drawPath(
                            path = path,
                            color = Color(0xFFFFC107)
                        )
                    }
                    .background(
                        Color(0xFFFFC107),
                        RoundedCornerShape(9.dp, 0.dp, 0.dp, 9.dp)
                    ) // Yellow color
                    .padding(horizontal = 5.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp,0.dp).size(30.dp),
                        painter = painterResource(id = R.drawable.fire2),
                        contentDescription = "fire",
                        tint = Color.Black
                    )
                    Column(modifier = Modifier, horizontalAlignment = Alignment.End) {

                        Text(
                            text = "CURRENT",
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "STREAK",
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = fontSize,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

            }

            Box(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$weeks WEEKS",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}


@Preview
@Composable
fun NoStreakIndicator( ) {

    Box(
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(100, 100, 100), RoundedCornerShape(9.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 20.dp)
                    .drawBehind {
                        val path = Path().apply {
                            RoundedCornerShape(9.dp)
                            moveTo(
                                x = size.width-10,
                                y = 0f
                            )
                            lineTo(
                                x = size.width+80,
                                y = 0f
                            )
                            lineTo(
                                x = size.width,
                                y = size.height
                            )
                            close()
                        }
                        drawPath(
                            path = path,
                            color = Color(0xFF75736D)
                        )
                    }
                    .background(
                        Color(0xFF75736D),
                        RoundedCornerShape(9.dp, 0.dp, 0.dp, 9.dp)
                    ) // Yellow color
                    .padding(horizontal = 5.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp,0.dp).size(30.dp),
                        painter = painterResource(id = R.drawable.fire2),
                        contentDescription = "fire",
                        tint = Color.Black
                    )
                    Column(modifier = Modifier, horizontalAlignment = Alignment.End) {

                        Text(
                            text = "CURRENT",
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "STREAK",
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

            }

            Box(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "START TRAINING",
                    textAlign = TextAlign.Center,
                    color = GreenLight,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}


@Preview
@Composable
fun StreakIndicatorNew2(
    weeks: Int = 0
) {

    Box(
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color(100, 100, 100), RoundedCornerShape(9.dp)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            val cornerRadius = with(LocalDensity.current){8.dp.toPx()}

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        Color(0xFFFFC107),
                        RoundedCornerShape(9.dp, 0.dp, 0.dp, 9.dp)
                    ) // Yellow color
                    .padding(horizontal = 5.dp, vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(10.dp,0.dp).size(30.dp),
                        painter = painterResource(id = R.drawable.fire2),
                        contentDescription = "fire",
                        tint = Color.Black
                    )
                    Column(modifier = Modifier, horizontalAlignment = Alignment.End) {

                        Text(
                            text = "CURRENT",
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                        Text(
                            text = "STREAK",
                            color = Color.White,
                            textAlign = TextAlign.Right,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

            }

            Box(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$weeks WEEKS",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

