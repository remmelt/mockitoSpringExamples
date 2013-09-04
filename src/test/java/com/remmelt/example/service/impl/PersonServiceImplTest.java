package com.remmelt.example.service.impl;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.remmelt.example.exception.DatabaseDiskFullException;
import com.remmelt.example.exception.EntityNotFoundException;
import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest extends AbstractJUnit4SpringContextTests {
	@InjectMocks
	private PersonServiceImpl personServiceImpl;

	@Mock
	private PersonRepository personRepository;

	/**
	 * Sometimes the database disk is full so it will throw an exception.
	 * Since this application is not mission critical, we just log and ignore the error.
	 * <p/>
	 * Exercise 2
	 * Make personRepository.savePerson throw a DatabaseDiskFullException
	 *
	 * @see com.remmelt.example.exception.DatabaseDiskFullException
	 */
	@Test // Not allowed to expect anything!
	public void testSavePersonHandlesDatabaseErrorCorrectly() {
		// do stuff here

		personServiceImpl.savePerson(new Person(2, "name", "email"));

		Assert.fail(); // then remove the fail()
	}

	@Test
	public void testCorrectErrorHandlingWhenEntityNotFound() throws EntityNotFoundException {
		int idNotFoundInDb = 1;
		when(personRepository.getPersonBy(eq(idNotFoundInDb))).thenThrow(new EntityNotFoundException("Entity not found! (MOCK)"));

		Person person = personServiceImpl.getPersonBy(idNotFoundInDb);
		assertThat(person).isNull();
	}

	@Test
	@Ignore
	public void testSendEmailToPersonBy() throws Exception {
		/**
		 * How do we do this? There are multiple options:
		 * - external logging smtp server (how to automatically check output?)
		 * - TestApplicationConfig that wires a different bean instead of the regular EmailService
		 */

		personServiceImpl.sendEmailToPersonBy(1, "Hi!", "This is a test message.");
	}
}
