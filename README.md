# Mobile application development - Coroutines

## Part A

### Q1

newSingleThreadContext keeps mutable state on a single thread, so after delay thread won't change; while in Unconfined
after running on caller thread once delayed coroutine will run on whichever thread resumed it. newSingleThreadContext
won't change thread at all.

    @OptIn(DelicateCoroutinesApi::class)
    fun main() = runBlocking {
	    val single = newSingleThreadContext("MyThread")
	    launch(single) {
	        println("single       before: ${Thread.currentThread().name}")
	        delay(100)
	        println("single       after : ${Thread.currentThread().name}")
	    }.join()
	    launch(Dispatchers.Unconfined) {
	        println("unconfined   before: ${Thread.currentThread().name}")
	        delay(100)
	        println("unconfined   after : ${Thread.currentThread().name}")
	    }.join()
	    single.close()
    }

Output:

    single       before: MyThread
    single       after : MyThread
    unconfined   before: main
    unconfined   after : kotlinx.coroutines.DefaultExecutor

### Q2

async returns the result which can be fetched using await, while launch returns the job. This job can be executed with
join.

### Q3

A coroutine is a suspendable computation that lets you write concurrent code in a clear, sequential style.
delay() just suspends the coroutine, other coroutines continue; while Thread.sleep() blocks the thread and another one
waits it.

### Q4

suspend says to kotlin that this function can be suspended, but it doesn't create a coroutine itself. To call the
suspend function caller also should have suspend or it should be called in coroutine block.
Suspend functions will receive hidden parameter Continuation to tell it where to continue.

### Q5

On concurrency - one thread is used and processes take turns; while on parallelism - each task runs at the same instant
and it needs multiple cores.

### Q6

When inside one coroutine another coroutines created it will be structured concurrency. It will create tree with parent
and child coroutines with linked lifecycles, which means when parent finishes, fails or cancelled it will effect all its
children and grandchildren.

### Q7

launch - returns job and used to start or continue the job when result is not needed.
async - returns Deferred&lt;T&gt; and also used to start and continue the job, but when result is needed. Result can be
fetched with await().
runBlocking - returns T and blocks the current thread until it is done. If used on Android UI can cause freezes because
it blocks the thread.
withContext - returns T and switches the context, suspends until done.
coroutineScope - returns T and suspends until all children are done.

### Q8

CoroutineScope defines the coroutine’s lifecycle (when). Every new coroutine must start in a scope, and it becomes a
child of that scope.
CoroutineContext is the scope plus the dispatcher (where, i.e. which threads are used). Scope + Dispatcher =
CoroutineContext.
No dispatcher specified: the coroutine inherits its parent’s dispatcher. If the context has none, builders use
Dispatchers.Default.

### Q9

Default - CPU-intensive work, i.e: Sorting or parsing data, you should avoid blocking calls
IO - Blocking input/output, i.e: Database, file or network call, you should avoid heavy CPU loops
Main - UI updates only, i.e: Updating the screen state, you should avoid any heavy work

### Q10

viewModelScope: a scope tied to the ViewModel’s lifecycle.
.launch { ... }: starts a new coroutine without blocking. It runs on the Main thread, since viewModelScope uses the Main
dispatcher.
withContext(Dispatchers.IO) { ... }: switches to a worker thread, which keeps the blocking call (database or network)
off the UI thread. It returns the result once done, and the coroutine then goes back to Main.
When the ViewModel is cleared: viewModelScope is cancelled, so the coroutine and all its children are cancelled too.

## Part B

### Task 1

If instead of delay Thread.sleep() is used it will freeze the thread until coroutine A is done, so it will first finish
then it will move to the B one.

### Task 2

Error message: Suspend function 'suspend fun fetchName(): String?' can only be called from a coroutine or another
suspend function.

### Task 3

If we call await for each async right after the block it will wait until the function's delay in async finishes as we
are asking for the result. Only when result is fetched it will move to the next async, so total time will be more than
200 milliseconds.
