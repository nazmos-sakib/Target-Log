package com.example.targetlog.commons

 import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

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