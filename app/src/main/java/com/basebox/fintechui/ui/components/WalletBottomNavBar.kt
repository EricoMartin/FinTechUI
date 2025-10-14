package com.basebox.fintechui.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesomeMosaic
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun WalletBottomNavBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val gradientBrush = Brush.linearGradient(listOf(Color(0xFFFF6B81), Color(0xFFFF8FA2)))


    NavigationBar {
        val items = listOf(Icons.Default.Home, Icons.Default.CreditCard, Icons.Default.Star, Icons.Default.Menu, Icons.Default.AutoAwesomeMosaic)
        items.forEachIndexed { index, icon ->
            if (index == 2) {
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Box(modifier = Modifier.size(56.dp).shadow(elevation = 6.dp, shape = CircleShape).clip(CircleShape).background(gradientBrush).clickable { selectedItem = index }, contentAlignment = Alignment.Center) { Icon(Icons.Default.SyncAlt, contentDescription = "Transfer", tint = Color.White) }
                }
            } else {
                NavigationBarItem(selected = selectedItem == index, onClick = { selectedItem = index }, icon = { Icon(icon, contentDescription = null) })
            }
        }
    }
}