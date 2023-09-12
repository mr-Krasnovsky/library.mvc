package ru.krasnov.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.krasnov.library.dao.BookDAO;
import ru.krasnov.library.models.Book;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
	this.bookDAO = bookDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
	return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
	Book book = (Book) o;

	if (bookDAO.show(book.getName()).isPresent()) {
	    // поле, код ошибки, сообщение ошибки
	    errors.rejectValue("name", "", "This name is already in use");
	}
    }
}
