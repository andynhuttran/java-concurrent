

1. Without **synchronized** keywork at method, a function will allow multi threads to execute at the same time.
This is not safe if there is a common resource is changed.

2. With **synchronized** keywork at method, a function will **NOT** allow multi threads to execute at the same time.
It mean, only one thread call the method at the time. This is safe but it is cost.

3. Take a look to both examples:

    MainAppWithoutSynchronized: create 2 thread to increase **count** variable, **count** is increased 10000 times each thread 
    with no synchronized. At the end, count is NOT equal 20000 
    
    MainAppWithSynchronized: do the same above, but **synchronized** keywork is used at increment method, 
    At the end, count is equal 20000 all the time.
