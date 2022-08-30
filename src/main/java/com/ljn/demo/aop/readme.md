# 切面类
1. AOP最重要的是切面类，使用@Aspect标识
2. 同时通过@Component将切面类对象讲给spring容器管理
# 切面类构成
1. 切点（功能增强的位置，可以通过切点表达式或者注解指定）@PointCut
2. 通知（功能增强的代码）@Around（功能最强）、@Before、@After