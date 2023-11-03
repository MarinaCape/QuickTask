package com.cape.quicktask.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.formatLocalDate(): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return format(formatter)
}