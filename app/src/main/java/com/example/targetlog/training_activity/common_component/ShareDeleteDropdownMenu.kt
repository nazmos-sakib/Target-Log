package com.example.targetlog.training_activity.common_component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
 import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.targetlog.ui.theme.Blue94
import com.example.targetlog.ui.theme.GreenLight

@Preview
@Composable
fun ShareDeleteDropdownMenu(
    onMenuSelect:(String)->Unit = {}
){
    var expanded by remember { mutableStateOf(false) }

        Icon(
            modifier = Modifier.clickable { expanded = !expanded },
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More",
            tint = when(expanded){
                true-> Color.Blue
                false-> GreenLight
            }
        )

        DropdownMenu(
            modifier = Modifier.background(color = Blue94, shape = RoundedCornerShape(8.dp)),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Share", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                onClick = {
                    onMenuSelect("Share")
                    expanded = false
                }
            )
            DropdownMenuItem(
                text = { Text("Delete", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) },
                onClick = {
                    onMenuSelect("Delete")
                    expanded = false
                }
            )
        }

}