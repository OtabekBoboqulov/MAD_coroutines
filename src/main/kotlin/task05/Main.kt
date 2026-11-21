package task05

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    val job = launch(start = CoroutineStart.LAZY) { delay(500.milliseconds); println("body") }
    println("${job.isActive} ${job.isCompleted} ${job.isCancelled}")
    job.start()
    println("${job.isActive} ${job.isCompleted} ${job.isCancelled}")
    delay(100.milliseconds)
    job.cancel()
    println("${job.isActive} ${job.isCompleted} ${job.isCancelled}")
    job.join()
    println("${job.isActive} ${job.isCompleted} ${job.isCancelled}")
}
