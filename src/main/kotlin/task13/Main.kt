package task13

import kotlinx.coroutines.*

class FakeViewModel {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    fun load() {
        scope.launch {
            repeat(3) { step ->
                delay(200)
                println("step ${step + 1}")
            }
        }
    }

    fun onCleared() {
        println("onCleared")
        scope.cancel()
    }
}

fun main() = runBlocking {
    val viewModel = FakeViewModel()
    viewModel.load()
    delay(300)
    viewModel.onCleared()
    delay(1_000)
}