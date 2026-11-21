package task06

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

fun main() = runBlocking {
    val job = launch(Dispatchers.Default) {
        repeat(5) {
            delay(100.milliseconds)
//            Thread.sleep(100)
//            ensureActive()
            println("work $it")
        }
    }
    delay(250.milliseconds)
    job.cancelAndJoin()
}