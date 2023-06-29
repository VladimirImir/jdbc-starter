package com.dev.jdbc.starter;

import com.dev.jdbc.starter.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionRunner {

    public static void main(String[] args) throws SQLException {
        long flightId = 8;
        var deleteFlightSql = "DELETE FROM flight WHERE id = " + flightId;
        var deleteTicketsSql = "DELETE FROM ticket WHERE flight_id = " + flightId;
        Connection connection = null;
        Statement statement = null;
        //PreparedStatement deleteFlightStatement = null;
        //PreparedStatement deleteTicketsStatement = null;
        try {
            connection = ConnectionManager.open();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.addBatch(deleteTicketsSql);
            statement.addBatch(deleteFlightSql);

            var ints = statement.executeBatch();

            //deleteFlightStatement = connection.prepareStatement(deleteFlightSql);
            //deleteTicketsStatement = connection.prepareStatement(deleteTicketsSql);
            /**
             * connection.setAutoCommit(false); - это нужно делать в самом начале, до выполнения любых запросов!
             * Так как по дефолку он выставлен в - true.
             * Таким образом мы берем управление транзакциями на себя.
             */
            //connection.setAutoCommit(false);

            //deleteFlightStatement.setLong(1, flightId);
            //deleteTicketsStatement.setLong(1, flightId);

            //deleteTicketsStatement.executeUpdate();
            //var deleteTicketsResult = deleteTicketsStatement.executeUpdate();
            //if (true) {
            //    throw new RuntimeException("Ooops!");
            //}
            //deleteFlightStatement.executeUpdate();
            //var deleteFlightResult = deleteFlightStatement.executeUpdate();
            /**
             * Нельзя вызывать методы commit() and rollback(),
             * если у вас стоит AutoCommit в 'true' - будет исключение.
             */
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            /*if (deleteFlightStatement != null) {
                deleteFlightStatement.close();
            }
            if (deleteTicketsStatement != null) {
                deleteTicketsStatement.close();
            }*/
        }
    }
}
