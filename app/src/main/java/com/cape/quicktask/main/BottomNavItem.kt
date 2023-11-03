package com.cape.quicktask.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    object TaskScreen : BottomNavItem(
        "task_screen", selectedIcon = Icons.Filled.Edit,
        unselectedIcon = Icons.Outlined.Edit,
    )

    object CalendarScreen : BottomNavItem(
        "calendar_screen", selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange
    )
}