package task05

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    launch {
        println("Inherited:")
        println(Thread.currentThread().name)
    }
    withContext(Dispatchers.Default) {
        println("Default:")
        println(Thread.currentThread().name)
    }
    withContext(Dispatchers.IO) {
        println("IO:")
        println(Thread.currentThread().name)
    }
    withContext(Dispatchers.Unconfined) {
        println("Unconfined:")
        println(Thread.currentThread().name)
    }
    val single = newSingleThreadContext("My Thread")
    withContext(single) {
        println("SingleThread:")
        println(Thread.currentThread().name)
    }
    single.close()
}