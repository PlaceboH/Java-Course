package com.example.soap;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.example.models.Payment;

@WebService
public interface PaymentService {
	List<Payment> getAllPayments();
    int createNewPayment(Payment payment);
    int deletePayment(@WebParam(name="id") @XmlElement(required=true) long id);
    int updatePayment(@WebParam(name="id") @XmlElement(required=true) long id, Payment payment);
}
