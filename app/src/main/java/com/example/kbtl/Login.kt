package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(
    onBackClick: () -> Unit,
    onProceedClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit
) {
    // State for input fields
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Root Box to stack background and content
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background8),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        LazyColumn {
            item {
                // Main Content Column
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(), // Handles system bars padding
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Top App Bar with back button
                    TopAppBar(
                        title = {},
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
                            containerColor = Color.Transparent // Make app bar transparent
                        )
                    )
                    Spacer(modifier = Modifier.height(72.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .zIndex(1f)
                                .offset(y = (-100).dp),
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.person_illustration),
                                contentDescription = "Person illustration",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(16.dp),
                                contentScale = ContentScale.Fit,
                                alignment = Alignment.TopEnd
                            )

                        }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(), // Add padding to make space for the overlapping icon
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFF4EF).copy(alpha = 0.95f)
                                ),
                                elevation = CardDefaults.elevatedCardElevation(8.dp),
                                shape = RoundedCornerShape(
                                    topStart = 150.dp,
                                    bottomStart = 0.dp,
                                    bottomEnd = 0.dp,
                                    topEnd = 0.dp
                                ),

                                ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 60.dp), // Add padding to avoid overlapping text
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                Text(
                                    text = "Login",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(top = 35.dp, bottom = 24.dp),
                                )

                                // Email Input Field
                                OutlinedTextField(
                                    value = email,
                                    onValueChange = { email = it },
                                    label = { Text("E-mail") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp, vertical = 8.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = Color.LightGray,
                                        unfocusedContainerColor = Color.White
                                    ),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                    singleLine = true
                                )

                                // Password Input Field
                                OutlinedTextField(
                                    value = password,
                                    onValueChange = { password = it },
                                    label = { Text("Password") },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp, vertical = 8.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        unfocusedBorderColor = Color.LightGray,
                                        unfocusedContainerColor = Color.White
                                    ),
                                    visualTransformation = PasswordVisualTransformation(),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                    singleLine = true
                                )

                                // Proceed Button
                                ElevatedButton(
                                    onClick = { onProceedClick(email, password) },
                                    modifier = Modifier
                                        .fillMaxWidth(0.5f)
                                        .padding(horizontal = 24.dp, vertical = 16.dp),
                                    shape = RoundedCornerShape(30.dp),
                                    colors = ButtonDefaults.elevatedButtonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(
                                        text = "Proceed",
                                        modifier = Modifier
                                            .padding(vertical = 8.dp)
                                    )
                                }

                                // Sign Up Text Button
                                TextButton(
                                    onClick = onSignUpClick,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 24.dp)
                                ) {
                                    Text(
                                        text = "Don't have any account? Sign Up",
                                        textAlign = TextAlign.Center,
                                        color = Color.Black
                                    )
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}