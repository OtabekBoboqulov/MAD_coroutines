package task11

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    loadAndShow()
}

suspend fun loadAndShow() {
    println("start on ${Thread.currentThread().name}")

    val data = withContext(Dispatchers.IO) {
        println("read file on ${Thread.currentThread().name}")
        delay(100.milliseconds)
        "10,20,30"
    }

    val sum = withContext(Dispatchers.Default) {
        println("parse on ${Thread.currentThread().name}")
        data.split(",").sumOf { it.toInt() }
    }

    println("show $sum on ${Thread.currentThread().name}")
}