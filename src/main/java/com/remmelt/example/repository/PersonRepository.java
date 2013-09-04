package com.remmelt.example.repository;

import com.remmelt.example.exception.EntityNotFoundException;
import com.remmelt.example.model.Person;

public interface PersonRepository {
	Person getPersonBy(int id) throws EntityNotFoundException;

	void savePerson(Person person);
}
