package com.example

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class DashboardUiState(
    val selectedService: AppService? = null,
    val lastUpdated: String = "",
    val isRefreshing: Boolean = false,
    val serviceItems: Map<AppService, List<ServiceItem>> = emptyMap(),
    val quickSummary: List<String> = emptyList()
)

class DashboardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DashboardUiState())
    val uiState: StateFlow<DashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    fun selectService(service: AppService?) {
        _uiState.value = _uiState.value.copy(
            selectedService = if (_uiState.value.selectedService == service) null else service
        )
    }

    fun refreshData() {
        _uiState.value = _uiState.value.copy(isRefreshing = true)
        loadDashboardData()
        _uiState.value = _uiState.value.copy(isRefreshing = false)
    }

    private fun loadDashboardData() {
        val dateFormat = SimpleDateFormat("EEEE, MMM d • h:mm a", Locale.getDefault())
        val formattedTime = dateFormat.format(Date())

        val items = mapOf(
            AppService.GMAIL to listOf(
                ServiceItem("g1", "Meeting update from Design team", "Sarah Chen: Assets reviewed and finalized for release", "9:45 AM", "Gmail", true),
                ServiceItem("g2", "Urgent: Project roadmap milestone", "Alex Rivera: Q4 timeline signed off by stakeholders", "8:30 AM", "Gmail", true),
                ServiceItem("g3", "Weekly Google Workspace digest", "Google Workspace: Storage optimization tips & report", "Yesterday", "Gmail", false)
            ),
            AppService.CALENDAR to listOf(
                ServiceItem("c1", "Executive Sync & Quarterly Review", "Conference Room A • 4 attendees", "10:00 - 10:45 AM", "Calendar", true),
                ServiceItem("c2", "Product Performance Retrospective", "Google Meet call", "2:00 - 3:00 PM", "Calendar", false)
            ),
            AppService.DRIVE to listOf(
                ServiceItem("d1", "Q4_Performance_Report.pdf", "Modified 15 min ago by You", "15m ago", "Drive", true),
                ServiceItem("d2", "Client_Strategy_Proposal_v3.docx", "Shared with engineering leads", "2h ago", "Drive", false),
                ServiceItem("d3", "Workspace_Analytics_2026.xlsx", "Updated by Finance team", "1d ago", "Drive", false)
            ),
            AppService.TASKS to listOf(
                ServiceItem("t1", "Approve final deployment build", "Pending QA sign-off review", "Due today", "Tasks", true),
                ServiceItem("t2", "Respond to partner API inquiries", "High priority ticket queue", "Due 4:00 PM", "Tasks", true),
                ServiceItem("t3", "Audit monthly cloud infrastructure budget", "Consolidate expenses", "Due tomorrow", "Tasks", false)
            ),
            AppService.MESSENGER to listOf(
                ServiceItem("m1", "Engineering Core", "Elena: CI/CD runner build completed cleanly", "Just now", "Chat", true),
                ServiceItem("m2", "Product Ops", "Marcus: Ready for app launch testing", "12m ago", "Chat", false)
            )
        )

        val summaries = listOf(
            "Gmail: 3 unread priority threads require attention",
            "Calendar: Next event at 10:00 AM (Executive Sync)",
            "Drive: Q4 Report edited 15m ago and synced",
            "Tasks: 2 high-priority tasks due before EOD"
        )

        _uiState.value = _uiState.value.copy(
            lastUpdated = formattedTime,
            serviceItems = items,
            quickSummary = summaries
        )
    }
}
