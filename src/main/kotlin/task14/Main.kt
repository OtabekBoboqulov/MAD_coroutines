package task14

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch(CoroutineName("A")) {
        repeat(3) {
            println("A $it")
            delay(300)
        }
    }

    launch(CoroutineName("B")) {
        repeat(3) {
            println("B $it")
            delay(500)
        }
    }

    println("done")
}