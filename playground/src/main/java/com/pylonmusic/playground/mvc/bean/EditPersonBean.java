package com.pylonmusic.playground.mvc.bean;


import javax.validation.Valid;

import com.pylonmusic.playground.domain.Address;
import com.pylonmusic.playground.domain.Person;

/**
 * Bean class for editing a {@link Person}
 * @author johan
 *
 */
public class EditPersonBean {
	
	@Valid
	private Person person;
	
	@Valid
	private Address address;
	
	public EditPersonBean() {}
	
	public EditPersonBean(@Valid Person person, @Valid Address address) {
		setPerson(person);
		setAddress(address);
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	
	
}
