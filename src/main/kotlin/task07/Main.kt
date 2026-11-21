package task07

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.milliseconds

suspend fun fetchPrice(): Int {
    delay(500.milliseconds)
    return 42
}

fun main() = runBlocking {
    val result = withTimeoutOrNull(800.milliseconds) {
        fetchPrice()
    }
    println(result)

    try {
        val result2 = withTimeout(300.milliseconds) {
            fetchPrice()
        }
        println(result2)
    } catch (e: TimeoutCancellationException) {
        println("timeout caught")
    }
}
