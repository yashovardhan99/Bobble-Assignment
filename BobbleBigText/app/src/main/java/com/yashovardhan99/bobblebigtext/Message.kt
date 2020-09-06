package com.yashovardhan99.bobblebigtext

import androidx.compose.ui.unit.TextUnit
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity(tableName = "messages_table")
data class Message(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val text: String,
    val size: Float,
    val timestamp: Date = Date()
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}