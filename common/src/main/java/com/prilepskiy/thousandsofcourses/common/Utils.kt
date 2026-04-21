package com.prilepskiy.thousandsofcourses.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import java.util.Locale

fun <T> emitFlow(action: suspend () -> T) = flow { emit(action.invoke()) }

fun <T> Flow<T>.subscribe(
    scope: CoroutineScope,
    success: suspend (value: T) -> Unit,
    error: suspend (Throwable) -> Unit = { },
    onStart: suspend () -> Unit = { },
    complete: () -> Unit = { }
) = scope.launch {
    subscribe(
        success = { success.invoke(it) },
        error = { error.invoke(it) },
        onStart = { onStart.invoke() }
    )
}.apply { invokeOnCompletion { complete.invoke() } }

suspend fun <T> Flow<T>.subscribe(
    success: suspend (value: T) -> Unit,
    error: suspend (Throwable) -> Unit = { },
    onStart: (suspend () -> Unit)? = null,
    onEnd: (suspend () -> Unit)? = null,
) {
    onStart?.invoke()
    try {
        collect {
            success.invoke(it)
            onEnd?.invoke()
        }
    } catch (throwable: Throwable) {
        error.invoke(throwable)
        onEnd?.invoke()
        throwable.printStackTrace()
    }
}
fun convertDateFormat(inputDateString: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru", "RU"))

    try {
        val date = inputFormat.parse(inputDateString)
        return outputFormat.format(date)
    } catch (e: Exception) {
        return "Ошибка парсинга даты"
    }
}

fun dateStringToTimestamp(dateString: String,pattern: String =  DATE_FORMAT_PATTERN_RESPONSE): Long {
    val inputFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return try {
        inputFormat.parse(dateString)?.time ?: 0L
    } catch (e: Exception) {
        0L
    }

}



fun timestampToDateString(timestamp: Long,pattern: String = DATE_FORMAT_PATTERN_MODEL): String {
    if (timestamp == 0L) return "Некорректная дата"

    val date = Date(timestamp)
    val outputFormat = SimpleDateFormat(pattern, Locale("ru", "RU"))
    return outputFormat.format(date)
}

const val DATE_FORMAT_PATTERN_MODEL="d MMMM yyyy"

const val DATE_FORMAT_PATTERN_RESPONSE="yyyy-MM-dd"

