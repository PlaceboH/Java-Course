package com.example.models;


import java.time.LocalDate;

public class Event {
	
	private long eventId;
	private String location;
	private String name;
	private LocalDate date;
	
	public Event() {}

	public Event(long eventId, String location, String name, LocalDate date) {
		super();
		this.eventId = eventId;
		this.location = location;
		this.name = name;
		this.date = date;
	}

	public Event(String location, String name, LocalDate date) {
		super();
		this.location = location;
		this.name = name;
		this.date = date;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getLocalDate() {
		return date;
	}

	public void setLocalDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Event [eventId= " + eventId + ", location= " + location + ", name= " + name + ", date= "
				+ date + "]";
	}
	
}
