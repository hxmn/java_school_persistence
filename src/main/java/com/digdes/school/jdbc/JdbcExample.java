package com.digdes.school.jdbc;

import java.sql.*;

/**
 * @author Ilya Ashikhmin (ashikhmin.ilya@gmail.com)
 */
public class JdbcExample {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");

        Connection connection = DriverManager.getConnection(
                "jdbc:hsqldb:file:./database/weather_db;shutdown=true;hsqldb.write_delay=false",
                "sa", "");

        PreparedStatement ps  = connection.prepareStatement("select * from forecast where id > ?");
        ps.setInt(1, 4);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt("id"));
        }
        rs.close();
        ps.close();
        connection.close();
    }
}
