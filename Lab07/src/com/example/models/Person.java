package com.example.models;

public class Person {
	
	private long personId;
	private String firstName;
	private String secondName;
	
	public Person() {}
	
	public Person(long id, String firstName, String secondName) {
		super();
		this.personId = id;
		this.firstName = firstName;
		this.secondName = secondName;
	}
	
	public Person(String firstName, String secondName) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public long getPersonId() {
		return personId;
	}

	public void setPersonId(long id) {
		this.personId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Override
	public String toString() {
		return "Client [personId=" + personId + ", firstName=" + firstName + ", secondName=" + secondName + "]";
	}

}
