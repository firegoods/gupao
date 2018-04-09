package com.lss.mybatis.v1;

import com.lss.mybatis.beans.Test;

import java.sql.*;

/**
 * Created by liushaoshuai on 2018/4/9.
 */
public class LssSimpleExecutor implements LssExecutor {

    @Override
    public <T> T query(String statement, String params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Test test = null;
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "123456");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", "tx_landsale", "tx_landsale");
            String sql = String.format(statement,Integer.parseInt(params));
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                test = new Test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T)test;
    }
}
