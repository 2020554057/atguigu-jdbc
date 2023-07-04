package com.atguigu.api.preparestatement;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:PrepareStatementCURD
 * Package:IntelliJ IDEA
 * Description:
 *
 * @Author 吴苏杰
 * @Create 2023/7/3 19:59
 * @Version 1.0
 */
public class PrepareStatementCURD {

    //插入
    @Test
    public void testInsert() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        String sql = "insert into t_user (account,password,nickname) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"test");
        preparedStatement.setString(2,"test");
        preparedStatement.setString(3,"test");
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }
        preparedStatement.close();
        connection.close();

    }

    //更新
    @Test
    public void testUpdate() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        String sql = "update t_user set nickname=? where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"二狗子");
        preparedStatement.setInt(2,2);
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("修改成功！");
        }else {
            System.out.println("修改失败！");
        }
        preparedStatement.close();
        connection.close();

    }

    //删除
    @Test
    public void testDelete() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        String sql = "delete from t_user where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 4);
        int i = preparedStatement.executeUpdate();
        if (i>0){
            System.out.println("删除成功！");
        }else {
            System.out.println("删除失败！");
        }
        preparedStatement.close();
        connection.close();

    }

    //查询
    @Test
    public void testQuery() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu", "root", "123456");
        String sql = "select account,password,nickname from t_user";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Map> list = new ArrayList<Map>();
        while (resultSet.next()) {
            Map map = new HashMap();
            map.put("account", resultSet.getString("account"));
            map.put("password", resultSet.getString("password"));
            map.put("nickname", resultSet.getString("nickname"));
            list.add(map);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        System.out.println(list);
    }
}
