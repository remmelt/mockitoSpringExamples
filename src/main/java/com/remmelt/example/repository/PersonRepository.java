package com.remmelt.example.repository;

import com.remmelt.example.model.Person;

public interface PersonRepository {
	Person getPersonBy(int id) ;

	void savePerson(Person person);
}
