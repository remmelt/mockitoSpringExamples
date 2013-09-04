package com.remmelt.example.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.remmelt.example.exception.EntityNotFoundException;
import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;
import com.remmelt.example.service.EmailService;

import static org.fest.assertions.Assertions.assertThat;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest extends AbstractJUnit4SpringContextTests {
	@InjectMocks
	private PersonServiceImpl personServiceImpl;

	@Mock
	private PersonRepository personRepository; // <-- you can't use this mock in this exercise!

	@Mock
	private EmailService emailService;

	/**
	 * Sometimes you cannot get around mocking a method in the class under test.
	 * Note that this is usually, mostly, practically always a design error, and in
	 * legacy code you will sometimes have to deal with it.
	 * For example, a service you can't or don't want to break apart into smaller pieces where
	 * you want to unit test a single method but it calls another method in the same class
	 * that does something you don't want (send email/hit a disk/etc.)
	 */
	@Test
	public void testGetPersonsByReturnsSpecifiedPersonInList() throws EntityNotFoundException {
		int id = 1;
		Person person = new Person(id, "Bart Simpson", "bart@example.com");

		// In this example it is of course possible to mock the personRepository like this:
//		when(personRepository.getPersonBy(eq(id))).thenReturn(person);
		// This is not allowed in this exercise!

		// Try and mock the personServiceImpl.getPersonBy(int) method itself:

		// code goes here

		List<Person> persons = personServiceImpl.getPersonsBy(id);

		assertThat(persons).hasSize(1);
		assertThat(persons.get(0)).isEqualTo(person);
	}
}
