package task02

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch(CoroutineName("worker-1")) {
        println(Thread.currentThread().name);
    }
    println("done")
}
