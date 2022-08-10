
package com.example.soapImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import com.example.daoImpl.EventDao;
import com.example.models.Event;
import com.example.soap.EventService;

@WebService(endpointInterface = "com.example.soap.EventService")
public class EventServiceImpl implements EventService {

	private EventDao eventDao;

	@PostConstruct
	public void init() {
		eventDao = new EventDao();
	}

	@Override
	public List<Event> getAllEvents() {
		return eventDao.getAll();
	}

	@Override
	public int createNewEvent(Event event) {
		Event eventToInsert = new Event(event.getLocation(), event.getName(), event.getLocalDate());
        eventDao.add(eventToInsert);
		return 0;
	}

	@Override
	public int deleteEvent(long id) {
		return eventDao.delete(id);
	}

	@Override
	public int updateEvent(long id, Event event) {
		Event e = eventDao.get(id);
        e.setLocation(event.getLocation());
        e.setName(event.getName());
        e.setLocalDate(event.getLocalDate());
		return eventDao.update(e);
	}

}

