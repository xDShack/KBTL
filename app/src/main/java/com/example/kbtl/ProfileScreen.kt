package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    onBackClick: () -> Unit,
    onNavigateToWishes: () -> Unit,
    onNavigateToAccount: () -> Unit,
    onNavigateToReviews: () -> Unit,
    onNavigateToEditProfile: () -> Unit,
    backgroundResId: Int = R.drawable.background6
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = backgroundResId),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        // Content
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar with back button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left_icon),
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Profile Section
            LazyColumn{
                item{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Overlapping Profile Icon


                // Card with content
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.925f)
                        .padding(top = 60.dp) // Add padding to make space for the overlapping icon
                        .align(Alignment.Center), // Center the card horizontally
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF4EF).copy(alpha = 0.95f)
                    ),
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .padding(top = 60.dp), // Add padding to avoid overlapping text
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Profile Name
                        Text(
                            text = "Donor",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black,
                            fontSize = 24.sp,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )

                        // Menu Items
                        val menuItems = listOf(
                            MenuItem(
                                title = "My warm wishes count",
                                icon = Icons.Default.Favorite,
                                onClick = onNavigateToWishes
                            ),
                            MenuItem(
                                title = "Account",
                                icon = Icons.Default.Person,
                                onClick = onNavigateToAccount
                            ),
                            MenuItem(
                                title = "Reviews",
                                icon = Icons.Default.Star,
                                onClick = onNavigateToReviews
                            ),
                            MenuItem(
                                title = "Edit profile",
                                icon = Icons.Default.Edit,
                                onClick = onNavigateToEditProfile
                            )
                        )

                        menuItems.forEach { item ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                                    .clickable { item.onClick() },
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFFFDACC).copy(alpha = 0.95f)
                                ),
                                elevation = CardDefaults.elevatedCardElevation(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Icon(
                                            imageVector = item.icon,
                                            contentDescription = item.title,
                                            tint = Color.Black,
                                            modifier = Modifier.size(28.dp)
                                        )
                                        Text(
                                            text = item.title,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.Black,
                                            fontSize = 18.sp
                                        )
                                    }
                                    Icon(
                                        painter = painterResource(R.drawable.chevronright),
                                        contentDescription = "Navigate",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.TopCenter) // Align the icon at the top center of the Box
                        .offset(y = (-10).dp), // Move the icon up to overlap the card
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.Center),
                            tint = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }
            }
            }
            }
        }
    }
}

data class MenuItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val onClick: () -> Unit
)