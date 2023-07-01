package com.dev.jdbc.starter;

import com.dev.jdbc.starter.dao.TicketDao;
import com.dev.jdbc.starter.entity.Ticket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DaoRunner {

    public static void main(String[] args) {
        //saveTest();
        //var ticketDao = TicketDao.getInstance();
        //var deleteResult = ticketDao.delete(56L);
        //System.out.println(deleteResult);

        //updateTest();

        var tickets = TicketDao.getInstance().findAll();
        System.out.println();
    }

    private static void updateTest() {
        var ticketDao = TicketDao.getInstance();
        var maybeTicket = ticketDao.findById(2L);
        System.out.println(maybeTicket);

        maybeTicket.ifPresent(ticket -> {
            ticket.setCost(BigDecimal.valueOf(188.88));
            ticketDao.update(ticket);
        });
    }

    private static void saveTest() {
        var ticketDao = TicketDao.getInstance();
        var ticket = new Ticket();
        ticket.setPassengerNo("1234567");
        ticket.setPassengerName("Test");
        ticket.setFlightId(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);

        var savedTicked = ticketDao.save(ticket);
        System.out.println(savedTicked);
    }
}
