package com.example.kbtl

import android.os.Build
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import java.time.LocalDate
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                NavigationBarItem(
                    selected = currentDestination?.route == "unlockSmileScreen",
                    onClick = { navController.navigate("unlockSmileScreen") },
                    label = { Text("Home") },
                    icon = { Icon(Icons.Default.Home, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == "aboutScreen",
                    onClick = { navController.navigate("aboutScreen") },
                    label = { Text("About") },
                    icon = { Icon(Icons.Default.Info, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == "profileScreen",
                    onClick = { navController.navigate("profileScreen") },
                    label = { Text("Profile") },
                    icon = { Icon(Icons.Default.AccountCircle, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == "calendar",
                    onClick = { navController.navigate("calendar") },
                    label = { Text("Calendar") },
                    icon = { Icon(Icons.Default.Create, contentDescription = null) }
                )
                NavigationBarItem(
                    selected = currentDestination?.route == "connectScreen",
                    onClick = { navController.navigate("connectScreen") },
                    label = { Text("Connect") },
                    icon = { Icon(Icons.Default.Share, contentDescription = null) }
                )
            }
        }
    ) { innerPadding ->
        val viewModel = remember { KindnessCalendarViewModel() }
        NavHost(
            navController = navController,
            startDestination = "unlockSmileScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("unlockSmileScreen") {
                UnlockSmile(
                    onFrontClick = { navController.navigate("welcomeScreen") },
                    onHealthClick = { navController.navigate("healthcare_service") },
                    onOldageClick = { navController.navigate("oldage_service") },
                    onEducationClick = { navController.navigate("education_service") },
                    onLivelihoodClick = { navController.navigate("livelihood_service") },
                    onDifferentlyAbledClick = { navController.navigate("differently_abled_service") },
                    onWomanEmpowermentClick = { navController.navigate("women_empowerment_service") },
                    onSpreadKindnessClick = { navController.navigate("wishesScreen") },
                    onRequestSupportClick = { navController.navigate("wishesScreen") }
                )
            }

            composable("welcomeScreen") {
                Welcome(
                    onBackClick = { navController.navigateUp() },
                    onCardClick = { cardTitle ->
                        // Handle card clicks based on the title
                        when (cardTitle) {
                            "Donate" -> navController.navigate("donateScreen")
                            "Volunteer" -> navController.navigate("unlockSmileScreen")
                            "Write a warm wish" -> navController.navigate("wishesScreen")
                            "Join us" -> navController.navigate("unlockSmileScreen")
                        }
                    }
                )
            }
            composable("loginScreen") {
                Login(
                    onBackClick = { navController.navigateUp() },
                    onProceedClick = { email, password ->
                        // Handle login logic
                    },
                    onSignUpClick = {
                        navController.navigate("signUpScreen")
                    }
                )
            }
            composable("signUpScreen") {
                SignUpScreen(
                    onBackClick = { navController.navigateUp() },
                    onSignUpClick = { signUpData ->
                        navController.navigate("profileScreen")
                    }
                )
            }
            composable("connectScreen") {
                ConnectWithUsScreen(
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("profileScreen") {
                ProfileScreen(
                    onBackClick = { navController.navigateUp() },
                    onNavigateToWishes = { navController.navigate("wishesScreen") },
                    onNavigateToAccount = { navController.navigate("signUpScreen") },
                    onNavigateToReviews = { navController.navigate("reviewScreen") },
                    onNavigateToEditProfile = { navController.navigate("loginScreen") },
                )
            }
            composable("navigationScreen") {
                NavScreen(navController)
            }
            composable("aboutScreen"){

                AboutScreen(
                    onBackClick = { navController.navigateUp() },
                    faqs = faqList
                )
            }
            composable("favoritesScreen"){
                FavoritesScreen(
                    onBackClick = {navController.navigateUp()},
                    favorites = favoritesList
                    )
            }
            composable("donateScreen"){
                DonationScreen(
                    onBackClick = { navController.navigateUp() },
                    onNavigateToQR = { amount, domain ->
                        navController.navigate("qr_screen/$amount/$domain")
                    },
                    onNavigateToUPI = { amount, domain ->
                        navController.navigate("upi_screen/$amount/$domain")
                    }
                )
            }
            composable(
                "qr_screen/{amount}/{domain}",
                arguments = listOf(
                    navArgument("amount") { type = NavType.IntType },
                    navArgument("domain") { type = NavType.StringType }
                )
            ) {
                QRScreenNavigation(navController)
            }
            composable("wishesScreen"){
                WarmWishesScreen(onBackClick = {navController.navigateUp()})
            }
            composable(
                route = getUPIScreenRoute(),
                arguments = getUPIScreenNavArgs()
            ) { backStackEntry ->
                val amount = backStackEntry.arguments?.getInt("amount") ?: 0
                val domain = backStackEntry.arguments?.getString("domain") ?: ""

                UPIScreen(
                    amount = amount,
                    domain = domain,
                    onBackClick = { navController.navigateUp() },
                    onPaymentSelect = { paymentMethod, upiId ->
                        // Handle payment selection
                        // For example, navigate to a payment confirmation screen
                        // Or show a dialog/toast
                        navController.navigateUp() // Or navigate to another screen
                    }
                )
            }
            composable("healthcare_service") {
                DynamicServiceScreen(
                    serviceInfo = healthcareService,
                    onBackClick = { navController.navigateUp()},
                    onOptionClick = { option ->
                        navController.navigate(option.route)
                    },
                )
            }
            composable("oldage_service") {
                DynamicServiceScreen(
                    serviceInfo = oldageHomeService,
                    onBackClick = { navController.navigateUp()},
                    onOptionClick = { option ->
                        navController.navigate(option.route)
                    },                )
            }
            composable("education_service") {
                DynamicServiceScreen(
                    serviceInfo = educationService,
                    onBackClick = { navController.navigateUp()},
                    onOptionClick = { option ->
                        navController.navigate(option.route)
                    },                )
            }
            composable("livelihood_service") {
                DynamicServiceScreen(
                    serviceInfo = livelihoodService,
                    onBackClick = { navController.navigateUp()},
                    onOptionClick = { option ->
                        navController.navigate(option.route)
                    },                )
            }
            composable("differently_abled_service") {
                DynamicServiceScreen(
                    serviceInfo = differentlyAbledService,
                    onBackClick = { navController.navigateUp()},
                    onOptionClick = { option ->
                        navController.navigate(option.route)
                    },                )
            }
            composable("women_empowerment_service") {
                DynamicServiceScreen(
                    serviceInfo = womenEmpowermentService,
                    onBackClick = { navController.navigateUp()},
                    onOptionClick = { option ->
                        navController.navigate(option.route)
                    },                )
            }
            composable("healthcare_about") {
                ServiceAboutScreen(
                    serviceInfo = healthcareService,
                    onBackClick = { navController.navigateUp() },

                )
            }
            composable("healthcare_donors") {
                val donors = remember { mutableStateListOf<DonorInfo>() }
                ServiceDonorsScreen(
                    serviceTitle = healthcareService.title,
                    donors = donorsList,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("healthcare_activities") {
                val activities = remember { mutableStateListOf<ActivityInfo>() }
                ServiceActivitiesScreen(
                    serviceTitle = healthcareService.title,
                    activities = activitiesList,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("healthcare_gallery") {
                val images = remember { mutableStateListOf<GalleryImage>().apply { addAll(defaultImages) } }
                ServiceGalleryScreen(
                    serviceTitle = healthcareService.title,
                    images = images,
                    onBackClick = { navController.navigateUp() },
                    onImageAdd = { uri ->
                        images.add(GalleryImage(uri.toString(), "New Image"))
                    }
                )
            }

            // Oldage Home routes
            composable("oldage_about") {
                ServiceAboutScreen(
                    serviceInfo = oldageHomeService,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("oldage_donors") {
                val donors = remember { mutableStateListOf<DonorInfo>() }
                ServiceDonorsScreen(
                    serviceTitle = oldageHomeService.title,
                    donors = donors,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("oldage_activities") {
                val activities = remember { mutableStateListOf<ActivityInfo>() }
                ServiceActivitiesScreen(
                    serviceTitle = oldageHomeService.title,
                    activities = activities,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("oldage_gallery") {
                val images = remember { mutableStateListOf<GalleryImage>() }
                ServiceGalleryScreen(
                    serviceTitle = oldageHomeService.title,
                    images = images,
                    onBackClick = { navController.navigateUp() },
                    onImageAdd = { uri ->
                        images.add(GalleryImage(uri.toString(), "New Image"))
                    }
                )
            }

            // Education routes
            composable("education_about") {
                ServiceAboutScreen(
                    serviceInfo = educationService,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("education_donors") {
                val donors = remember { mutableStateListOf<DonorInfo>() }
                ServiceDonorsScreen(
                    serviceTitle = educationService.title,
                    donors = donors,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("education_activities") {
                val activities = remember { mutableStateListOf<ActivityInfo>() }
                ServiceActivitiesScreen(
                    serviceTitle = educationService.title,
                    activities = activities,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("education_gallery") {
                val images = remember { mutableStateListOf<GalleryImage>() }
                ServiceGalleryScreen(
                    serviceTitle = educationService.title,
                    images = images,
                    onBackClick = { navController.navigateUp() },
                    onImageAdd = { uri ->
                        images.add(GalleryImage(uri.toString(), "New Image"))
                    }
                )
            }

            // Livelihood routes
            composable("livelihood_about") {
                ServiceAboutScreen(
                    serviceInfo = livelihoodService,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("livelihood_donors") {
                val donors = remember { mutableStateListOf<DonorInfo>() }
                ServiceDonorsScreen(
                    serviceTitle = livelihoodService.title,
                    donors = donors,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("livelihood_activities") {
                val activities = remember { mutableStateListOf<ActivityInfo>() }
                ServiceActivitiesScreen(
                    serviceTitle = livelihoodService.title,
                    activities = activities,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("livelihood_gallery") {
                val images = remember { mutableStateListOf<GalleryImage>() }
                ServiceGalleryScreen(
                    serviceTitle = livelihoodService.title,
                    images = images,
                    onBackClick = { navController.navigateUp() },
                    onImageAdd = { uri ->
                        images.add(GalleryImage(uri.toString(), "New Image"))
                    }
                )
            }

            // Differently Abled routes
            composable("differently_abled_about") {
                ServiceAboutScreen(
                    serviceInfo = differentlyAbledService,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("differently_abled_donors") {
                val donors = remember { mutableStateListOf<DonorInfo>() }
                ServiceDonorsScreen(
                    serviceTitle = differentlyAbledService.title,
                    donors = donors,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("differently_abled_activities") {
                val activities = remember { mutableStateListOf<ActivityInfo>() }
                ServiceActivitiesScreen(
                    serviceTitle = differentlyAbledService.title,
                    activities = activities,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("differently_abled_gallery") {
                val images = remember { mutableStateListOf<GalleryImage>() }
                ServiceGalleryScreen(
                    serviceTitle = differentlyAbledService.title,
                    images = images,
                    onBackClick = { navController.navigateUp() },
                    onImageAdd = { uri ->
                        images.add(GalleryImage(uri.toString(), "New Image"))
                    }
                )
            }

            // Women Empowerment routes
            composable("women_empowerment_about") {
                ServiceAboutScreen(
                    serviceInfo = womenEmpowermentService,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("women_empowerment_donors") {
                val donors = remember { mutableStateListOf<DonorInfo>() }
                ServiceDonorsScreen(
                    serviceTitle = womenEmpowermentService.title,
                    donors = donors,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("women_empowerment_activities") {
                val activities = remember { mutableStateListOf<ActivityInfo>() }
                ServiceActivitiesScreen(
                    serviceTitle = womenEmpowermentService.title,
                    activities = activities,
                    onBackClick = { navController.navigateUp() }
                )
            }
            composable("women_empowerment_gallery") {
                val images = remember { mutableStateListOf<GalleryImage>() }
                ServiceGalleryScreen(
                    serviceTitle = womenEmpowermentService.title,
                    images = images,
                    onBackClick = { navController.navigateUp() },
                    onImageAdd = { uri ->
                        images.add(GalleryImage(uri.toString(), "New Image"))
                    }
                )
            }
            composable("reviewScreen"){
                ReviewScreen(
                    onBackClick = {navController.navigateUp()},
                    Reviews = reviewList
                )
            }

            composable("calendar") {
                CalendarScreen(
                    viewModel = viewModel,
                    onDateClick = { date ->
                        viewModel.selectedDate = date
                        navController.navigate("tasks")
                    }
                )
            }
            composable("tasks") {
                TasksScreen(
                    viewModel = viewModel,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
