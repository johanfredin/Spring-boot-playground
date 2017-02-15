package com.pylonmusic.playground.repository;

import java.util.List;

import com.pylonmusic.playground.domain.Person;

public interface PersonRepository extends BaseRepository<Person> {

	List<Person> findByLastName(String lastName);
	
}
