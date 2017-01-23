package com.pylonmusic.playground.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

@Component("personService")
@Transactional
public class PersonServiceImpl implements PersonService {

//	private PersonRepository personRepository;
//	
//	@Override
//	public List<Person> getAllPersons() {
//		return this.personRepository.findAll();
//	}
//
//	@Override
//	public List<Person> getAllPersonsWithLastNameLike(String firstName) {
//		return this.personRepository.findByLastName(firstName);
//	}
//
//
//	@Override
//	public List<String> getExistingEmails(Person person) {
//		return null;
//	}
//
//	@Override
//	public List<String> getExistingPhoneNrs(Person person) {
//		return null;
//	}
//
//	@Override
//	public boolean isUniqueEmail(Person person) {
//		return false;
//	}
//
//	@Override
//	public boolean isUniquePhoneNr(Person person) {
//		return false;
//	}
//
//	@Override
//	public Person getPersonWithChildren(long id) {
//		return this.personRepository.getPersonWithChildren(id);
//	}

}
