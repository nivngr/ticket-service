package com.example.ticketapp.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    
    @Id
    private String id;
    private String title;
    private String content;
    private String userEmail;
    private Long creationTime;
    private List<String> labels;
}
