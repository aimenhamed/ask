package com.aimen

import java.io.File

fun main(args: Array<String>) {
    val a = Args(args)
    val small = a.small
    val dir = File(a.dir)
    var files = arrayOf<FileInfo>()
    dir.listFiles()?.map {
        files += FileInfo(
            it.name,
            it.isDirectory,
            it.canExecute(),
            it.length(),
            it.lastModified(),
            Formatter.getFilePermissions(it)
        )
    }

    println(Formatter.getHeader(small))
    println(Formatter.getDelimiter(small))
    var count = 0
    files.forEach {
        count += 1
        if (small) {
            println("| %-2d | %s |".format(count, it.formatName()))
            return@forEach
        }
        println(
            "| %-2d | %s | %-4s | %8s | %-15s | %-5s |".format(
                count,
                it.formatName(),
                it.getFileType(),
                it.formatSize(),
                it.formatModifiedTime(),
                it.filePermissions
            )
        )
    }
}