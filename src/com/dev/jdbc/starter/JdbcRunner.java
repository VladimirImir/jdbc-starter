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
                CREATE TABLE IF NOT EXISTS info (
                    id SERIAL PRIMARY KEY ,
                    data TEXT NOT NULL 
                );
                """;
        try (var connection = ConnectionManager.open();
             var statement = connection.createStatement()) {
            System.out.println(connection.getSchema());
            System.out.println(connection.getTransactionIsolation());
            var executeResult = statement.execute(sql);
            System.out.println(executeResult);
        }
    }
}
