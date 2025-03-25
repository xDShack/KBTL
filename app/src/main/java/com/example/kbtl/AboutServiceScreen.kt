package com.example.kbtl

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

data class DonorInfo(
    val name: String,
    val amount: String,
    val date: String
)

data class ActivityInfo(
    val title: String,
    val description: String,
    val date: String
)

data class GalleryImage(
    val imageUri: String,
    val description: String
)

val donorsList = listOf(
    DonorInfo("John Doe", "1000", "2023-01-15"),
    DonorInfo("Jane Smith", "500", "2023-02-20"),
    DonorInfo("Jane Smith", "500", "2023-02-20"),
    DonorInfo("Jane Smith", "500", "2023-02-20"),
    DonorInfo("Jane Smith", "500", "2023-02-20"),
    DonorInfo("Jane Smith", "500", "2023-02-20"),
)
val activitiesList = listOf(
    ActivityInfo("Activity 1", "Description for Activity 1", "2023-03-10"),
    ActivityInfo("Activity 2", "Description for Activity 2", "2023-04-15"),
)
val defaultImages = listOf(
    GalleryImage("https://cdn-icons-png.flaticon.com/128/2966/2966334.png", "Doctor with patient"),
    GalleryImage("https://cdn-icons-png.flaticon.com/128/2382/2382461.png", "Medical team"),
    GalleryImage("https://cdn-icons-png.flaticon.com/128/3004/3004451.png", "Surgical operation"),
    GalleryImage("https://cdn-icons-png.flaticon.com/128/4807/4807695.png", "Stethoscope and clipboard"),
    GalleryImage("https://cdn-icons-png.flaticon.com/128/1225/1225490.png", "Healthcare technology"),
    GalleryImage("https://cdn-icons-png.flaticon.com/128/10605/10605926.png", "Medical research lab")
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceAboutScreen(
    serviceInfo: ServiceInfo,
    onBackClick: () -> Unit,

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background13),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
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

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.90f)
                                .size(125.dp)
                                .align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(
                                topStart = 72.dp,
                                topEnd = 8.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 72.dp
                            ),
                            elevation = CardDefaults.cardElevation(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xEEB898).copy(alpha = 1f)
                            ),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = serviceInfo.title, color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = serviceInfo.subtitle,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = serviceInfo.about,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ServiceDonorsScreen(
        serviceTitle: String,
        donors: List<DonorInfo> = donorsList,
        onBackClick: () -> Unit
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background13),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
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
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .size(125.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(
                        topStart = 72.dp,
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 72.dp
                    ),
                    elevation = CardDefaults.cardElevation(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xEEB898).copy(alpha = 1f)
                    ),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = serviceTitle, color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Donors",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))

                this@LazyColumn.items(donors) { donor ->
                    Card(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFFFCCBC)
                        ),
                        elevation = CardDefaults.elevatedCardElevation(16.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = donor.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Amount: ${donor.amount}",
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                            Text(
                                text = "Date: ${donor.date}",
                                color = Color.Gray,
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ServiceActivitiesScreen(
        serviceTitle: String,
        activities: List<ActivityInfo>,
        onBackClick: () -> Unit
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background13),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
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

                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.90f)
                                .size(125.dp)
                                .align(Alignment.CenterHorizontally),
                            shape = RoundedCornerShape(
                                topStart = 72.dp,
                                topEnd = 8.dp,
                                bottomStart = 8.dp,
                                bottomEnd = 72.dp
                            ),
                            elevation = CardDefaults.cardElevation(24.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xEEB898).copy(alpha = 1f)
                            ),
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = serviceTitle, color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 32.sp,
                                    textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "Activities",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(32.dp))

                        this@LazyColumn.items(activities) { activity ->
                            Card(
                                modifier = Modifier.fillMaxWidth(0.9f),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(0xFFFFCCBC)
                                ),
                                elevation = CardDefaults.elevatedCardElevation(16.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = activity.title,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(text = activity.description, fontSize = 16.sp)
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = "Date: ${activity.date}",
                                        color = Color.Gray,
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ServiceGalleryScreen(
        serviceTitle: String,
        images: List<GalleryImage>,
        onBackClick: () -> Unit,
        onImageAdd: (Uri) -> Unit
    ) {
        val context = LocalContext.current
        var showImagePickerDialog by remember { mutableStateOf(false) }

        val cameraLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { success ->
            if (success) {
                // Handle the captured image
            }
        }

        val galleryLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { onImageAdd(it) }
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background13),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopAppBar(
                    title = { },
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
                    actions = {
                        // Removed the Add button from the TopAppBar
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent
                    )
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.90f)
                        .size(125.dp)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(
                        topStart = 72.dp,
                        topEnd = 8.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 72.dp
                    ),
                    elevation = CardDefaults.cardElevation(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xEEB898).copy(alpha = 1f)
                    ),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = serviceTitle, color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "Gallery",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                FloatingActionButton(
                    onClick = { showImagePickerDialog = true },
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.primary,
                    elevation = FloatingActionButtonDefaults.elevation(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Image",
                        tint = Color.White
                    )
                }

                Box(modifier = Modifier.fillMaxSize()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(0.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(images.size) { index ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(1f),
                                colors = CardDefaults.cardColors(
                                    containerColor = Color.White.copy(alpha = 0.9f)
                                )
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(images[index].imageUri)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = images[index].description,
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }

                    // Floating Action Button (FAB) for adding images

                }
            }
        }

        if (showImagePickerDialog) {
            AlertDialog(
                onDismissRequest = { showImagePickerDialog = false },
                title = { Text("Add Image") },
                text = { Text("Choose image source") },
                confirmButton = {
                    TextButton(onClick = {
                        showImagePickerDialog = false
                        galleryLauncher.launch("image/*")
                    }) {
                        Text("Gallery")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showImagePickerDialog = false
                        // Launch camera
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }

