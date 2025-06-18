package com.example.targetlog.commons

import java.util.Calendar
import java.util.Date
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun getCombinedDateTimeAsLong(date: Date): Long {
    val calendar = Calendar.getInstance()
    calendar.time = date

    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1 // Months are 0-indexed
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val hour = calendar.get(Calendar.HOUR_OF_DAY) // 24-hour format
    val minute = calendar.get(Calendar.MINUTE)
    val second = calendar.get(Calendar.SECOND)

    // Combine into a single integer as YYYYMMDDHHMMSS
    return "$year%02d%02d%02d%02d%02d".format(month, day, hour, minute,second).toLong()
}


fun getCombinedDateTimeAsString(datetime: Long):  String  {
    val year = (datetime / 10000000000L).toInt() // Extract the year
    val month = ((datetime / 100000000) % 100).toInt() // Extract the month
    val day = ((datetime / 1000000) % 100).toInt() // Extract the day
    val hour = ((datetime / 10000) % 100).toInt() // Extract the hour
    val minute = ((datetime / 100) % 100).toInt() // Extract the minute
    val second = (datetime % 100).toInt() // Extract the second

    return "$year-%02d-%02d_%02d:%02d:%02d".format(month, day, hour, minute,second)
}

fun getDateTimeParts(date: Date): Map<String, String> {
    val instant = date.toInstant()
    val zoneId = ZoneId.systemDefault() // or specify a zone: ZoneId.of("UTC")
    val zonedDateTime = instant.atZone(zoneId)

    return mapOf(
        "date" to zonedDateTime.dayOfMonth.toString(),
        "day" to zonedDateTime.dayOfWeek.toString().take(3), // "MON", "TUE", etc.
        "month" to zonedDateTime.month.toString().take(3), // "JAN", "FEB", etc.
        "year" to zonedDateTime.year.toString(),
        "time" to DateTimeFormatter.ofPattern("HH:mm:ss").format(zonedDateTime)
    )
}

fun daysFromToday(targetDate: Date): Long {
    val today = LocalDate.now()
    val targetLocalDate = targetDate.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    return ChronoUnit.DAYS.between(targetLocalDate,today)
}
