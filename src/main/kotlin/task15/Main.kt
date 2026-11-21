package task15

import kotlinx.coroutines.*
import java.io.IOException
import kotlin.system.measureTimeMillis

data class Dashboard(
    val weather: String?,
    val news: String?,
    val rates: String?
)

suspend fun loadDashboard(): Dashboard = coroutineScope {
    val weather = async(Dispatchers.IO) {
        try {
            withTimeoutOrNull(450) {
                delay(300)
                "Sunny"
            }
        } finally {
            println("weather stopped")
        }
    }

    val news = async(Dispatchers.IO) {
        try {
            withTimeoutOrNull(700) {
                delay(600)
                "Headlines"
            }
        } finally {
            println("news stopped")
        }
    }

    val rates = async(Dispatchers.IO) {
        try {
            withTimeoutOrNull(450) {
                delay(100)
                throw IOException("Rates unavailable")
                @Suppress("UNREACHABLE_CODE")
                "Rates"
            }
        } catch (e: IOException) {
            null
        }
    }

    Dashboard(weather.await(), news.await(), rates.await())
}

fun main() = runBlocking {
    val job = launch {
        try {
            val elapsed = measureTimeMillis {
                val dashboard = loadDashboard()
                println(dashboard)
            }
            println("time: $elapsed ms")
        } catch (e: CancellationException) {
            println("Dashboard cancelled")
            throw e
        }
    }

//    delay(200)
//    job.cancelAndJoin()
    job.join()
}