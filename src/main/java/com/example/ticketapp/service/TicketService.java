package com.example.ticketapp.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.ticketapp.repository.TicketRepository;
import com.example.ticketapp.exception.InvalidRequestException;
import com.example.ticketapp.model.Request;
import com.example.ticketapp.model.Ticket;
import com.example.ticketapp.model.TimeRequest;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Ticket> allTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> saveTickets(List<Ticket> tickets) {
        return ticketRepository.saveAll(tickets);
    }

    public List<Ticket> getMatchingTickets(Request request) throws InvalidRequestException {

        String searchField = request.getField();
        String searchedText = request.getSearchedText();

        if (searchField == "title" || searchField == "content" || searchField == "email") {
            System.out.println(searchField);
            Query query = new Query();
            query.addCriteria(Criteria.where(searchField).regex(".*" + searchedText + ".*"));
            return mongoTemplate.find(query, Ticket.class);
        } else {
            throw new InvalidRequestException("Not a valid request");
        }
    }

    public List<Ticket> getTicketsByTime(TimeRequest timeRequest) throws InvalidRequestException {

        Long to = timeRequest.getTo();
        Long from = timeRequest.getFrom();
        if ((to != null) && (from != null)) {
            return ticketRepository.findTicketsFromTo(from, to);
        } else if (to != null) {
            return ticketRepository.findTicketsTo(to);
        } else if (from != null) {
            return ticketRepository.findTicketsFrom(from);
        } else {
            throw new InvalidRequestException("Not a valid request");
        }
        
    }
}
