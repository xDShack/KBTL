package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Welcome(onBackClick: () -> Unit, onCardClick: (String) -> Unit) {
    // Using Box to stack background image and content
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background15),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Content Column
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally // Center aligns all column content
        ) {
            LazyColumn (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
                ){
                item{
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
                }
                item{
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(0.90f)
                            .size(125.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(topStart = 72.dp, topEnd = 48.dp, bottomStart = 8.dp, bottomEnd = 72.dp),
                        elevation = CardDefaults.cardElevation(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xf5854e).copy(alpha = 1f)
                        ),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "WELCOME !",
                                fontSize = 30.sp, // Increased font size
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(),
                            )
                        }
                    }
                }
                item{
                    Spacer(modifier = Modifier.height(24.dp))
                }
                item {
                    Card(modifier = Modifier
                        .fillMaxWidth(), // Add padding to make space for the overlapping icon
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF4EF).copy(alpha = 0.95f)
                        ),
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        shape = RoundedCornerShape(topStart = 24.dp, bottomStart = 0.dp, bottomEnd = 0.dp, topEnd = 24.dp)
                    ) {
                        Text(
                            text = "Discover something new every day",
                            fontSize = 30.sp, // Increased font size
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                                .padding(top = 24.dp), // Increased padding
                            verticalArrangement = Arrangement.spacedBy(24.dp) // Increased spacing
                        ) {
                            // First row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(24.dp) // Increased spacing
                            ) {
                                ActionCardItem(
                                    title = "Donate",
                                    iconResId = R.drawable.donate_icon,
                                    modifier = Modifier.weight(1f)
                                ) { onCardClick("Donate") }

                                ActionCardItem(
                                    title = "Volunteer",
                                    iconResId = R.drawable.volunteers_icon,
                                    modifier = Modifier.weight(1f)
                                ) { onCardClick("Volunteer") }
                            }

                            // Second row
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(24.dp) // Increased spacing
                            ) {
                                ActionCardItem(
                                    title = "Write a warm wish",
                                    iconResId = R.drawable.write_wish_icon,
                                    modifier = Modifier.weight(1f)
                                ) { onCardClick("Write a warm wish") }

                                ActionCardItem(
                                    title = "Join us",
                                    iconResId = R.drawable.join_us_icon,
                                    modifier = Modifier.weight(1f)
                                ) { onCardClick("Join us") }
                            }
                            Spacer(modifier = Modifier.height(500.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ActionCardItem(
    title: String,
    iconResId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick)
            .height(150.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = iconResId),
                contentDescription = title,
                modifier = Modifier
                    .size(84.dp) // Increased image size from 64.dp to 84.dp
                    .padding(bottom = 8.dp)
            )

            Text(
                text = title,
                fontSize = 16.sp, // Slightly increased text size
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
