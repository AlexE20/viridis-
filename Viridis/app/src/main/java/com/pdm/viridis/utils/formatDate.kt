package com.pdm.viridis.utils

import TimestampResponse
import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun TimestampResponse.toFormattedDate(): String {
    val instant = Instant.ofEpochSecond(_seconds, _nanoseconds.toLong())
    val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        .withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}
