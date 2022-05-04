package com.ljn.demo.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

// 使用默认生成模板引擎 Velocity
public class CodeGenerator {

    public static void main(String[] args) {
        // 1、创建代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 2、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir"); //得到项目路径  E:\Java\8project\generator
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("ljn");
        gc.setOpen(false); //生成后是否自动打开资源管理器
        gc.setBaseResultMap(true); // 在xml中生成一个ResultMap标签，指定表的字段名与对象的属性名之间的映射关系
        gc.setBaseColumnList(true); // 在xml中生成一个sql标签，用于保存所有字段名
        gc.setFileOverride(false); //重新生成时文件是否覆盖
        gc.setServiceName("%sService");    //去掉Service接口的首字母I
//        gc.setIdType(IdType.ID_WORKER); //生成主键的策略，雪花算法
        gc.setDateType(DateType.ONLY_DATE); //定义生成的实体类中日期类型，mysql是datetime，java是Date
//        gc.setSwagger2(true);//开启Swagger2模式

        mpg.setGlobalConfig(gc);

        // 3、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/seckill?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 4、包配置
        PackageConfig pc = new PackageConfig();
        // 包名：com.xxx.seckill
        pc.setParent("com.ljn");
        pc.setModuleName("seckill");  //模块名
        // 子包名：com.xxx.seckill.controller
        pc.setController("controller");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 5、策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 根据表进行逆向工程，多个表逗号隔开
        strategy.setInclude("t_goods", "t_order", "t_seckill_goods", "t_seckill_order");
        // 数据库表名映射到实体类名的策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 生成实体类名时去掉表名前缀，只有模块名和表名前缀一致时才起作用，否则直接指定表名前缀
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setTablePrefix("t_");
        // 数据库表字段名映射到实体类属性名的策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // @Data注解 @Accessors(chain = true) setter链式操作
        strategy.setEntityLombokModel(true); // lombok 模型
        // restful api风格控制器
        strategy.setRestControllerStyle(true);
        // url中驼峰转连字符
        strategy.setControllerMappingHyphenStyle(false);

        mpg.setStrategy(strategy);

        // 6、执行
        mpg.execute();
    }
}
