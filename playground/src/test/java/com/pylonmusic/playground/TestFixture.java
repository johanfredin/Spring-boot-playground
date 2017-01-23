package com.pylonmusic.playground;



import java.util.Arrays;
import java.util.List;

import com.pylonmusic.playground.domain.Address;
import com.pylonmusic.playground.domain.Person;


public class TestFixture {
	
	// -----------------------------------------------------------------------------------------------------------------------
	// ------------------------------ PERSON ---------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * @return a new {@link Person} with all fields filled in with dummy values
	 */
	public static Person getValidPerson() {
		return getValidPerson("Jon", "Doe", "jon.doe@dobi.com", "1234 531 341", getValidAddress());
	}
	
	public static Person getValidPerson2() {
		return getValidPerson("Jane", "Doe", "jane.doe@dobi.com", "1234 666 341", getValidAddress("hagåkers", "mölndal", "43141", "västra götaland"));
	}
	
	public static Person getValidPerson(String firstName, String lastName, String email, String phoneNr, Address address) {
		return new Person(firstName, lastName, email, phoneNr, address);
	}
	
	public static List<Person> getValidPersons() {
		return Arrays.asList(
			new Person[]{
				getValidPerson(), 
				getValidPerson2()
			}
		);
	}
	
	// -----------------------------------------------------------------------------------------------------------------------
	// ------------------------------ ADDRESS --------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------------------------------------------
	
	/**
	 * @return street="Street", city="City", zipCode="Zippie", stateOrRegion="Region"
	 */
	public static Address getValidAddress() {
		return getValidAddress("Street", "City", "Zippie", "Region");
	}
	
	public static Address getValidAddress(String street, String city, String zipCode, String region) {
		return new Address(street, city, zipCode, region, "");
	}
	
	
	
	
}
