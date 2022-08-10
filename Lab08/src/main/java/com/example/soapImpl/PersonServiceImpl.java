package com.example.soapImpl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebService;

import com.example.daoImpl.PersonDao;
import com.example.models.Person;
import com.example.soap.PersonService;

@WebService(endpointInterface = "com.example.soap.PersonService")
public class PersonServiceImpl implements PersonService{
	
	private PersonDao personDao = new PersonDao();
	
	@PostConstruct
	public void init() {
		personDao = new PersonDao();
	}

	@Override
	public List<Person> getAllPersons() {
		return personDao.getAll();
	}

	@Override
	public int createNewPerson(Person person) {
		Person personToInsert = new Person( person.getFirstName(), person.getSecondName() );
		personDao.add(personToInsert);
		return 0;
	}

	@Override
	public int deletePerson(long id) {
		return personDao.delete(id);
	}

	@Override
	public int updatePerson(long id, Person person) {
		Person p = personDao.get(id);
		if(!person.getFirstName().equals("") && !person.getSecondName().equals("")) {
			p.setFirstName(person.getFirstName());
			p.setSecondName(person.getSecondName());
		}
		return personDao.update(p);
	}


}
