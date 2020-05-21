
## Semaphore

1. Take a look to example about semaphore in java 
[link](https://github.com/colenhuttran/java-concurrent/blob/master/semaphore-example/src/com/SamephorExample.java).


2. **semaphore.acquire();** require a permit to run, if YES, the thread could access shared resource, if NO, it have to wait 
util there is a permision.

3. **semaphore.release();** release a permit, so that other thread could get permit to do task.

4. Permit look like a count number
    
    when **semaphore.acquire()** is called, permit count is descreased.
    
    when **semaphore.release()** is called, permit count is inscreased.
    
    The thread have to wait, the permit count equal rezo.


