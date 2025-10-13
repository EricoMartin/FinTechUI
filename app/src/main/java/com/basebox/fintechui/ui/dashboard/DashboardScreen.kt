package com.basebox.fintechui.ui.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basebox.fintechui.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun WalletDashboardScreen() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { WalletBottomNavBar() }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TopSection()
            }
            item {
                BalanceCard()
            }
            item {
                ActionButtons(
                    onActionClick = { action ->
                        scope.launch {
                            snackbarHostState.showSnackbar("Clicked: $action")
                        }
                    }
                )
            }
            item {
                AssetsSection()
            }
            items(transactions) { transaction ->
                TransactionItem(transaction)
            }
        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.sample_profile),
                contentDescription = "User",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Gray)
                )
                Text(
                    text = "Dianne Russell",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Profile Icon with white circular background
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
                    .clickable { /* handle click */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.Black // adjust to match your theme
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Notifications Icon with white circular background and red badge
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, CircleShape)
                    .clickable { /* handle click */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = Color.Black
                )

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color.Red, CircleShape)
                        .align(Alignment.TopEnd)
                )
            }
        }

    }
}

@Composable
fun BalanceCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFFF6B81), Color(0xFFFF8FA2))
                )
            )
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween // this spreads top and bottom
        ) {
            // 🔹 Top section
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Est. Total Value",
                        style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
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

                // Mid text directly below the top section
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = "$34,567.90",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            // 🔹 Bottom section (pushed down)
            Text(
                text = "$8,784.13 (8.78%)",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.White.copy(alpha = 0.9f)
                )
            )
        }
    }

}

@Composable
fun ActionButtons(onActionClick: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val actions = listOf("Deposit" to Icons.Default.Add, "Sent" to Icons.Default.ArrowBack, "Convert" to Icons.Default.CreditCard)
        actions.forEach { (title, icon) ->
            ElevatedButton(
                onClick = { onActionClick(title) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.weight(1f).padding(horizontal = 2.dp)
            ) {
                Icon(icon, contentDescription = title)
                Spacer(Modifier.width(12.dp))
                Text(title,
                    maxLines = 1, fontSize = 8.sp)
            }
        }
    }
}

@Composable
fun AssetsSection() {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("My Assets", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text("See All", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
        }
        Spacer(Modifier.height(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(
                listOf(
                    Triple("USDC", "$567.90", R.drawable.usdc),
                    Triple("BTC", "$0.003146", R.drawable.btc),
                    Triple("USDC", "$567.90", R.drawable.usdc),
                    Triple("BTC", "$0.003146", R.drawable.btc)
                )
            ) { (name, amount, iconRes) ->
                AssetCard(name, amount, iconRes)
            }
        }

        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("Transactions", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
            Text("See All", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
        }
    }
}

@Composable
fun AssetCard(name: String, value: String, icon: Int) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .width(width = 160.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(icon), contentDescription = name, modifier = Modifier.size(32.dp))
            Spacer(Modifier.width(8.dp))
            Column {
                Text(name, fontWeight = FontWeight.SemiBold)
                Text(value, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

data class Transaction(val title: String, val amount: String, val status: String, val type: String, val time: String)

val transactions = listOf(
    Transaction("Reward", "+149.87 USD", "Received", "Success", "15:22:55 • 2025-06-19"),
    Transaction("Deposit", "-289.99 USD", "Fail", "Failed", "08:12:49 • 2025-06-16"),
    Transaction("Credit", "+462.43 USD", "Received", "Success", "15:22:55 • 2025-06-19"),
    Transaction("Withdrawal", "-99.99 USD", "Fail", "Failed", "08:12:49 • 2025-06-16")
)

@Composable
fun TransactionItem(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = when (transaction.title) {
                "Reward" -> Icons.Default.CardGiftcard
                else -> Icons.Default.Add
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null)
            }
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(transaction.title, fontWeight = FontWeight.Bold)
                Text(transaction.time, fontSize = 12.sp, color = Color.Gray)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(transaction.amount, color = if (transaction.amount.startsWith("+")) Color(0xFF00C853) else Color.DarkGray, fontWeight = FontWeight.Bold)
                Text(transaction.status, color = if (transaction.title == "Deposit") Color.Red else Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun WalletBottomNavBar() {
    var selectedItem by remember { mutableStateOf(0) }
    val gradientBrush = Brush.linearGradient(listOf(Color(0xFFFF6B81), Color(0xFFFF8FA2)))

    NavigationBar {
        val items = listOf(Icons.Default.Home, Icons.Default.CreditCard, Icons.Default.Star, Icons.Default.Menu, Icons.Default.AutoAwesomeMosaic)
        items.forEachIndexed { index, icon ->
            if (index == 2) {
                Box(Modifier.weight(1f), contentAlignment = Alignment.Center) {
//                    FloatingActionButton(
//                        onClick = { selectedItem = index },
//                        containerColor = gradientBrush.toSolidColor(),
//                        elevation = FloatingActionButtonDefaults.elevation(6.dp)
//                    )
                    Box(
                        modifier = Modifier
                            .size(56.dp) // Standard FAB size
                            .shadow(elevation = 6.dp, shape = CircleShape)
                            .clip(CircleShape)
                            .background(gradientBrush)
                            .clickable { selectedItem = index }, // Make it clickable
                        contentAlignment = Alignment.Center
                    )
                    {
                        Icon(Icons.Default.SyncAlt, contentDescription = "Transfer", tint = Color.White)
                    }
                }
            } else {
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    icon = { Icon(icon, contentDescription = null) }
                )
            }
        }
    }
}

