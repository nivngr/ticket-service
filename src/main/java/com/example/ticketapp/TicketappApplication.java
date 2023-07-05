package com.example.ticketapp;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

import com.example.ticketapp.model.Ticket;
import com.example.ticketapp.service.TicketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootApplication
public class TicketappApplication implements ApplicationRunner {

	@Autowired
    private TicketService ticketService;

	public static void main(String[] args) {
		SpringApplication.run(TicketappApplication.class, args);
	}

	public void run(ApplicationArguments args) {
		ObjectMapper objectMapper = new ObjectMapper();
		String userDirectory = System.getProperty("user.dir");
		File jsonFile = new File(userDirectory + "/data.json");
		try {
			List<Ticket> tickets = objectMapper.reader().forType(new TypeReference<List<Ticket>>() {}).readValue(jsonFile);
			ticketService.saveTickets(tickets);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
