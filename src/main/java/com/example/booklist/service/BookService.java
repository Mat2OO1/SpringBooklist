package com.example.booklist.service;

import com.example.booklist.dao.BookDao;
import com.example.booklist.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(@Qualifier("postgres") BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public int addBook(List<Book> books) {
        return bookDao.insertBook(books);
    }
    public List<Book> getAllBooks(){
        return bookDao.selectAllBooks();
    }

    public Book getBookById(int id){
        return bookDao.selectBookById(id);
    }

    public int deleteBook(int id){
        return bookDao.deleteBookById(id);
    }

    public int updateBook(Book book){
        return bookDao.updateBook(book);
    }



}
