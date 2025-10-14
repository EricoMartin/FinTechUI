package com.basebox.fintechui.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ActionButtons(onActionClick: (String) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        val actions = listOf("Deposit" to Icons.Default.Add, "Sent" to Icons.Default.ArrowOutward, "Convert" to Icons.Default.CreditCard)
        actions.forEach { (title, icon) ->
            ElevatedButton(onClick = { onActionClick(title) }, modifier = Modifier.weight(1f).padding(horizontal = 2.dp)) {
                Icon(icon, contentDescription = title)
                Spacer(Modifier.width(4.dp))
                Text(title, maxLines = 1, fontSize = 6.sp)
            }
        }
    }
}