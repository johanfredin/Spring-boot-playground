package com.pylonmusic.playground.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

public class Person extends AbstractEntity {
	
	private static final long serialVersionUID = 6757754030293161155L;

	@NotBlank
	@Email
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String phoneNr;
	
	@JsonManagedReference
	private Address address;
	
	/**
	 * Default constructor
	 */
	public Person() {}
	
	/**
	 * Create a new {@link Person} instance passing in email and role
	 * @param email the email of the {@link Person}
	 * @param phoneNr the phone nr of the {@link Person}
	 */
	public Person(String email) {
		this("", "", email, "");
	}
	
	/**
	 * Create a new {@link Person} instance passing in first and last name
	 * @param firstName the first name of the {@link Person}
	 * @param lastName the last name of the {@link Person}
	 */
	public Person(String firstName, String lastName) {
		this(firstName, lastName, "", "");
	}
	
	/**
	 * Create a new {@link Person} instance passing in first name, last name and email
	 * @param firstName the first name of the {@link Person}
	 * @param lastName the last name of the {@link Person}
	 * @param email the email of the {@link Person}
	 */
	public Person(String firstName, String lastName, String email) {
		this(firstName, lastName, email, "");
	}

	/**
	 * Create a new {@link Person} instance passing in all fields of this class
	 * @param firstName the first name of the {@link Person}
	 * @param lastName the last name of the {@link Person}
	 * @param email the email of the {@link Person}
	 * @param phoneNr the phone nr of the {@link Person}
	 */
	public Person(String firstName, String lastName, String email, String phoneNr) {
		this(firstName, lastName, email, phoneNr, null);
	}
	
	
	/**
	 * Create a new {@link Person} instance passing in all fields of this class
	 * @param firstName the first name of the {@link Person}
	 * @param lastName the last name of the {@link Person}
	 * @param email the email of the {@link Person}
	 * @param address the {@link Address} of this {@link Person}
	 * 
	 */
	public Person(String firstName, String lastName, String email, String phoneNr, Address address) {
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
		setPhoneNr(phoneNr);
		setAddress(address);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
			.append(super.toString())
			.append("First name=").append(this.firstName).append(NEW_LINE)
			.append("Last name=").append(this.lastName).append(NEW_LINE)
			.append("Email=").append(this.email).append(NEW_LINE)
			.append("Phone=").append(this.phoneNr).append(NEW_LINE)
		.toString();
	}
	
	@Override
	public void setRelations() {
		if(getAddress() != null) {
			getAddress().setPerson(this);
		}
	}
	
	@Override
	public void copyDataFromEntity(AbstractEntity populatedEntity) {
		Person populaterPerson = (Person) populatedEntity;
		setId(populaterPerson.getId());
		setEmail(populaterPerson.getEmail());
		setFirstName(populaterPerson.getFirstName());
		setLastName(populaterPerson.getLastName());
		setPhoneNr(populaterPerson.getPhoneNr());
		super.copyDataFromEntity(populaterPerson);
	}

	@Override
	public void copyReferencesFromEntity(AbstractEntity populatedEntity) {
		Person populaterPerson = (Person) populatedEntity;
		setAddress(populaterPerson.getAddress());
		
	}

}
