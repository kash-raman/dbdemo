package org.kash.tutorial.service;

import org.kash.tutorial.model.Person;

import java.util.List;


public interface PersonDao {
	List<Person> getAllPerson();
	Person getPerson(int id);
	int getCount();

}
