package com.example.booklist.dao;

import com.example.booklist.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class BookDataAccessService implements BookDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int insertBook(List<Book> books) {
        books.forEach(book -> jdbcTemplate
                .update("INSERT INTO booklist (name,author,rating) VALUES (?, ?, ?)",
                        book.getName(),book.getAuthor(),book.getRating()

        ));

        return 1;
    }

    @Override
    public List<Book> selectAllBooks() {
        String sql = "SELECT id,name,author,rating FROM Booklist";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            int rating = resultSet.getInt("rating");
            return new Book(
                    id,
                    name,
                    author,
                    rating
            );
        });
    }

    @Override
    public Book selectBookById(int id) {
        String sql = "SELECT id,name,author,rating FROM Booklist WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) ->
                new Book(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("author"),
                        resultSet.getInt("rating")
                ));

    }

    @Override
    public int deleteBookById(int id) {
        String sql = "DELETE FROM Booklist WHERE id = ?";
        Object[] args = new Object[]{id};

        jdbcTemplate.update(sql,args);
        return 0;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "UPDATE booklist SET name = ?, author = ?, rating = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                book.getName(),book.getAuthor(),book.getRating(),book.getId());
    }
}
