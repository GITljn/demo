# 注意
1. ReentrantLock不能在用wait和notify
2. 唤醒时要唤醒全部生产者和消费者
3. 释放锁之后要等一会，避免再次获得锁