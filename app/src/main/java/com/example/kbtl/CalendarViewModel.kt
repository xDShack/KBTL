package com.example.kbtl

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate
import java.time.YearMonth

// Define a data class for tasks with optional image
data class KindnessTask(
    val text: String,
    val imageUri: Uri? = null
)

@RequiresApi(Build.VERSION_CODES.O)
class KindnessCalendarViewModel {
    // Current displayed month/year
    var currentMonth by mutableStateOf(YearMonth.now())

    // Calendar view mode (Monthly or Yearly)
    var calendarViewMode by mutableStateOf(CalendarViewMode.MONTHLY)

    // Selected date for task detail view
    var selectedDate by mutableStateOf(LocalDate.now())

    // Map of recommended kindness tasks for each day
    private val recommendedTasks = mutableMapOf<LocalDate, String>()

    // Map of user added tasks for each day (now using KindnessTask instead of String)
    private val userTasks = mutableMapOf<LocalDate, MutableList<KindnessTask>>()

    // Flow to observe changes to user tasks
    private val _userTasksFlow = MutableStateFlow<Map<LocalDate, List<KindnessTask>>>(emptyMap())
    val userTasksFlow: StateFlow<Map<LocalDate, List<KindnessTask>>> = _userTasksFlow.asStateFlow()

    init {
        // Initialize with recommended kindness tasks for the current month
        generateRecommendedTasks()
    }

    // Get the recommended task for a specific date
    fun getRecommendedTask(date: LocalDate): String {
        return recommendedTasks[date] ?: "Do something kind today!"
    }

    // Get all user tasks for a specific date
    fun getUserTasks(date: LocalDate): List<KindnessTask> {
        return userTasks[date] ?: emptyList()
    }

    // Add a new user task for a specific date with optional image
    @RequiresApi(Build.VERSION_CODES.O)
    fun addUserTask(date: LocalDate, task: String, imageUri: Uri? = null) {
        if (!userTasks.containsKey(date)) {
            userTasks[date] = mutableListOf()
        }
        userTasks[date]?.add(KindnessTask(task, imageUri))
        _userTasksFlow.value = userTasks.toMap()
    }

    // Remove a user task
    fun removeUserTask(date: LocalDate, index: Int) {
        userTasks[date]?.removeAt(index)
        _userTasksFlow.value = userTasks.toMap()
    }

    // Get count of user tasks for a specific date
    fun getUserTaskCount(date: LocalDate): Int {
        return userTasks[date]?.size ?: 0
    }

    // Get total task count (recommended + user tasks) for a specific date
    fun getTotalTaskCount(date: LocalDate): Int {
        val recommendedCount = if (recommendedTasks.containsKey(date)) 1 else 0
        return recommendedCount + getUserTaskCount(date)
    }

    // Get monthly task counts for yearly view
    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlyTaskCounts(year: Int): Map<Int, Int> {
        val result = mutableMapOf<Int, Int>()

        for (month in 1..12) {
            var count = 0
            val yearMonth = YearMonth.of(year, month)

            for (day in 1..yearMonth.lengthOfMonth()) {
                val date = LocalDate.of(year, month, day)
                count += getTotalTaskCount(date)
            }

            result[month] = count
        }

        return result
    }

    // Generate random recommended tasks for demonstration
    @RequiresApi(Build.VERSION_CODES.O)
    private fun generateRecommendedTasks() {
        val kindnessTasks = listOf(
            "Compliment a stranger",
            "Call a family member",
            "Donate to charity",
            "Help a neighbor",
            "Write a thank you note",
            "Buy coffee for someone",
            "Volunteer locally",
            "Pick up litter",
            "Share a positive message",
            "Cook for someone",
            "Give directions to someone lost",
            "Let someone go ahead in line",
            "Share your knowledge",
            "Listen actively to someone",
            "Send an encouraging text",
            "Donate unused items",
            "Plant a tree",
            "Leave a generous tip",
            "Offer to babysit",
            "Make a bird feeder",
            "Visit an elderly person",
            "Leave positive reviews",
            "Recycle properly",
            "Be patient with others",
            "Offer your seat on public transport",
            "Smile at strangers",
            "Pay for someone's meal",
            "Send flowers to someone",
            "Share your umbrella",
            "Help someone carry groceries"
        )

        val yearMonth = currentMonth
        for (day in 1..yearMonth.lengthOfMonth()) {
            val date = yearMonth.atDay(day)
            recommendedTasks[date] = kindnessTasks.random()
        }
    }

    // Change month and generate new tasks
    @RequiresApi(Build.VERSION_CODES.O)
    fun changeMonth(yearMonth: YearMonth) {
        currentMonth = yearMonth
        generateRecommendedTasks()
    }
}

enum class CalendarViewMode {
    MONTHLY,
    YEARLY
}