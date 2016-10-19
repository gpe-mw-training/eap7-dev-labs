package org.jboss.gpse.eap6ws.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer")
public class Customer {
	

	private int id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String zip;
	private String state;
	private String country;
	private String mobileNumber;
	private String email;
	
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name="first-name")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@XmlElement(name="last-name")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlElement(name="street")
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@XmlElement(name="city")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@XmlElement(name="zip")
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	@XmlElement(name="state")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@XmlElement(name="country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@XmlElement(name="mobile-number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@XmlElement(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer  [\nid=" + id + "\n firstName=" + firstName
				+ "\n lastName=" + lastName + "\n street=" + street + "\n city="
				+ city + "\n zip=" + zip + "\n state=" + state + "\n country="
				+ country + "\n mobileNumber=" + mobileNumber + "\n email="
				+ email + "\n]";
	}
	
	public void copyValues(Customer c) {
		this.firstName = c.getFirstName();
		this.lastName = c.getLastName();
		this.city = c.getCity();
		this.country = c.getCountry();
		this.email = c.getEmail();
		this.mobileNumber = c.getMobileNumber();
		this.state = c.getState();
		this.street = c.getStreet();
		this.zip = c.getZip();
	}
	
}
