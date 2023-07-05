package com.example.ticketapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.ticketapp.model.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, String> {
    
    @Query("{creationTime : {$gte : ?0} }")
	public List<Ticket> findTicketsFrom(Long from);

    @Query("{creationTime : {$lt : ?0} }")
	public List<Ticket> findTicketsTo(Long from);

    @Query("{creationTime : {$gte : ?0}, {$lt : ?1} }")
	public List<Ticket> findTicketsFromTo(Long from, Long to);
}
