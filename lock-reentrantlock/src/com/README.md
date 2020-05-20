
# Reentrantlock is a way to replace synchronized block with flexibel using.





1. **reentrantLock.lock** and **reentrantLock.unlock** is a block to hold lock, it look like synchronized block

    **Very important**: *reentrantLock.unlock* must put into finanlly block to make sure it is called always.

2. **Condition** is created from a lock, and is used as wait() and notify()

    **condition.await()** is similar to **wait()**
    
    **condition.signal()** is similar to **notify()**
    
3. Do more some research from Internet to get more information about the look

    a. link from geeksforgeeks [link](https://www.geeksforgeeks.org/reentrant-lock-java/).
    
    b. link from journaldev [link](https://www.journaldev.com/2377/java-lock-example-reentrantlock).
