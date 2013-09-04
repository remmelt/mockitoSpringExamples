package com.remmelt.example.repository.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.external.database.DatabaseAccess;
import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;

@Repository
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {
	@Autowired
	private DatabaseAccess databaseAccess;

	@Override
	public Person getPersonBy(int id) {
		log.info("{}.getPersonBy({})", "PersonRepositoryImpl", id);
		return databaseAccess.getPersonBy(id);
	}

	@Override
	public void savePerson(Person person) {
		log.info("{}.savePerson({})", "PersonRepositoryImpl", person);
		databaseAccess.savePerson(person);
	}
}
