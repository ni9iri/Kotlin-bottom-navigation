package com.example.bottomnavigation

import androidx.compose.ui.graphics.vector.ImageVector


data class TabItem(
    var label: String,
    var icon: ImageVector,
    var route: String
)