## wait() and notify()

1. **wait()** and **notify()** have to be inside the synchronized block

2. call **wait()** to wait until there is another thread call **notify()** the same "lock" to continue running.

    If there is no any thread call **notify()**, the thread which calls **wait()** will wait forever.
    
    We have **wait(long miliseconds)** method will wait until **notify()** is called or meet the time condition. 
    
3. In this example, try to comment out **lock.wait()** or **lock.notify()**, then see the output result to know
how to the methods work.
