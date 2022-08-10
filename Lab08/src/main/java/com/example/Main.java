package com.example;

import com.example.daoImpl.PersonDao;
import com.example.soapImpl.PersonServiceImpl;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class Main {

    public static void main(String args[]) throws MalformedURLException {

        final String path = "http://localhost:8080/Lab08CXF/PersonService/getAllPersons";
        final URL url = URI.create(path).toURL();

        PersonDao personDao = new PersonDao();
        PersonServiceImpl personService = new PersonServiceImpl();
        personService.getAllPersons().stream().forEach(x -> System.out.println(x.toString()));
        personDao.getAll().stream().forEach(x -> System.out.println(x.toString()));
    }

}
