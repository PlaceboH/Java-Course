package com.example.models;

import java.time.LocalDate;

public class Instalment {
	
	private long instalmentId;
	private String instalmentNumber;
	private float payment;
	private Event event;
	private LocalDate paymentDate;

	public Instalment() {}
	
	public Instalment(long instalmentId, String instalmentNumber, float payment, LocalDate date, Event event) {
		super();
		this.instalmentId = instalmentId;
		this.instalmentNumber = instalmentNumber;
		this.payment = payment;
		this.event = event;
		this.paymentDate = date;
	}

	public Instalment(String instalmentNumber, float payment, LocalDate date, Event event) {
		super();
		this.instalmentNumber = instalmentNumber;
		this.payment = payment;
		this.event = event;
		this.paymentDate = date;
	}


	public long getInstalmentId() {
		return instalmentId;
	}

	public void setInstalmentId(long instalmentId) {
		this.instalmentId = instalmentId;
	}

	public String getInstalmentNumber() {
		return instalmentNumber;
	}

	public void setInstalmentNumber(String instalmentNumber) {
		this.instalmentNumber = instalmentNumber;
	}

	public float getPayment() {
		return payment;
	}

	public void setPayment(float payment) {
		this.payment = payment;
	}

	public LocalDate getPaymentDate(){
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate){
		this.paymentDate = paymentDate;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}


	@Override
	public String toString() {
		return "Instalment [instalmentId=" + instalmentId + ", instalmentNumber=" + instalmentNumber + ", payment=" + payment
				+ ", =" + ", event=" + event.toString()
				+ " ]";
	}
	
	

}
