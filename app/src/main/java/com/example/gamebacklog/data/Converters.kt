package com.example.gamebacklog.data

import androidx.room.TypeConverter
import java.util.*

/**
 * TODO RIGHT DATE CONVERTER
 */
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}