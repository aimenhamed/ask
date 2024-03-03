package com.aimen

class Args(private val args: Array<String>) {
    var small: Boolean = false
    lateinit var dir: String

    init {
        if (args.isEmpty()) {
            this.dir = "."
            this.small = true
        }
        if (args.size == 1) {
            this.small = args[0] != "-a"
            this.dir = if (this.small) {
                args[0]
            } else {
                "."
            }
        }
        if (args.size == 2) {
            this.small = args[0] != "-a"
            this.dir = args[1]
        }
    }
}