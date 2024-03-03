package com.aimen

import java.io.File

class Formatter {
    companion object {
        fun getHeader(small: Boolean): String {
            return if (small) {
                "| %-2s | %-30s |".format("#", "name")
            } else {
                "| %-2s | %-30s | %-4s | %8s | %-15s | %s |".format("#", "name", "type", "size", "modified", "perms")
            }
        }

        fun getDelimiter(small: Boolean): String {
            return if (small) {
                "-".repeat(39)
            } else {
                "-".repeat(83)
            }
        }

        fun getFilePermissions(file: File): String {
            val readable = file.canRead()
            val writable = file.canWrite()
            val executable = file.canExecute()

            val perms = StringBuilder()
            perms.append(if (readable) "r" else "-")
            perms.append(if (writable) "w" else "-")
            perms.append(if (executable) "x" else "-")

            return perms.toString()
        }
    }
}