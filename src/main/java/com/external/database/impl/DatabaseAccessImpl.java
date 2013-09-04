package com.external.database.impl;

import lombok.extern.slf4j.Slf4j;

import com.external.database.DatabaseAccess;
import com.remmelt.example.model.Person;

@Slf4j
public class DatabaseAccessImpl implements DatabaseAccess {
	@Override
	public Person getPersonBy(int id) {
		log.info("{}.getPersonBy({})", "DatabaseAccessImpl", id);
		switch (id) {
			case 1:
				return new Person(1, "Remmelt van der Pit", "remmelt@info.nl");
			case 2:
				return new Person(2, "Marten Hoekstra", "martenh@info.nl");
			case 3:
				return new Person(3, "Daan Debie", "daan@info.nl");
			default:
				throw new IllegalArgumentException("Person with id {} not found in \"database\"");
		}
	}

	@Override
	public void savePerson(Person person) {
		log.info("{}.savePerson({})", "DatabaseAccessImpl", person);
		// Store the person entity in the database
	}
}
