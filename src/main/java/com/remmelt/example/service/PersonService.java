package com.remmelt.example.service;

import java.util.List;

import com.remmelt.example.model.Person;

public interface PersonService {
	Person getPersonBy(int id);

	void savePerson(Person person);

	void sendEmailToPersonBy(int id, String subject, String message);

	List<Person> getPersonsBy(int... id);
}
