package com.pylonmusic.playground.mvc.bean;

import com.pylonmusic.playground.domain.Address;

public class EditAddressBean {

	private Address address;
	
	public EditAddressBean() {}
	
	public EditAddressBean(Address address) {
		setAddress(address);
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	
}
