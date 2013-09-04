package com.remmelt.example.service.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.verification.SmartNullPointerException;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.remmelt.example.exception.EntityNotFoundException;
import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest extends AbstractJUnit4SpringContextTests {
	@InjectMocks
	private PersonServiceImpl personServiceImpl;

	/**
	 * To return smart nulls, you can do either this, which gets real tired, real quick,
	 * or you can extend the DefaultMockitoConfiguration class. You don't need both.
	 * See http://docs.mockito.googlecode.com/hg/org/mockito/Mockito.html#14
	 *
	 * @see org.mockito.configuration.MockitoConfiguration
	 */
	@Mock(answer = Answers.RETURNS_SMART_NULLS)
	private PersonRepository personRepository;

	@Test(expected = SmartNullPointerException.class)
	public void testGetPersonWithUnkownId() {
		int id = 1;

		Person personActual = personServiceImpl.getPersonBy(id);
		personActual.getEmailAddress();

		/**
		 * Note that the SmartNullPointerException is on line 40, not on 39!
		 * This can be very convenient when you are writing unit tests where not all your
		 * services/repositories are mocked or not completely mocked.
		 * Instead of "null pointer somewhere, good luck!" you now get a smart message.
		 */
	}

	@Test
	public void testGetPersonByCallsPersonRepositoryWithKnownId() throws Exception {
		int id = 1;
		Person personMock = new Person(id, "T. van der Test", "webmaster@example.com");

		// Mock the PersonRepository:
		Mockito.when(personRepository.getPersonBy(Mockito.eq(id))).thenReturn(personMock);
		// Or, more concisely:
		when(personRepository.getPersonBy(eq(id))).thenReturn(personMock);

		Person personActual = personServiceImpl.getPersonBy(id);

		// This obviously succeeds because we're just testing the Mockito framework here:
		assertThat(personActual).isEqualTo(personMock);
	}

	/**
	 * This test no longer works correctly since the returned values are of type SmartNull, and not null.
	 *
	 * @throws EntityNotFoundException
	 */
	@Test
	@Ignore
	public void testGetPersonByCallsPersonRepositoryReturnsNullWhenNotMocked() throws EntityNotFoundException {
		// Note that any calls that are not mocked will return null.

		Person person;

		person = personServiceImpl.getPersonBy(1);
		assertThat(person).isNull();

		person = personServiceImpl.getPersonBy(-9);
		assertThat(person).isNull();

		// Even when some calls or parameters are mocked:

		int mockedId = 12;
		when(personRepository.getPersonBy(eq(mockedId))).thenReturn(new Person());

		person = personServiceImpl.getPersonBy(mockedId);
		assertThat(person).isNotNull();

		person = personServiceImpl.getPersonBy(1);
		assertThat(person).isNull();
	}

	@Test
	public void testCorrectErrorhandlingWhenEntityNotFound() throws EntityNotFoundException {
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
