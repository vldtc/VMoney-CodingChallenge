package com.example.vmoney_codingchallenge.domain

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale


fun convertDateFormat(inputDate: String?): String? {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("[dd/MM/yyyy]", Locale.getDefault())
    val date = inputDate?.let { inputFormat.parse(it) }
    return date?.let { outputFormat.format(it) }
}
