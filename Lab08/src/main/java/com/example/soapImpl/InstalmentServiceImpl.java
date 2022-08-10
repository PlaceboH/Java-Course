package com.example.soapImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import com.example.daoImpl.EventDao;
import com.example.daoImpl.InstalmentDao;
import com.example.models.Instalment;
import com.example.soap.InstalmentService;
@WebService(endpointInterface = "com.example.soap.InstalmentService")
public class InstalmentServiceImpl implements InstalmentService {

	private InstalmentDao instalmentDao;
	private EventDao event;

	@PostConstruct
	public void init() {
		instalmentDao = new InstalmentDao();
		event = new EventDao();
	}

	@Override
	public List<Instalment> getAllInstalments() {
		return instalmentDao.getAll();
	}
//    String instalmentNumber, float payment, LocalDate date, EventDao event
	@Override
	public int createNewInstalment(Instalment instalment) {
		Instalment instalmentToInsert = new Instalment(instalment.getInstalmentNumber(), instalment.getPayment(),
				instalment.getPaymentDate(), event.get(instalment.getEventId()));
		instalmentDao.add(instalmentToInsert);
        return 0;
	}

	@Override
	public int deleteInstalment(long id) {
		return instalmentDao.delete(id);
	}

	@Override
	public int updateInstalment(long id, Instalment instalment) {
		instalment.setInstalmentId(instalmentDao.get(id).getEventId());
		return instalmentDao.update(instalment);
	}


}
