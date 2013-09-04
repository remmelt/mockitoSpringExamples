package com.remmelt.example.repository;

import com.remmelt.example.model.Person;

public interface PersonRepository {
	Person getPersonBy(Long id) ;

	void savePerson(Person person);
}
