package com.atguigu.api.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ClassName:DruidUsePart
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/4 8:12
 * @Version 1.0
 */
public class DruidUsePart {

    //硬方式创建
    public void test01() throws Exception{
        //1、注册驱动
        DruidDataSource druidDataSource = new DruidDataSource();
        //2.设置驱动属性
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/atguigu");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("123456");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(5);
        druidDataSource.setMaxActive(10);
        //3、获取连接
        DruidPooledConnection connection = druidDataSource.getConnection();
        //4、sql操作

        //5、关闭资源
        connection.close();
    }


    //软方式创建
    public void test02() throws Exception{
        //1、读取外部配置文件 Properties
        Properties properties = new Properties();
        //scr下的文件，可以使用类加载器提供的方法加载io流文件
        InputStream ips = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);

        //2、使用druid连接池的工厂模式创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        //3、获取连接对象
        Connection connection = dataSource.getConnection();

        //4、关闭连接资源
        connection.close();

    }
}
