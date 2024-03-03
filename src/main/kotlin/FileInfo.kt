package com.aimen

import java.time.Duration
import java.time.Instant
import kotlin.math.ln
import kotlin.math.pow
import kotlin.math.round

class FileInfo(
    private val name: String,
    private val directory: Boolean,
    private val executable: Boolean,
    val totalSpace: Long,
    private val lastModified: Long,
    val filePermissions: String
) {
    fun formatName(): String {
        return if (directory) {
            "\u001B[1;32m$name\u001B[0m".padEnd(41)
        } else {
            if (executable) {
                "\u001B[3m$name\u001B[0m".padEnd(38)
            } else {
                name.padEnd(30)
            }
        }
    }

    fun getFileType(): String {
        return if (directory) {
            "dir"
        } else {
            "file"
        }
    }

    fun formatModifiedTime(): String {
        val elapsedTime = Duration.between(Instant.ofEpochMilli(lastModified), Instant.now())

        return when {
            elapsedTime.seconds < 60 -> "${elapsedTime.seconds} seconds ago"
            elapsedTime.toMinutes() < 60 -> "${elapsedTime.toMinutes()} minutes ago"
            elapsedTime.toHours() < 24 -> "${elapsedTime.toHours()} hours ago"
            elapsedTime.toDays() < 30 -> "${elapsedTime.toDays()} days ago"
            elapsedTime.toDays() < 365 -> "${elapsedTime.toDays() / 30} months ago"
            else -> "${elapsedTime.toDays() / 365} years ago"
        }
    }

    fun formatSize(): String {
        val sizes = arrayOf("B", "KB", "MB", "GB", "TB", "PB", "EB")
        if (totalSpace == 0L) {
            return "0B"
        }
        val i = (ln(totalSpace.toDouble()) / ln(1024.0)).toInt().coerceAtMost(sizes.size - 1)
        val sizeStr = (totalSpace.toDouble() / 1024.0.pow(i.toDouble())).let { round(it).toInt().toString() }
        var sizeFormatted = sizeStr.trimEnd('0', '.')
        if (sizeFormatted == "0") {
            sizeFormatted = "0"
        }
        return sizeFormatted + sizes[i]
    }
}