package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FAQ(
    val id: Int,
    val question: String = ""
)
val faqList = listOf(
    FAQ(1),
    FAQ(2),
    FAQ(3),
    FAQ(4),
    FAQ(5),
    FAQ(6),
    FAQ(7),
    FAQ(8),
    FAQ(9),
    FAQ(10),
    FAQ(11),
    FAQ(12),
    FAQ(13),
    FAQ(14),
    FAQ(15),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackClick: () -> Unit,
    faqs: List<FAQ>
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.background8),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Column(
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
                            containerColor = Color.Transparent // Make app bar transparent
                        )
                    )


                    // About Us Title

                    Text(
                        text = "About Us",
                        fontSize = 66.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 16.dp),
                        textAlign = TextAlign.Center
                    )


                    Column(
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Our Impact",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                    }

                    // Content
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(), // Add padding to make space for the overlapping icon
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFF4EF).copy(alpha = 0.95f)
                            ),
                            elevation = CardDefaults.elevatedCardElevation(8.dp),
                            shape = RoundedCornerShape(
                                topStart = 48.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp,
                                topEnd = 48.dp
                            ),

                            ) {
                            Text(
                                text = "The desire to help others is a valuable quality. Serving others, especially the less fortunate, can be a wonderfully uplifting and inspiring experience and a true source of joy.",
                                fontSize = 18.sp,
                                color = Color.Black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 8.dp),
                            )
                            Text(
                                text = "Frequently Asked Questions",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black,
                                modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
                                textAlign = TextAlign.Center
                            )


                            // FAQ Cards

                            faqs.forEach { faq ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth(0.85f)
                                        .padding(vertical = 12.dp)
                                        .align(Alignment.CenterHorizontally),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(0xFFFFCCBC)
                                    ),
                                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(66.dp)
                                            .padding(horizontal = 16.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(
                                            text = "${faq.id}.",
                                            color = Color.Black,
                                            fontSize = 16.sp
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
