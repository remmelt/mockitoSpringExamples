package com.remmelt.example.repository.impl;


import org.junit.Assert;
import org.junit.Test;

import com.remmelt.example.model.Person;

/**
 * Exercise 1
 * Implement the test in this class using Mockito.
 */
public class PersonRepositoryImplTest {

	private PersonRepositoryImpl personRepository;

	@Test
	public void testThatWhenSavePersonIsCalledItCallsDatabaseAccessSavePerson() {
		personRepository.savePerson(new Person(1, "name", "email"));

		Assert.fail();
	}

}
