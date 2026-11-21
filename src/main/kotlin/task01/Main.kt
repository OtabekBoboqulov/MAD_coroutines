package task01

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

suspend fun main() {
    coroutineScope {
        this.launch {
            delay(300.milliseconds)
            println("A")
        }
        this.launch {
            delay(200.milliseconds)
            println("B")
        }
        this.launch {
            delay(100.milliseconds)
            println("C")
        }
        println("All launched")
    }
//    println("All launched")
    println("Scope done")
}