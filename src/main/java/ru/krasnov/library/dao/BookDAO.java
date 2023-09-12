package ru.krasnov.library.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.krasnov.library.models.Book;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
	return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Optional<Book> show(String name) {
	return jdbcTemplate.query("SELECT * FROM Book WHERE book_name=?", new Object[] { name }, new BookMapper())
		.stream().findAny();
    }

    public Book show(int book_id) {
	return (Book) jdbcTemplate
		.query("SELECT * FROM Book WHERE book_id=?", new Object[] { book_id }, new BookMapper()).stream()
		.findAny().orElse(null);
    }

    public void save(Book book) {
	jdbcTemplate.update("INSERT INTO Book (book_name, author, year) VALUES(?, ?, ?)", book.getName(),
		book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook) {
	jdbcTemplate.update("UPDATE Book SET book_name=?, author=?, year=? WHERE book_id=?", updatedBook.getName(),
		updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void delete(int book_id) {
	jdbcTemplate.update("DELETE FROM Book WHERE book_id=?", book_id);
    }
}
