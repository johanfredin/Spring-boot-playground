package com.pylonmusic.playground.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Document
public class Address extends AbstractEntity {
	
	private static final long serialVersionUID = 2582703777627336786L;

	@NotBlank
	private String street;
	
	@NotBlank
	private String city;
	
	private String zipCode;

	private String stateOrRegion;
	
	private String country;
	
	@JsonBackReference
	private Person person;
	
	/**
	 * Construct a new empty {@link Address} instance 
	 */
	public Address() {
		this("", "", "", "", "");
	}
	
	/**
	 * Construct a new {@link Address} instance
	 * @param street the street of the {@link Address}
	 * @param city the city of the {@link Address}
	 */
	public Address(String street, String city) {
		this(street, city, "", "", "");
	}
	
	/**
	 * Construct a new {@link Address} instance
	 * @param street the street of the {@link Address}
	 * @param city the city of the {@link Address}
	 * @param zipCode the the zipcode of the {@link Address}
	 * @param stateOrRegion the state/region of the {@link Address}
	 */
	public Address(String street, String city, String zipCode, String stateOrRegion) {
		this(street, city, zipCode, stateOrRegion, "");
	}
	
	/**
	 * Construct a new {@link Address} instance
	 * @param street the street of the {@link Address}
	 * @param city the city of the {@link Address}
	 * @param zipCode the the zipcode of the {@link Address}
	 * @param stateOrRegion the state/region of the {@link Address}
	 * @param country the country where the {@link Address} is
	 */
	public Address(String street, String city, String zipCode, String stateOrRegion, String country) {
		setStreet(street);
		setCity(city);
		setZipCode(zipCode);
		setStateOrRegion(stateOrRegion);
		setCountry(country);
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getStateOrRegion() {
		return stateOrRegion;
	}

	public void setStateOrRegion(String stateOrRegion) {
		this.stateOrRegion = stateOrRegion;
	}

	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Person getPerson() {
		return person;
	}
	
	@Override
	public String toString() {
		return new StringBuilder().
			append(super.toString()).
			append("Address Id=").append(this.id).append(NEW_LINE).
			append("Street=").append(this.street).append(NEW_LINE).
			append("Zip Code=").append(this.zipCode).append(NEW_LINE).
			append("State/Region=").append(this.stateOrRegion).append(NEW_LINE).
			append("Country=").append(this.country).append(NEW_LINE).
		toString();
	}
	
	@Override
	public void setRelations() {
		if(getPerson() != null) {
			getPerson().setAddress(this);
		}
	}

	@Override
	public void copyDataFromEntity(AbstractEntity populatedEntity) {
		Address populatedAddres = (Address) populatedEntity;
		setId(populatedAddres.getId());
		setCity(populatedAddres.getCity());
		setCountry(populatedAddres.getCountry());
		setStateOrRegion(populatedAddres.getStateOrRegion());
		setStreet(populatedAddres.getStreet());
		setZipCode(populatedAddres.getZipCode());
		super.copyDataFromEntity(populatedAddres);
	}

	@Override
	public void copyReferencesFromEntity(AbstractEntity populatedEntity) {
		Address populatedAddres = (Address) populatedEntity;
		setPerson(populatedAddres.getPerson());
	}


}
