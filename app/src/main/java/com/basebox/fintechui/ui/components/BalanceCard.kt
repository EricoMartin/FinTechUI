package com.basebox.fintechui.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.basebox.fintechui.ui.viewmodel.BalanceUiState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BalanceCard(balanceUiStateFlow: StateFlow<BalanceUiState>, onToggle: () -> Unit) {
    // Collect the state from the StateFlow
    val balanceUi by balanceUiStateFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.linearGradient(colors = listOf(Color(0xFFFF6B81), Color(0xFFFF8FA2))))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Est. Total Value", style = MaterialTheme.typography.labelMedium.copy(color = Color.White))
                    // Use the 'show' property from the collected state object
                    IconButton(onClick = onToggle) { Icon(imageVector = if (balanceUi.show) Icons.Default.Visibility else Icons.Default.VisibilityOff, contentDescription = null, tint = Color.White) }
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .width(24.dp)
                            .padding(start = 16.dp)
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "USD",
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                // Use the 'show' and 'total' properties from the collected state object
                Crossfade(targetState = balanceUi.show) { visible ->
                    Text(text = if (visible) balanceUi.total else "******", style = MaterialTheme.typography.headlineLarge.copy(color = Color.White), fontWeight = FontWeight.Bold)
                }
            }
            // Use the 'show' and 'totalChange' properties from the collected state object
            Text(text = if (balanceUi.show) balanceUi.totalChange else "******", style = MaterialTheme.typography.labelMedium.copy(color = Color.White.copy(alpha = 0.9f)))
        }
    }
}
