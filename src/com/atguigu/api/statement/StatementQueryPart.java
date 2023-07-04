package com.atguigu.api.statement;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * ClassName:StatementQueryPart
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/3 15:52
 * @Version 1.0
 */
public class StatementQueryPart {
    public static void main(String[] args) throws SQLException {
        //1、注册驱动
        DriverManager.registerDriver(new Driver());
        //2、获取连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        //3、创建发送sql语句的对象
        Statement statement = connection.createStatement();
        //4、发送sql语句,并返回结果
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);
        //5、结果解析
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String PASSWORD = resultSet.getString("PASSWORD");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"-"+account+"-"+PASSWORD+"-"+nickname);
        }
        //6、资源关闭
        resultSet.close();
        statement.close();
        connection.close();
    }
}
