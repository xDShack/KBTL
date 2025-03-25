package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationScreen(
    onBackClick: () -> Unit,
    onNavigateToQR: (amount: Int, domain: String) -> Unit,
    onNavigateToUPI: (amount: Int, domain: String) -> Unit,
    modifier: Modifier = Modifier
) {
    // State declarations
    var selectedAmount by remember { mutableStateOf<Int?>(null) }
    var customAmount by remember { mutableStateOf("") }
    var isCustomAmountVisible by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    var selectedCause by remember { mutableStateOf<String?>(null) }

    val amounts = listOf(1000, 2000, 3000)
    val causes = listOf(
        "Education",
        "Healthcare",
        "Environmental Conservation",
        "Food Security",
        "Animal Welfare"
    )

    Box(modifier = modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background16),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp) // Only bottom padding
        ) {
            item {
                // Semi-ellipse section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    // Semi-ellipse background
                    Image(
                        painter = painterResource(R.drawable.ellipse_3),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TopAppBar(
                            title = {},
                            navigationIcon = {
                                IconButton(onClick = onBackClick) {
                                    Icon(
                                        painter = painterResource(R.drawable.arrow_left_icon),
                                        contentDescription = "Back",
                                        tint = Color.Black,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent
                            )
                        )

                        // Donation image
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.donate1),
                                contentDescription = null,
                                contentScale = ContentScale.Fit,
                                modifier = Modifier.size(145.dp)
                            )
                        }
                        Text(
                            text = "Donate",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


                    // Select amount text
                    Text(
                        text = "Select amount",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Currency Label
                    Surface(
                        modifier = Modifier
                            .padding(bottom = 16.dp)
                            .width(150.dp),
                        color = Color(0xFFFFCCBC),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Box(
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Rupees (INR)",
                                color = Color.Black
                            )
                        }
                    }

                    if (isCustomAmountVisible) {
                        OutlinedTextField(
                            value = customAmount,
                            onValueChange = {
                                if (it.all { char -> char.isDigit() }) {
                                    customAmount = it
                                    selectedAmount = it.toIntOrNull()
                                }
                            },
                            label = { Text("Enter amount") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                containerColor = Color.White.copy(alpha = 0.9f)
                            )
                        )
                    }

                    // Amount buttons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        amounts.forEach { amount ->
                            Surface(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp)
                                    .selectable(
                                        selected = selectedAmount == amount,
                                        onClick = {
                                            selectedAmount = amount
                                            isCustomAmountVisible = false
                                            customAmount = ""
                                        }
                                    ),
                                color = if (selectedAmount == amount) Color(0xFFFF7043) else Color(0xFFFFCCBC),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Box(
                                    modifier = Modifier.padding(vertical = 12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "₹$amount",
                                        color = Color.Black,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .selectable(
                                    selected = isCustomAmountVisible,
                                    onClick = {
                                        isCustomAmountVisible = true
                                        selectedAmount = null
                                    }
                                ),
                            color = if (isCustomAmountVisible) Color(0xFFFF7043) else Color(0xFFFFCCBC),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 12.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "+",
                                    color = Color.Black,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                        // Donation Cause Dropdown
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 24.dp)
                        ) {
                            Surface(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                color = Color(0xFFFFCCBC),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = selectedCause ?: "Select domain for donation",
                                        color = Color.Black
                                    )
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand"
                                    )
                                }
                            }
                            Box(
                                modifier = Modifier.defaultMinSize(minWidth = 1.dp)
                            )
                            {
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    modifier = Modifier.fillMaxWidth(),
                                ) {
                                    causes.forEach { cause ->
                                        DropdownMenuItem(
                                            text = {
                                                Box(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = cause,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                            },
                                            onClick = {
                                                selectedCause = cause
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        // DONATE text
                        Text(
                            text = "DONATE",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )

                        // Payment Option Buttons
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button(
                                onClick = {
                                    selectedAmount?.let { amount ->
                                        selectedCause?.let { cause ->
                                            onNavigateToQR(amount, cause)
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(end = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFFF7043
                                    )
                                ),
                                enabled = selectedAmount != null && selectedCause != null
                            ) {
                                Text("Donate with QR")
                            }

                            Button(
                                onClick = {
                                    selectedAmount?.let { amount ->
                                        selectedCause?.let { cause ->
                                            onNavigateToUPI(amount, cause)
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 8.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(
                                        0xFFFF7043
                                    )
                                ),
                                enabled = selectedAmount != null && selectedCause != null
                            ) {
                                Text("Donate with UPI")
                            }
                        }
                    }
                }
            }
        }
    }


// Example of how to use the DonationScreen in your navigation
@Composable
fun DonationNavigation() {
    DonationScreen(
        onBackClick = { /* Handle back navigation */ },
        onNavigateToQR = { amount, domain ->
            // Navigate to QR screen with the data
            // Example: navController.navigate("qr_screen/$amount/$domain")
        },
        onNavigateToUPI = { amount, domain ->
            // Navigate to UPI screen with the data
            // Example: navController.navigate("upi_screen/$amount/$domain")
        }
    )
}