package com.example.kbtl

import androidx.navigation.NavController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.navArgument

data class UPIOption(
    val name: String,
    val logoId: Int,
    val upiId: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UPIScreen(
    amount: Int,
    domain: String,
    onBackClick: () -> Unit,
    onPaymentSelect: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {
    // State to track which card is expanded
    var expandedCard by remember { mutableStateOf<String?>(null) }

    // UPI payment options
    val upiOptions = listOf(
        UPIOption("UPI", R.drawable.upi_logo, "example@upi"),
        UPIOption("PhonePe", R.drawable.phonepe_logo, "example@ybl"),
        UPIOption("Paytm", R.drawable.paytm_logo, "example@paytm"),
        UPIOption("Google Pay", R.drawable.gpay_logo, "example@okicici"),
        UPIOption("BHIM", R.drawable.bhim_logo, "example@upi")
    )

    Box(modifier = modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background11),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn {

            item {
                // Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TopAppBar(
                        title = { Text("UPI Payment", color = Color.Black) },
                        navigationIcon = {
                            IconButton(onClick = onBackClick) {
                                Icon(
                                    painter = painterResource(R.drawable.arrow_left_icon),
                                    contentDescription = "Back",
                                    tint = Color.Black,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Donate\nvia UPI",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Display amount and domain information
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFCCBC)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Amount: ₹$amount",
                                fontSize = 18.sp,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text(
                                text = "Domain: $domain",
                                fontSize = 18.sp
                            )
                        }
                    }

                    // UPI Options
                    upiOptions.forEach { upiOption ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .padding(vertical = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFCCBC)
                            ),
                            onClick = {
                                expandedCard =
                                    if (expandedCard == upiOption.name) null else upiOption.name
                            }
                        ) {
                            Column {
                                // Card Header
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                                        modifier = Modifier.fillMaxWidth(0.8f)
                                    ) {
                                        Image(
                                            painter = painterResource(id = upiOption.logoId),
                                            contentDescription = upiOption.name,
                                            modifier = Modifier.size(48.dp),
                                            contentScale = ContentScale.Fit
                                        )
                                        Text(
                                            text = upiOption.name,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                    Icon(
                                        imageVector = if (expandedCard == upiOption.name) {
                                            Icons.Default.KeyboardArrowUp
                                        } else {
                                            Icons.Default.KeyboardArrowDown
                                        },
                                        contentDescription = "Expand"
                                    )
                                }

                                // Expandable Content
                                AnimatedVisibility(
                                    visible = expandedCard == upiOption.name,
                                    enter = expandVertically(),
                                    exit = shrinkVertically()
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp)
                                    ) {
                                        Button(
                                            onClick = {
                                                onPaymentSelect(upiOption.name, upiOption.upiId)
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFFFF7043)
                                            )
                                        ) {
                                            Text("Proceed with ${upiOption.name}")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// Updated navigation function
fun getUPIScreenRoute() = "upi_screen/{amount}/{domain}"

// Navigation arguments for UPI Screen
fun getUPIScreenNavArgs() = listOf(
    navArgument("amount") { type = NavType.IntType },
    navArgument("domain") { type = NavType.StringType }
)