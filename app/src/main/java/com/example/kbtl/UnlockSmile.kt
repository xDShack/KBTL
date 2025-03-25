package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnlockSmile(
    onFrontClick: () -> Unit,
    onHealthClick: () -> Unit,
    onOldageClick: () -> Unit,
    onEducationClick: () -> Unit,
    onWomanEmpowermentClick: () -> Unit,
    onDifferentlyAbledClick: () -> Unit,
    onLivelihoodClick: () -> Unit,
    onSpreadKindnessClick: () -> Unit,
    onRequestSupportClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background11),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Unlock Your Smile",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Center)
                    )
                    TopAppBar(
                        title = {},
                        navigationIcon = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp),
                                horizontalArrangement = Arrangement.End  // This aligns content to the end (right) of the Row
                            ) {
                                IconButton(onClick = onFrontClick) {
                                    Icon(
                                        painter = painterResource(R.drawable.arrow_icon),
                                        contentDescription = "Back",
                                        tint = Color.Black,
                                        modifier = Modifier
                                            .size(24.dp)
                                    )
                                }
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent // Make app bar transparent
                        )
                    )
                }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    ServiceButtons(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        onHealthClick = onHealthClick,
                        onOldageClick = onOldageClick,
                        onEducationClick = onEducationClick,
                        onWomanEmpowermentClick = onWomanEmpowermentClick,
                        onDifferentlyAbledClick = onDifferentlyAbledClick,
                        onLivelihoodClick = onLivelihoodClick,
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ActionButton(
                            text = "Spread Kindness",
                            icon = R.drawable.spread_kindness_icon,
                            onClick = { onSpreadKindnessClick() }
                        )

                        ActionButton(
                            text = "Request to Support",
                            icon = R.drawable.request_support_icon,
                            onClick = {onRequestSupportClick()}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ServiceButtons(
    modifier: Modifier = Modifier,
    onHealthClick: () -> Unit,
    onOldageClick: () -> Unit,
    onEducationClick: () -> Unit,
    onWomanEmpowermentClick: () -> Unit,
    onDifferentlyAbledClick: () -> Unit,
    onLivelihoodClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ServiceCard(
                imageRes = R.drawable.healthcare,
                title = "Healthcare Support",
                subtitle = "(Varumun Kappom)",
                modifier = Modifier.weight(1f),
                onClick = {onHealthClick()}
            )
            ServiceCard(
                imageRes = R.drawable.oldage,
                title = "Oldage Home Management",
                subtitle = "(Kai Kodukkumkai)",
                modifier = Modifier.weight(1f),
                onClick = {onOldageClick()}
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ServiceCard(
                imageRes = R.drawable.education,
                title = "Educational Support",
                subtitle = "(Puthiya Vidiyel)",
                modifier = Modifier.weight(1f),
                onClick = {onEducationClick()}
            )
            ServiceCard(
                imageRes = R.drawable.livelihood,
                title = "Livelihood Support",
                subtitle = "(Maghizhvithu Maghizh)",
                modifier = Modifier.weight(1f),
                onClick = {onLivelihoodClick()}
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ServiceCard(
                imageRes = R.drawable.differently_abled,
                title = "Differently Abled",
                subtitle = "(Petrathan Pillaiya)",
                modifier = Modifier.weight(1f),
                onClick = {onDifferentlyAbledClick()}
            )
            ServiceCard(
                imageRes = R.drawable.women,
                title = "Women Empowerment",
                subtitle = "(Yaavum Velvaai)",
                modifier = Modifier.weight(1f),
                onClick = {onWomanEmpowermentClick()}
            )
        }
    }
}

@Composable
fun ServiceCard(
    imageRes: Int,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .clickable ( onClick = onClick )
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(60.dp)
                    .padding(bottom = 8.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ActionButton(
    text: String,
    icon: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFEEB898)
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp)
            )
            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}

