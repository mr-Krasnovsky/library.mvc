package ru.krasnov.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.krasnov.library.dao.PersonDAO;
import ru.krasnov.library.models.Person;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
	this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
	return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
	Person person = (Person) o;

	if (personDAO.show(person.getName()).isPresent()) {
	    // поле, код ошибки, сообщение ошибки
	    errors.rejectValue("name", "", "This name is already in use");
	}
    }
}
