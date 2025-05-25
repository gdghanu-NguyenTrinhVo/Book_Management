package com.example.Book_Management.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Book {
    private long id;
    private String title;
    private String author;
    private double price;
    private LocalDate publishedDate;
}
