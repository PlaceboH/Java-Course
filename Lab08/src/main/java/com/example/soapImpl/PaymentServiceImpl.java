package com.example.soapImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import com.example.daoImpl.EventDao;
import com.example.daoImpl.PaymentDao;
import com.example.daoImpl.PersonDao;
import com.example.models.Payment;
import com.example.soap.PaymentService;

@WebService(endpointInterface = "com.example.soap.PaymentService")
public class PaymentServiceImpl implements PaymentService{

	private PaymentDao paymentDao;
	private EventDao eventDao;
    private PersonDao personDao;

	@PostConstruct
	public void init() {
		paymentDao = new PaymentDao();
		eventDao = new EventDao();
        personDao = new PersonDao();
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentDao.getAll();
	}

	@Override
	public int createNewPayment(Payment payment) {
		Payment paymentToInsert = new Payment( payment.getPaymentDate(), payment.getPaymentAmount(), payment.getInstalmentNumber(),
				eventDao.get(payment.getEventId()), personDao.get(payment.getPersonId()) );
		paymentDao.add(paymentToInsert);
        return 0;
	}

	@Override
	public int deletePayment(long id) {
		return paymentDao.delete(id);
	}

	@Override
	public int updatePayment(long id, Payment payment) {
        payment.setPaymentId( paymentDao.get(id).getPaymentId());
		return paymentDao.update(payment);
	}

}
