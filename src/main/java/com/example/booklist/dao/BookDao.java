package com.example.booklist.dao;

import com.example.booklist.model.Book;

import java.util.List;

public interface BookDao {

    int insertBook(List<Book> books);
    List<Book> selectAllBooks();
    Book selectBookById(int id);
    int deleteBookById(int id);
    int updateBook(Book book);

}
