package task12

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
fun main() {
    println("Available processors: ${Runtime.getRuntime().availableProcessors()}")

    val delayTime = measureTimeMillis {
        runBlocking {
            repeat(4) { launch { delay(500) } }
        }
    }

    val blockingTime = measureTimeMillis {
        runBlocking {
            repeat(4) { launch { Thread.sleep(500) } }
        }
    }

    val defaultTime = measureTimeMillis {
        runBlocking {
            repeat(4) { launch(Dispatchers.Default) { Thread.sleep(500) } }
        }
    }

    val singleThread = newSingleThreadContext("one")
    val singleTime = try {
        measureTimeMillis {
            runBlocking {
                repeat(4) { launch(singleThread) { Thread.sleep(500) } }
            }
        }
    } finally {
        singleThread.close()
    }

    println("Setup | Time (ms)")
    println("runBlocking + delay | $delayTime")
    println("runBlocking + Thread.sleep | $blockingTime")
    println("Dispatchers.Default + Thread.sleep | $defaultTime")
    println("Single thread + Thread.sleep | $singleTime")
}