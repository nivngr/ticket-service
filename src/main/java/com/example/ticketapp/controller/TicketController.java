package com.example.ticketapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketapp.exception.InvalidRequestException;
import com.example.ticketapp.model.Request;
import com.example.ticketapp.model.Ticket;
import com.example.ticketapp.model.TimeRequest;
import com.example.ticketapp.service.TicketService;

@RestController
@RequestMapping("/")
public class TicketController {

    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<List<Ticket>>(ticketService.allTickets(), HttpStatus.OK);
    }

    @GetMapping("/tickets/match")
    public ResponseEntity<List<Ticket>> getMatchingTickets(@RequestBody Request request) throws InvalidRequestException {
        return new ResponseEntity<List<Ticket>>(ticketService.getMatchingTickets(request), HttpStatus.OK);
    }

    @GetMapping("/tickets/by-time")
    public ResponseEntity<List<Ticket>> getTicketsByTime(@RequestBody TimeRequest timeRequest) throws InvalidRequestException {
        return new ResponseEntity<List<Ticket>>(ticketService.getTicketsByTime(timeRequest), HttpStatus.OK);
    }
}
