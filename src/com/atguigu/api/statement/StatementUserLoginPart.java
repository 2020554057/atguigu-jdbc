package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName:StatementUserLoginPart
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/3 19:12
 * @Version 1.0
 */
public class StatementUserLoginPart {
    public static void main(String[] args) throws SQLException {
//        键盘输入账户密码，判断是否登录成功
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

//        jdbc操作
//        1.加载驱动
        DriverManager.registerDriver(new Driver());
//        2.创建连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
//        3.获取连接声明对象
        Statement statement = connection.createStatement();
//        4.执行sql语句
        String sql = "select * from t_user where account = '"+username+"' and PASSWORD = '"+password+"'";
        ResultSet resultSet = statement.executeQuery(sql);
//        5.判断是否查询到
        if (resultSet.next()) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
//        6.关闭资源
        resultSet.close();
        statement.close();
        connection.close();
    }

}
