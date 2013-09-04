package com.remmelt.example.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remmelt.example.model.Person;
import com.remmelt.example.repository.PersonRepository;
import com.remmelt.example.service.EmailService;
import com.remmelt.example.service.PersonService;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private EmailService emailService;

	@Override
	public Person getPersonBy(int id) {
		log.info("{}.getPersonBy({})", "PersonServiceImpl", id);
		return personRepository.getPersonBy(id);
	}

	@Override
	public void savePerson(Person person) {
		log.info("{}.savePerson({})", "PersonServiceImpl", person);
		personRepository.savePerson(person);
	}

	@Override
	public void sendEmailToPersonBy(int id, String subject, String message) {
		log.info("{}.sendEmailToPerson({}, {}, {})", "PersonServiceImpl", id, subject, message);
		Person person = personRepository.getPersonBy(id);
		emailService.sendMail(person.getEmailAddress(), subject, message);
	}
}
