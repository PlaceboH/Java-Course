package com.example.models;

import java.time.LocalDate;

public class Payment {
	
	private long paymentId;
	private LocalDate paymentDate;
	private float paymentAmount;
	private String instalmentNumber;
	private Event event;
	private Person person;
	
	public Payment() {} 
	
	public Payment(long paymentId, LocalDate paymentDate, float paymentAmount, String instalmentNumber, Event event, Person person) {
		super();
		this.paymentId = paymentId;
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.instalmentNumber = instalmentNumber;
		this.event = event;
		this.person = person;
	}

	public Payment(LocalDate paymentDate, float paymentAmount, String instalmentNumber, Event event, Person person) {
		super();
		this.paymentDate = paymentDate;
		this.paymentAmount = paymentAmount;
		this.instalmentNumber = instalmentNumber;
		this.event = event;
		this.person = person;
	}

	public Person getPerson(){
		return person;
	}
	public void setPerson(Person person){
		this.person = person;
	}
	public Event getEvent(){
		return event;
	}
	public void setEvent(Event event){
		this.event = event;
	}
	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public float getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getInstalmentNumber() {
		return instalmentNumber;
	}

	public void setInstalmentNumber(String instalmentNumber) {
		this.instalmentNumber = instalmentNumber;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentDate=" + paymentDate + ", paymentAmount=" + paymentAmount
				+ ", instalmentNumber=" + instalmentNumber + "event= " + event.toString() + " person= " + person.toString() + " ]";
	}
	
}
