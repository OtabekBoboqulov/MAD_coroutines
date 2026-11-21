package task03

import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    val jobCoroutine: Job = launch {
        for (i in 0..4) {
            println("tick $i")
            delay(100.milliseconds)
        }
    }
    delay(250.milliseconds)
    jobCoroutine.cancelAndJoin()
//    jobCoroutine.join()
    println("isCalled=true")
}