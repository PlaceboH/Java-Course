package com.example;

import com.example.daoImpl.PersonDao;
import com.example.models.Person;

import java.util.List;
import java.util.Scanner;

public class PersonView {

    private PersonDao personDao;


    PersonView(){
        personDao = new PersonDao();
    }

    public PersonDao PersonDao() {
        return personDao;
    }

    public void startView(){
        Person person = new Person();
        boolean exit = false;
        do
        {
            System.out.println("1.Add Person");
            System.out.println("2.Change Person");
            System.out.println("3.Get Person by id");
            System.out.println("4.Get all");
            System.out.println("5.Delete person by id");
            System.out.println("6.exit");

            Scanner sd=new Scanner(System.in);
            System.out.println("enter your choice");
            int num=sd.nextInt();
            switch(num)
            {
                case 1:
                    System.out.println("Podaj dane osoby(fist_name, second_name");
                    sd.nextLine();
                    person.setFirstName(sd.nextLine());
                    person.setSecondName(sd.nextLine());

                    this.personDao.add(person);
                    System.out.println("\n");
                    break;

                case 2:
                    Person updatePerson = new Person();
                    System.out.println("Podaj dane osoby(id, location, name, date[dd. MMM. yyyy])");
                    person.setPersonId(sd.nextInt());
                    person.setFirstName(sd.nextLine());
                    person.setSecondName(sd.nextLine());
                    this.personDao.add(updatePerson);
                    System.out.println("\n");
                    break;

                case 3:
                    System.out.println("Podaj id osoby");
                    System.out.println(this.personDao.get(sd.nextInt()).toString());
                    System.out.println("\n");
                    break;

                case 4:
                    System.out.println("Wydarzenia: ");
                    List<Person> listPerson = this.personDao.getAll();
                    listPerson.forEach(e -> System.out.println(e.toString()));
                    System.out.println("\n");
                    break;
                case 5:
                    System.out.println("Podaj id osoby");
                    this.personDao.delete(sd.nextInt());
                    System.out.println("\n");
                    break;

                case 6:
                    exit=true;
                    break;
            }
        }while(!exit);

    }

}
