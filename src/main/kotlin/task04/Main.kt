package task04

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis
import kotlin.time.Duration.Companion.milliseconds

suspend fun loadName(): String {
    delay(100.milliseconds)
    return "Otabek"
}

suspend fun loadAge(): Int {
    delay(100.milliseconds)
    return 20
}

fun main() = runBlocking {
    var name: Deferred<String>
    var age: Deferred<Int>
    println(measureTimeMillis {
        name = async { loadName() }
        println(name.await())
        age = async { loadAge() }
//        println(name.await())
        println(age.await())
    })
}