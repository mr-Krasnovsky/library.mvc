package ru.krasnov.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name book should be between 2 and 100 characters")
    private String name;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 2, max = 100, message = "Author name should be between 2 and 100 characters")
    private String author;

    @Min(value = 1500, message = "Year should be greater than 1500")
    private int year;

    private int person_id;

    public Book() {

    }

    public Book(int id, String name, String author, int year, int person_id) {
	this.id = id;
	this.name = name;
	this.author = author;
	this.year = year;
	this.person_id = person_id;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public int getYear() {
	return year;
    }

    public void setYear(int year) {
	this.year = year;
    }

    public int getPerson_id() {
	return person_id;
    }

    public void setPerson_id(int person_id) {
	this.person_id = person_id;
    }

}
