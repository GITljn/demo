# CountDownLatch和CyclicBarrier的区别
1. CountDownLatch是-1，CyclicBarrier是+1
2. CountDownLatch阻塞的是“主线程”，CyclicBarrier阻塞的是“子线程”

# Semaphore
1. 如果信号量设为1，则变为互斥锁
