package com.remmelt.example.repository.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.external.database.DatabaseAccess;
import com.remmelt.example.exception.DatabaseDiskFullException;
import com.remmelt.example.exception.EntityNotFoundException;
import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;

@Repository
@Slf4j
public class PersonRepositoryImpl implements PersonRepository {
	@Autowired
	private DatabaseAccess databaseAccess;

	@Override
	public Person getPersonBy(int id) throws EntityNotFoundException {
		log.info("{}.getPersonBy({})", "PersonRepositoryImpl", id);
		try {
			return databaseAccess.getPersonBy(id);
		} catch (IllegalArgumentException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}

	@Override
	public void savePerson(Person person) {
		log.info("{}.savePerson({})", "PersonRepositoryImpl", person);

		if (person.getName().equals("Bassie")) {
			throw new DatabaseDiskFullException("db disk is full, aborting save.");
		}
		databaseAccess.savePerson(person);
	}
}
