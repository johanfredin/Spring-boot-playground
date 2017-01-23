package com.pylonmusic.playground.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.pylonmusic.playground.domain.Person;

public interface PersonRepository extends BaseRepository<Person> {

	List<Person> findByLastName(String lastName);
	
	@Query("select e from Person e left join e.address where e.id=?1")
	Person getPersonWithChildren(long id);
	
	@Query("select e from Person e where e.id !=?1")
	List<String> getExistingEmails(long id);
	
//	@Query
//	List<String> getExistingPhoneNrs(long id);
	
}
