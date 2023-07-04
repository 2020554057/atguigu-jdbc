package com.atguigu.api.preparestatement;

import java.sql.*;

/**
 * ClassName:PrepareStatementBatch
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/3 23:37
 * @Version 1.0
 */
public class PrepareStatementBatch {
    public static void main(String[] args) throws Exception {
        //批量插入数据
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?rewriteBatchedStatements=true", "root", "123456");
        String sql = "insert into t_user(account,password,nickname) values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "wsj"+i);
            preparedStatement.setString(2, "202055"+i);
            preparedStatement.setString(3, "吴苏杰"+i);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println("运行时间："+(end-start));

        preparedStatement.close();
        connection.close();
    }
}
