package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    onBackClick: () -> Unit,
    onSignUpClick: (SignUpData) -> Unit
) {
    // State for all input fields
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Custom colors for the theme
    val orangeColor = Color(0xFFFF5722) // Color for the sign up button

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background3),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Main Content
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Back Button and Title Row
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
                        Spacer(modifier = Modifier.height(36.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally), // Add padding to make space for the overlapping icon
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF4EF).copy(alpha = 0.95f)
                            ),
                            elevation = CardDefaults.elevatedCardElevation(8.dp),
                            shape = RoundedCornerShape(
                                topStart = 75.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                                topEnd = 0.dp
                            ),

                            ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .systemBarsPadding(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(24.dp))
                                Text(
                                    text = "Create An Account",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }


                        // Input Fields
                        Spacer(modifier = Modifier.height(24.dp))

                        // Custom function to create consistent text fields
                        @Composable
                        fun CustomTextField(
                            value: String,
                            onValueChange: (String) -> Unit,
                            label: String,
                            keyboardType: KeyboardType = KeyboardType.Text,
                            visualTransformation: VisualTransformation = VisualTransformation.None
                        ) {
                            OutlinedTextField(
                                value = value,
                                onValueChange = onValueChange,
                                label = { Text(label) },
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .padding(vertical = 8.dp)
                                    .align(Alignment.CenterHorizontally),
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    unfocusedBorderColor = Color.LightGray,
                                    unfocusedContainerColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                                visualTransformation = visualTransformation,
                                singleLine = true
                            )
                        }

                        // Using our custom text field for all inputs
                        CustomTextField(firstName, { firstName = it }, "Name")
                        CustomTextField(lastName, { lastName = it }, "Last name")
                        CustomTextField(
                            email,
                            { email = it },
                            "E-mail",
                            KeyboardType.Email
                        )
                        CustomTextField(
                            mobile,
                            { mobile = it },
                            "Mobile No.",
                            KeyboardType.Phone
                        )
                        CustomTextField(
                            password,
                            { password = it },
                            "Password",
                            KeyboardType.Password,
                            PasswordVisualTransformation()
                        )
                        CustomTextField(
                            confirmPassword,
                            { confirmPassword = it },
                            "Confirm Password",
                            KeyboardType.Password,
                            PasswordVisualTransformation()
                        )

                        // Sign Up Button
                        Button(
                            onClick = {
                                onSignUpClick(
                                    SignUpData(
                                        firstName,
                                        lastName,
                                        email,
                                        mobile,
                                        password
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(vertical = 24.dp)
                                .height(56.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orangeColor,
                                contentColor = Color.Black
                            ),
                            elevation = ButtonDefaults.elevatedButtonElevation(8.dp)
                        ) {
                            Text(
                                "Sign Up",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}

// Data class to hold sign up form data
data class SignUpData(
    val firstName: String,
    val lastName: String,
    val email: String,
    val mobile: String,
    val password: String
)