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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest extends AbstractJUnit4SpringContextTests {
	@InjectMocks
	private PersonServiceImpl personServiceImpl;

	@Mock
	private PersonRepository personRepository;

	@Mock
	private EmailService emailService;

	@Test
	public void testGetPersonsReturnsListOfPersons() throws EntityNotFoundException {
		Person person1 = new Person(1, "Bart Simpson", "bart@example.com");
		Person person2 = new Person(2, "Lisa Simpson", "lisa@example.com");
		Person person3 = new Person(3, "Maggie Simpson", "maggie@example.com");

		// You can chain thenReturns()
		when(personRepository.getPersonBy(anyInt())).thenReturn(person1).thenReturn(person2).thenReturn(person3);
		// Any subsequent calls will return the last thenReturn, i.e. person3
		// Or chain further: .thenAnswer().thenCallRealMethod().thenThrow()

		List<Person> persons = personServiceImpl.getPersonsBy(1, 2, 99);

		// These are aliases, note that you can pass varargs
		verifyNoMoreInteractions(emailService);
		verifyZeroInteractions(emailService);
		// ... and do the same as
		//verify(emailService.sendMail(anyString(),anyString(),anyString()), never());
		// but that does not work with void methods

		// Or you can count how many times a mocked method is called:
		verify(personRepository, times(3)).getPersonBy(anyInt());
		verify(personRepository, atMost(3)).getPersonBy(anyInt());
		verify(personRepository, atLeast(3)).getPersonBy(anyInt());
		verify(personRepository, atLeastOnce()).getPersonBy(anyInt());

		// These are identical:
		// verify(mock)...
		// verify(mock, times(1))...

		assertThat(persons).hasSize(3);
		assertThat(persons.get(0)).isEqualTo(person1);
		assertThat(persons.get(1)).isEqualTo(person2);
		assertThat(persons.get(2)).isEqualTo(person3);
	}
}
