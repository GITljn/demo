# 介绍
1. 双重验证的情况下，第一次验证是为了提高效率，可有可无，如果没有近似与在方法上加锁，第二次验证才保证了对象不会重复创建
2. 在进入synchronized时，对象有可能已经创建了，因此需要第二次验证
3. 双重验证时必须要禁止指令重排