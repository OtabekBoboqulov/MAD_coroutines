package task01

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    launch {
        delay(200.milliseconds)
        println("A done")
    }
    launch {
        delay(100.milliseconds)
        println("B done")
    }
    println("Start")
}