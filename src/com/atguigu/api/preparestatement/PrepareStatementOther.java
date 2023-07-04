package com.atguigu.api.preparestatement;

import java.sql.*;

/**
 * ClassName:PrepareStatementOther
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/3 23:20
 * @Version 1.0
 */
public class PrepareStatementOther {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "wsj");
        preparedStatement.setString(2, "202055");
        preparedStatement.setString(3, "吴苏杰");
        int i = preparedStatement.executeUpdate();
        if (i>0){
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            int index = resultSet.getInt(1);
            System.out.println("插入的数据的index: " + index);
        }else{
            System.out.println("插入失败");
        }
        preparedStatement.close();
        connection.close();
    }
}
