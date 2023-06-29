package com.dev.jdbc.starter;

import com.dev.jdbc.starter.util.ConnectionManager;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRunner {

    public static void main(String[] args) throws SQLException {
        Class<Driver> driverClass = Driver.class;
        String sql = """
                /*CREATE TABLE IF NOT EXISTS info (
                    id SERIAL PRIMARY KEY ,
                    data TEXT NOT NULL 
                );*/
                /*INSERT INTO info (data)
                VALUES 
                ('Test1'),
                ('Test2'),
                ('Test3'),
                ('Test4');*/
                UPDATE info
                SET data = 'TestTest'
                WHERE id = 5
                RETURNING *
                """;
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {
            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());
            var executeResult = statement.execute(sql);
            //var executeResult = statement.executeUpdate(sql);
            System.out.println(executeResult);
            System.out.println(statement.getUpdateCount());
        }
    }
}
