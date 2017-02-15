package com.pylonmusic.playground.service;

import java.util.List;

import com.pylonmusic.playground.domain.Person;
import com.pylonmusic.playground.repository.PersonRepository;

public interface PersonService {
	
	default Person save(Person p) {
		return getRepository().save(p);
	}
	
	default void delete(Person p) {
		getRepository().delete(p);
	}
	
	default List<Person> findAll() {
		return getRepository().findAll();
	}
	
	default void deleteAll() {
		getRepository().deleteAll();
	}
	
	PersonRepository getRepository();
	
	void setRepositry(PersonRepository personRepository);
	
}
