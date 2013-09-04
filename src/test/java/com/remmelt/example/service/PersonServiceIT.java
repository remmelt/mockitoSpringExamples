package com.remmelt.example.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.remmelt.example.configuration.ApplicationConfig;
import com.remmelt.example.model.Person;
import com.remmelt.example.service.impl.PersonServiceImpl;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class PersonServiceIT extends AbstractJUnit4SpringContextTests {
	@Autowired
	private PersonService personService;

	@Test
	public void testWiring() {
		assertThat(personService).isNotNull();
		assertThat(personService instanceof PersonServiceImpl).isTrue();
	}

	@Test
	public void testGetPersonBy() throws Exception {
		/**
		 * This test goes to the "real" database, so unless we have something like dbUnit, the state is unknown.
		 * This leads to brittle tests in complex applications.
		 */
		int id = 1;

		Person person = personService.getPersonBy(id);

		assertThat(person.getId()).isEqualTo(1);
		assertThat(person.getEmailAddress()).contains("remmelt");
	}

	@Test
	public void testSendEmailToPersonBy() throws Exception {
		/**
		 * How do we do this? There are multiple options:
		 * - external logging smtp server (how to automatically check output?)
		 * - TestApplicationConfig that wires a different bean instead of the regular EmailService
		 */

		personService.sendEmailToPersonBy(1, "Hi!", "This is a test message.");
	}
}
