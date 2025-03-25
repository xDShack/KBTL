package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WarmWishesScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    var wishes by remember { mutableStateOf(listOf(
        "Your positivity is contagious, lighting up every room you enter. Keep shining and spreading that joyful energy!",
        "Your presence is a gift, and the world is a better place with you in it. Shine on with your unique brilliance!"
    )) }

    Box(modifier = modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background12),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Main content
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
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
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Warm\nWishes",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    fontSize = 32.sp,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(R.drawable.image1),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(175.dp)
                )
            }

            // Add Button
            FloatingActionButton(
                onClick = {
                    wishes = wishes + "New warm wish..."
                },
                modifier = Modifier
                    .padding(bottom = 16.dp, end = 32.dp)
                    .align(Alignment.End),
                containerColor = MaterialTheme.colorScheme.primary,
                elevation = FloatingActionButtonDefaults.elevation(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add wish",
                    tint = Color.White
                )
            }

            // Scrollable Cards
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    items = wishes,
                    key = { wish -> wish }
                ) { wish ->
                    var isEditing by remember { mutableStateOf(false) }
                    var editedText by remember { mutableStateOf(wish) }

                    Card(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            if (isEditing) {
                                TextField(
                                    value = editedText,
                                    onValueChange = { editedText = it },
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = TextFieldDefaults.textFieldColors(
                                        containerColor = Color.Transparent,
                                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                                        unfocusedIndicatorColor = Color.Gray
                                    )
                                )
                            } else {
                                Text(
                                    text = wish,
                                    color = Color.Black
                                )
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(onClick = { /* Handle comments */ }) {
                                    Text(
                                        "Comments",
                                        color = Color.Gray
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        if (isEditing) {
                                            // Check if the edited text is empty
                                            if (editedText.trim().isEmpty()) {
                                                // Remove the card if text is empty
                                                wishes = wishes.filter { it != wish }
                                            } else {
                                                // Save changes only if text is not empty
                                                wishes = wishes.map {
                                                    if (it == wish) editedText.trim() else it
                                                }
                                            }
                                        }
                                        isEditing = !isEditing
                                    }
                                ) {
                                    Icon(
                                        imageVector = if (isEditing) Icons.Default.Check else Icons.Default.Edit,
                                        contentDescription = if (isEditing) "Save" else "Edit",
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        }
                    }
                }

                // Add bottom padding for better scrolling experience
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
    }
}