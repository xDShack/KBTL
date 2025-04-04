package com.example.kbtl

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(
    viewModel: KindnessCalendarViewModel,
    onDateClick: (LocalDate) -> Unit
) {
    val currentMonth = viewModel.currentMonth
    val calendarViewMode = viewModel.calendarViewMode
    var showViewModeMenu by remember { mutableStateOf(false) }
    val today = LocalDate.now()
    val todaysRecommendedTask = viewModel.getRecommendedTask(today)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.background11),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Calendar content overlaid on top of the background
        Scaffold(
            modifier = Modifier.background(Color.Transparent), // Ensure transparency
            topBar = {
                TopAppBar(
                    title = { Text("Kindness Calendar") },
                    actions = {
                        Row {
                            Button(
                                onClick = { showViewModeMenu = true },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = when (calendarViewMode) {
                                            CalendarViewMode.DAILY -> "Daily"
                                            CalendarViewMode.MONTHLY -> "Monthly"
                                            CalendarViewMode.YEARLY -> "Yearly"

                                        }
                                    )
                                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Switch View")
                                }
                            }

                            DropdownMenu(
                                expanded = showViewModeMenu,
                                onDismissRequest = { showViewModeMenu = false }
                            ) {
                                DropdownMenuItem(
                                    text = { Text("Daily View") },
                                    onClick = {
                                        viewModel.calendarViewMode = CalendarViewMode.DAILY
                                        showViewModeMenu = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Monthly View") },
                                    onClick = {
                                        viewModel.calendarViewMode = CalendarViewMode.MONTHLY
                                        showViewModeMenu = false
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("Yearly View") },
                                    onClick = {
                                        viewModel.calendarViewMode = CalendarViewMode.YEARLY
                                        showViewModeMenu = false
                                    }
                                )

                            }
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                // Today's Recommended Task Card
                if (calendarViewMode == CalendarViewMode.MONTHLY) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        elevation = CardDefaults.cardElevation(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xEEB898).copy(alpha = 0.95f)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Today's Recommended Kindness",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = todaysRecommendedTask,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            TextButton(
                                onClick = { onDateClick(today) },
                                modifier = Modifier.align(Alignment.End),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Black)
                            ) {
                                Text("Add Your Act of Kindness")
                            }
                        }
                    }
                }

                when (calendarViewMode) {
                    CalendarViewMode.DAILY -> DailyView(
                        viewModel = viewModel,
                        onNextDayClick = { viewModel.updateSelectedDate(viewModel.selectedDate.plusDays(1)) },
                        onPreviousDayClick = { viewModel.updateSelectedDate(viewModel.selectedDate.minusDays(1)) },
                        onAddTaskClick = { onDateClick(viewModel.selectedDate) }
                    )
                    CalendarViewMode.MONTHLY -> MonthlyCalendarView(
                        currentMonth = currentMonth,
                        viewModel = viewModel,
                        onDateClick = onDateClick,
                        onPreviousMonthClick = {
                            viewModel.changeMonth(currentMonth.minusMonths(1))
                        },
                        onNextMonthClick = {
                            viewModel.changeMonth(currentMonth.plusMonths(1))
                        }
                    )

                    CalendarViewMode.YEARLY -> YearlyCalendarView(
                        year = currentMonth.year,
                        viewModel = viewModel,
                        onPreviousYearClick = {
                            viewModel.changeMonth(currentMonth.minusYears(1))
                        },
                        onNextYearClick = {
                            viewModel.changeMonth(currentMonth.plusYears(1))
                        },
                        onMonthClick = { month ->
                            viewModel.changeMonth(YearMonth.of(currentMonth.year, month))
                            viewModel.calendarViewMode = CalendarViewMode.MONTHLY
                        }
                    )


                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyView(
    viewModel: KindnessCalendarViewModel,
    onNextDayClick: () -> Unit,
    onPreviousDayClick: () -> Unit,
    onAddTaskClick: () -> Unit
) {
    val selectedDate = viewModel.selectedDate
    val recommendedTask = viewModel.getRecommendedTask(selectedDate)
    val userTasks = viewModel.getUserTasks(selectedDate)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Date navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousDayClick) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Day")
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = selectedDate.format(DateTimeFormatter.ofPattern("EEEE")),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = selectedDate.format(DateTimeFormatter.ofPattern("MMMM d, yyyy")),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            IconButton(onClick = onNextDayClick) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Day")
            }
        }

        // Divider
        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Content
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            // Recommended task card
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xEEB898).copy(alpha = 0.95f)
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Recommended Kindness",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = recommendedTask,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }
                }
            }

            // Section header for user tasks
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your Acts of Kindness",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    val taskCount = userTasks.size
                    Text(
                        text = "$taskCount ${if (taskCount == 1) "activity" else "activities"}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // User tasks
            if (userTasks.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "No Tasks",
                                modifier = Modifier.size(48.dp),
                                tint = Color(0xEEB898).copy(alpha = 0.95f)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "No kindness activities added yet",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Add one to track your kind deeds",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            } else {
                items(userTasks) { task ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Show image if it exists
                            task.imageUri?.let { uri ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .padding(bottom = 12.dp)
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(
                                            ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                                                .data(uri)
                                                .build()
                                        ),
                                        contentDescription = "Task Image",
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(8.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }

                            Text(
                                text = task.text,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }

        // Add task button
        Button(
            onClick = onAddTaskClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF7043)
            ),
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add Task",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Add Act of Kindness")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyCalendarView(
    currentMonth: YearMonth,
    viewModel: KindnessCalendarViewModel,
    onDateClick: (LocalDate) -> Unit,
    onPreviousMonthClick: () -> Unit,
    onNextMonthClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Month navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousMonthClick) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Month")
            }

            Text(
                text = currentMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = onNextMonthClick) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Month")
            }
        }

        // Weekday headers
        Row(modifier = Modifier.fillMaxWidth()) {
            for (dayOfWeek in DayOfWeek.values()) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Days grid
        val days = getDaysInMonthGrid(currentMonth)
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(4.dp)
        ) {
            items(days) { day ->
                DayCell(
                    date = day,
                    isCurrentMonth = day.month == currentMonth.month,
                    viewModel = viewModel,
                    onClick = {
                        viewModel.updateSelectedDate(day)
                        onDateClick(day)
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DayCell(
    date: LocalDate,
    isCurrentMonth: Boolean,
    viewModel: KindnessCalendarViewModel,
    onClick: () -> Unit
) {
    val isToday = date == LocalDate.now()
    val hasRecommendedTask = viewModel.hasRecommendedTask(date)
    val userTaskCount = viewModel.getUserTaskCount(date)

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(4.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isToday) Color(0xEEB898).copy(alpha = 0.5f)
                else if (isCurrentMonth) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            )
            .clickable(enabled = isCurrentMonth, onClick = {
                onClick()
                // Switch to daily view when clicking on a day
                viewModel.calendarViewMode = CalendarViewMode.DAILY
            }),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Date number
            Text(
                text = date.dayOfMonth.toString(),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isToday) FontWeight.Bold else FontWeight.Normal,
                color = if (isCurrentMonth) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            // Indicator for tasks
            if (isCurrentMonth) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    // Recommended task indicator (orange dot)
                    if (hasRecommendedTask) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFFF7043))
                        )
                    }

                    // User-added tasks count
                    if (userTaskCount > 0) {
                        Text(
                            text = "+$userTaskCount",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(start = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun YearlyCalendarView(
    year: Int,
    viewModel: KindnessCalendarViewModel,
    onPreviousYearClick: () -> Unit,
    onNextYearClick: () -> Unit,
    onMonthClick: (Int) -> Unit
) {
    val monthlyTaskCounts = viewModel.getMonthlyTaskCounts(year)

    Column(modifier = Modifier.fillMaxWidth()) {
        // Year navigation
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onPreviousYearClick) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Previous Year")
            }

            Text(
                text = year.toString(),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            IconButton(onClick = onNextYearClick) {
                Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next Year")
            }
        }

        // Months grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(12) { monthIndex ->
                val month = monthIndex + 1
                val monthName = YearMonth.of(year, month)
                    .format(DateTimeFormatter.ofPattern("MMMM"))
                val taskCount = monthlyTaskCounts[month] ?: 0

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clickable { onMonthClick(month) },
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = monthName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$taskCount kind acts",
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

// Helper function to get days in month with proper grid alignment
@RequiresApi(Build.VERSION_CODES.O)
fun getDaysInMonthGrid(yearMonth: YearMonth): List<LocalDate> {
    val result = mutableListOf<LocalDate>()
    val firstDayOfMonth = yearMonth.atDay(1)
    val lastDayOfMonth = yearMonth.atEndOfMonth()

    // Fill in days from previous month
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
    if (firstDayOfWeek > 0) {
        val previousMonth = yearMonth.minusMonths(1)
        val daysFromPreviousMonth = firstDayOfWeek
        for (i in 0 until daysFromPreviousMonth) {
            val day = previousMonth.lengthOfMonth() - daysFromPreviousMonth + i + 1
            result.add(previousMonth.atDay(day))
        }
    }

    // Current month days
    for (day in 1..lastDayOfMonth.dayOfMonth) {
        result.add(yearMonth.atDay(day))
    }

    // Fill in days from next month
    val totalDaysNeeded = 42 // 6 rows x 7 days
    val daysFromNextMonth = totalDaysNeeded - result.size
    val nextMonth = yearMonth.plusMonths(1)
    for (day in 1..daysFromNextMonth) {
        result.add(nextMonth.atDay(day))
    }

    return result
}