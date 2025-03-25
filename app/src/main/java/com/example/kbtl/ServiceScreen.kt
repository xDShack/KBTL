package com.example.kbtl

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data classes to hold service information
data class ServiceInfo(
    val title: String,
    val subtitle: String,
    val imageRes: Int,
    val options: List<ServiceOption>,
    val about: String
)

data class ServiceOption(
    val title: String,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicServiceScreen(
    serviceInfo: ServiceInfo,
    onOptionClick: (ServiceOption) -> Unit,
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
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
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Transparent // Make app bar transparent
                        )
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
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
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = serviceInfo.title,
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 28.sp,
                                    modifier = Modifier.padding(
                                        top = 15.dp,
                                        start = 30.dp,
                                        end = 30.dp
                                    ),
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = serviceInfo.subtitle,
                                    fontSize = 20.sp,
                                    color = Color.Gray,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                    ServiceOptionsGrid(
                        options = serviceInfo.options,
                        onOptionClick = onOptionClick
                    )
                    Image(
                        painter = painterResource(id = serviceInfo.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}

@Composable
fun ServiceOptionsGrid(
    options: List<ServiceOption>,
    onOptionClick: (ServiceOption) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.chunked(2).forEach { rowOptions ->
            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowOptions.forEach { option ->
                    ServiceOptionCard(
                        title = option.title,
                        modifier = Modifier.weight(1f),
                        onClick = { onOptionClick(option) } // Correctly pass the option
                    )
                }
                // Add empty space if odd number of options
                if (rowOptions.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ServiceOptionCard(
    title: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp)
                .clickable(onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
        }
    }
}

// Service definitions
val healthcareService = ServiceInfo(
    title = "Healthcare Support",
    subtitle = "Varumun Kappom",
    imageRes = R.drawable.healthcare,
    options = listOf(
        ServiceOption("About", "healthcare_about"),
        ServiceOption("Donor's List", "healthcare_donors"),
        ServiceOption("Gallery", "healthcare_gallery"),
        ServiceOption("Activities", "healthcare_activities")
    ),
    about = "Examples of social determinants that are barriers for rural communities in accessing healthcare include:\n" +
            "Low health literacy levels and incomplete perceptions of health.\n" +
            "Linguistic and educational disparities limited affordable, reliable, or public transportation options\n" +
            "Higher poverty rates, which can make it difficult for participants to pay for services or programsCultural and social norms surrounding health behaviors.\n" +
            "Keeping the above points in mind, we KBTL Foundation initiated Varumun Kappom,focusing on the healthcare reach to the rural area’s of The Nilgiris mainly on tribal community who resides inside the forest area."
)

val oldageHomeService = ServiceInfo(
    title = "Oldage Home Management",
    subtitle = "Kai Kodukkumkai",
    imageRes = R.drawable.oldage,
    options = listOf(
        ServiceOption("About", "oldage_about"),
        ServiceOption("Donor's List", "oldage_donors"),
        ServiceOption("Gallery", "oldage_gallery"),
        ServiceOption("Activities", "oldage_activities")
    ),
    about = "Due to their inability to earn a living, elderly people who live in poverty risk being abandoned by their own families. They frequently need to beg to survive. After their offspring go to urban areas in quest of better employment opportunities, many elderly people are left alone.\n" +
            "KBTL aspires to empower the elderly in the way that their loved ones envision for them when they are unable to be physically present with them for an extended period of time due to unforeseen circumstances.KBTL’s goal is to offer a trustworthy and dependable support system enables service that not only offers medical care but also serves as a social and emotional pillar."
)

val educationService = ServiceInfo(
    title = "Educational Support",
    subtitle = "Puthiya Vidiyel",
    imageRes = R.drawable.education,
    options = listOf(
        ServiceOption("About", "education_about"),
        ServiceOption("Donor's List", "education_donors"),
        ServiceOption("Gallery", "education_gallery"),
        ServiceOption("Activities", "education_activities")
    ),
    about = "KBTL Foundation places a strong emphasis on education support, recognizing it as the foundation for personal and societal growth. The foundation provides scholarships, learning resources, and mentoring to students from marginalized communities, ensuring access to quality education for all. By offering financial aid, school supplies, and academic guidance, KBTL empowers students to overcome economic challenges and pursue their academic aspirations. Additionally, the foundation promotes digital literacy and skill-building programs to equip students with the tools necessary for success in a rapidly evolving world."
)

val livelihoodService = ServiceInfo(
    title = "Livelihood Support",
    subtitle = "Maghizhvithu Maghizh",
    imageRes = R.drawable.livelihood,
    options = listOf(
        ServiceOption("About", "livelihood_about"),
        ServiceOption("Donor's List", "livelihood_donors"),
        ServiceOption("Gallery", "livelihood_gallery"),
        ServiceOption("Activities", "livelihood_activities")
    ),
    about = "Examples of social determinants that are barriers for rural communities in accessing healthcare include:\n" +
            "Low health literacy levels and incomplete perceptions of health.\n" +
            "Linguistic and educational disparities limited affordable, reliable, or public transportation options\n" +
            "Higher poverty rates, which can make it difficult for participants to pay for services or programsCultural and social norms surrounding health behaviors.\n" +
            "Keeping the above points in mind, we KBTL Foundation initiated Varumun Kappom,focusing on the healthcare reach to the rural area’s of The Nilgiris mainly on tribal community who resides inside the forest area."
)

val differentlyAbledService = ServiceInfo(
    title = "Differently Abled",
    subtitle = "Petrathan Pillaiya",
    imageRes = R.drawable.differently_abled,
    options = listOf(
        ServiceOption("About", "differently_abled_about"),
        ServiceOption("Donor's List", "differently_abled_donors"),
        ServiceOption("Gallery", "differently_abled_gallery"),
        ServiceOption("Activities", "differently_abled_activities")
    ),
    about = "KBTL’S Faith -on each child we touch :Let each youngster know that KBTL have complete faith in him or her. We Regularly demonstrate our devotion with both words and deeds.The child believe that the only thing the outside world sees in them is their disadvantage. His own parents shouldn’t ever make him feel the same way. KBTL TEAM are his biggest admirer and each youngster know that we have complete faith in him or her."
)

val womenEmpowermentService = ServiceInfo(
    title = "Women Empowerment",
    subtitle = "Yaavum Velvaai",
    imageRes = R.drawable.women,
    options = listOf(
        ServiceOption("About", "women_empowerment_about"),
        ServiceOption("Donor's List", "women_empowerment_donors"),
        ServiceOption("Gallery", "women_empowerment_gallery"),
        ServiceOption("Activities", "women_empowerment_activities")
    ),
    about = "KBTL Foundation is committed to fostering women empowerment by promoting education, skill development, and financial independence for women in underprivileged communities. Through various initiatives such as vocational training, entrepreneurship programs, and scholarships, the foundation helps women build confidence, gain economic stability, and break societal barriers. By creating a supportive environment, KBTL enables women to realize their potential, participate actively in decision-making processes, and contribute meaningfully to their families and communities."
)



