package com.example

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

enum class AppService(
    val title: String,
    val subtitle: String,
    val badge: String,
    val color: Long
) {
    GMAIL("Gmail", "3 Unread priority messages", "3 New", 0xFFEF4444),
    CALENDAR("Calendar", "2 Events scheduled today", "2 Events", 0xFF3B82F6),
    DRIVE("Drive", "4 Docs recently synchronized", "4 Files", 0xFF10B981),
    TASKS("Tasks", "5 Priority action items", "5 Pending", 0xFFF59E0B),
    MESSENGER("Chat", "Direct messages & mentions", "Online", 0xFF8B5CF6)
}

data class ServiceItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val time: String,
    val category: String,
    val isImportant: Boolean = false
)
