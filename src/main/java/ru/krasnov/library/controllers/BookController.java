package ru.krasnov.library.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.krasnov.library.dao.BookDAO;
import ru.krasnov.library.models.Book;
import ru.krasnov.library.util.BookValidator;

@Controller
@RequestMapping(value = "/book", produces = "text/html; charset=utf-8")
public class BookController {

    private final BookDAO bookDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BookController(BookDAO bookDAO, BookValidator bookValidator) {
	this.bookDAO = bookDAO;
	this.bookValidator = bookValidator;
    }

    @GetMapping()
    public String index(Model model) {
	model.addAttribute("book", bookDAO.index());
	return "book/index";
    }

    @GetMapping("/{book_id}")
    public String show(@PathVariable("book_id") int book_id, Model model) {
	model.addAttribute("book", bookDAO.show(book_id));
	return "book/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
	return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
	bookValidator.validate(book, bindingResult);
	if (bindingResult.hasErrors())
	    return "book/new";
	bookDAO.save(book);
	return "redirect:/book";
    }

    @GetMapping("/{book_id}/edit")
    public String edit(Model model, @PathVariable("book_id") int book_id) {
	model.addAttribute("book", bookDAO.show(book_id));
	return "book/edit";
    }

    @PatchMapping("/{book_id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
	    @PathVariable("book_id") int book_id) {
	// bookValidator.validate(book, bindingResult);
	if (bindingResult.hasErrors())
	    return "book/edit";
	bookDAO.update(book_id, book);
	return "redirect:/book";
    }

    @DeleteMapping("/{book_id}")
    public String delete(@PathVariable("book_id") int book_id) {
	bookDAO.delete(book_id);
	return "redirect:/book";
    }
}
