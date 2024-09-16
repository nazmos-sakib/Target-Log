package com.example.targetlog.data_classes

import android.icu.text.CaseMap.Title
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomMenuContent(
    val title: String,
    val route: String,
    val iconId: ImageVector,
    val badgeCount: Int = 0
)