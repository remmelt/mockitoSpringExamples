package com.remmelt.example.repository.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;

@Repository
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {
	@Override
	public Person getPersonBy(int id) {
		log.info("{}.getPersonBy({})", "PersonRepositoryImpl", id);
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
		// Get a Person entity from the database
	}

	@Override
	public void savePerson(Person person) {
		log.info("{}.savePerson({})", "PersonServiceImpl", person);
		// Store the person entity in the database
	}
}
