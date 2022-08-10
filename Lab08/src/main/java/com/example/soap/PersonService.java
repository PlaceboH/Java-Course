package com.example.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.example.models.Person;

@WebService
public interface PersonService {
    @WebMethod
    List<Person> getAllPersons();
    @WebMethod
    int createNewPerson(Person person);
    @WebMethod
    int deletePerson(@WebParam(name="id") @XmlElement(required=true) long id);
    @WebMethod
    int updatePerson(@WebParam(name="id") @XmlElement(required=true) long id, Person person);
}
