package task04

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    val parent = launch {
        launch {
            try {
                delay(100.milliseconds)
                println("Child 1 done")
            } catch (e: CancellationException) {
                println("Child 1 cancelled")
            }
        }
        launch {
            try {
                delay(200.milliseconds)
                println("Child 2 done")
            } catch (e: CancellationException) {
                println("Child 2 cancelled")
            }
        }
        launch {
            try {
                delay(300.milliseconds)
                println("Child 3 done")
            } catch (e: CancellationException) {
                println("Child 3 cancelled")
            }
        }
    }
//    parent.join()
    delay(150.milliseconds)
    parent.cancelAndJoin()
}
