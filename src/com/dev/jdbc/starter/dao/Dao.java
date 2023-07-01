package com.dev.jdbc.starter.dao;

import com.dev.jdbc.starter.dto.TicketFilter;
import com.dev.jdbc.starter.entity.Ticket;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    boolean delete(K id);

    E save(E ticket);

    void update(E ticket);

    Optional<E> findById(K id);

    List<E> findAll();

}
