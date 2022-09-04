package com.example.booklist.api;

import com.example.booklist.model.Book;
import com.example.booklist.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/book")
@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public int addBook(@RequestBody List<Book> books){
        return bookService.addBook(books);
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping(path="{id}")
    public Book getBookById(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }

    @DeleteMapping(path="{id}")
    public void deleteBookById(@PathVariable("id") int id){
        bookService.deleteBook(id);
    }

    @PutMapping(path="{id}")
    public int updateBook(@PathVariable("id") int id,@RequestBody Book bookToUpdate){
        Book book = bookService.getBookById(id);

        if (book != null){
            book.setName(bookToUpdate.getName());
            book.setAuthor(bookToUpdate.getAuthor());
            book.setRating(bookToUpdate.getRating());

            bookService.updateBook(book);

            return 1;
        }
        else{
            return 0;
        }
    }

}
