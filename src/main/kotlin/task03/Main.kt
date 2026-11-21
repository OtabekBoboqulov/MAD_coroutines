package task03

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

fun CoroutineScope.launchTicker(name: String, period: Long, times: Int): Job {
    return launch {
        for (i in 1..times) {
            delay(period.milliseconds)
            println("$name $i")
        }
    }
}

suspend fun main() {
    coroutineScope {
        this.launchTicker("fast", 100, 3)
        this.launchTicker("slow", 250, 2)
    }
}
