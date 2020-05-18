
1. Compare output **MainAppSynchronizedBlock** and **MainAppSynchronizedMethod**, we will see.

    # The common:
    
    Value of **count1** and **count2** are equal, because they is increase in synchronized mode.
  
  
    # The difference.

    MainAppSynchronizedMethod: method **add1** is blocked until it finish, 
      the method **add2** is called later. Thread 2 can NOT call add2 method,
      when method **add1** is blocked by **Synchronized Method**
    
    MainAppSynchronizedBlock: method **add1** and **add2** are called concurrently. 
      It is faster than **Synchronized Method**
