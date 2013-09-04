package com.external.database;

import com.remmelt.example.model.Person;

/**
 * This is an external dependency, included via maven. No way to change anything in here.
 */
public interface DatabaseAccess {
	void savePerson(Person person);

	Person getPersonBy(int id);
}
