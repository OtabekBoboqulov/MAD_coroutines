package task08

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(10) { delay(100.milliseconds) }
        } finally {
            withContext(NonCancellable) { // added in the second part of the task
                println("cleanup start"); delay(100.milliseconds); println("draft saved")
            }
        }
    }
    delay(250.milliseconds)
    job.cancelAndJoin()
}
