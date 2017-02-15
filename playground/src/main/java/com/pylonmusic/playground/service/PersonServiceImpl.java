package com.pylonmusic.playground.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pylonmusic.playground.repository.PersonRepository;

@Component("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public PersonRepository getRepository() {
		return personRepository;
	}

	@Override
	public void setRepositry(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}


}
