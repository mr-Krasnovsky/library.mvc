package ru.krasnov.library.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ru.krasnov.library.models.Book;

public class BookMapper implements RowMapper {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
	Book book = new Book();

	book.setId(rs.getInt("book_id"));
	book.setName(rs.getString("book_name"));
	book.setAuthor(rs.getString("author"));
	book.setYear(rs.getInt("year"));
	book.setPerson_id(rs.getInt("person_id"));
	return book;
    }
}
