package com.example.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.example.models.Event;

@WebService
public interface EventService {
    @WebMethod
    List<Event> getAllEvents();
    @WebMethod
    int createNewEvent(Event event);
    @WebMethod
    int deleteEvent(@WebParam(name="id") @XmlElement(required=true) long id);
    @WebMethod
    int updateEvent(@WebParam(name="id") @XmlElement(required=true) long id, Event event);

}

