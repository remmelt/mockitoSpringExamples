package com.remmelt.example.service;

import com.remmelt.example.model.Person;

public interface PersonService {
	Person getPersonBy(int id);

	void savePerson(Person person);

	void sendEmailToPersonBy(int id, String subject, String message);
}
