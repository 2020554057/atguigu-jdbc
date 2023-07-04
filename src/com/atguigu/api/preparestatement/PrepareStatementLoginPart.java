package com.atguigu.api.preparestatement;

import java.sql.*;
import java.util.Scanner;

/**
 * ClassName:PrepareStatementLoginPart
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/3 19:41
 * @Version 1.0
 */
public class PrepareStatementLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //键盘输入账户密码，判断是否登录成功
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu","root","123456");
        //获取preparestatement对象
        String sql = "select * from t_user where account = ? and PASSWORD = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        //执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        //处理返回结果
        if (resultSet.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
        //释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
