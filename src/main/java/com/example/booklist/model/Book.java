package com.example.booklist.model;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int id;
    private String name;
    private String author;
    private int rating;

}
