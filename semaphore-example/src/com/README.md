
## Semaphore

1. Take a look to example about semaphore in java 
[link](https://github.com/colenhuttran/java-concurrent/blob/master/semaphore-example/src/com/SamephorExample.java).


2. **semaphore.acquire();** require a permit to run, if YES, the thread could access shared resource, if NO, it have to wait 
util there is a permision.

3. **semaphore.release();** release a permit, so that other thread could get permit to do task.

4. Permit look like a count number
    
    when **semaphore.acquire()** is called, "permit num" is descreased.
    
    when **semaphore.release()** is called, "permit num" is inscreased.
    
    Other threads have to wait if the "permit num" equal rezo.
    
    "permit num" is given when a semaphore is created.


5. Using **semaphore**, you can allow how many thread access to shared resource at the same time.

    Mutex is a **semaphore** with "permit num" is 1, it means only one thread accessing shared resource at a time.
