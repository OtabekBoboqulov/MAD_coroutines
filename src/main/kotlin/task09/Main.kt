package task09

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

suspend fun download(ms: Long): String {
    delay(ms.milliseconds)
    return "file-$ms"
}

fun main() = runBlocking {
    val times = listOf(100L, 200L, 300L, 400L, 500L)
    println(measureTimeMillis {
//        println(times.map {
//            download(it)
//        }) // returned 1533
//        println(times.map {
//            async {
//                download(it)
//            }
//        }.awaitAll()) // returned 546
        times.forEach {
            val res = async(start = CoroutineStart.LAZY) {
                download(it)
            }
            println(res.await())
        } // returned 1569
    })
}
