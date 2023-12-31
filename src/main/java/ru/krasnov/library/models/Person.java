package ru.krasnov.library.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 255, message = "Name should be between 2 and 255 characters")
    private String name;

    @Min(value = 1900, message = "Age should be greater than 1900")
    private int age;

    public Person() {

    }

    public Person(int id, String name, int age) {
	this.id = id;
	this.name = name;
	this.age = age;
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

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }
}