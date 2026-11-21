package task10

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import kotlin.time.Duration.Companion.milliseconds

suspend fun main() {
    try {
//        supervisorScope {
        coroutineScope {
            this.launch {
                try {
                    delay(1000.milliseconds)
                    println("A done")
                } catch (e: Exception) {
                    println("A cancelled")
                    throw e
                }
            }
            this.launch {
                try {
                    delay(300.milliseconds)
                    println("B done")
                } catch (e: Exception) {
                    println("B cancelled")
                    throw e
                }
            }
            this.launch {
                delay(200.milliseconds)
                throw IllegalStateException("boom")
            }

        }
    } catch (e: IllegalStateException) {
        println("caught: ${e.message}")
    }
}