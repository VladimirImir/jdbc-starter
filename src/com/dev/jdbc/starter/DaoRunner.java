package com.dev.jdbc.starter;

import com.dev.jdbc.starter.dao.TicketDao;
import com.dev.jdbc.starter.dto.TicketFilter;
import com.dev.jdbc.starter.entity.Ticket;

import java.math.BigDecimal;
import java.util.Optional;

public class DaoRunner {

    public static void main(String[] args) {
        //saveTest();
        //var ticketDao = TicketDao.getInstance();
        //var deleteResult = ticketDao.delete(56L);
        //System.out.println(deleteResult);

        //updateTest();
        //var tickets = TicketDao.getInstance().findAll();
        //filterTest();

        var ticket = TicketDao.getInstance().findById(5L);
        System.out.println(ticket);
    }

    private static void filterTest() {
        var ticketFilter = new TicketFilter(3, 0, "Евгений Кудрявцев", "A1");
        var tickets = TicketDao.getInstance().findAll(ticketFilter);
        System.out.println(tickets);
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
        //ticket.setFlight(3L);
        ticket.setSeatNo("B3");
        ticket.setCost(BigDecimal.TEN);

        var savedTicked = ticketDao.save(ticket);
        System.out.println(savedTicked);
    }
}
