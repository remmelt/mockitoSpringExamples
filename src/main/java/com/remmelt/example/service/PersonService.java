package com.remmelt.example.service;

import com.remmelt.example.model.Person;

public interface PersonService {
	Person getPersonBy(Long id);

	void savePerson(Person person);

	void sendEmailToPerson(Person person, String subject, String message);
}
