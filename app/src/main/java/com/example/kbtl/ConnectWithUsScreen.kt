package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class to hold our social media information
data class SocialMediaItem(
    val title: String,
    val iconResId: Int,
    val url: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConnectWithUsScreen(onBackClick: () -> Unit) {
    val uriHandler = LocalUriHandler.current

    val socialMediaList = listOf(
        SocialMediaItem("WhatsApp", R.drawable.whatsapp_icon, "https://kbtlfoundation.org"),
        SocialMediaItem("Facebook", R.drawable.facebook_icon, "https://www.facebook.com/KBTLFoundation#"),
        SocialMediaItem("Instagram", R.drawable.instagram_icon, "https://kbtlfoundation.org"),
        SocialMediaItem("LinkedIn", R.drawable.linkedin_icon, "https://www.linkedin.com/company/kbtl-foundation/posts/?feedView=all"),
        SocialMediaItem("Website", R.drawable.website_icon, "https://kbtlfoundation.org")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background4),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    containerColor = Color.Transparent
                )
            )
            Text(
                text = "Connect with us",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
        LazyColumn{
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Our Social media handles",
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 24.dp, top = 24.dp, bottom = 10.dp)
                            .fillMaxWidth(0.5f),
                    )

                    Image(
                        painter = painterResource(id = R.drawable.join_icon),
                        contentDescription = "Join Us",
                        modifier = Modifier
                            .size(140.dp)
                            .padding(end = 24.dp)
                    )
                }
            }
            item{
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // First two rows with two cards each
                for (i in 0..2 step 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterHorizontally
                        )
                    ) {
                        SocialMediaCard(
                            socialMedia = socialMediaList[i],
                            modifier = Modifier.size(150.dp)
                        ) {
                            try {
                                uriHandler.openUri(socialMediaList[i].url)
                            } catch (e: Exception) {
                                // Handle error
                            }
                        }

                        SocialMediaCard(
                            socialMedia = socialMediaList[i + 1],
                            modifier = Modifier.size(150.dp)
                        ) {
                            try {
                                uriHandler.openUri(socialMediaList[i + 1].url)
                            } catch (e: Exception) {
                                // Handle error
                            }
                        }
                    }
                }

                // Last card (Website) centered
                SocialMediaCard(
                    socialMedia = socialMediaList.last(),
                    modifier = Modifier.size(150.dp)
                ) {
                    try {
                        uriHandler.openUri(socialMediaList.last().url)
                    } catch (e: Exception) {
                        // Handle error
                    }
                }
            }
            }
            }
        }
    }
}

@Composable
fun SocialMediaCard(
    socialMedia: SocialMediaItem,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable(onClick = onClick),
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
                painter = painterResource(id = socialMedia.iconResId),
                contentDescription = socialMedia.title,
                modifier = Modifier
                    .size(84.dp)
                    .padding(bottom = 8.dp)
            )
        }
    }
}