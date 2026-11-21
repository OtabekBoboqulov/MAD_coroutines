package task02

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

var calls = 0

suspend fun fetchName(): String? {
    delay(100.milliseconds)
    calls++
    return if (calls <= 2) null else "Ada"
}

suspend fun showUser(n: Int) {
    for (k in 1..n) {
        val name = fetchName()
        if (name != null) {
            println("User: $name")
            return
        }
        println("try $k failed")
    }
    println("Server is down, show cached user")
}

fun main() = runBlocking {
    println("n = 3")
    showUser(3)
    println("-".repeat(20))
    calls = 0
    println("n = 2")
    showUser(2)
}
